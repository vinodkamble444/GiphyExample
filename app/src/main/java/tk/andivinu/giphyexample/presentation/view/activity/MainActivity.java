package tk.andivinu.giphyexample.presentation.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import tk.andivinu.giphyexample.R;
import tk.andivinu.giphyexample.databinding.ActivityMainBinding;
import tk.andivinu.giphyexample.presentation.view.adapter.RecyclerViewAdapter;
import tk.andivinu.giphyexample.presentation.view.utils.ItemOffsetDecoration;
import tk.andivinu.giphyexample.presentation.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel viewModel;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager layoutManager;
    private boolean loading = true;

    private RecyclerView.OnScrollListener endlessScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0 && loading) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    loading = false;
                    viewModel.loadNewGifPackage();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        initSearch(searchView);

        return true;
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainActivityBinding.setMainViewModel(viewModel);
        mainActivityBinding.executePendingBindings();
        subscribeDataStreams(viewModel);
    }

    private void subscribeDataStreams(MainViewModel viewModel) {
        viewModel.getGifUrlsList().observe(this, urls -> adapter.setUrls(urls));
        viewModel.isLoading().subscribe(isLoadingEnabled -> this.loading = isLoadingEnabled);
    }


    private void initViews() {
        setSupportActionBar(mainActivityBinding.toolbar);

        layoutManager = new GridLayoutManager(this, 2);
        adapter = new RecyclerViewAdapter(this);

        RecyclerView recyclerView = mainActivityBinding.rvGiphy;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.gif_adapter_item_offset));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(endlessScrollListener);
        recyclerView.setAdapter(adapter);

    }

    private void initSearch(SearchView searchView) {
        RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(charSequence -> !TextUtils.isEmpty(charSequence))
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNewSearchQueryEntered);
    }

    private void onNewSearchQueryEntered(String searchQuery) {
        viewModel.setNewSearchQuery(searchQuery);
        adapter.clear();
    }

}

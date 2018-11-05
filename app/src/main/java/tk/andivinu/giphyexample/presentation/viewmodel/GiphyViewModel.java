package tk.andivinu.giphyexample.presentation.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import tk.andivinu.giphyexample.R;


public class GiphyViewModel extends ViewModel {

    private String url;

    public GiphyViewModel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().centerCrop().placeholder(R.color.gray))
                .into(imageView);
    }

}

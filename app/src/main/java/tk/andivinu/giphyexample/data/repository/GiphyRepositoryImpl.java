package tk.andivinu.giphyexample.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tk.andivinu.giphyexample.GiphyApplication;
import tk.andivinu.giphyexample.data.mapper.GiphyMapper;
import tk.andivinu.giphyexample.data.network.GiphyAPI;
import tk.andivinu.giphyexample.domain.repository.GiphyRepository;

public class GiphyRepositoryImpl implements GiphyRepository {

    private final GiphyMapper giphyMapper;

    private final GiphyAPI giphyAPI;


    @Inject GiphyRepositoryImpl(GiphyMapper giphyMapper,GiphyAPI giphyAPI){
        this.giphyMapper = giphyMapper;
        this.giphyAPI=giphyAPI;
    }

    @Override public Observable<List<String>> searchGiphies(String query, int limit, int offset) {

        return  giphyAPI
                .searchGiphy(query, limit, offset)
                .map(giphyMapper)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}

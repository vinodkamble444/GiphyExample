package tk.andivinu.giphyexample.presentation.di.modules;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.andivinu.giphyexample.BuildConfig;
import tk.andivinu.giphyexample.data.network.ApiKeyInterceptor;
import tk.andivinu.giphyexample.data.network.GiphyAPI;
import tk.andivinu.giphyexample.data.repository.GiphyRepositoryImpl;
import tk.andivinu.giphyexample.domain.repository.GiphyRepository;

@Module
public class GiphyModule {

    @Provides @Singleton public GiphyRepository providesGiphyRepository(GiphyRepositoryImpl giphyRepository){
        return giphyRepository;
    }

    /*@Provides @Singleton public OkHttpClient providesOhKttpClient(){
         return new OkHttpClient
                .Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }*/

    @Provides @Singleton public GiphyAPI providesGiphyApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(new OkHttpClient
                .Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GiphyAPI.class);

    }
}

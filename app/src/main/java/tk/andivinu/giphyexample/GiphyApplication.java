package tk.andivinu.giphyexample;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.andivinu.giphyexample.data.network.ApiKeyInterceptor;
import tk.andivinu.giphyexample.data.network.GiphyAPI;
import tk.andivinu.giphyexample.presentation.di.components.ApplicationComponent;
import tk.andivinu.giphyexample.presentation.di.components.DaggerApplicationComponent;
import tk.andivinu.giphyexample.presentation.di.modules.GiphyModule;


public class GiphyApplication extends Application {

    //private static GiphyAPI sGiphyAPI;
    private static ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
       // initRetrofit();
        initDagger();
    }

    private void initDagger() {
        sAppComponent = DaggerApplicationComponent.builder()
                .giphyModule(new GiphyModule())
                .build();
    }

   /* private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        sGiphyAPI = retrofit.create(GiphyAPI.class);
    }*/

   /* public static GiphyAPI getApi() {
        return sGiphyAPI;
    }
*/
    @NonNull
    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    /*private OkHttpClient getClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }*/
}

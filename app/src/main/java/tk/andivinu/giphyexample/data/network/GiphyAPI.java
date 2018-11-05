package tk.andivinu.giphyexample.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tk.andivinu.giphyexample.data.model.GiphyResponse;

public interface GiphyAPI {

    @GET("search")
    Observable<GiphyResponse> searchGiphy(
            @Query("q") String query,
            @Query("limit") int limit,
            @Query("offset") int offset
    );
}

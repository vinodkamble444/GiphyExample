package tk.andivinu.giphyexample.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import tk.andivinu.giphyexample.data.model.SingleGiphy;

/**
 * Interface that represents a Repository for getting {@link tk.andivinu.giphyexample.data.model.GiphyResponse} related data.
 */
public interface GiphyRepository {

    /**
     *
     * Get an {@link Observable} which will emit a list of {@link SingleGiphy}'s urls.
     * @param query searched query
     * @param limit response items limit
     * @param offset page to load
     *
     */
    Observable<List<String>> searchGiphies(String query, int limit, int offset);

}

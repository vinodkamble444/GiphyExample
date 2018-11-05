package tk.andivinu.giphyexample.data.mapper;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import tk.andivinu.giphyexample.data.model.GiphyResponse;
import tk.andivinu.giphyexample.data.model.SingleGiphy;


public class GiphyMapper implements Function<GiphyResponse, List<String>> {

    @Inject public GiphyMapper() {}

    @Override
    public List<String> apply(GiphyResponse giphyResponse) throws Exception {
        List<SingleGiphy> giphyList = giphyResponse.getGiphyList();
        List<String> urlsList = new ArrayList<>();

        for (SingleGiphy giphy : giphyList) {
            if (giphy.getImages() != null &&
                    giphy.getImages().getOriginalGiphy() != null &&
                    giphy.getImages().getOriginalGiphy().getUrl() != null) {

                urlsList.add(giphy
                        .getImages()
                        .getOriginalGiphy()
                        .getUrl()
                );
            }
        }

        return urlsList;
    }

}

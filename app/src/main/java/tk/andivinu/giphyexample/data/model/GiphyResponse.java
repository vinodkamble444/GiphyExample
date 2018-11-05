package tk.andivinu.giphyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GiphyResponse {

    @SerializedName("data") @Expose private List<SingleGiphy> giphyList;

    public List<SingleGiphy> getGiphyList() {
        return giphyList;
    }

    public void setGiphyList(List<SingleGiphy> giphyList) {
        this.giphyList = giphyList;
    }
}

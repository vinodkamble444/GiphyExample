package tk.andivinu.giphyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleGiphy {

    @SerializedName("images") @Expose private GiphyImages images;

    public GiphyImages getImages() {
        return images;
    }

    public void setImages(GiphyImages images) {
        this.images = images;
    }
}

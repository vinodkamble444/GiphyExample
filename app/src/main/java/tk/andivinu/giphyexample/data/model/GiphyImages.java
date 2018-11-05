package tk.andivinu.giphyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GiphyImages {

    @SerializedName("preview_gif") @Expose private PreviewGiphy originalGiphy;

    public PreviewGiphy getOriginalGiphy() {
        return originalGiphy;
    }

    public void setOriginalGiphy(PreviewGiphy originalGiphy) {
        this.originalGiphy = originalGiphy;
    }
}

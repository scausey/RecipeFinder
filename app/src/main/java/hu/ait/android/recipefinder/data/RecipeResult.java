package hu.ait.android.recipefinder.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeResult {
    // http://www.jsonschema2pojo.org

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("usedIngredientCount")
    @Expose
    private Integer usedIngredientCount;
    @SerializedName("missedIngredientCount")
    @Expose
    private Integer missedIngredientCount;
    @SerializedName("likes")
    @Expose
    private Integer likes;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The usedIngredientCount
     */
    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    /**
     * @param usedIngredientCount The usedIngredientCount
     */
    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    /**
     * @return The missedIngredientCount
     */
    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    /**
     * @param missedIngredientCount The missedIngredientCount
     */
    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    /**
     * @return The likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes The likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

}

package simplycodighub.masjid_e_ala.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example3 {


    @SerializedName("main_title")
    @Expose
    private String mainTitle;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("arabic")
    @Expose
    private String arabic;
    @SerializedName("english_arabic_style")
    @Expose
    private String englishArabicStyle;
    @SerializedName("english_meaning")
    @Expose
    private String englishMeaning;

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getEnglishArabicStyle() {
        return englishArabicStyle;
    }

    public void setEnglishArabicStyle(String englishArabicStyle) {
        this.englishArabicStyle = englishArabicStyle;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

}

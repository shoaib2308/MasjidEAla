package simplycodighub.masjid_e_ala.Util;

import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://simplycodinghub.com";
    //public static Retrofit retrofit;
    public static boolean currentStatus;

    public static retrofit2.Retrofit getRetrofit() {

        return new retrofit2.Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();
    }
}

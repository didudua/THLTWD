package due.cuoiky.thltwd.network;

import java.util.List;

import due.cuoiky.thltwd.model.Hotel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API_Hotel {

    // URL gốc của API
    String SERVER_URL = "http://10.0.2.2:3000/"; // Thay bằng địa chỉ IP đúng của API

    API_Hotel apiService = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API_Hotel.class);

    @GET("api/hotels")
    Call<List<Hotel>> getListHotel();

    @GET("api/hotels/{id}")
    Call<Hotel> getHotel(@Path("id") String hotelId);
}



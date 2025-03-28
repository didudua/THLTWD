package due.cuoiky.thltwd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.adapter.ListHotelAdapter;
import due.cuoiky.thltwd.databinding.FragmentHomeBinding;
import due.cuoiky.thltwd.model.Hotel;
import due.cuoiky.thltwd.network.API_Hotel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ListHotelAdapter hotelAdapter;

    private FloatingActionButton fabBook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvListHotels;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Gọi API để lấy danh sách khách sạn
        API_Hotel.apiService.getListHotel().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Hotel> hotelList = response.body();
                    // Khởi tạo adapter với callback xử lý sự kiện click
                    hotelAdapter = new ListHotelAdapter(hotelList, hotel -> {
                        // Khi người dùng nhấn vào một item, chuyển màn hình chi tiết
                        onViewHotel(hotel.getName(), hotel.getId());
                    });

                    recyclerView.setAdapter(hotelAdapter);
                } else {
                    Toast.makeText(getActivity(), "Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void onViewHotel(String hotelName, String hotelId) {
        NavController navController = NavHostFragment.findNavController(this);

        // Cách 1: Truyền Bundle thủ công
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelId", hotelId);
        navController.navigate(R.id.action_navigation_home_to_navigation_hotel_detail, bundle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
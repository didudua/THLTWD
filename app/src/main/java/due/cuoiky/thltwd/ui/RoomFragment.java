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

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.adapter.RoomAdapter;
import due.cuoiky.thltwd.databinding.FragmentHotelRoomBinding;
import due.cuoiky.thltwd.model.Hotel;
import due.cuoiky.thltwd.model.Room;
import due.cuoiky.thltwd.network.API_Hotel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomFragment extends Fragment {

    private FragmentHotelRoomBinding binding;
    private RecyclerView recyclerView;
    private String hotelId = "defaultHotelId";
    private String hotelName = "defaultHotelName";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHotelRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Nếu có dữ liệu truyền vào (ví dụ: từ Bundle) lay du lieu
        if (getArguments() != null) {
            hotelId = getArguments() != null ? getArguments().getString("hotelId") : "defaultId";
            hotelName = getArguments() != null ? getArguments().getString("hotelName") : "defaultId";
        }


        recyclerView = binding.rvListHotelRooms;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments() != null) {
            hotelId = getArguments() != null ? getArguments().getString("hotelId") : "defaultId";
        }

        API_Hotel.apiService.getHotel(hotelId).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Hotel hotel = response.body();

//                    RoomAdapter roomAdapter = new RoomAdapter(hotel.getRooms());
                    RoomAdapter roomAdapter = new RoomAdapter(hotel.getRooms(), new RoomAdapter.OnRoomItemClickListener() {
                        @Override public void onChooseRoom(Room room) {onViewInfor(hotelName, hotelId, room.getOriginalPrice(), room.getDiscountedPrice(), room.getTitle()); }
                    });
                    binding.rvListHotelRooms.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rvListHotelRooms.setAdapter(roomAdapter);
                }
            }
            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void onViewInfor(String hotelName, String hotelId, String price, String priceDiscount, String roomName) {
        NavController navController = NavHostFragment.findNavController(this);

        // Cách 1: Truyền Bundle thủ công
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelId", hotelId);
        bundle.putString("price", price);
        bundle.putString("priceDiscount", priceDiscount );
        bundle.putString("roomName", roomName );

        navController.navigate(R.id.action_navigation_listRoom_to_navigation_formInfor, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
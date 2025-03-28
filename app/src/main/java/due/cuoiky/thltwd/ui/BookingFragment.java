package due.cuoiky.thltwd.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import due.cuoiky.thltwd.adapter.BookingAdapter;
import due.cuoiky.thltwd.databinding.FragmentBookingBinding;
import due.cuoiky.thltwd.model.Booking;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding binding;
    private static final String BOOKING_PREFS = "BookingPrefs";
    private static final String KEY_BOOKING_LIST = "booking_list";
    public List<Booking> bookingList = new ArrayList<>();
    private BookingAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Load danh sách booking từ SharedPreferences
        loadBookingList();

        // Nếu có dữ liệu, ẩn thông báo "Chưa có booking"
        if (!bookingList.isEmpty()) {
            binding.textShow.setText("");
        }

        // Thiết lập RecyclerView với adapter
        adapter = new BookingAdapter(getContext(), bookingList);
        binding.rvListBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvListBooking.setAdapter(adapter);

        return root;
    }

    // Phương thức đọc danh sách booking từ SharedPreferences
    private void loadBookingList() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(BOOKING_PREFS, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_BOOKING_LIST, "[]"); // Mặc định là mảng JSON rỗng
        Gson gson = new Gson();
        Type type = new TypeToken<List<Booking>>(){}.getType();
        bookingList = gson.fromJson(json, type);
        if (bookingList == null) {
            bookingList = new ArrayList<>();
        }
    }

    public void addBooking(Booking newBooking) {
        bookingList.add(newBooking);
        adapter.notifyDataSetChanged();
        saveBookingList();
    }

    private void saveBookingList() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(BOOKING_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookingList);
        editor.putString(KEY_BOOKING_LIST, json);
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}

package due.cuoiky.thltwd.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.databinding.FragmentQrPaymentBinding;
import due.cuoiky.thltwd.model.Booking;

public class QRPaymentFragment extends Fragment {

    private FragmentQrPaymentBinding binding;
    private CountDownTimer countDownTimer;
    private Runnable paymentRunnable;
    private Handler handler;

    private static final String BOOKING_PREFS = "BookingPrefs";
    private static final String KEY_BOOKING_LIST = "booking_list";

    String BookingCode = "default";
    String priceDiscount = "default";
    String price = "default";
    String hotelName = "default";
    String phone = "default";
    String checkInDate = "default";
    String checkInTime = "default";
    String fullNameRoom = "default";
    String hotelId = "default";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentQrPaymentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Lấy dữ liệu từ Bundle (nếu có)
        if (getArguments() != null) {
            hotelId = getArguments().getString("hotelId", hotelId);
            hotelName = getArguments().getString("hotelName", hotelName);
            price = getArguments().getString("price", price);
            priceDiscount = getArguments().getString("priceDiscount", priceDiscount);
            checkInDate = getArguments().getString("checkInDate", checkInDate);
            checkInTime = getArguments().getString("checkingTime", checkInTime);
            fullNameRoom = getArguments().getString("fullNameRoom", fullNameRoom);
            BookingCode = getArguments().getString("BookingCode", BookingCode);
        }

        initView();
        return root;
    }

    private void initView() {
        // Gán giá trị cho các TextView
        binding.tvBookingCode.setText(BookingCode);
        binding.nameRoom.setText(fullNameRoom);

        binding.priceRoom.setText(price);
        // Gạch ngang giá cũ
        binding.priceRoom.setPaintFlags(binding.priceRoom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        binding.priceRoomDiscount.setText(priceDiscount);

        // Khởi tạo CountDownTimer đếm ngược 10 phút (600000 ms)
        countDownTimer = new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                binding.time.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                binding.time.setText("00:00");
                onViewHomeCancer();
            }
        }.start();

        // Handler và Runnable để tự động thanh toán sau 5 giây (demo)
        handler = new Handler(Looper.getMainLooper());
        paymentRunnable = new Runnable() {
            @Override
            public void run() {
                // Lưu booking vào SharedPreferences (dưới dạng JSON)
                saveBookingData();
                // Điều hướng sang màn hình thanh toán thành công
                onViewHomePayment();
            }
        };
        handler.postDelayed(paymentRunnable, 5000);

        // Sự kiện nút hủy
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(paymentRunnable);
                binding.time.setText("00:00");
                onViewHomeCancer();
            }
        });
    }

    private void onViewHomeCancer() {
        Toast.makeText(getContext(), "Đã Hủy Thanh Toán", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_navigation_QRPayment_to_navigation_home);
    }

    private void onViewHomePayment() {
        Toast.makeText(getContext(), "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_navigation_QRPayment_to_navigation_home);
    }

    /**
     * Lưu booking vào SharedPreferences dưới dạng JSON.
     */
    private void saveBookingData() {
        // 1) Đọc danh sách booking hiện có
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(BOOKING_PREFS, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_BOOKING_LIST, "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<List<Booking>>(){}.getType();
        List<Booking> bookingList = gson.fromJson(json, type);
        if (bookingList == null) {
            bookingList = new ArrayList<>();
        }

        // 2) Tạo booking mới
        Booking newBooking = new Booking(fullNameRoom, BookingCode, checkInDate, checkInTime, price);

        // 3) Thêm vào danh sách
        bookingList.add(newBooking);

        // 4) Ghi lại danh sách dưới dạng JSON
        String updatedJson = gson.toJson(bookingList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_BOOKING_LIST, updatedJson);

        editor.apply();
    }

    @Override
    public void onDestroyView() {
        // Hủy CountDownTimer nếu đang chạy
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        // Hủy runnable trong Handler nếu chưa chạy
        if (handler != null && paymentRunnable != null) {
            handler.removeCallbacks(paymentRunnable);
        }
        binding = null;
        super.onDestroyView();
    }
}

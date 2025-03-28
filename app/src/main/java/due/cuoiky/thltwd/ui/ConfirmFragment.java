package due.cuoiky.thltwd.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.databinding.FragmentConfirmInforBinding;

public class ConfirmFragment extends Fragment{

    private FragmentConfirmInforBinding binding;
    String roomName ="defaul";
    String priceDiscount ="defaul";
    String price ="defaul";
    String hotelName ="defaul";
    String phone ="defaul";
    String checkInDate ="defaul";
    String checkInTime ="defaul";
    String fullNameRoom ="defaul";
    String fullName ="defaul";
    String hotelId ="defaul";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentConfirmInforBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            hotelId = getArguments() != null ? getArguments().getString("hotelId") : "defaultId";
            hotelName = getArguments() != null ? getArguments().getString("hotelName") : "default";
            price = getArguments() != null ? getArguments().getString("price") : "default";
            priceDiscount = getArguments() != null ? getArguments().getString("priceDiscount") : "default";
            phone = getArguments() != null ? getArguments().getString("phone") : "default";
            checkInDate = getArguments() != null ? getArguments().getString("checkInDate") : "default";
            checkInTime = getArguments() != null ? getArguments().getString("checkingTime") : "default";
            fullNameRoom = getArguments() != null ? getArguments().getString("fullNameRoom") : "default";
            fullName = getArguments() != null ? getArguments().getString("fullName") : "default";

        }
        initView();

        return root;
    }

    private void initView() {
        // Tạo 1 số double ngẫu nhiên 0.0 <= x < 1.0
        double x = Math.random();
        int randomInt = (int) (Math.random() * 1000000);
        String randomCode = String.valueOf(randomInt);
        // Gán giá trị mặc định cho tên phòng
        binding.tvBookingCode.setText(hotelId +"-"+ randomCode);
        binding.tvConfirmationCode.setText(randomCode);
        binding.etFullName.setText(fullName);
        binding.etPhone.setText(phone);
        binding.etCheckInDate.setText(checkInDate);
        binding.etCheckInTime.setText(checkInTime);
        binding.nameRoom.setText(fullNameRoom);
        binding.priceRoom.setText(price);
        binding.priceRoom.setPaintFlags(binding.priceRoom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.priceRoomDiscount.setText(priceDiscount);

        // Thiết lập sự kiện click cho nút Next
        binding.btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String phone = binding.etPhone.getText().toString().trim();
                String checkInDate = binding.etCheckInDate.getText().toString().trim();
                String checkInTime = binding.etCheckInTime.getText().toString().trim();
                String BookingCode = binding.tvBookingCode.getText().toString().trim();

                boolean isValid = true;

                // Kiểm tra số điện thoại
                if (phone.isEmpty()) {
                    binding.etPhone.setError("Vui lòng nhập số điện thoại");
                    isValid = false;
                } else if (!Patterns.PHONE.matcher(phone).matches()) {
                    binding.etPhone.setError("Số điện thoại không hợp lệ");
                    isValid = false;
                }

                // Kiểm tra giờ check-in (định dạng HH:mm)
                String timeRegex = "^([01]\\d|2[0-3]):[0-5]\\d$";
                if (checkInTime.isEmpty()) {
                    binding.etCheckInTime.setError("Vui lòng nhập giờ check-in");
                    isValid = false;
                } else if (!checkInTime.matches(timeRegex)) {
                    binding.etCheckInTime.setError("Định dạng giờ không hợp lệ. VD: 14:30");
                    isValid = false;
                }

                // Kiểm tra ngày check-in (định dạng dd/MM/yyyy)
                String dateRegex = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
                if (checkInDate.isEmpty()) {
                    binding.etCheckInDate.setError("Vui lòng nhập ngày check-in");
                    isValid = false;
                } else if (!checkInDate.matches(dateRegex)) {
                    binding.etCheckInDate.setError("Định dạng ngày không hợp lệ. VD: 31/12/2021");
                    isValid = false;
                }
                onViewQRPayment(hotelId,hotelName,checkInDate, checkInTime, fullNameRoom, price, priceDiscount, BookingCode);
            }
        });
    }

    private void onViewQRPayment(String hotelId,String hotelName, String checkInDate, String checkInTime, String fullNameRoom, String price, String priceDiscount, String BookingCode) {
        NavController navController = NavHostFragment.findNavController(this);

        // Cách 1: Truyền Bundle thủ công
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelId", hotelId);
        bundle.putString("checkingDate", checkInDate);
        bundle.putString("checkingTime", checkInTime);
        bundle.putString("fullNameRoom", fullNameRoom);
        bundle.putString("price", price);
        bundle.putString("BookingCode", BookingCode);
        bundle.putString("priceDiscount", priceDiscount);

        navController.navigate(R.id.action_navigation_Confirm_to_navigation_QRPayment, bundle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
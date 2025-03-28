package due.cuoiky.thltwd.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.databinding.FragmentFormInforBinding;

public class InfoFragment extends Fragment{

    private FragmentFormInforBinding binding;
    String roomName ="defaul";
    String priceDiscount ="defaul";
    String price ="defaul";
    String hotelName ="defaul";
    String hotelId ="defaul";

    private static final String PREFS_NAME = "FormDataPrefs";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFormInforBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            hotelId = getArguments() != null ? getArguments().getString("hotelId") : "defaultId";
            hotelName = getArguments() != null ? getArguments().getString("hotelName") : "default";
            price = getArguments() != null ? getArguments().getString("price") : "default";
            priceDiscount = getArguments() != null ? getArguments().getString("priceDiscount") : "default";
            roomName = getArguments() != null ? getArguments().getString("roomName") : "default";

        }
        // Load dữ liệu đã lưu (nếu có)
        loadFormData();

        initView();

        return root;
    }

    private void initView() {
        // Gán giá trị mặc định cho tên phòng
        binding.nameRoom.setText(hotelName + ": " + roomName);
        binding.priceRoom.setText(price);
        binding.priceRoom.setPaintFlags(binding.priceRoom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.priceRoomDiscount.setText(priceDiscount);

        binding.cbSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFormData();
            }
        });

        // Thiết lập sự kiện click cho nút Next
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String lastName = binding.etLastName.getText().toString().trim();
                String firstName = binding.etFirstName.getText().toString().trim();
                String email = binding.etEmail.getText().toString().trim();
                String phone = binding.etPhone.getText().toString().trim();
                String region = binding.etRegion.getText().toString().trim();
                String checkInDate = binding.etCheckInDate.getText().toString().trim();
                String checkInTime = binding.etCheckInTime.getText().toString().trim();

                boolean isValid = true;

                // Kiểm tra email
                if (email.isEmpty()) {
                    binding.etEmail.setError("Vui lòng nhập email");
                    isValid = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.etEmail.setError("Email không hợp lệ");
                    isValid = false;
                }

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

                // Kiểm tra dữ liệu nhập (bắt buộc nhập đầy đủ thông tin)
                if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() ||
                        phone.isEmpty() || region.isEmpty() ||
                        checkInDate.isEmpty() || checkInTime.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Ví dụ: Sử dụng họ và tên để làm tên khách sạn, email làm id (chỉ mang tính demo)
                    String fullName = lastName + " " + firstName;
                    String fullNameRoom = hotelName + ": " + roomName;
                    if (isValid){
                        onViewConfirmInf(hotelName, hotelId, fullName, phone, checkInDate, checkInTime, fullNameRoom, price, priceDiscount , email);
                    }
                }
            }
        });

    }

    private void saveFormData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastName", binding.etLastName.getText().toString());
        editor.putString("firstName", binding.etFirstName.getText().toString());
        editor.putString("email", binding.etEmail.getText().toString());
        editor.putString("phone", binding.etPhone.getText().toString());
        editor.putString("region", binding.etRegion.getText().toString());
        editor.putString("checkInDate", binding.etCheckInDate.getText().toString());
        editor.putString("checkInTime", binding.etCheckInTime.getText().toString());
        editor.apply();
    }

    // Phương thức đọc dữ liệu form từ SharedPreferences và điền vào EditText (nếu có)
    private void loadFormData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String lastName = sharedPreferences.getString("lastName", "");
        String firstName = sharedPreferences.getString("firstName", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String region = sharedPreferences.getString("region", "");
        String checkInDate = sharedPreferences.getString("checkInDate", "");
        String checkInTime = sharedPreferences.getString("checkInTime", "");

        // Nếu dữ liệu không rỗng thì điền vào form
        binding.etLastName.setText(lastName);
        binding.etFirstName.setText(firstName);
        binding.etEmail.setText(email);
        binding.etPhone.setText(phone);
        binding.etRegion.setText(region);
        binding.etCheckInDate.setText(checkInDate);
        binding.etCheckInTime.setText(checkInTime);
        binding.cbSaveInfo.setChecked(true);
    }

    private void onViewConfirmInf(String hotelName, String hotelId, String fullName, String phone, String checkInDate, String checkInTime, String fullNameRoom, String price, String priceDiscount, String email ) {
        NavController navController = NavHostFragment.findNavController(this);

        // Cách 1: Truyền Bundle thủ công
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelId", hotelId);
        bundle.putString("phone", phone);
        bundle.putString("checkingDate", checkInDate);
        bundle.putString("checkingTime", checkInTime);
        bundle.putString("fullNameRoom", fullNameRoom);
        bundle.putString("price", price);
        bundle.putString("priceDiscount", priceDiscount);
        bundle.putString("fullName", fullName);
        bundle.putString("email", email);
        navController.navigate(R.id.action_navigation_formInfor_to_navigation_Confirm, bundle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
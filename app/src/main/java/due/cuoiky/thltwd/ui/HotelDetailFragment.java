package due.cuoiky.thltwd.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.adapter.FAQAdapter;
import due.cuoiky.thltwd.adapter.HotelDetailAdapter;
import due.cuoiky.thltwd.adapter.ReviewAdapter;
import due.cuoiky.thltwd.databinding.FragmentHotelDetailBinding;
import due.cuoiky.thltwd.model.Hotel;
import due.cuoiky.thltwd.model.Image;
import due.cuoiky.thltwd.network.API_Hotel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private FAQAdapter FAQ_Adapter;
    ImageView ivMainImage;
    ImageView ivSecondaryImage1;
    ImageView ivSecondaryImage2;

    // Hotel Info
    TextView tvHotelName;
    RatingBar ratingBar;
    TextView tvRatingScore;
    TextView tvRatingScore2;
    TextView tvRatingScore3;
    TextView tvRatingScore4;
    TextView tvRatingScore5;

    // Check-in / Check-out
    TextView tvCheckInDate;
    TextView tvCheckOutDate;

    // Room Info
    TextView tvRoomInfo;

    // Price Info
    TextView tvOriginalPrice;
    TextView tvDiscountedPrice;
    TextView tvPriceDesc;
    TextView tvPromotion1;
    TextView tvPromotion2;


    // Map Info
    TextView tvMapDescription;

    // Reviews Summary
    TextView tvReviewsSummary;

    // FAQ Summary
    TextView tvFAQSummary;

    // Full Description
    TextView tvFullDescription;

    private FragmentHotelDetailBinding binding;

    private HotelDetailAdapter hotelDetailAdapter;
    private String hotelId = "defaultHotelId";
    private Button btnBookRoom;
    private String hotelName = "defaultHotelName";// Giá trị mặc định, có thể được truyền qua Bundle

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho fragment (file: fragment_hotel.xml)
        binding = FragmentHotelDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView();

        // Nếu có dữ liệu truyền vào (ví dụ: từ Bundle) lấy hotelId
        if (getArguments() != null) {
            hotelId = getArguments() != null ? getArguments().getString("hotelId") : "defaultId";
            hotelName = getArguments() != null ? getArguments().getString("hotelName") : "defaultId";

        }

        // Lấy tham chiếu FAB từ binding và gán sự kiện click
        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình room khi nhấn FAB
                onViewRooms(hotelName, hotelId);
            }
        });

        // Gọi API lấy chi tiết khách sạn theo hotelId
        API_Hotel.apiService.getHotel(hotelId).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Hotel hotel = response.body();

                    // -- FAQ Aapter ---
                    if (hotel.getQuestions() != null && !hotel.getQuestions().isEmpty()) {
                        FAQAdapter faqAdapter = new FAQAdapter(hotel.getQuestions());
                        binding.faqRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.faqRecyclerView.setAdapter(faqAdapter);
                    }
                    // Cập nhật Reviews vào RecyclerView
                    if (hotel.getReviews() != null && !hotel.getReviews().isEmpty()) {
                        ReviewAdapter reviewAdapter = new ReviewAdapter(hotel.getReviews());
                        binding.reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.reviewsRecyclerView.setAdapter(reviewAdapter);
                    }

                    // --- GridLayout: Ảnh khách sạn ---
                    // Load ảnh chính (ví dụ: sử dụng thumbnail)
                    if (hotel.getThumbnail() != null && !hotel.getThumbnail().isEmpty()) {
                        Picasso.get().load(hotel.getThumbnail()).into(ivMainImage);
                    }
                    // Nếu có danh sách hình ảnh, load ảnh phụ (giả định thứ 1 và thứ 2)
                    List<Image> images = hotel.getImages();
                    if (images != null && images.size() > 0) {
                        // Nếu có ảnh thứ 1
                        if (images.size() > 1) {
                            Picasso.get().load(images.get(1).getUrl()).into(ivSecondaryImage1);
                        }
                        // Nếu có ảnh thứ 2
                        if (images.size() > 2) {
                            Picasso.get().load(images.get(2).getUrl()).into(ivSecondaryImage2);
                        }
                    }

                    // --- Hotel Info ---
                    tvHotelName.setText(hotel.getName());
                    ratingBar.setRating((float) hotel.getRating());
                    tvRatingScore.setText(String.valueOf(hotel.getRating()));
                    tvRatingScore2.setText(String.valueOf(hotel.getRating()));
                    tvRatingScore3.setText(String.valueOf(hotel.getRating()));
                    tvRatingScore4.setText(String.valueOf(hotel.getRating()));
                    tvRatingScore5.setText(String.valueOf(hotel.getRating()));

                    // --- Check-in / Check-out ---
                    tvCheckInDate.setText(hotel.getCheckIn());
                    tvCheckOutDate.setText(hotel.getCheckOut());

                    // --- Room Info ---
                    tvRoomInfo.setText(hotel.getRoomInfo());

                    // --- Price Info ---
                    if (hotel.getPrice() != null) {
                        // Hiển thị giá theo định dạng, bạn có thể format thêm cho đẹp (ví dụ: sử dụng String.format)
//                        tvOriginalPrice.setText(String.format("%,d đ", hotel.getPrice().getOriginal()));
                        tvOriginalPrice.setText(String.format("%,d đ", hotel.getPrice().getOriginal()));
                        tvOriginalPrice.setPaintFlags(tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        tvDiscountedPrice.setText(String.format("%,d đ", hotel.getPrice().getDiscounted()));
                        tvPriceDesc.setText(hotel.getPrice().getDescription());
                        // Nếu có thông tin khuyến mãi (promotion) dạng List<String>
                        if (hotel.getPrice().getPromotion() != null && hotel.getPrice().getPromotion().size() >= 2) {
                            tvPromotion1.setText(hotel.getPrice().getPromotion().get(0));
                            tvPromotion2.setText(hotel.getPrice().getPromotion().get(1));
                        }

                    }

                    // --- Map Info ---
                    if (hotel.getMap() != null) {
                        tvMapDescription.setText(hotel.getMap().getDescription());
                    }

                    // --- Reviews Summary ---
                    if (hotel.getReviews() != null) {
                        tvReviewsSummary.setText("Xem tất cả " + hotel.getReviews().size() + " đánh giá");
                    } else {
                        tvReviewsSummary.setText("Không có đánh giá");
                    }

                    // --- FAQ Summary ---
                    if (hotel.getQuestions() != null) {
                        tvFAQSummary.setText("Xem tất cả " + hotel.getQuestions().size() + " câu hỏi");
                    } else {
                        tvFAQSummary.setText("Không có câu hỏi");
                    }

                    // --- Description (Miêu tả) ---
                    tvFullDescription.setText(hotel.getDescription());

//                    onViewRooms(hotel.getName(), hotel.getId());

                } else {
                    Toast.makeText(getContext(), "Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    private void initView() {
        // Ảnh
        ivMainImage = binding.ivMainImage;
        ivSecondaryImage1 = binding.ivSecondaryImage1;
        ivSecondaryImage2 = binding.ivSecondaryImage2;

        // Hotel Info
        tvHotelName = binding.hotelName;
        ratingBar = binding.ratingBar;
        tvRatingScore = binding.ratingScore;
        tvRatingScore2 = binding.ratingScore2;
        tvRatingScore3 = binding.ratingScore3;
        tvRatingScore4 = binding.ratingScore4;
        tvRatingScore5 = binding.ratingScore5;

        // Check-in / Check-out
        tvCheckInDate = binding.checkInDate;
        tvCheckOutDate = binding.checkOutDate;

        // Room Info
        tvRoomInfo = binding.roomInfo;

        // Price Info
        tvOriginalPrice = binding.tvOriginalPrice;
        tvDiscountedPrice = binding.tvDiscountedPrice;
        tvPriceDesc = binding.tvPriceDesc;
        tvPromotion1 = binding.tvPromotion1;
        tvPromotion2 = binding.tvPromotion2;

        // Map Info
        tvMapDescription = binding.tvMapDescription;

        // Reviews Summary
        tvReviewsSummary = binding.tvReviewsSummary;

        // FAQ Summary
        tvFAQSummary = binding.tvFAQSummary;

        // Full Description
        tvFullDescription = binding.tvFullDescription;
    }

    private void onViewRooms(String hotelName, String hotelId) {
        NavController navController = NavHostFragment.findNavController(this);

        // Cách 1: Truyền Bundle thủ công
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelId", hotelId);
        navController.navigate(R.id.action_navigation_hotel_detail_to_navigation_booking, bundle);

    }

}

package due.cuoiky.thltwd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.Hotel;
import due.cuoiky.thltwd.model.Image;

public class HotelDetailAdapter extends RecyclerView.Adapter<HotelDetailAdapter.HotelDetailViewHolder> {

    // Đối tượng Hotel chứa toàn bộ dữ liệu chi tiết
    private Hotel hotel;

    public HotelDetailAdapter(Hotel hotel) {
        this.hotel = hotel;
    }

    // Phương thức cập nhật lại dữ liệu (ví dụ sau khi load từ API)
    public void updateHotel(Hotel hotel) {
        this.hotel = hotel;
        notifyDataSetChanged();
    }

    @Override
    public HotelDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hotel_detail, parent, false);
        return new HotelDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelDetailViewHolder holder, int position) {
        if (hotel == null) return;

        // --- GridLayout: Ảnh khách sạn ---
        // Load ảnh chính (ví dụ: sử dụng thumbnail)
        if (hotel.getThumbnail() != null && !hotel.getThumbnail().isEmpty()) {
            Picasso.get().load(hotel.getThumbnail()).into(holder.ivMainImage);
        }
        // Nếu có danh sách hình ảnh, load ảnh phụ (giả định thứ 1 và thứ 2)
        List<Image> images = hotel.getImages();
        if (images != null && images.size() > 0) {
            // Nếu có ảnh thứ 1
            if (images.size() > 1) {
                Picasso.get().load(images.get(1).getUrl()).into(holder.ivSecondaryImage1);
            }
            // Nếu có ảnh thứ 2
            if (images.size() > 2) {
                Picasso.get().load(images.get(2).getUrl()).into(holder.ivSecondaryImage2);
            }
        }

        // --- Hotel Info ---
        holder.tvHotelName.setText(hotel.getName());
        holder.ratingBar.setRating((float) hotel.getRating());
        holder.tvRatingScore.setText(String.valueOf(hotel.getRating()));

        // --- Check-in / Check-out ---
        holder.tvCheckInDate.setText(hotel.getCheckIn());
        holder.tvCheckOutDate.setText(hotel.getCheckOut());

        // --- Room Info ---
        holder.tvRoomInfo.setText(hotel.getRoomInfo());

        // --- Price Info ---
        if (hotel.getPrice() != null) {
            // Hiển thị giá theo định dạng, bạn có thể format thêm cho đẹp (ví dụ: sử dụng String.format)
            holder.tvOriginalPrice.setText(String.format("%,d đ", hotel.getPrice().getOriginal()));
            holder.tvDiscountedPrice.setText(String.format("%,d đ", hotel.getPrice().getDiscounted()));
            holder.tvPriceDesc.setText(hotel.getPrice().getDescription());
            // Nếu có thông tin khuyến mãi (promotion) dạng List<String>
            if (hotel.getPrice().getPromotion() != null && hotel.getPrice().getPromotion().size() >= 2) {
                holder.tvPromotion1.setText(hotel.getPrice().getPromotion().get(0));
                holder.tvPromotion2.setText(hotel.getPrice().getPromotion().get(1));
            }

        }

        // --- Map Info ---
        if (hotel.getMap() != null) {
            holder.tvMapDescription.setText(hotel.getMap().getDescription());
        }

        // --- Reviews Summary ---
        if (hotel.getReviews() != null) {
            holder.tvReviewsSummary.setText("Xem tất cả " + hotel.getReviews().size() + " đánh giá");
        } else {
            holder.tvReviewsSummary.setText("Không có đánh giá");
        }

        // --- FAQ Summary ---
        if (hotel.getQuestions() != null) {
            holder.tvFAQSummary.setText("Xem tất cả " + hotel.getQuestions().size() + " câu hỏi");
        } else {
            holder.tvFAQSummary.setText("Không có câu hỏi");
        }

        // --- Description (Miêu tả) ---
        holder.tvFullDescription.setText(hotel.getDescription());
    }

    @Override
    public int getItemCount() {
        // Vì đây là màn hình chi tiết, chỉ có 1 item nếu hotel không null
        return hotel != null ? 1 : 0;
    }

    public static class HotelDetailViewHolder extends RecyclerView.ViewHolder {
        // GridLayout image views
        ImageView ivMainImage;
        ImageView ivSecondaryImage1;
        ImageView ivSecondaryImage2;

        // Hotel Info
        TextView tvHotelName;
        RatingBar ratingBar;
        TextView tvRatingScore;

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

        public HotelDetailViewHolder(View itemView) {
            super(itemView);
            // Gán id cho các view (đảm bảo file XML đã có các id này)
            ivMainImage = itemView.findViewById(R.id.ivMainImage);
            ivSecondaryImage1 = itemView.findViewById(R.id.ivSecondaryImage1);
            ivSecondaryImage2 = itemView.findViewById(R.id.ivSecondaryImage2);

            tvHotelName = itemView.findViewById(R.id.hotel_name);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            tvRatingScore = itemView.findViewById(R.id.rating_score);

            tvCheckInDate = itemView.findViewById(R.id.check_in_date);
            tvCheckOutDate = itemView.findViewById(R.id.check_out_date);

            tvRoomInfo = itemView.findViewById(R.id.room_info);

            tvOriginalPrice = itemView.findViewById(R.id.tvOriginalPrice);
            tvDiscountedPrice = itemView.findViewById(R.id.tvDiscountedPrice);
            tvPriceDesc = itemView.findViewById(R.id.tvPriceDesc);
            tvPromotion1 = itemView.findViewById(R.id.tvPromotion1);
            tvPromotion2 = itemView.findViewById(R.id.tvPromotion2);

            tvMapDescription = itemView.findViewById(R.id.tvMapDescription);

            tvReviewsSummary = itemView.findViewById(R.id.tvReviewsSummary);
            tvFAQSummary = itemView.findViewById(R.id.tvFAQSummary);

            tvFullDescription = itemView.findViewById(R.id.tvFullDescription);
        }
    }
}

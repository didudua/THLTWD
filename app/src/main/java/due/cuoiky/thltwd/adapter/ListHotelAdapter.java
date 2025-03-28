package due.cuoiky.thltwd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.Hotel;

public class ListHotelAdapter extends RecyclerView.Adapter<ListHotelAdapter.HotelViewHolder> {

    private List<Hotel> hotelList;
    private OnItemClickListener onItemClickListener;

    // Interface để lắng nghe sự kiện click vào item
    public interface OnItemClickListener {
        void onItemClick(Hotel hotel);
    }

    public ListHotelAdapter(List<Hotel> hotelList, OnItemClickListener onItemClickListener) {
        this.hotelList = hotelList;
        this.onItemClickListener = onItemClickListener;
    }

    public void reLoadData(List<Hotel> list){
        this.hotelList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);

        Picasso.get().load(hotel.getThumbnail()).into(holder.ivCover);
        holder.tvTitle.setText(hotel.getName());
        holder.tvContent.setText(hotel.getDescription());
        holder.tvStar.setText(String.valueOf(hotel.getRating()));

        // Thiết lập sự kiện click cho từng item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(hotel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle, tvContent, tvStar;

        public HotelViewHolder(View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvStar = itemView.findViewById(R.id.tvStar);
        }
    }
}

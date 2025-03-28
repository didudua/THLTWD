package due.cuoiky.thltwd.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;

    private OnRoomItemClickListener listener;

    public interface OnRoomItemClickListener {
        void onChooseRoom(Room room);
    }

    public RoomAdapter(List<Room> roomList, OnRoomItemClickListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho mỗi item (item_room.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        // Lấy thông tin phòng tại vị trí position
        Room room = roomList.get(position);

        // Liên kết dữ liệu phòng với các view
        holder.tvRoomTitle.setText(room.getTitle());
        holder.tvRoomCapacity.setText(room.getCapacity());
        holder.tvRoomBedType.setText(room.getBedType());
        holder.tvRoomSize.setText(room.getRoomSize());
        holder.tvRoomAvailability.setText(room.getAvailability());
        holder.tvRoomDiscount.setText(room.getDiscount());
        holder.tvRoomLimitedTimeOffer.setText(room.getLimitedTimeOffer());

        // Hiển thị giá phòng
        holder.tvRoomOriginalPriceRoom.setText(room.getOriginalPrice());
        holder.tvRoomOriginalPriceRoom.setPaintFlags(holder.tvRoomOriginalPriceRoom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvRoomDiscountedPriceRoom.setText(room.getDiscountedPrice());

        // Nếu có ảnh, load ảnh bằng Picasso
        if (room.getImageUrl() != null && !room.getImageUrl().isEmpty()) {
            Picasso.get().load(room.getImageUrl()).into(holder.ivRoomImage);
        }

        holder.btnChooseRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onChooseRoom(room);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }

    // ViewHolder class để liên kết view với dữ liệu
    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomTitle, tvRoomCapacity, tvRoomBedType, tvRoomSize;
        TextView tvRoomAvailability, tvRoomDiscount, tvRoomLimitedTimeOffer;
        TextView tvRoomOriginalPriceRoom, tvRoomDiscountedPriceRoom;
        ImageView ivRoomImage;
        Button btnChooseRoom;

        public RoomViewHolder(View itemView) {
            super(itemView);

            // Khởi tạo các view
            tvRoomTitle = itemView.findViewById(R.id.tvRoomName);
            tvRoomCapacity = itemView.findViewById(R.id.tvRoomType);
            tvRoomBedType = itemView.findViewById(R.id.tvRoomBedType);
            tvRoomSize = itemView.findViewById(R.id.tvRoomSize);
            tvRoomAvailability = itemView.findViewById(R.id.tvRoomAvailability);
            tvRoomDiscount = itemView.findViewById(R.id.tvRoomDiscount);
            tvRoomLimitedTimeOffer = itemView.findViewById(R.id.tvRoomLimitedTimeOffer);
            tvRoomOriginalPriceRoom = itemView.findViewById(R.id.tvOriginalPriceRoom);
            tvRoomDiscountedPriceRoom = itemView.findViewById(R.id.tvDiscountedPriceRoom);
            ivRoomImage = itemView.findViewById(R.id.ivRoomImage);
            btnChooseRoom = itemView.findViewById((R.id.btn_chooseRoom));
        }
    }
}

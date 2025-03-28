package due.cuoiky.thltwd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.Booking;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private Context context;
    private List<Booking> bookingList;

    public BookingAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.tvFullNameRoom.setText(booking.getRoomName());
        holder.tvBookingCode.setText("Mã đặt phòng: "+ booking.getBookingCode());
        holder.tvCheckIn.setText(booking.getCheckInTime() + ", " + booking.getCheckInDate());
        holder.tvPriceBooking.setText("Giá: "+booking.getPrice());
        holder.tvStatus.setText("Đã thanh toán");
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullNameRoom, tvBookingCode, tvCheckIn, tvPriceBooking, tvStatus;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullNameRoom = itemView.findViewById(R.id.tvFullNameRoom);
            tvBookingCode = itemView.findViewById(R.id.tvBookingCode);
            tvCheckIn = itemView.findViewById(R.id.tvCheckIn);
            tvPriceBooking = itemView.findViewById(R.id.tvPriceBooking);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}

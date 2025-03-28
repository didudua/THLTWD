package due.cuoiky.thltwd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item (item_review.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        // Get the Review item at the given position
        Review review = reviewList.get(position);

        // Bind the user information and review text to the respective views
        holder.tvUsername.setText(review.getUser());
        holder.tvCountry.setText(review.getCountry());
        holder.tvReviewText.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviewList != null ? reviewList.size() : 0;
    }

    // ViewHolder class for binding views
    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvUsername, tvCountry, tvReviewText;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvReviewText = itemView.findViewById(R.id.tvReviewText);
        }
    }
}

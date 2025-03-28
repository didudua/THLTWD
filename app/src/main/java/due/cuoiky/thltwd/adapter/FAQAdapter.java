package due.cuoiky.thltwd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import due.cuoiky.thltwd.R;
import due.cuoiky.thltwd.model.FAQ;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    private List<FAQ> faqList;

    public FAQAdapter(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item (item_faq.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        // Get the FAQ item at the given position
        FAQ faq = faqList.get(position);

        // Bind the question and date data to the respective views
        holder.tvQuestion.setText(faq.getQuestion());
        holder.tvDate.setText(faq.getDate());

        // Loop through the answers and add them to the view
        StringBuilder answers = new StringBuilder();
        for (String answer : faq.getAnswers()) {
            answers.append(answer).append("\n\n");
        }
        holder.tvAnswers.setText(answers.toString());
    }

    @Override
    public int getItemCount() {
        return faqList != null ? faqList.size() : 0;
    }

    // ViewHolder class for binding views
    public static class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvDate, tvAnswers;

        public FAQViewHolder(View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAnswers = itemView.findViewById(R.id.tvAnswers);
        }
    }
}

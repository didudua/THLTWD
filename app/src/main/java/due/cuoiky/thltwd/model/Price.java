package due.cuoiky.thltwd.model;

import java.util.List;

public class Price {
    // Tuỳ theo dữ liệu mà bạn có thể để int, long hoặc String
    private int original;
    private int discounted;
    private String description;
    private List<String> promotion;

    public Price() {
    }

    public Price(int original, int discounted, String description, List<String> promotion) {
        this.original = original;
        this.discounted = discounted;
        this.description = description;
        this.promotion = promotion;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public int getDiscounted() {
        return discounted;
    }

    public void setDiscounted(int discounted) {
        this.discounted = discounted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<String> promotion) {
        this.promotion = promotion;
    }
}

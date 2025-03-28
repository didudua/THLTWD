package due.cuoiky.thltwd.model;

public class Room {
    private String title;
    private String capacity;
    private String bedType;
    private String roomSize;
    private String bathroom;
    private String cancellationPolicy;
    private String paymentInfo;
    private String amenities;
    private String availability;
    private String discount;
    private String limitedTimeOffer;
    private String originalPrice;
    private String discountedPrice;
    private String taxInfo;
    private String imageUrl;

    // Constructor
    public Room(String title, String capacity, String bedType, String roomSize, String bathroom,
                String cancellationPolicy, String paymentInfo, String amenities, String availability,
                String discount, String limitedTimeOffer, String originalPrice, String discountedPrice,
                String taxInfo, String imageUrl) {
        this.title = title;
        this.capacity = capacity;
        this.bedType = bedType;
        this.roomSize = roomSize;
        this.bathroom = bathroom;
        this.cancellationPolicy = cancellationPolicy;
        this.paymentInfo = paymentInfo;
        this.amenities = amenities;
        this.availability = availability;
        this.discount = discount;
        this.limitedTimeOffer = limitedTimeOffer;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.taxInfo = taxInfo;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLimitedTimeOffer() {
        return limitedTimeOffer;
    }

    public void setLimitedTimeOffer(String limitedTimeOffer) {
        this.limitedTimeOffer = limitedTimeOffer;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(String taxInfo) {
        this.taxInfo = taxInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

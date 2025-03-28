package due.cuoiky.thltwd.model;

import java.util.List;

public class Hotel {
    private String id;
    private String name;
    private String address;
    private double rating;       // hoặc float tuỳ ý
    private String thumbnail;
    private List<Image> images;
    private String checkIn;
    private String checkOut;
    private String roomInfo;
    private Price price;
    private MapInfo map;
    private List<Review> reviews;
    private List<FAQ> questions;
    private String description;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(String id, String name, String address, double rating, String thumbnail, List<Image> images,
                 String checkIn, String checkOut, String roomInfo, Price price, MapInfo map,
                 List<Review> reviews, List<FAQ> questions, String description, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.images = images;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomInfo = roomInfo;
        this.price = price;
        this.map = map;
        this.reviews = reviews;
        this.questions = questions;
        this.description = description;
        this.rooms = rooms;
    }

    // Getter / Setter (có thể generate tự động trong IDE)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public MapInfo getMap() {
        return map;
    }

    public void setMap(MapInfo map) {
        this.map = map;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<FAQ> getQuestions() {
        return questions;
    }

    public void setQuestions(List<FAQ> questions) {
        this.questions = questions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}

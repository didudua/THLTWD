package due.cuoiky.thltwd.model;

public class Booking {
    private String roomName;
    private String bookingCode;
    private String checkInDate;
    private String checkInTime;
    private String price;

    public Booking(String roomName, String bookingCode, String checkInDate, String checkInTime, String price) {
        this.roomName = roomName;
        this.bookingCode = bookingCode;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getPrice() {
        return price;
    }
}

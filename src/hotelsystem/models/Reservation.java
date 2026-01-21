package hotelsystem.models;

import java.util.Date;

public class Reservation {

    private int reservationId;
    private int customerId;
    private String customerName;
    private String roomNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalPrice;

    public Reservation(int reservationId, int customerId, String customerName, String roomNum, Date checkInDate, Date checkOutDate, String roomNumber, double totalPrice) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getAllInformation() {
        return reservationId + "," + customerId + "," + customerName + ","
                + roomNumber + "," + checkInDate + "," + checkOutDate + ","
                + status + "," + totalPrice;
    }

    public Date getCheckIn() {
        return checkInDate;
    }

    public Date getCheckOut() {
        return checkOutDate;
    }
}

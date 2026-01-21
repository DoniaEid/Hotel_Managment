package hotelsystem.models;

public class Room {

    private int roomNumber;
    private String type;
    private double price;
    private String status;
    private String floor;
    private int capacity;
    private String amenities;

    public Room(int roomNumber, String type, double price, String status,
            String floor, int capacity, String amenities) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.status = status;
        this.floor = floor;
        this.capacity = capacity;
        this.amenities = amenities;
    }

    // Getters and Setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getAllInformation() {
        return roomNumber + "," + type + "," + price + "," + status + ","
                + floor + "," + capacity + "," + amenities;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\n"
                + "Type: " + type + "\n"
                + "Price: $" + price + "\n"
                + "Status: " + status + "\n"
                + "Floor: " + floor + "\n"
                + "Capacity: " + capacity + "\n"
                + "Amenities: " + (amenities.isEmpty() ? "None" : amenities);
    }
}

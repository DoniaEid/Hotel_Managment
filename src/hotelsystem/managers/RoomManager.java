
package hotelsystem.managers;
import hotelsystem.models.Room;
import hotelsystem.io.FileIOHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomManager {
    private List<Room> rooms = new ArrayList<>();

    public void loadFromFile() {
        rooms.clear();
        try {
            List<String> lines = FileIOHandler.readAll("room.txt");

            for (String line : lines) {
                try {
                    String[] data = line.split(",");
                    if (data.length < 7) continue; 
                    
                    int roomNumber = Integer.parseInt(data[0].trim());  
                    String type = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    String status = data[3].trim(); 
                    String floor = data[4].trim();
                    int capacity = Integer.parseInt(data[5].trim());
                    String amenities = data[6].trim();

                    Room r = new Room(roomNumber, type, price, status, floor, capacity, amenities);
                    rooms.add(r);
                } catch (Exception e) {
                    System.err.println("Invalid line in room.txt: " + line);
                }
            }
            System.out.println("✓ Loaded " + rooms.size() + " rooms from file.");
        } catch (Exception e) {
            System.err.println("✗ Error loading room.txt: " + e.getMessage());
        }
    }

    public void addRoom(Room r) throws Exception {
        for (Room existing : rooms) {
            if (existing.getRoomNumber() == r.getRoomNumber()) {
                throw new Exception("Room number already exists!");
            }
        }
        
        if (r.getRoomNumber() <= 0) {
            throw new Exception("Room number must be positive!");
        }
        if (r.getPrice() <= 0) {
            throw new Exception("Price must be positive!");
        }
        if (r.getCapacity() <= 0) {
            throw new Exception("Capacity must be positive!");
        }
        
        rooms.add(r);
        FileIOHandler.appendLine("room.txt", r.getAllInformation());
        System.out.println("✓ Room added to file: " + r.getRoomNumber());
    }

    public Room findRoomByNumber(int roomNumber) { 
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    public void deleteRoom(int roomNumber) throws Exception { 
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room r = iterator.next();
            if (r.getRoomNumber() == roomNumber) {  
                FileIOHandler.deleteLineFromFile("room.txt", r.getAllInformation());
                iterator.remove();
                System.out.println("✓ Room deleted: " + roomNumber);
                return;
            }
        }
        throw new Exception("Room not found!");
    }

    public void updateRoom(Room newRoom, int oldRoomNumber) throws Exception { 
        boolean found = false;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == oldRoomNumber) { 
                if (newRoom.getRoomNumber() != oldRoomNumber) {
                    for (Room r : rooms) {
                        if (r.getRoomNumber() == newRoom.getRoomNumber()) {
                            throw new Exception("New room number already exists!");
                        }
                    }
                }
                
                FileIOHandler.updateLineInFile(
                    "room.txt",
                    rooms.get(i).getAllInformation(),
                    newRoom.getAllInformation()
                );
                rooms.set(i, newRoom);
                found = true;
                System.out.println("✓ Room updated: " + oldRoomNumber + " → " + newRoom.getRoomNumber());
                break;
            }
        }
        if (!found) throw new Exception("Room not found!");
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }
    
    public List<Room> filterRooms(String type, String status, String floor) {
        List<Room> filtered = new ArrayList<>();
        
        for (Room r : rooms) {
            boolean matches = true;
            
            if (type != null && !type.equals("All") && !type.isEmpty() && !r.getType().equals(type))
                matches = false;
            
            if (status != null && !status.equals("All") && !status.isEmpty() && !r.getStatus().equals(status))
                matches = false;
            
            if (floor != null && !floor.equals("All") && !floor.isEmpty() && !r.getFloor().equals(floor))
                matches = false;
            
            if (matches)
                filtered.add(r);
        }
        
        return filtered;
    }
}
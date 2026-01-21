package hotelsystem.managers;

import hotelsystem.models.Reservation;
import hotelsystem.io.FileIOHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationManager {

    private List<Reservation> reservations = new ArrayList<>();
    private static final String RESERVATION_FILE = "Reservation.txt";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public void loadFromFile() {
        reservations.clear();
        List<String> lines = FileIOHandler.readAll(RESERVATION_FILE);

        if (lines == null || lines.isEmpty()) {
            return;
        }

        for (String line : lines) {
            try {
                String[] data = line.split(",");
                if (data.length < 8) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                int reservationId = Integer.parseInt(data[0].trim());
                int customerId = Integer.parseInt(data[1].trim());
                String customerName = data[2].trim();
                String roomNumber = data[3].trim();

                Date checkIn = SDF.parse(data[4].trim());
                Date checkOut = SDF.parse(data[5].trim());
                String status = data[6].trim();
                double totalPrice = Double.parseDouble(data[7].trim());

                Reservation r = new Reservation(
                        reservationId, customerId, customerName, roomNumber,
                        checkIn, checkOut, status, totalPrice
                );
                reservations.add(r);
            } catch (Exception e) {
                System.out.println("Invalid line in Reservation.txt: " + line);
                e.printStackTrace();
            }
        }
    }

    public void createReservation(Reservation reservation) throws Exception {
        if (findReservationById(reservation.getReservationId()) != null) {
            throw new Exception("Reservation ID already exists!");
        }

        reservations.add(reservation);

        String line = reservation.getReservationId() + ","
                + reservation.getCustomerId() + ","
                + reservation.getCustomerName() + ","
                + reservation.getRoomNumber() + ","
                + SDF.format(reservation.getCheckInDate()) + ","
                + SDF.format(reservation.getCheckOutDate()) + ","
                + reservation.getStatus() + ","
                + reservation.getTotalPrice();

        FileIOHandler.appendLine(RESERVATION_FILE, line);
    }

    public void updateReservation(Reservation updatedRes, int oldResId) throws Exception {
        Reservation oldRes = findReservationById(oldResId);
        if (oldRes == null) {
            throw new Exception("Reservation not found!");
        }

        if (oldResId != updatedRes.getReservationId()) {
            if (findReservationById(updatedRes.getReservationId()) != null) {
                throw new Exception("New reservation ID already exists!");
            }
        }

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId() == oldResId) {
                reservations.set(i, updatedRes);
                break;
            }
        }

        saveAllToFile();
    }

    public void cancelReservation(int reservationId) throws Exception {
        Reservation res = findReservationById(reservationId);
        if (res == null) {
            throw new Exception("Reservation not found!");
        }

        res.setStatus("Cancelled");
        saveAllToFile();
        System.out.println("Reservation cancelled successfully.");
    }

    public void deleteReservation(int reservationId) throws Exception {
        boolean removed = reservations.removeIf(r -> r.getReservationId() == reservationId);

        if (!removed) {
            throw new Exception("Reservation not found!");
        }

        saveAllToFile();
    }

    private void saveAllToFile() {
        List<String> lines = new ArrayList<>();

        for (Reservation r : reservations) {
            String line = r.getReservationId() + ","
                    + r.getCustomerId() + ","
                    + r.getCustomerName() + ","
                    + r.getRoomNumber() + ","
                    + SDF.format(r.getCheckInDate()) + ","
                    + SDF.format(r.getCheckOutDate()) + ","
                    + r.getStatus() + ","
                    + r.getTotalPrice();
            lines.add(line);
        }

        FileIOHandler.appendLine(RESERVATION_FILE, lines.toString());
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public Reservation findReservationById(int resId) {
        return reservations.stream()
                .filter(r -> r.getReservationId() == resId)
                .findFirst()
                .orElse(null);
    }

    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomerId() == customerId) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Reservation> getActiveReservations() {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (!r.getStatus().equals("Cancelled") && !r.getStatus().equals("Completed")) {
                result.add(r);
            }
        }
        return result;
    }

    public void listReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\n=== All Reservations ===");
        System.out.println("ID\tCustomer\tRoom\tCheck-In\tCheck-Out\tStatus\t\tTotal");
        System.out.println("--------------------------------------------------------------------------------");

        for (Reservation r : reservations) {
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t$%.2f%n",
                    r.getReservationId(),
                    r.getCustomerName(),
                    r.getRoomNumber(),
                    SDF.format(r.getCheckInDate()),
                    SDF.format(r.getCheckOutDate()),
                    r.getStatus(),
                    r.getTotalPrice()
            );
        }
    }

    public boolean isRoomAvailable(String roomNumber, Date checkIn, Date checkOut) {
        for (Reservation r : reservations) {
            if (r.getRoomNumber().equals(roomNumber)
                    && !r.getStatus().equals("Cancelled")) {

                if ((checkIn.before(r.getCheckOutDate()) && checkOut.after(r.getCheckInDate()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateReservationStatuses() {
        Date currentDate = new Date();

        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")
                    && currentDate.after(r.getCheckOutDate())) {
                r.setStatus("Completed");
            }
        }
        saveAllToFile();
    }
}

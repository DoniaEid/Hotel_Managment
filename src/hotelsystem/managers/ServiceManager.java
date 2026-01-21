package hotelsystem.managers;

import hotelsystem.io.FileIOHandler;
import hotelsystem.models.Service;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private List<Service> services_hotel = new ArrayList<>();

    public void loadFromFile() {
        services_hotel.clear();
        List<String> lines = FileIOHandler.readAll("Service.txt");

        for (String line : lines) {
            try {
                String[] data = line.split(",");
                String name = data[0];
                double cost = Double.parseDouble(data[1]);
                String description = data[2];
                boolean available = Boolean.parseBoolean(data[3]);

                Service s = new Service(name, cost, description, available);
                services_hotel.add(s);

            } catch (Exception e) {
                System.out.println("Invalid line in Service.txt: " + line);
            }
        }
    }

    public void addService(Service s) throws Exception {

        for (Service existing : services_hotel) {
            if (existing.getName().equalsIgnoreCase(s.getName())) {
                throw new Exception("Service already exists!");
            }
        }

        services_hotel.add(s);
        FileIOHandler.appendLine("Service.txt", s.getAllInformation());
    }

    public void removeService(String name) throws Exception {
        boolean found = false;

        for (int i = 0; i < services_hotel.size(); i++) {
            if (services_hotel.get(i).getName().equalsIgnoreCase(name)) {
                Service s = services_hotel.remove(i);
                FileIOHandler.deleteLineFromFile("Service.txt", s.getAllInformation());
                found = true;
                break;
            }
        }

        if (!found) {
            throw new Exception("Service not found!");
        }
    }

    public void updateService(Service oldService, Service newService) throws Exception {

        for (int i = 0; i < services_hotel.size(); i++) {
            if (services_hotel.get(i).getName().equalsIgnoreCase(oldService.getName())) {

                FileIOHandler.updateLineInFile(
                        "Service.txt",
                        oldService.getAllInformation(),
                        newService.getAllInformation()
                );

                services_hotel.set(i, newService);
                return;
            }
        }
        throw new Exception("Service not found!");
    }

    // 🔹 Show all services
    public void showAllServices() {
        if (services_hotel.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        for (Service s : services_hotel) {
            System.out.println(s);
        }
    }

    public List<Service> getAllServices() {
        return services_hotel;
    }
}

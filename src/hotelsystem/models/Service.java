package hotelsystem.models;

public class Service {

    private String name;
    private double cost;
    private String description;
    private boolean available;

    public Service(String name, double cost, String description, boolean available) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.available = available;
    }

    public Service(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAllInformation() {
        return name + "," + cost + "," + description + "," + available;
    }

    @Override
    public String toString() {
        return "Service Name: " + name
                + ", Cost: " + cost
                + ", Description: " + description
                + ", Available: " + (available ? "Yes" : "No");
    }
}

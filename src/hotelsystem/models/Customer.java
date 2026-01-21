package hotelsystem.models;

public class Customer extends Person {

    private String email;

    public Customer() {
    }

    public Customer(int id, String name, String phone_number, String email) {
        super(id, name, phone_number);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAllInformation() {
        return id + ","
                + name + ","
                + phone_number + ","
                + email;
    }

}


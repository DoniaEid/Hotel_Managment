
package hotelsystem.managers;

import hotelsystem.io.FileIOHandler;
import hotelsystem.models.Customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerManager {

    private List<Customer> customers = new ArrayList<>();

    public void loadFromFile() {
        customers.clear();
        List<String> lines = FileIOHandler.readAll("customer.txt");

        for (String line : lines) {
            try {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String phone = data[2];
                String email = data[3];

                Customer c = new Customer(id, name, phone, email);
                customers.add(c);

            } catch (Exception e) {
                System.out.println("Invalid line in customer.txt: " + line);
            }
        }
    }

    public void add(Customer c) throws Exception {

        for (Customer existing : customers) {
            if (existing.getId() == c.getId()) {
                throw new Exception("ID already exists!");
            }
        }

        if (c.getName() == null || c.getName().isEmpty())
            throw new Exception("Name can't be empty.");

        if (c.getPhone_number() == null || c.getPhone_number().length() < 11)
            throw new Exception("Phone number must be at least 11 digits.");

        if (c.getEmail() == null || !c.getEmail().contains("@gmail.com"))
            throw new Exception("Invalid email.");

        customers.add(c);
        FileIOHandler.appendLine("customer.txt", c.getAllInformation());
    }

    public void delete(int id) throws Exception {

        boolean found = false;
        Iterator<Customer> it = customers.iterator();

        while (it.hasNext()) {
            Customer c = it.next();
            if (c.getId() == id) {
                FileIOHandler.deleteLineFromFile("customer.txt", c.getAllInformation());
                it.remove();
                found = true;
                break;
            }
        }

        if (!found)
            throw new Exception("Customer with ID " + id + " not found.");
    }

    public void update(Customer newC, int oldId) throws Exception {

        boolean found = false;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == oldId) {
                FileIOHandler.updateLineInFile(
                        "customer.txt",
                        customers.get(i).getAllInformation(),
                        newC.getAllInformation()
                );
                customers.set(i, newC);
                found = true;
                break;
            }
        }

        if (!found)
            throw new Exception("Customer with ID " + oldId + " not found.");
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    public void printAllCustomers() {
        for (Customer c : customers) {
            System.out.println(c.getAllInformation());
        }
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}

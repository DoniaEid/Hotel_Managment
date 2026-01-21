package hotelsystem.managers;

import hotelsystem.io.FileIOHandler;
import hotelsystem.models.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeManager {

    private List<Employee> employees = new ArrayList<>();

    public void loadFromFile() {
        employees.clear();
        List<String> lines = FileIOHandler.readAll("Employee.txt");

        for (String line : lines) {
            try {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String phone = data[2];
                String password = data[3];
                String jobTitle = data[4];

                Employee e = new Employee(id, name, phone, password, jobTitle);
                employees.add(e);

            } catch (Exception ex) {
                System.out.println("Invalid line in Employee.txt: " + line);
            }
        }
    }

    public void add(Employee e) throws Exception {

        for (Employee existing : employees) {
            if (existing.getId() == e.getId()) {
                throw new Exception("ID already exists!");
            }
        }

        if (e.getName() == null || e.getName().isEmpty())
            throw new Exception("Name cannot be empty.");

        if (e.getPhone_number() == null || e.getPhone_number().length() < 11)
            throw new Exception("Phone number must be at least 11 digits.");

        if (e.getEmployee_password() == null || e.getEmployee_password().isEmpty())
            throw new Exception("Password cannot be empty.");

        if (e.getJob_title() == null || e.getJob_title().isEmpty())
            throw new Exception("Job title cannot be empty.");

        employees.add(e);
        FileIOHandler.appendLine("Employee.txt", e.getAllInformation());
    }

    public void delete(int id) throws Exception {

        boolean found = false;
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee e = iterator.next();
            if (e.getId() == id) {
                FileIOHandler.deleteLineFromFile("Employee.txt", e.getAllInformation());
                iterator.remove();
                found = true;
                break;
            }
        }

        if (!found)
            throw new Exception("Employee with ID " + id + " not found.");
    }

    public void update(Employee newE, int oldId) throws Exception {

        boolean found = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == oldId) {

                FileIOHandler.updateLineInFile(
                        "Employee.txt",
                        employees.get(i).getAllInformation(),
                        newE.getAllInformation()
                );

                employees.set(i, newE);
                found = true;
                break;
            }
        }

        if (!found)
            throw new Exception("Can't update Employee, not found!");
    }

    public Employee findEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    public void printAllEmployees() {
        for (Employee e : employees) {
            System.out.println(e.getAllInformation());
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}


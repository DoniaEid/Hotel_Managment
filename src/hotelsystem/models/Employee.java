package hotelsystem.models;

public class Employee extends Person {

    private String employee_password;
    private String job_title;

    public Employee() {
    }

    public Employee(int id, String name, String phone_number,
            String employee_password, String job_title) {
        super(id, name, phone_number);
        this.employee_password = employee_password;
        this.job_title = job_title;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public String getJob_title() {
        return job_title;
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

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    @Override
    public String getAllInformation() {
        return id + "," + name + "," + phone_number + ","
                + employee_password + "," + job_title;
    }
}

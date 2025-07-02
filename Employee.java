package ems_project;

public class Employee {
    private int id;
    private String name;
    private String email;
    private int age;
    private String department;
    private String jobTitle;
    private String phone;

    //Constructor
    public Employee(int id, String name, String email,  int age, String department, String jobTitle, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.phone = phone;
    }

    // getter and setters

    public int getId() {
        return id;
    }
    public String getName () {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getAge(){
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
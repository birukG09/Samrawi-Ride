package persons;

public class Person {

    private String nationalID;

    protected String name;
    protected int age;
    protected String phone;
    protected String email;

    public Person(String name, int age, String nationalID, String phone, String email) {
        this.name = name;
        this.age = age;
        this.nationalID = nationalID;
        this.phone = phone;
        this.email = email;
    }

    public String getNationalID() { return nationalID; }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public void displayProfile() {
        System.out.println("  Name  : " + name);
        System.out.println("  Age   : " + age);
        System.out.println("  Email : " + email);
        System.out.println("  Phone : [hidden until ride is confirmed]");
    }

    @Override
    public String toString() {
        return name + " (age " + age + ")";
    }
}

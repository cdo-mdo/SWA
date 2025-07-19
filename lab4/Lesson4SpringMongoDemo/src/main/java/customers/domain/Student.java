package customers.domain;

import org.springframework.data.annotation.Id;

public class Student {
    @Id
    private Long id;
    private String name;
    private String phone;
    private String email;

    private Address address;

    public Student(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "name = " +  this.name + ", phone = " + this.phone + ", email = " + this.email;
    }
}

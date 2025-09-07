package domain;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String phone;

    public Student() {}
    public Student(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name = " +  this.name + ", Email = " + this.email + ", Phone = " + this.phone;
    }

    @Embedded
    private Address address;
}

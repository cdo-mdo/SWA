package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address() {
        this.students = new ArrayList<Student>();
    }
    public Address(String street, String city, String state, String zip) {
        this();
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @OneToMany
    List<Student> students;

}

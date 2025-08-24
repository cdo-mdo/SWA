package cs590de.excercise1.service;

import cs590de.excercise1.entity.Student;
import cs590de.excercise1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(int id) {
        return studentRepository.findStudentById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}

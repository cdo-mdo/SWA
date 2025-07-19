package customers.repository;

import customers.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    Student getStudentByName(String name);

    Student getStudentByPhone(String phone);

    Student getStudentByCity(String city);

}

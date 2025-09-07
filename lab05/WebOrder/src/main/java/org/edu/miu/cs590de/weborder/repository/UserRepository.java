package org.edu.miu.cs590de.weborder.repository;

import org.edu.miu.cs590de.weborder.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

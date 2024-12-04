package ua.ilya.s.statistics_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ilya.s.statistics_api.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}

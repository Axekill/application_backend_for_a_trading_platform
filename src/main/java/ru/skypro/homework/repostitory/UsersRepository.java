package ru.skypro.homework.repostitory;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Users;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {

    Optional<Users> findByEmail(String email);





}

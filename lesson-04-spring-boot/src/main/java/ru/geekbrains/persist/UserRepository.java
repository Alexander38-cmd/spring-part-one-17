package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameStartsWith(String prefix);

    @Query("select u " +
            "from User u " +
            "where ( u.username like CONCAT(:prefix, '%') or :prefix is null ) and " +
            "( u.age >= :minAge or :minAge is null ) and " +
            "( u.age <= :maxAge or :maxAge is null )")
    List <User> filterUsers (@Param("prefix") String prefix,
                             @Param("minAge") Integer minAge,
                             @Param("maxAge") Integer maxAge);
}

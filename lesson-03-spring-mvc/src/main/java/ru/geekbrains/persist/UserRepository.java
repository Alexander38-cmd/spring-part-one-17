package ru.geekbrains.persist;

//import org.hibernate.annotations.Table;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class UserRepository {
    //---------//

    //---------//

     //Identity Map
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new User(1L, "user1", 20));
        this.insert(new User(2L, "user2", 21));
        this.insert(new User(3L, "user3", 22));
    }


    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public void insert(User user) {
        long id = identity.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
    }

    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    public void delete(long id) {
        userMap.remove(id);
    }

    public void save(User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            update(user);
        }
    }
}

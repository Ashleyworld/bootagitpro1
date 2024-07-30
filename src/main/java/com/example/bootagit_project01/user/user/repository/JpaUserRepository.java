package com.example.bootagit_project01.user.user.repository;

import com.example.bootagit_project01.user.user.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository{


    private final EntityManager em;

    // JPA 쓰려면 EntityManager 가 있어야한다
    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        // jpa가 insert,query 만들어서 db 에 집어넣고, setid까지 해줌
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userid) {
        User user = em.find(User.class, userid);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String username) {
        List<User> result = em.createQuery("select User from User u where u.name = :name", User.class)
                .setParameter("name", username)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> result = em.createQuery("select User from User u", User.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<User> deleteById(User user) {
        return Optional.empty();
    }
}

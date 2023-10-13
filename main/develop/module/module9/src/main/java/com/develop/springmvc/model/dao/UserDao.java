package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class UserDao implements DAO<User, UUID> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to save user: {}", user);
            session.beginTransaction();
            User savedUser = session.merge(user);
            session.getTransaction().commit();
            user.setId(savedUser.getId());
            log.info("Successfully saved user: {}", user);
        } catch (Exception e) {
            log.error("Error saving user: {}", user, e);
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to find user by id: {}", id);
            Optional<User> result = Optional.ofNullable(session.get(User.class, id));
            log.info("Result for id {}: {}", id, result);
            return result;
        } catch (Exception e) {
            log.error("Error finding user by id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to retrieve all users.");
            List<User> users = session.createQuery("from User", User.class).list();
            log.info("Found {} users.", users.size());
            return users;
        } catch (Exception e) {
            log.error("Error retrieving all users", e);
            return List.of();
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to delete user by id: {}", id);
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                log.info("Successfully deleted user with id: {}", id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error deleting user by id: {}", id, e);
        }
    }
}

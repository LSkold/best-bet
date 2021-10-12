package pl.pg.eti.jee.bestbet.datastore;


import lombok.extern.java.Log;
import pl.pg.eti.jee.bestbet.bet.entity.Bet;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.serialization.CloningUtility;
import pl.pg.eti.jee.bestbet.user.entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For the sake of simplification instead of using real database this example is using an data source object which
 * should be put in servlet context in a single instance. In order to avoid
 * {@link java.util.ConcurrentModificationException} all methods are synchronized. Normally synchronization would be
 * carried on by the database server.
 */
@Log
@ApplicationScoped
public class DataStore {

    /**
     * Set of all available bets.
     */
    private Set<Bet> bets = new HashSet<>();

    /**
     * Set of all coupons.
     */
    private Set<Coupon> coupons = new HashSet<>();

    /**
     * Set of all users.
     */
    private Set<User> users = new HashSet<>();

    /**
     * Seeks for all bets.
     *
     * @return list (can be empty) of all bets
     */
    public synchronized List<User> findAllUsers() {
        return users.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<User> findUser(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized Optional<User> findUser(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Seeks for single user using login and password. Can be use in authentication module.
     *
     * @param login    user's login
     * @param password user's password
     * @return container (can be empty) with user
     */
    public synchronized Optional<User> findUser(String login, String password) throws IllegalArgumentException {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(users -> users.getPassword().equals(password))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new user.
     *
     * @param user new user to be stored
     * @throws IllegalArgumentException if user with provided login already exists
     */
    public synchronized void createUser(User user) throws IllegalArgumentException {
        findUser(user.getLogin()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The user login \"%s\" is not unique", user.getLogin()));
                },
                () -> users.add(user));
    }

    public synchronized void deleteUser(String login) throws IllegalArgumentException {
        findUser(login).ifPresentOrElse(
                original -> users.remove(original),
                () -> {
                    throw new IllegalArgumentException("User with this login doesn't exist.");
                }
        );
    }

    public synchronized void updateUser(User user) throws IllegalArgumentException {
        findUser(user.getLogin()).ifPresentOrElse(
                original -> {
                    users.remove(original);
                    users.add(CloningUtility.clone(user));
                },
                () -> {
                    throw new IllegalArgumentException(
                            "The user with login " + user.getLogin() + " does not exist");
                });
    }
}
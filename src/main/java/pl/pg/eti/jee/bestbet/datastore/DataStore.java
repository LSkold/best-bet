package pl.pg.eti.jee.bestbet.datastore;


import lombok.extern.java.Log;
import pl.pg.eti.jee.bestbet.coupon.entity.bet.entity.Bet;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.serialization.CloningUtility;
import pl.pg.eti.jee.bestbet.user.entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

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
    private Set<CouponType> couponTypes = new HashSet<>();

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

    // coupon

    public synchronized List<Coupon> findAllCouponsByCouponType(CouponType couponType) {
        return coupons.stream()
                .filter(coupon -> coupon.getCouponType().equals(couponType))
                .map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized List<Coupon> findAllCoupons() {
        return coupons.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Coupon> findCoupon(Long id) {
     return coupons.stream()
             .filter(coupon -> coupon.getId().equals(id))
             .findFirst()
             .map(CloningUtility::clone);
    }

    public synchronized void createCoupon(Coupon coupon) throws IllegalArgumentException {
        coupon.setId(findAllCoupons().stream().mapToLong(Coupon::getId).max().orElse(0) + 1);
        coupons.add(coupon);
    }

    public synchronized void updateCoupon(Coupon coupon) throws IllegalArgumentException {
        findCoupon(coupon.getId()).ifPresentOrElse(
                original -> {
                    coupons.remove(original);
                    coupons.add(coupon);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The coupon with id \"%d\" does not exist", coupon.getId()));
                });
    }

    public synchronized void deleteCoupon(Long id) throws IllegalArgumentException {
        findCoupon(id).ifPresentOrElse(
                original -> coupons.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The coupon with id \"%d\" does not exist", id));
                });
    }

    // couponTypes

    public synchronized List<CouponType> findAllCouponTypes() {
        return new ArrayList<>(couponTypes);
    }

    public Optional<CouponType> findCouponType(String name){
        return  couponTypes.stream()
                .filter(couponType -> couponType.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCouponType(CouponType couponType) throws IllegalArgumentException {
        findCouponType(couponType.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The CouponType name \"%s\" is not unique", couponType.getName()));
                },
                () -> couponTypes.add(couponType));
    }

}
package pl.pg.eti.jee.bestbet.coupon.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.pg.eti.jee.bestbet.coupon.entity.bet.entity.Bet;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.user.entity.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Coupon implements Serializable {

    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    /**
     * Coupon multiplier in case of a win
     */
    private double rate;

    /**
     *  How much did user spend on coupon
     */
    private float price;

    /**
     * How much did user win
     */
    private float value;

    /**
     * State of coupon
     */
    private CouponState state;

    /**
     * Coupon's user
     */
    private User user;

    /**
     * Coupon's type
     */
    private CouponType couponType;

}

package pl.pg.eti.jee.bestbet.coupon.entity;


import lombok.*;
import pl.pg.eti.jee.bestbet.bet.entity.Bet;
import pl.pg.eti.jee.bestbet.user.entity.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Coupon {

    enum CouponState {
        WON,
        LOST,
        IN_GAME
    }
    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    private double price;

    private CouponState state;

    private User user;

    private Bet bet;

}

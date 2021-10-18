package pl.pg.eti.jee.bestbet.coupon.model;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.user.entity.User;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CouponModel {
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

    public static Function<Coupon, CouponModel> entityToModelMapper() {
        return coupon -> CouponModel.builder()
                .rate(coupon.getRate())
                .price(coupon.getPrice())
                .value(coupon.getValue())
                .state(coupon.getState())
                .couponType(coupon.getCouponType())
                .build();
    }
}

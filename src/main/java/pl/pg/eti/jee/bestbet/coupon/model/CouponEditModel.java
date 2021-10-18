package pl.pg.eti.jee.bestbet.coupon.model;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CouponEditModel {
    /**
     * Coupon multiplier in case of a win
     */
    private double rate;

    /**
     *  How much did user spend on coupon
     */
    private float price;

    /**
     * State of coupon
     */
    private CouponState state;


    public static Function<Coupon, CouponEditModel> entityToModelMapper() {
        return coupon -> CouponEditModel.builder()
                .rate(coupon.getRate())
                .price(coupon.getPrice())
                .state(coupon.getState())
                .build();
    }

    public static BiFunction<Coupon, CouponEditModel, Coupon> modelToEntityUpdater() {

        return (coupon, request) -> {
            coupon.setRate(request.getRate());
            coupon.setPrice(request.getPrice());
            coupon.setState(request.getState());
            return coupon;
        };
    }

}

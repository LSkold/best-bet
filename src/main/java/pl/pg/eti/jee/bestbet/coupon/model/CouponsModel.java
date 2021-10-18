package pl.pg.eti.jee.bestbet.coupon.model;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.user.entity.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CouponsModel implements Serializable {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Coupon{

        private Long id;

        /**
         *  How much did user spend on coupon
         */
        private float price;

        /**
         * Coupon multiplier in case of a win
         */
        private double rate;

        /**
         * State of coupon
         */
        private CouponState state;

        /**
         * Coupon's type
         */
        private CouponType couponType;
    }

    @Singular
    private List<Coupon> coupons;

    public static Function<Collection<pl.pg.eti.jee.bestbet.coupon.entity.Coupon>, CouponsModel> entityToModelMapper() {
        return coupons -> {
            CouponsModel.CouponsModelBuilder model = CouponsModel.builder();
            coupons.stream()
                    .map(coupon -> Coupon.builder()
                            .id(coupon.getId())
                            .rate(coupon.getRate())
                            .price(coupon.getPrice())
                            .state(coupon.getState())
                            .couponType(coupon.getCouponType())
                            .build())
                    .forEach(model::coupon);
            return model.build();
        };
    }
}

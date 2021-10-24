package pl.pg.eti.jee.bestbet.coupon.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;

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
public class GetCouponsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Coupon {

        private Long id;

        private double rate;

        private CouponState state;

    }

    @Singular
    private List<Coupon> coupons;

    public static Function<Collection<pl.pg.eti.jee.bestbet.coupon.entity.Coupon>, GetCouponsResponse> entityToDtoMapper() {
        return coupons -> {
            GetCouponsResponseBuilder response = GetCouponsResponse.builder();
            coupons.stream()
                    .map(coupon -> Coupon.builder()
                    .id(coupon.getId())
                    .rate(coupon.getRate())
                    .state(coupon.getState())
                    .build())
                    .forEach(response::coupon);
            return response.build();
        };
    }

}

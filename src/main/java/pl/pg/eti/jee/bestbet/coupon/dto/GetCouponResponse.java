package pl.pg.eti.jee.bestbet.coupon.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCouponResponse {

    private Long id;

    private double rate;

    private float price;

    private float value;

    private CouponState state;

    private CouponType couponType;

    public static Function<Coupon, GetCouponResponse> entityToDtoMapper(){
        return coupon -> GetCouponResponse.builder()
                .id(coupon.getId())
                .rate(coupon.getRate())
                .price(coupon.getPrice())
                .value(coupon.getValue())
                .state(coupon.getState())
                .couponType(coupon.getCouponType())
                .build();
    }
}

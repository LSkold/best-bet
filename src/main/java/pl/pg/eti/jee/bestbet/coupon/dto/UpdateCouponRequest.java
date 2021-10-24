package pl.pg.eti.jee.bestbet.coupon.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.dto.UpdateCouponTypeRequest;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCouponRequest {

    private double rate;

    private float price;

    private CouponState state;

    public static BiFunction<Coupon, UpdateCouponRequest, Coupon> dtoToEntityUpdater() {
        return (coupon, request) -> {
            coupon.setRate(request.getRate());
            coupon.setPrice(request.getPrice());
            coupon.setState(request.getState());
            return coupon;
        };
    }
}

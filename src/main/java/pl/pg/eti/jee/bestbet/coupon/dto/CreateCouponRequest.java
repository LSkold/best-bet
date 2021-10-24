package pl.pg.eti.jee.bestbet.coupon.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupontype.dto.CreateCouponTypeRequest;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCouponRequest {
    private double rate;

    private float price;

    private CouponState state;

    public static Function<CreateCouponRequest, Coupon> dtoToEntityMapper() {
        return createCouponRequest -> Coupon.builder()
                .rate(createCouponRequest.getRate())
                .price(createCouponRequest.getPrice())
                .state(createCouponRequest.getState())
                .build();
    }
}

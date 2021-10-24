package pl.pg.eti.jee.bestbet.coupontype.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupon.dto.CreateCouponRequest;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCouponTypeRequest {

    private String name;

    private String description;

    private boolean isLive;

    public static Function<CreateCouponTypeRequest, CouponType> dtoToEntityMapper() {
        return createCouponTypeRequest -> CouponType.builder()
                .name(createCouponTypeRequest.getName())
                .description(createCouponTypeRequest.getDescription())
                .isLive(createCouponTypeRequest.isLive())
                .build();
    }
}

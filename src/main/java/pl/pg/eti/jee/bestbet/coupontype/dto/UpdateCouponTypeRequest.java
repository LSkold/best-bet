package pl.pg.eti.jee.bestbet.coupontype.dto;

import lombok.*;
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
public class UpdateCouponTypeRequest {

    private String name;

    private String description;

    private boolean isLive;

    public static BiFunction<CouponType, UpdateCouponTypeRequest, CouponType> dtoToEntityUpdater() {
        return (couponType, request) -> {
            couponType.setName(request.getName());
            couponType.setDescription(request.getDescription());
            couponType.setLive(request.isLive());
            return couponType;
        };
    }
}

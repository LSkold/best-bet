package pl.pg.eti.jee.bestbet.coupontype.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCouponTypeResponse {
    private String name;

    private String description;

    private boolean isLive;

    public static Function<CouponType, GetCouponTypeResponse> entityToDtoMapper() {
        return couponType -> {
            GetCouponTypeResponse.GetCouponTypeResponseBuilder response = GetCouponTypeResponse.builder();
            response.name(couponType.getName());
            response.description(couponType.getDescription());
            response.isLive(couponType.isLive());
            return response.build();
        };
    }
}

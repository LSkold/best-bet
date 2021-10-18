package pl.pg.eti.jee.bestbet.coupontype.model;

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
public class CouponTypeModel {
    private String name;

    private String description;

    private boolean isLive;

    public static Function<CouponType, CouponTypeModel> entityToModelMapper(){
        return couponType -> CouponTypeModel.builder()
                .name(couponType.getName())
                .description(couponType.getDescription())
                .isLive(couponType.isLive())
                .build();
    }
}

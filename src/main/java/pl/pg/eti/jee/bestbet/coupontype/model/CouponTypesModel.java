package pl.pg.eti.jee.bestbet.coupontype.model;

import lombok.*;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

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
public class CouponTypesModel implements Serializable {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class CouponType{
        private String name;

        private String description;

        private boolean isLive;
    }

    @Singular
    private List<CouponType> couponTypes;

    public static Function<Collection<pl.pg.eti.jee.bestbet.coupontype.entity.CouponType>, CouponTypesModel> entityToModelMapper() {
        return couponTypes -> {
            CouponTypesModel.CouponTypesModelBuilder model = CouponTypesModel.builder();
            couponTypes.stream()
                    .map(couponType -> CouponType.builder()
                    .name(couponType.getName())
                    .description(couponType.getDescription())
                    .isLive(couponType.isLive())
                    .build())
                    .forEach(model::couponType);
            return model.build();
        };
    }
}

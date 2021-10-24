package pl.pg.eti.jee.bestbet.coupontype.dto;

import lombok.*;


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
public class GetCouponTypesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class CouponType {
        private String name;

        private String description;
    }

    @Singular
    private List<CouponType> couponTypes;

    public static Function<Collection<pl.pg.eti.jee.bestbet.coupontype.entity.CouponType>,GetCouponTypesResponse> entityToDtoMapper() {
        return couponTypes -> {
            GetCouponTypesResponseBuilder response = GetCouponTypesResponse.builder();
            couponTypes.stream()
                    .map(couponType -> CouponType.builder()
                    .name(couponType.getName())
                    .description(couponType.getDescription())
                    .build())
                    .forEach(response::couponType);
            return response.build();
        };
    }
}

package pl.pg.eti.jee.bestbet.coupontype.entity;


import lombok.*;
import pl.pg.eti.jee.bestbet.coupontype.coupontypename.CouponTypeName;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CouponType implements Serializable {

    private String name;

    private String description;

    private boolean isLive;
}

package pl.pg.eti.jee.bestbet.configuration;

import lombok.SneakyThrows;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;
import pl.pg.eti.jee.bestbet.cryption.Sha256Utility;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.entity.UserRole;
import pl.pg.eti.jee.bestbet.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.InputStream;

@ApplicationScoped
public class InitializedData {
    /**
     * Service for users operations.
     */
    private final UserService userService;

    private final CouponTypeService couponTypeService;

    private final CouponService couponService;

    @Inject
    public InitializedData(UserService userService, CouponTypeService couponTypeService, CouponService couponService) {
        this.userService = userService;
        this.couponTypeService = couponTypeService;
        this.couponService = couponService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private synchronized void init() {
        User admin = User.builder()
                .login("admin")
                .role(UserRole.ADMIN)
                .age(22)
                .password(Sha256Utility.hash("adminadmin"))
                .build();

        User luke = User.builder()
                .login("luke")
                .role(UserRole.USER)
                .age(23)
                .password(Sha256Utility.hash("useruser"))
                .build();

        User pablo = User.builder()
                .login("pablo")
                .role(UserRole.USER)
                .age(18)
                .password(Sha256Utility.hash("useruser"))
                .build();
        User lauren = User.builder()
                .login("lauren")
                .role(UserRole.USER)
                .age(24)
                .password(Sha256Utility.hash("useruser"))
                .build();

        userService.create(admin);
        userService.create(luke);
        userService.create(pablo);
        userService.create(lauren);

        CouponType ako = CouponType.builder()
                .name("AKO")
                .description("The bet on the outcome of two or more sport events")
                .isLive(false)
                .build();

        CouponType akoLive = CouponType.builder()
                .name("AKO-LIVE")
                .description("The bet on the outcome of two or more sport events")
                .isLive(true)
                .build();

        couponTypeService.create(ako);
        couponTypeService.create(akoLive);

        Coupon coupon1 = Coupon.builder()
                .price(5.00f)
                .rate(1.44444)
                .state(CouponState.WON)
                .value(5.00f)
                .couponType(ako)
                .build();

        couponService.create(coupon1);

    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}

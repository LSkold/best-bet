package pl.pg.eti.jee.bestbet.coupon.service;


import lombok.NoArgsConstructor;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.repository.CouponRepository;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.user.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor//Empty constructor is required for creating proxy while CDI injection.
public class CouponService {
    private CouponRepository couponRepository;

    private UserRepository userRepository;

    @Inject
    public CouponService(CouponRepository couponRepository, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
    }

    public Optional<Coupon> find(Long id) { return couponRepository.find(id); }

    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    public List<Coupon> findAllByCouponTypeName(CouponType couponType) {
        return couponRepository.findAllByCouponType(couponType);
    }

    public void create(Coupon coupon){
        couponRepository.create(coupon);
    }
    public void update(Coupon coupon){
        couponRepository.update(coupon);
    }
    public void delete(Long id){
        couponRepository.delete(couponRepository.find(id).orElseThrow());
    }

}

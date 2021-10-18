package pl.pg.eti.jee.bestbet.coupontype.service;

import lombok.NoArgsConstructor;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.repository.CouponTypeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor//Empty constructor is required for creating proxy while CDI injection.
public class CouponTypeService {

    private CouponTypeRepository couponTypeRepository;

    @Inject
    public CouponTypeService(CouponTypeRepository repository){
        this.couponTypeRepository = repository;
    }

    public Optional<CouponType> find(String name){
        return couponTypeRepository.find(name);
    }

    public List<CouponType> findAll() {
        return couponTypeRepository.findAll();
    }

    public void create(CouponType couponType){
        couponTypeRepository.create(couponType);
    }
}

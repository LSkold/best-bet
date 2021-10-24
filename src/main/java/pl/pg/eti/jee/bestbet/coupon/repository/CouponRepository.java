package pl.pg.eti.jee.bestbet.coupon.repository;

import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.datastore.DataStore;
import pl.pg.eti.jee.bestbet.repository.Repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class CouponRepository implements Repository<Coupon, Long> {

    private DataStore store;

    @Inject
    public CouponRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Coupon> find(Long id) {
        return store.findCoupon(id);
    }

    @Override
    public List<Coupon> findAll() {
        return store.findAllCoupons();
    }

    @Override
    public void create(Coupon entity) {
        store.createCoupon(entity);
    }

    @Override
    public void delete(Coupon entity) {
        store.deleteCoupon(entity.getId());
    }

    @Override
    public void update(Coupon entity) {
        store.updateCoupon(entity);
    }

    public List<Coupon> findAllByCouponType(CouponType couponType) {
        return store.findAllCouponsByCouponType(couponType);
    }

    public Optional<Coupon> findByCouponTypeAndId(CouponType couponType, Long id) {
        return store.findByCouponTypeAndId(couponType,id);
    }

    public void createWithSpecifiedCouponType(Coupon coupon, CouponType couponType) {
        store.createCoupon(coupon,couponType);
    }
}

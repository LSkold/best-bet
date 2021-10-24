package pl.pg.eti.jee.bestbet.coupon.view;


import pl.pg.eti.jee.bestbet.coupon.model.CouponsModel;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.model.CouponTypesModel;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

/**
 * View bean for rendering list of coupons.
 */
@RequestScoped
@Named
public class CouponList implements Serializable {
    private final CouponService couponService;
    private final CouponTypeService couponTypeService;

    private CouponsModel coupons;

    @Inject
    public CouponList(CouponService couponService, CouponTypeService couponTypeService) {
        this.couponService = couponService;
        this.couponTypeService = couponTypeService;
    }

    public CouponsModel getCoupons() {
        if(coupons == null) coupons = CouponsModel.entityToModelMapper().apply(couponService.findAll());
        return coupons;
    }

    public CouponsModel getCouponsByCouponType(String couponType) {
        Optional<CouponType> couponTypeToFind = couponTypeService.find(couponType);
        if(couponTypeToFind.isPresent()){
            if(coupons == null) coupons = CouponsModel.entityToModelMapper().apply(couponService.findAllByCouponType(couponTypeToFind.get()));
            return coupons;
        }
        return null;
    }

    public String deleteAction(CouponsModel.Coupon coupon){
        couponService.delete(coupon.getId());
        return "coupon_type_view#name=?faces-redirect=true";
    }


}

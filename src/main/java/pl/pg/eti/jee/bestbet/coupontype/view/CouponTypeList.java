package pl.pg.eti.jee.bestbet.coupontype.view;

import pl.pg.eti.jee.bestbet.coupon.model.CouponsModel;
import pl.pg.eti.jee.bestbet.coupontype.model.CouponTypesModel;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class CouponTypeList implements Serializable {
    private final CouponTypeService couponTypeService;

    private CouponTypesModel couponTypes;

    @Inject
    public CouponTypeList(CouponTypeService couponTypeService) {
        this.couponTypeService = couponTypeService;
    }

    public CouponTypesModel getCouponTypes(){
        if(couponTypes == null) couponTypes = CouponTypesModel.entityToModelMapper().apply(couponTypeService.findAll());
        return couponTypes;
    }


}

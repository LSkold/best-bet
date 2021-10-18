package pl.pg.eti.jee.bestbet.coupon.view;

import lombok.Getter;
import lombok.Setter;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupon.model.CouponEditModel;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@ViewScoped
@Named
public class CouponCreate implements Serializable {
    private final CouponService couponService;
    private final CouponTypeService couponTypeService;

    @Setter
    @Getter
    private double rate;

    @Setter
    @Getter
    private float price;

    @Setter
    @Getter
    private float value;

    @Setter
    @Getter
    private CouponState state;

    @Setter
    @Getter
    private CouponType couponType;

    public List<CouponType> couponTypes() {
        return couponTypeService.findAll();
    }

    public CouponState[] getCouponStates() {
        return CouponState.values();
    }

    @Inject
    public CouponCreate(CouponService couponService, CouponTypeService couponTypeService) {
        this.couponService = couponService;
        this.couponTypeService = couponTypeService;
    }

    public String createAction(){
        Coupon tempCoupon = Coupon.builder()
                .price(getPrice())
                .rate(getRate())
                .state(getState())
                .couponType(couponTypeService.find("AKO").get())
                .value(getValue())
                .build();
        couponService.create(tempCoupon);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }



}

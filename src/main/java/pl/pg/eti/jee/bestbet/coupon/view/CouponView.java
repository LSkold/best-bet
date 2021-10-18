package pl.pg.eti.jee.bestbet.coupon.view;

import lombok.Getter;
import lombok.Setter;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.model.CouponModel;
import pl.pg.eti.jee.bestbet.coupon.model.CouponsModel;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@RequestScoped
@Named
public class CouponView implements Serializable {

    private final CouponService couponService;

    @Setter
    @Getter
    private Long id;

    @Getter
    private CouponModel coupon;

    @Inject
    public CouponView(CouponService couponService){
        this.couponService = couponService;
    }

    public void init() throws IOException {
        Optional<Coupon> coupon = couponService.find(id);
        if(coupon.isPresent()){
            this.coupon = CouponModel.entityToModelMapper().apply(coupon.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Coupon not found");
        }
    }
}

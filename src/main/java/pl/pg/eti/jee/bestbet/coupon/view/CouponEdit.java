package pl.pg.eti.jee.bestbet.coupon.view;

import lombok.Getter;
import lombok.Setter;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.entity.CouponState;
import pl.pg.eti.jee.bestbet.coupon.model.CouponEditModel;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@ViewScoped
@Named
public class CouponEdit implements Serializable {

    private final CouponService couponService;

    @Setter
    @Getter
    private Long id;

    @Getter
    private CouponEditModel coupon;

    public CouponState[] getCouponStates() {
        return CouponState.values();
    }

    @Inject
    public CouponEdit(CouponService couponService) {
        this.couponService = couponService;
    }

    public void init() throws IOException {
        Optional<Coupon> coupon = couponService.find(id);
        if(coupon.isPresent()){
            this.coupon = CouponEditModel.entityToModelMapper().apply(coupon.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Coupon not found");
        }
    }

    public String saveAction() {
        couponService.update(CouponEditModel.modelToEntityUpdater().apply(couponService.find(id).orElseThrow(),coupon));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}

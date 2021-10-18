package pl.pg.eti.jee.bestbet.coupontype.view;

import lombok.Getter;
import lombok.Setter;
import pl.pg.eti.jee.bestbet.coupon.model.CouponsModel;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.model.CouponTypeModel;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.enterprise.context.RequestScoped;
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
public class CouponTypeView implements Serializable {

    private  final CouponTypeService couponTypeService;
    private final CouponService couponService;

    @Setter
    @Getter
    private String name;

    @Getter
    private CouponTypeModel couponType;

    @Inject
    public CouponTypeView(CouponTypeService couponTypeService, CouponService couponService) {
        this.couponTypeService = couponTypeService;
        this.couponService = couponService;
    }

    public void init() throws IOException {
        Optional<CouponType> couponType = couponTypeService.find(name);
        if(couponType.isPresent()){
            this.couponType = CouponTypeModel.entityToModelMapper().apply(couponType.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, String.format("CouponType not found, name: \"%s\"",name));
        }
    }

    public String deleteAction(CouponsModel.Coupon coupon) {
        couponService.delete(coupon.getId());
        return "coupon_type_view?name="+name+"&faces-redirect=true";
    }

}
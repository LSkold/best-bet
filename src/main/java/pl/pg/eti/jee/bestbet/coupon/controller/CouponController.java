package pl.pg.eti.jee.bestbet.coupon.controller;

import pl.pg.eti.jee.bestbet.coupon.dto.GetCouponResponse;
import pl.pg.eti.jee.bestbet.coupon.dto.GetCouponsResponse;
import pl.pg.eti.jee.bestbet.coupon.dto.UpdateCouponRequest;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;

import javax.inject.Inject;
import javax.swing.text.html.Option;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/coupons")
public class CouponController {

    private CouponService couponService;

    public CouponController(){
    }

    @Inject
    public void setCouponService(CouponService service){this.couponService = service;}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupons(){
        return Response.ok(GetCouponsResponse.entityToDtoMapper().apply(couponService.findAll())).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupon(@PathParam("id") Long id){
        Optional<Coupon> coupon = couponService.find(id);
        if(coupon.isPresent()) {
            return Response.ok(GetCouponResponse.entityToDtoMapper().apply(coupon.get())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCoupon(@PathParam("id") Long id, UpdateCouponRequest request){
        Optional<Coupon> coupon = couponService.find(id);
        if(coupon.isPresent()){
            UpdateCouponRequest.dtoToEntityUpdater().apply(coupon.get(),request);
            couponService.update(coupon.get());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCouponType(@PathParam("id") Long id) {
        Optional<Coupon> coupon = couponService.find(id);
        if(coupon.isPresent()){
            couponService.delete(coupon.get().getId());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

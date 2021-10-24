package pl.pg.eti.jee.bestbet.coupon.controller;

import lombok.Getter;
import pl.pg.eti.jee.bestbet.coupon.dto.CreateCouponRequest;
import pl.pg.eti.jee.bestbet.coupon.dto.GetCouponResponse;
import pl.pg.eti.jee.bestbet.coupon.dto.GetCouponsResponse;
import pl.pg.eti.jee.bestbet.coupon.dto.UpdateCouponRequest;
import pl.pg.eti.jee.bestbet.coupon.entity.Coupon;
import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.inject.Inject;
import javax.swing.text.html.Option;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;

@Path("")
public class CouponTypeCouponController {

    private CouponService couponService;

    private CouponTypeService couponTypeService;

    public CouponTypeCouponController(){
    }

    @Inject
    public void setCouponService(CouponService couponService){this.couponService = couponService;}

    @Inject
    public void setCouponTypeService(CouponTypeService couponTypeService){this.couponTypeService = couponTypeService;}

    @GET
    @Path("/coupontypes/{name}/coupons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupons(@PathParam("name") String couponTypeName){
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            return Response.ok(GetCouponsResponse.entityToDtoMapper()
            .apply(couponService.findAllByCouponType(couponType.get()))).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/coupontypes/{name}/coupons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupon(@PathParam("name") String couponTypeName, @PathParam("id") Long id){
        Optional<CouponType> couponType =couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            Optional<Coupon> coupon = couponService.findByCouponTypeAndId(couponType.get(),id);
            if(coupon.isPresent()){
                return Response.ok(GetCouponResponse.entityToDtoMapper().apply(coupon.get())).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/coupontypes/{name}/coupons")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCoupon(@PathParam("name") String couponTypeName, CreateCouponRequest request){
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            Coupon coupon = CreateCouponRequest
                    .dtoToEntityMapper().apply(request);
            couponService.createWithCouponType(coupon,couponType.get());
            return Response.created(UriBuilder.fromMethod(CouponTypeCouponController.class, "getCoupon").build(couponType.get().getName(),coupon.getId())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/coupontypes/{name}/coupons/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCoupon(@PathParam("name") String couponTypeName, @PathParam("id") Long id, UpdateCouponRequest request){
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            Optional<Coupon> coupon = couponService.findByCouponTypeAndId(couponType.get(),id);
            if(coupon.isPresent()){
                UpdateCouponRequest.dtoToEntityUpdater().apply(coupon.get(),request);
                couponService.update(coupon.get());
                return Response.noContent().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/coupontypes/{name}/coupons/{id}")
    public Response deleteCouponType(@PathParam("name") String couponTypeName, @PathParam("id") Long id) {
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()) {
            Optional<Coupon> coupon = couponService.findByCouponTypeAndId(couponType.get(),id);
            if(coupon.isPresent()){
                couponService.delete(coupon.get().getId());
                return Response.noContent().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

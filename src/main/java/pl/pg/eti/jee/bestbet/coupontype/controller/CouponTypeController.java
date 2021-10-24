package pl.pg.eti.jee.bestbet.coupontype.controller;

import pl.pg.eti.jee.bestbet.coupon.service.CouponService;
import pl.pg.eti.jee.bestbet.coupontype.dto.CreateCouponTypeRequest;
import pl.pg.eti.jee.bestbet.coupontype.dto.GetCouponTypeResponse;
import pl.pg.eti.jee.bestbet.coupontype.dto.GetCouponTypesResponse;
import pl.pg.eti.jee.bestbet.coupontype.dto.UpdateCouponTypeRequest;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.coupontype.service.CouponTypeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;

/**
 * REST controller for {@link pl.pg.eti.jee.bestbet.coupontype.entity.CouponType} entity.
 */
@Path("")
public class CouponTypeController {

    private CouponTypeService couponTypeService;
    private CouponService couponService;

    public CouponTypeController() {
    }

    @Inject
    public void setCouponTypeService(CouponTypeService service){this.couponTypeService = service;}

    @Inject
    public void setCouponService(CouponService service){this.couponService = service;}

    @GET
    @Path("/coupontypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCouponTypes() {
        return Response.ok(GetCouponTypesResponse.entityToDtoMapper().apply(couponTypeService.findAll())).build();
    }

    @GET
    @Path("/coupontypes/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCouponType(@PathParam("name") String couponTypeName) {
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            return Response.ok(GetCouponTypeResponse.entityToDtoMapper().apply(couponType.get())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/coupontypes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCouponType(CreateCouponTypeRequest request) {
        CouponType couponType = CreateCouponTypeRequest
                .dtoToEntityMapper().apply(request);
        couponTypeService.create(couponType);
        return Response.created(UriBuilder.fromMethod(CouponTypeController.class, "getCouponType").build(couponType.getName())).build();
    }

    @PUT
    @Path("/coupontypes/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCouponType(@PathParam("name") String couponTypeName , UpdateCouponTypeRequest request) {
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            UpdateCouponTypeRequest.dtoToEntityUpdater().apply(couponType.get(), request);

            couponTypeService.update(couponType.get());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/coupontypes/{name}")
    public Response deleteCouponType(@PathParam("name") String couponTypeName) {
        Optional<CouponType> couponType = couponTypeService.find(couponTypeName);
        if(couponType.isPresent()){
            couponService.findAllByCouponType(couponType.get()).forEach(
                    coupon -> couponService.delete(coupon.getId())
            );
            couponTypeService.delete(couponType.get());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

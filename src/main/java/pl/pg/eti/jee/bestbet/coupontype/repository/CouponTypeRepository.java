package pl.pg.eti.jee.bestbet.coupontype.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.pg.eti.jee.bestbet.coupontype.entity.CouponType;
import pl.pg.eti.jee.bestbet.datastore.DataStore;
import pl.pg.eti.jee.bestbet.repository.Repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class CouponTypeRepository implements Repository<CouponType, String> {
    private DataStore store;

    @Inject
    public CouponTypeRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<CouponType> find(String id) {
        return store.findCouponType(id);
    }

    @Override
    public List<CouponType> findAll() {
        return store.findAllCouponTypes();
    }

    @Override
    public void create(CouponType entity) {
        store.createCouponType(entity);
    }

    @Override
    public void delete(CouponType entity) {
    }

    @Override
    public void update(CouponType entity) {
    }
}

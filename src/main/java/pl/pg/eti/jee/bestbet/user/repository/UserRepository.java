package pl.pg.eti.jee.bestbet.user.repository;

import pl.pg.eti.jee.bestbet.datastore.DataStore;
import pl.pg.eti.jee.bestbet.repository.Repository;
import pl.pg.eti.jee.bestbet.serialization.CloningUtility;
import pl.pg.eti.jee.bestbet.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class UserRepository implements Repository<User, Long> {

    private DataStore store;

    @Inject
    public UserRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<User> find(Long id) {
        return store.findUser(id);
    }

    @Override
    public List<User> findAll() {
        return store.findAllUsers();
    }

    @Override
    public void create(User entity) {
        store.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void update(User entity) {
        store.updateUser(entity);
    }

    public Optional<User> findByLogin(String login) {
        return store.findUser(login);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findUser(login,password);
    }

    public void deleteAvatar(User user) {
        CloningUtility.clone(user);
        user.setAvatar(null);
        store.updateUser(user);

    }
}

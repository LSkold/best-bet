package pl.pg.eti.jee.bestbet.user.service;

import lombok.NoArgsConstructor;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor//Empty constructor is required for creating proxy while CDI injection.
public class UserService {
    private UserRepository repository;

    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id user's id
     * @return container with user
     */
    public Optional<User> find(Long id) {
        return repository.find(id);
    }

    /**
     * @param login user's id
     * @return container with user
     */
    public Optional<User> find(String login) {
        return repository.findByLogin(login);
    }

    /**
     * Seeks for single user using login and password. Can be use in authentication module.
     *
     * @param login    user's login
     * @param password user's password (hash)
     * @return container (can be empty) with user
     */
    public Optional<User> find(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new user in the storage.
     *
     * @param user new user
     */
    public void create(User user) {
        repository.create(user);
    }

    public void deleteAvatar(String login) {
        repository.deleteAvatar(repository.findByLogin(login).orElseThrow());
    }

    public void update(User user) {
        repository.update(user);
    }

    public void updateAvatar(String login, InputStream is) {
        repository.findByLogin(login).ifPresent(user -> {
            try {
                byte[] avatar = is.readAllBytes();
                user.setAvatar(avatar);
                repository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }
}

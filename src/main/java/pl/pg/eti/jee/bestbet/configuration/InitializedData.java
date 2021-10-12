package pl.pg.eti.jee.bestbet.configuration;

import lombok.SneakyThrows;
import pl.pg.eti.jee.bestbet.cryption.Sha256Utility;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.entity.UserRole;
import pl.pg.eti.jee.bestbet.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.InputStream;

@ApplicationScoped
public class InitializedData {
    /**
     * Service for users operations.
     */
    private final UserService userService;

    @Inject
    public InitializedData(UserService userService) {
        this.userService = userService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private synchronized void init() {
        User admin = User.builder()
                .login("admin")
                .role(UserRole.ADMIN)
                .age(22)
                .password(Sha256Utility.hash("adminadmin"))
                .build();

        User luke = User.builder()
                .login("luke")
                .role(UserRole.USER)
                .age(23)
                .password(Sha256Utility.hash("useruser"))
                .build();

        User pablo = User.builder()
                .login("pablo")
                .role(UserRole.USER)
                .age(18)
                .password(Sha256Utility.hash("useruser"))
                .build();
        User lauren = User.builder()
                .login("lauren")
                .role(UserRole.USER)
                .age(24)
                .password(Sha256Utility.hash("useruser"))
                .build();

        userService.create(admin);
        userService.create(luke);
        userService.create(pablo);
        userService.create(lauren);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}

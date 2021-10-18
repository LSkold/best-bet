package pl.pg.eti.jee.bestbet.user.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.entity.UserRole;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    /**
     * User's username (login)
     */
    private String login;

    /**
     * User's ID
     */
    private Long id;

    /**
     * User's age
     */
    private Integer age;

    /**
     * User's role
     */
    private UserRole role;


    public static Function<User, GetUserResponse> entityToDtoMapper() {
        return user -> GetUserResponse.builder()
                .login(user.getLogin())
                .age(user.getAge())
                .role(user.getRole())
                .build();
    }
}

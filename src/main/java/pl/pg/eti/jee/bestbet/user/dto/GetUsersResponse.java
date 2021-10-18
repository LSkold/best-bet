package pl.pg.eti.jee.bestbet.user.dto;

import lombok.*;
import pl.pg.eti.jee.bestbet.user.entity.User;
import pl.pg.eti.jee.bestbet.user.entity.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUsersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User{
        /**
         * User's username (login)
         */
        private String login;

        /**
         * User's age
         */
        private Integer age;

        /**
         * User's role
         */
        private UserRole role;
    }

    /**
     * List of all user names.
     */
    @Singular
    private List<String> users;

//    public static Function<Collection<pl.pg.eti.jee.bestbet.user.entity.User>, GetUsersResponse> entityToModelMapper() {
//        return users -> {
//            GetUsersResponse.GetUsersResponseBuilder responseBuilder = GetUsersResponse.builder();
//            users.stream()
//                    .map(user -> GetUsersResponse.User.builder()
//                            .login(user.getLogin())
//                            .age(user.getAge())
//                            .role(user.getRole())
//                            .build())
//                    .forEach(responseBuilder::user);
//            return responseBuilder.build();
//        };
//    }
}

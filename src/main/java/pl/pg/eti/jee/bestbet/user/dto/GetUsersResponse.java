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

    /**
     * List of all user names.
     */
    @Singular
    private List<String> users;
}

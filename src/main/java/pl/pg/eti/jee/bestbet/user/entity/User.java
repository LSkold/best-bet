package pl.pg.eti.jee.bestbet.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    private String login;

    private String password;

    private Integer age;

    private UserRole role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;


}

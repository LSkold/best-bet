package pl.pg.eti.jee.bestbet.bet.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Bet implements Serializable {

    @GeneratedValue(strategy= GenerationType.AUTO) Long id;

    private double price;

    private String description;

    private Date date;

}

package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
// usunieta tabela
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class healthmetrics {

    @Nullable
    private Long id;

    private User user;

    private Date date;

    private double weight;

    private double height;

    private double heartRate;

    public healthmetrics(
            final User user,
            final Date date,
            final double weight,
            final double height,
            final double heartRate) {

        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

}


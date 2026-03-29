package pl.wsb.fitnesstracker.ActivityType.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Activity_Type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ActivityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @JoinColumn(name = "activityType", nullable = false)
    private String activityType;

    public ActivityType(
            final String activityType) {
        this.activityType = activityType;
    }

}


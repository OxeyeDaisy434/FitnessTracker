package pl.wsb.fitnesstracker.achievement;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "Achivement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Achivement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate earnedAt;

    @Column(nullable = false)
    private String name;


    public Achivement(User user, LocalDate earnedAt, String name) {
        this.user = user;
        this.earnedAt = earnedAt;
        this.name = name;
    }
}

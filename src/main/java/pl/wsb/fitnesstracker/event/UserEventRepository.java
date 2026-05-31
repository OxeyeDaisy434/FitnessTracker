package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    void deleteAllByUser(User user);
}

package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserOldAgeDto;

@Component
class UserMapper {

    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthday(), user.getEmail());
   }

    UserBasicDto toUserBasicDto(User user) {
        return new UserBasicDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserOldAgeDto toUserOldAgeDto(User user) {
        return new UserOldAgeDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
package pl.wsb.fitnesstracker.user.internal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    UserController(final UserService userService,
                   final UserProvider userProvider,
                   final UserMapper userMapper) {
        this.userService = userService;
        this.userProvider = userProvider;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        final User createdUser = userService.createUser(new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()));

        return userMapper.toUserDto(createdUser);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }


    @GetMapping("/basic")
    public List<UserBasicDto> getUsersBasic() {
        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserBasicDto)
                .toList();
    }


    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") final Long userId) {
        return this.userProvider.getUser(userId)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }


    @GetMapping("/email")
    public List<UserDto> getUsersByEmail(@RequestParam(name = "email") final String email) {
        return this.userProvider.getUsersByEmail(email).stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate time) {
        return this.userProvider.getUsersOlderThan(time).stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") final Long userId,
                              @RequestBody UserDto userDto) {
        final User updatedUser = userService.updateUser(userId, new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()));

        return userMapper.toUserDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
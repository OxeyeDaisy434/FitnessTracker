package pl.wsb.fitnesstracker.user.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.event.UserEventRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService, UserProvider {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserEventRepository userEventRepository;

    UserServiceImpl(final UserRepository userRepository,
                    final UserEventRepository userEventRepository) {
        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;
    }

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final Long userId, final User user) {
        final User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByEmail(final String email) {
        return userRepository.findAllByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> getUsersOlderThan(final LocalDate date) {
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthday().isBefore(date))
                .toList();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userEventRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }

}
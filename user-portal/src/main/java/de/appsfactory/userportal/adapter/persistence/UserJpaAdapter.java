package de.appsfactory.userportal.adapter.persistence;

import de.appsfactory.userportal.error.exception.EntityNotFoundException;
import de.appsfactory.userportal.user.User;
import de.appsfactory.userportal.user.port.out.CreateOrUpdateUserPort;
import de.appsfactory.userportal.user.port.out.DeleteUserPort;
import de.appsfactory.userportal.user.port.out.LoadUserPort;
import de.appsfactory.userportal.util.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@RequiredArgsConstructor
@Repository
@Slf4j
class UserJpaAdapter implements LoadUserPort, CreateOrUpdateUserPort, DeleteUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User loadUser(User.UserId userId) {
        UserJpaEntity userJpaEntity = userRepository.findById(userId.getValue())
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", Objects.toString(userId.getValue())));
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User create(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        UserJpaEntity savedUser = userRepository.saveAndFlush(userJpaEntity);
        return userMapper.mapToDomainEntity(savedUser);
    }

    @Override
    public User update(User user) {
        UserJpaEntity newUserJpaEntity = userMapper.mapToJpaEntity(user);
        UserJpaEntity existingUserJpaEntity = userRepository.findById(user.getId().get().getValue()).get();
        CopyUtils.copyNonNullProperties(newUserJpaEntity, existingUserJpaEntity);
        UserJpaEntity savedUser = userRepository.saveAndFlush(existingUserJpaEntity);
        return userMapper.mapToDomainEntity(savedUser);
    }

    @Override
    public Boolean userExists(User.UserId userId) {
        return userRepository.existsById(userId.getValue());
    }

    @Override
    public void deleteUser(User.UserId userId) {
        userRepository.deleteById(userId.getValue());
    }
}

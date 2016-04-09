package com.tvajjala.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvajjala.exception.ResourceNotFoundException;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.domain.UserEntity;
import com.tvajjala.persistence.repository.AuthorityRepository;
import com.tvajjala.persistence.repository.UserRepository;
import com.tvajjala.persistence.vo.Authority;
import com.tvajjala.persistence.vo.User;
import com.tvajjala.web.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /** The authority repository. */
    @Autowired
    private AuthorityRepository authorityRepository;

    /** The salt source. */
    @Autowired
    private SaltSource saltSource;

    /** The password encoder. */
    @Autowired
    private BasePasswordEncoder passwordEncoder;

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void deleteUser(final Long id) throws ResourceNotFoundException {
        final UserEntity userEntity = userRepository.findOne(id);

        if (userEntity == null) {
            logger.info("There is not record with this id ");
            throw new ResourceNotFoundException("Record not exists with id :: " + id);
        }

        userEntity.getAuthorities().clear();
        userRepository.delete(userEntity);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void encryptPassword(final User user) {

        final String password = user.getPassword();
        final Object salt = saltSource.getSalt(user);
        user.setPassword(passwordEncoder.encodePassword(password, salt));
    }

    @Override
    public User saveUser(final User user) throws ServiceException {

        encryptPassword(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.getAuthorities().clear();
        userEntity = userRepository.save(userEntity);

        for (final Authority authority : user.getAuthorities()) {
            userEntity.getAuthorities().add(authorityRepository.findByAuthority(authority.getAuthority()));
        }
        userRepository.updateAuthorities(userEntity, userEntity.getId());
        user.setId(userEntity.getId());

        return user;
    }

    @Override
    public User updateUser(final User user) throws ResourceNotFoundException {
        final UserEntity userEntity = userRepository.findOne(user.getId());

        if (userEntity == null) {
            throw new ResourceNotFoundException("Record not found with id :: " + user.getId());
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) throws ResourceNotFoundException {

        final UserEntity userEntity = userRepository.findByUsername(username);
        System.out.println(" userEntity " + userEntity);

        final User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword("[PROTECTED]");

        System.out.println("returning user " + user);
        return user;
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

}

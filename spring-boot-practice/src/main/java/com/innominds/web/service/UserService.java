package com.innominds.web.service;

import com.innominds.exception.ResourceNotFoundException;
import com.innominds.exception.ServiceException;
import com.innominds.persistence.vo.User;

/**
 * The Interface UserService.
 */
public interface UserService {

    /**
     * @param id
     *            primary key
     * @throws ResourceNotFoundException
     */
    void deleteUser(Long id) throws ResourceNotFoundException;

    /**
     * Encrypt password.
     *
     * @param user
     *            the user
     */
    void encryptPassword(User user);

    /**
     * @param user
     * @return user with id
     * @throws ServiceException
     */
    User saveUser(User user) throws ServiceException;

    /**
     * @param user
     * @return updated user
     * @throws ResourceNotFoundException
     */
    User updateUser(User user) throws ResourceNotFoundException;

    /**
     *
     * @param username
     * @return user
     */
    User getUserByUsername(String username);

    public long getUserCount();

}

package com.tvajjala.web.service;

import com.tvajjala.exception.RecordNotFoundException;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.vo.User;

/**
 * The Interface UserService.
 */
public interface UserService {

    /**
     * @param id
     *            primary key
     * @throws RecordNotFoundException
     */
    void deleteUser(Long id) throws RecordNotFoundException;

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
     * @throws RecordNotFoundException
     */
    User updateUser(User user) throws RecordNotFoundException;
}

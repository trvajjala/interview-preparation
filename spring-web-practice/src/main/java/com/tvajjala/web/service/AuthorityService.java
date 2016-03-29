package com.tvajjala.web.service;

import java.util.List;

import com.tvajjala.exception.RecordNotFoundException;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.vo.Authority;

/**
 * The Interface AuthorityService.
 */
public interface AuthorityService {

    /**
     * Gets the authorities.
     *
     * @return the authorities
     */
    List<Authority> getAuthorities();

    /**
     *
     * @param authority
     * @return authority with primary key
     * @throws RecordNotFoundException
     */
    Authority getAuthority(String authority) throws RecordNotFoundException;

    /**
     *
     * @param authority
     * @return authority with primary key
     * @throws ServiceException
     */
    Authority saveAuthority(Authority authority) throws ServiceException;

}

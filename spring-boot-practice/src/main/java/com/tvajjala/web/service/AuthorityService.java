package com.tvajjala.web.service;

import java.util.List;

import com.tvajjala.exception.ResourceNotFoundException;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.vo.Authority;

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
     * @throws ResourceNotFoundException
     */
    Authority getAuthority(String authority) throws ResourceNotFoundException;

    /**
     *
     * @param authority
     * @return authority with primary key
     * @throws ServiceException
     */
    Authority saveAuthority(Authority authority) throws ServiceException;

}

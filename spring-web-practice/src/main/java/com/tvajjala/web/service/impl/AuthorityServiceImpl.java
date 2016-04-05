package com.tvajjala.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvajjala.exception.ResourceNotFoundException;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.domain.AuthorityEntity;
import com.tvajjala.persistence.repository.AuthorityRepository;
import com.tvajjala.persistence.vo.Authority;
import com.tvajjala.web.service.AuthorityService;

/**
 * The Class AuthorityServiceImpl.
 */
@Service("authorityService")
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    /** The authority repository. */
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Gets the authorities.
     *
     * @return the authorities
     */
    @Override
    public List<Authority> getAuthorities() {

        Authority authority = null;

        final List<Authority> lstAuthority = new ArrayList<Authority>();

        for (final AuthorityEntity authorityEntity : authorityRepository.findAll()) {
            authority = new Authority();

            authority.setAuthority(authorityEntity.getAuthority().trim());

            lstAuthority.add(authority);

        }

        return lstAuthority;
    }

    @Override
    public Authority getAuthority(final String authorityName) throws ResourceNotFoundException {

        final AuthorityEntity authorityEntity = authorityRepository.findByAuthority(authorityName);

        if (authorityEntity == null) {
            throw new ResourceNotFoundException("record not exists with property : " + authorityName);
        }

        final Authority authority = new Authority();
        authority.setAuthority(authorityEntity.getAuthority());
        authority.setId(authorityEntity.getId());
        authority.setTitle(authorityEntity.getTitle());
        return authority;

    }

    /**
     * Save authority.
     *
     * @param authority
     *            the authority
     * @return the authority
     */
    @Override
    public Authority saveAuthority(Authority authority) throws ServiceException {

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(authority.getAuthority());
        authorityEntity.setTitle(authority.getTitle());

        authorityEntity = authorityRepository.save(authorityEntity);
        authority.setAuthority(authorityEntity.getAuthority());
        authority.setId(authorityEntity.getId());
        authority.setTitle(authorityEntity.getTitle());

        return authority;

    }

}

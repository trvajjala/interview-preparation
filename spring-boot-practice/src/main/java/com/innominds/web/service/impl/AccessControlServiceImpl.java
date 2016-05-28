package com.innominds.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innominds.persistence.domain.AuthorityEntity;
import com.innominds.persistence.domain.EndpointEntity;
import com.innominds.persistence.repository.AuthorityRepository;
import com.innominds.persistence.repository.EndpointsRepository;
import com.innominds.persistence.vo.Authority;
import com.innominds.web.service.AccessControlService;

/**
 * <p>
 * This service used to provide roles against activities (URIs)
 * </p>
 * .
 *
 * @author ThirupathiReddy V
 */
@Service("accessControlService")
@Transactional
public class AccessControlServiceImpl implements AccessControlService {

    /** Reference to logger */
    private static final Logger logger = LoggerFactory.getLogger(AccessControlService.class);

    /** The authority repository. */
    @Autowired
    private AuthorityRepository authorityRepository;

    /** The activity repository. */
    @Autowired
    private EndpointsRepository endpointsRepository;

    @Override
    public List<ConfigAttribute> getAllRoles() {

        final List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
        final List<AuthorityEntity> entities = authorityRepository.findAll();

        for (final AuthorityEntity authorityEntity : entities) {
            final Authority authority = new Authority();
            authority.setAuthority(authorityEntity.getAuthority());
            authority.setId(authorityEntity.getId());
            authority.setTitle(authorityEntity.getTitle());
            list.add(authority);
        }
        logger.debug("Authorities " + list);
        return list;
    }

    @Override
    public List<ConfigAttribute> getAllRolesByURI(final String uri) {

        final List<ConfigAttribute> configList = new ArrayList<ConfigAttribute>();

        try {
            EndpointEntity permissionsEntity = endpointsRepository.findByEndpoint(uri);

            if (permissionsEntity == null) {

                for (final EndpointEntity ae : endpointsRepository.getRegexActivites()) {

                    if (uri.contains(ae.getEndpoint().replaceAll("/\\*\\*", ""))) {
                        permissionsEntity = ae;
                        break;
                    }
                }
            }

            if (permissionsEntity != null) {
                final Set<AuthorityEntity> entities = permissionsEntity.getAuthorities();

                for (final AuthorityEntity authorityEntity : entities) {
                    final Authority authority = new Authority();
                    authority.setAuthority(authorityEntity.getAuthority());
                    authority.setId(authorityEntity.getId());
                    authority.setTitle(authorityEntity.getTitle());
                    configList.add(authority);
                }
            }
        } catch (final Exception e) {
            logger.error("Exception  while checking access ", e);
        }

        logger.info("Permitted Roles : " + configList);

        if (configList.isEmpty()) {
            logger.warn("No Security Applied for URI : " + uri);
        }

        return configList;
    }

    @Override
    public void addActivityForRoles(final String endpoint, final boolean regex, final List<String> roleNamesList) {

        EndpointEntity endpointEntity = new EndpointEntity();
        endpointEntity.setRegex(regex);
        endpointEntity.setEndpoint(endpoint);
        endpointEntity.getAuthorities().clear();
        endpointEntity = endpointsRepository.save(endpointEntity);

        for (final String authority : roleNamesList) {
            endpointEntity.getAuthorities().add(authorityRepository.findByAuthority(authority));
        }

        endpointsRepository.updateAuthorites(endpointEntity, endpointEntity.getId());
    }

    @Override
    public void addActivityForRoles(final String endpoint, final boolean regex, final String... roleNames) {

        final EndpointEntity permissionsEntity = new EndpointEntity();
        permissionsEntity.setRegex(regex);
        permissionsEntity.setEndpoint(endpoint);

        for (final String authority : roleNames) {
            permissionsEntity.getAuthorities().add(authorityRepository.findByAuthority(authority));
        }

        endpointsRepository.save(permissionsEntity);

    }

}

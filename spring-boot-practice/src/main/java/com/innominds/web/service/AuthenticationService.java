/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.innominds.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innominds.persistence.domain.AuthorityEntity;
import com.innominds.persistence.domain.UserEntity;
import com.innominds.persistence.repository.UserRepository;
import com.innominds.persistence.vo.Authority;
import com.innominds.persistence.vo.User;

/**
 * only deals with authentication
 *
 * @author ThirupathiReddy V
 *
 */
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity personEntity = userRepository.findByUsername(username);

        if (personEntity == null) {
            throw new UsernameNotFoundException(String.format("Person %s does not exist!", username));
        }

        final User user = new User();

        user.setId(personEntity.getId());
        user.setUsername(personEntity.getUsername());
        user.setPassword(personEntity.getPassword()); // this will be verified on AuthenticationProvider

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        for (final AuthorityEntity authorityEntity : personEntity.getAuthorities()) {
            user.getAuthorities().add(new Authority(authorityEntity.getAuthority(), authorityEntity.getTitle()));
        }

        return user;
    }

}

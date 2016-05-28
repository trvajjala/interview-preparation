package com.innominds;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.innominds.exception.ServiceException;
import com.innominds.persistence.constants.ApplicationRole;
import com.innominds.persistence.vo.Authority;
import com.innominds.persistence.vo.User;
import com.innominds.web.service.AccessControlService;
import com.innominds.web.service.AuthorityService;
import com.innominds.web.service.UserService;

/**
 * {@link CommandLineRunner} is useful to execute some functionality right after application startup. (this gets invoked only once in its lifetime)
 *
 * @author ThirupathiReddy V
 *
 */
@Component
public class DataLoader implements InitializingBean {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger("DataLoader");

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The authority service. */
    @Autowired
    private AuthorityService authorityService;

    /** The access control service. */
    @Autowired
    private AccessControlService accessControlService;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info(" ############### IMPORTING SEED DATA INTO DATABASE ############### ");

        if (userService.getUserCount() == 0) {
            initializeUsers();
        }

        logger.info(" ############### DATABASE INITIALIZATION COMPLETED. APPLICATION READY TO USE ############### ");

    }

    public void createUser(final String username, final String email, final String phone, final String name, final Authority role) {
        try {
            final User user = new User();
            user.setUsername(username);
            user.setPassword("1234");
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.getAuthorities().add(role);
            userService.saveUser(user);
        } catch (final Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    private void initializeUsers() throws ServiceException {

        final String name = "ThirupathiReddy";

        final Authority adminRole = authorityService.saveAuthority(new Authority(ApplicationRole.ROLE_ADMIN));

        final Authority userRole = authorityService.saveAuthority(new Authority(ApplicationRole.ROLE_USER));

        createUser("admin", "admin@in.com", "9000211020", name, adminRole);

        createUser("tvajjala", "tvajjala@in.com", "9000211024", name, userRole);

        final List<String> list = new ArrayList<String>();
        list.add(ApplicationRole.ROLE_ADMIN.name());
        list.add(ApplicationRole.ROLE_USER.name());

        // add roles to each endPoint. you can also add regular expression
        accessControlService.addActivityForRoles("/api/cors", false, list);

        accessControlService.addActivityForRoles("/api/user/**", true, list);

        accessControlService.addActivityForRoles("/api/**", true, ApplicationRole.ROLE_ADMIN.name(), ApplicationRole.ROLE_USER.name());

    }
}

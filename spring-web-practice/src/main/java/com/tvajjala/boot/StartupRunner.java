package com.tvajjala.boot;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.tvajjala.exception.ServiceException;
import com.tvajjala.persistence.constants.ApplicationRole;
import com.tvajjala.persistence.vo.Authority;
import com.tvajjala.persistence.vo.User;
import com.tvajjala.web.service.AccessControlService;
import com.tvajjala.web.service.AuthorityService;
import com.tvajjala.web.service.UserService;

/**
 * {@link CommandLineRunner} is useful to execute some functionality right after application startup. (this gets invoked only once in its lifetime)
 *
 * @author ThirupathiReddy V
 *
 */
public class StartupRunner implements InitializingBean {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger("StartupRunner");

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

        System.err.println(" ############### IMPORTING SEED DATA INTO DATABASE ############### ");

        if (userService.getUserCount() == 0) {
            initializeUsers();
        }

        System.err.println(" ############### DATABASE INITIALIZATION COMPLETED. APPLICATION READY TO USE ############### ");

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
        accessControlService.addActivityForRoles("/api/user/save", false, list);

        accessControlService.addActivityForRoles("/api/user/**", true, list);

        accessControlService.addActivityForRoles("/api/org/**", true, ApplicationRole.ROLE_ADMIN.name(), ApplicationRole.ROLE_USER.name());

    }
}

package com.tvajjala;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tvajjala.constants.ApplicationRole;
import com.tvajjala.exception.ServiceException;
import com.tvajjala.vo.Authority;
import com.tvajjala.vo.User;
import com.tvajjala.web.service.AccessControlService;
import com.tvajjala.web.service.AuthorityService;
import com.tvajjala.web.service.UserService;

/**
 * <p>
 * <h4>Application Data Initializer</h4> This Bean initialized once the Spring container loaded successfully
 * </P>
 * <p>
 * It will insert some default data (users and privileges) into the database when application deployed for the first time.<br>
 * it reduces the time for the developer to write database scripts manually.
 * </p>
 *
 * @author ThirupathiReddy V
 * @since 1.0
 */
@Component("applicationBootstapper")
public class ApplicationBootstapper implements InitializingBean {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger("BOOTSTRAPPER");

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The authority service. */
    @Autowired
    private AuthorityService authorityService;

    /** The access control service. */
    @Autowired
    private AccessControlService accessControlService;

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

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeUsers();
    }

    /**
     * This method insert the seed data in to Database while deploying the Application.
     *
     * @throws ServiceException
     */
    private void initializeUsers() throws ServiceException {

        final String name = "ThirupathiReddy";

        final Authority adminRole = authorityService.saveAuthority(new Authority(ApplicationRole.ROLE_ADMIN));

        final Authority userRole = authorityService.saveAuthority(new Authority(ApplicationRole.ROLE_USER));

        createUser("adminini", "admin@in.com", "9000211020", name, adminRole);

        createUser("tvajjala", "tvajjala@in.com", "9000211024", name, userRole);

        final List<String> list = new ArrayList<String>();
        list.add(ApplicationRole.ROLE_ADMIN.name());
        list.add(ApplicationRole.ROLE_USER.name());

        accessControlService.addActivityForRoles("/api/user/all", false, list);
        accessControlService.addActivityForRoles("/api/user/one", false, list);

        accessControlService.addActivityForRoles("/api/org/**", true, ApplicationRole.ROLE_ADMIN.name(), ApplicationRole.ROLE_USER.name());

    }

}

package learn.solarfarm.security;

import learn.solarfarm.data.AppUserJdbcTemplateRepository;
import learn.solarfarm.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserService implements UserDetailsService {

    private final AppUserJdbcTemplateRepository repository;

    public AppUserService(AppUserJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = repository.findByUsername(username);

        if (user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException("username" + username + "not found.");
        }

        return user;
    }
}

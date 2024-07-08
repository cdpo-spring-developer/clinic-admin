package com.springlessons.clinicadmin.service;

import com.nimbusds.jose.JOSEException;
import com.springlessons.clinicadmin.entity.ApplicationUser;
import com.springlessons.clinicadmin.entity.UserRole;
import com.springlessons.clinicadmin.exception.AccountException;
import com.springlessons.clinicadmin.repository.ApplicationUserRepository;
import com.springlessons.clinicadmin.repository.UserRoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {
    private final ApplicationUserRepository applicationUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    //  private final JwtSecurityService jwtSecurityService;
    private final AuthenticationManager authenticationManager;

    public AccountService(ApplicationUserRepository applicationUserRepository,
                          UserRoleRepository userRoleRepository,
                          PasswordEncoder passwordEncoder,
                          //     JwtSecurityService jwtSecurityService,
                          AuthenticationManager authenticationManager) {
        this.applicationUserRepository = applicationUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        // this.jwtSecurityService = jwtSecurityService;
        this.authenticationManager = authenticationManager;
    }

    public void registration(ApplicationUser user) throws AccountException {
        if (applicationUserRepository.existsByUsername(user.getUsername())) {
            throw new AccountException("Username is already taken");
        }
        Set<UserRole> userRoles = new HashSet<>();
        userRoleRepository.findByRoleType(UserRole.RoleType.ROLE_CONT_MANAGER)
                .ifPresentOrElse(user::setUserRoles,
                        () -> {
                            UserRole userRole = new UserRole();
                            userRole.setRoleType(UserRole.RoleType.ROLE_CONT_MANAGER);
                            user.setUserRoles(userRole);
                            userRoleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    /*public Token loginAccount(String username, String password) throws AccountException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token = new Token();
        try {
            token.setToken(jwtSecurityService.generateToken((UserDetails) authentication.getPrincipal()));
            token.setRefreshToken(jwtSecurityService.generateRefreshToken());
        } catch (JOSEException e) {
            throw new AccountException("Token cannot ne created: " + e.getMessage());
        }
        return token;
    }*/
}

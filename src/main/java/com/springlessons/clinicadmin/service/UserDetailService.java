package com.springlessons.clinicadmin.service;


import com.springlessons.clinicadmin.entity.ApplicationUser;
import com.springlessons.clinicadmin.repository.ApplicationUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    public UserDetailService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
        Set<GrantedAuthority> authorities = applicationUser.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority(applicationUser.getUserRoles().toString()))
                .collect(Collectors.toSet());
        /*GrantedAuthority authority = new SimpleGrantedAuthority(
                applicationUser.getUserRole().getRoleType().name());*/
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority);
        }
        //  System.out.println(authority.getAuthority());

        return new User(username, applicationUser.getPassword(), Set.of((GrantedAuthority) authorities));
    }
}

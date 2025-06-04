package org.example.security;

import org.example.entity.User;
import org.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.example.service.UserService;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return (UserDetails) new UserDetailsImpl(user);
    }
}
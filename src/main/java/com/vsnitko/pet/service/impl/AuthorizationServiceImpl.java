package com.vsnitko.pet.service.impl;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.service.AuthorizationService;
import com.vsnitko.pet.service.UserService;
import com.vsnitko.pet.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDto signIn(final UserDto userDto) throws UsernameNotFoundException, BadCredentialsException, CredentialsExpiredException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return userMapper.map(userService.getUserByEmail(authentication.getName()));
    }
}

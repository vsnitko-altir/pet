package com.vsnitko.pet.service;

import com.vsnitko.pet.model.dto.UserDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
public interface AuthorizationService {

    /**
     * Checks whether user entered valid credential, sets authentication for securityContext
     *
     * @param userDto user dto
     * @return signed-in user
     * @throws UsernameNotFoundException if user with provided email does not exist
     * @throws BadCredentialsException if password is not correct
     * @throws CredentialsExpiredException if account in inactive
     */
    UserDto signIn(UserDto userDto) throws UsernameNotFoundException, BadCredentialsException, CredentialsExpiredException;
}

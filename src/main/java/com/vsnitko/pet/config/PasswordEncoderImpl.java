package com.vsnitko.pet.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Component
public class PasswordEncoderImpl extends BCryptPasswordEncoder implements PasswordEncoder {
}

package com.vsnitko.pet.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;

    private List<SimpleGrantedAuthority> authorities;

    private final boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * We use isActive attribute here because {@link CredentialsExpiredException} thrown only if credentials are correct.
     * (user who enters wrong password shouldn't know if account is active or not)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

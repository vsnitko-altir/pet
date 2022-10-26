package com.vsnitko.pet.service.impl;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.model.dto.UserSearchQuery;
import com.vsnitko.pet.model.entity.jpa.User;
import com.vsnitko.pet.model.entity.search.UserIndex;
import com.vsnitko.pet.repository.jpa.UserRepository;
import com.vsnitko.pet.repository.search.UserSearchRepository;
import com.vsnitko.pet.service.UserService;
import com.vsnitko.pet.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;
    private final UserMapper mapper;

    @Override
    public UserDto getUserById(final Long id) {
        return mapper.map(userRepository.findById(id).orElse(null));
    }

    @Override
    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        final User user = mapper.map(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User savedUser = userRepository.save(user);
        userSearchRepository.save(mapper.toIndex(savedUser));
        return mapper.map(savedUser);
    }

    @Override
    public List<UserIndex> findUsers(final UserSearchQuery userSearchQuery) {
        return userSearchRepository.findUsersByQuery(userSearchQuery);
    }

    @Override
    @Transactional
    public UserDto updateUser(final UserDto userDto, final Principal principal) throws AccessDeniedException {
        //todo session management
        //if (!principal.getName().equals(userDto.getEmail())) {
        //    throw new AccessDeniedException("You can edit only your own profile");
        //}
        userRepository.updateUser(userDto.getEmail(), userDto.getFirstName(), userDto.getLastName());
        userSearchRepository.updateUser(mapper.toIndex(userDto));
        return userDto;
    }
}

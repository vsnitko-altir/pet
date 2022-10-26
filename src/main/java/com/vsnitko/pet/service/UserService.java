package com.vsnitko.pet.service;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.model.dto.UserSearchQuery;
import com.vsnitko.pet.model.entity.jpa.User;
import com.vsnitko.pet.model.entity.search.UserIndex;
import org.springframework.security.access.AccessDeniedException;

import java.security.Principal;
import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
public interface UserService {

    /**
     * Find in database user with specified id
     *
     * @param id user id
     * @return user that was found or null
     */
    UserDto getUserById(final Long id);

    /**
     * Find in database user with specified email
     *
     * @param email user email
     * @return user that was found or null
     */
    User getUserByEmail(final String email);

    /**
     * Saves user to database and elasticsearch
     *
     * @param userDto to create
     * @return created user
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Searches for users in elasticsearch by specified query
     *
     * @param userSearchQuery query to perform search
     * @return result list of found users
     */
    List<UserIndex> findUsers(UserSearchQuery userSearchQuery);

    /**
     * Update entity in database and elasticsearch if exists
     *
     * @param userDto to update
     * @param principal current signed-in user
     * @return updated user
     * @throws AccessDeniedException if user doesn't have permissions to update user
     */
    UserDto updateUser(UserDto userDto, Principal principal) throws AccessDeniedException;
}

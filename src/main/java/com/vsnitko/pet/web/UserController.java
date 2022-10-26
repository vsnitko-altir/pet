package com.vsnitko.pet.web;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.model.dto.UserSearchQuery;
import com.vsnitko.pet.model.entity.search.UserIndex;
import com.vsnitko.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/find")
    public List<UserIndex> findUsers(@RequestBody UserSearchQuery userSearchQuery) {
        return userService.findUsers(userSearchQuery);
    }

    @PostMapping("/edit")
    public UserDto findUsers(@RequestBody UserDto userDto, Principal principal) {
        return userService.updateUser(userDto, principal);
    }
}

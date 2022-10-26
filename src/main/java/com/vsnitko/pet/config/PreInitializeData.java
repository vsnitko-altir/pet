package com.vsnitko.pet.config;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.model.entity.jpa.Role;
import com.vsnitko.pet.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Component
@RequiredArgsConstructor
public class PreInitializeData {

    private final UserService userService;

    @Value("${preInitialize.initializeUsers}")
    private boolean initializeUsers;

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        if (initializeUsers) {
            List<UserDto> usersToInitialize = List.of(
                    buildUser("John", "Johnson", "a@a1", "1111"),
                    buildUser("Johnson", "John", "a@a2", "1111"),
                    buildUser("Jane", "JohNson", "a@a3", "1111"),
                    buildUser("Joh", "Joh", "a@a4", "1111"),
                    buildUser("john", "Kennedy", "a@a5", "1111"),
                    buildUser("Kennedy", "John", "a@a6", "1111"),
                    buildUser("Ivan", "IvaNov", "a@a7", "1111"),
                    buildUser("John", "Ivanov", "a@a8", "1111"),
                    buildUser("John", "Johnson kennedy", "a@a9", "1111"),
                    buildUser("John", "Johnson-kennedy", "a@a10", "1111"),
                    buildUser("Maria", "Frolova", "a@a11", "1111"),
                    buildUser("Andrey", "Frolov", "a@a12", "1111"));
            for (UserDto user : usersToInitialize) {
                userService.saveUser(user);
            }
        }
    }

    private UserDto buildUser(String firstName, String lastName, String email, String password) {
        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .active(true)
                .role(Role.USER)
                .build();
    }
}

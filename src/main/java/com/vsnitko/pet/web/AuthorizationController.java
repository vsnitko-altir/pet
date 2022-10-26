package com.vsnitko.pet.web;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.service.AuthorizationService;
import com.vsnitko.pet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {
        authorizationService.signIn(userDto);
        try {
            return ResponseEntity.ok(authorizationService.signIn(userDto));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Bad credentials");
        } catch (CredentialsExpiredException e) {
            return ResponseEntity.badRequest().body("Account is disabled");
        }
    }

    @PostMapping("/sign-up")
    public UserDto signUp(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("User not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Bad credentials");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<String> handleCredentialsExpiredException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Account is disabled");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

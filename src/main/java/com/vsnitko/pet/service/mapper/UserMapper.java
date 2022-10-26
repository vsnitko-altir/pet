package com.vsnitko.pet.service.mapper;

import com.vsnitko.pet.model.dto.UserDto;
import com.vsnitko.pet.model.entity.jpa.User;
import com.vsnitko.pet.model.entity.search.UserIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Component
public class UserMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Simple copy of data from DTO to new Entity.
     *
     * @param dto as a source object
     * @return entity that was created based on the DTO data
     */
    public User map(final UserDto dto) {
        return mapper.convertValue(dto, User.class);
    }

    /**
     * Simple copy of data from Entity to new DTO.
     *
     * @param entity as a source object
     * @return DTO that was created based on the Entity data
     */
    public UserDto map(final User entity) {
        return mapper.convertValue(entity, UserDto.class);
    }

    /**
     * Simple copy of data from Entity to new UserIndex.
     *
     * @param entity as a source object
     * @return UserIndex object that represents index for elasticsearch
     */
    public UserIndex toIndex(final User entity) {
        return UserIndex.builder()
                .id(entity.getId().toString())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }

    /**
     * Simple copy of data from DTO to new UserIndex.
     *
     * @param dto as a source object
     * @return UserIndex object that represents index for elasticsearch
     */
    public UserIndex toIndex(final UserDto dto) {
        return UserIndex.builder()
                .id(dto.getId().toString())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }
}

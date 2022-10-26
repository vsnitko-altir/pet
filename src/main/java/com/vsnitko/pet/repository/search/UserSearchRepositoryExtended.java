package com.vsnitko.pet.repository.search;

import com.vsnitko.pet.model.dto.UserSearchQuery;
import com.vsnitko.pet.model.entity.search.UserIndex;

import java.util.List;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
public interface UserSearchRepositoryExtended {

    List<UserIndex> findUsersByQuery(UserSearchQuery userSearchQuery);

    UserIndex updateUser(UserIndex userIndex);
}

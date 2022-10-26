package com.vsnitko.pet.repository.search;

import com.vsnitko.pet.model.entity.search.UserIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
public interface UserSearchRepository extends ElasticsearchRepository<UserIndex, String>, UserSearchRepositoryExtended {
}

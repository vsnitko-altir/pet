package com.vsnitko.pet.repository.search.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.PrefixQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.util.ObjectBuilder;
import com.vsnitko.pet.model.dto.UserSearchQuery;
import com.vsnitko.pet.model.entity.search.UserIndex;
import com.vsnitko.pet.repository.search.UserSearchRepositoryExtended;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Repository
@RequiredArgsConstructor
public class UserSearchRepositoryExtendedImpl implements UserSearchRepositoryExtended {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<UserIndex> findUsersByQuery(final UserSearchQuery userSearchQuery) {
        final List<String> words = Arrays.stream(
                        userSearchQuery.getQuery()
                                .trim()
                                .replaceAll(" +", " ")
                                .toLowerCase()
                                .split(" "))
                .collect(Collectors.toList());
        if (words.size() == 1) {
            words.add("");
        }
        final Query searchQuery = NativeQuery.builder()
                .withQuery(query -> query
                        .bool(bool -> bool
                                .must(must -> must
                                        .bool(boolMust -> boolMust
                                                .should(should -> should
                                                        .prefix(getPrefix(FIRST_NAME, words.get(0))))
                                                .should(should -> should
                                                        .prefix(getPrefix(LAST_NAME, words.get(0), 0.5f)))))
                                .must(must -> must
                                        .bool(boolMust -> boolMust
                                                .should(should -> should
                                                        .prefix(getPrefix(LAST_NAME, words.get(1))))
                                                .should(should -> should
                                                        .prefix(getPrefix(FIRST_NAME, words.get(1), 0.5f)))))
                                .should(should -> should
                                        .term(getTerm(FIRST_NAME, words.get(0))))
                                .should(should -> should
                                        .term(getTerm(LAST_NAME, words.get(1))))
                        )
                )
                .build();
        final SearchHits<UserIndex> userSearchHits = elasticsearchOperations.search(searchQuery, UserIndex.class);
        return userSearchHits.get()
                .map(SearchHit::getContent)
                .toList();
    }

    @Override
    public UserIndex updateUser(final UserIndex userIndex) {
        elasticsearchOperations.update(userIndex);
        return userIndex;
    }

    private Function<PrefixQuery.Builder, ObjectBuilder<PrefixQuery>> getPrefix(final String field, final String word) {
        return prefix -> prefix
                .field(field)
                .value(word)
                .caseInsensitive(true);
    }

    private Function<PrefixQuery.Builder, ObjectBuilder<PrefixQuery>> getPrefix(final String field, final String word, final float boost) {
        return prefix -> prefix
                .field(field)
                .value(word)
                .caseInsensitive(true)
                .boost(boost);
    }

    private Function<TermQuery.Builder, ObjectBuilder<TermQuery>> getTerm(final String field, final String word) {
        return term -> term
                .field(field)
                .value(word)
                .caseInsensitive(true);
    }
}

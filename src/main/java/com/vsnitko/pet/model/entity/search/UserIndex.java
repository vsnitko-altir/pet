package com.vsnitko.pet.model.entity.search;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Data
@Builder
@Document(indexName = "user")
public class UserIndex {

    @Id
    @Field(type = Keyword)
    private String id;

    @Field(type = Text)
    private String firstName;

    @Field(type = Text)
    private String lastName;

    @Field(type = Text)
    private String email;
}

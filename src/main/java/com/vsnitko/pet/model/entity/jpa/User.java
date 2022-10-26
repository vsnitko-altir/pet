package com.vsnitko.pet.model.entity.jpa;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_")
@Document(indexName = "user")
public class User extends Identity {

    @Column(nullable = false)
    @Field(type = Text)
    private String firstName;

    @Column(nullable = false)
    @Field(type = Text)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean active = true;
}

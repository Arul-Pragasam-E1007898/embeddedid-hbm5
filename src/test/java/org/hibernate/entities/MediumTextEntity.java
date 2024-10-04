package org.hibernate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "medium_text_entity")
@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MediumTextEntity {
    @JsonIgnore
    @EmbeddedId
    private Identity id = Identity.builder().build();

    @Column(name = "medium_text")
    private String mediumText;
}

package org.hibernate.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "entity_note_associations")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "targetable_type", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode
public class EntityNoteAssociation {
    public EntityNoteAssociation(Long id, Long targetableId, Long noteId, Instant createdAt, Instant updatedAt) {
        this.identity = Identity.builder().id(id).build();
        this.targetableId = targetableId;
        this.noteId = noteId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    @EmbeddedId
    protected Identity identity = Identity.builder().build();

    @Column(name = "targetable_id")
    @Setter
    protected Long targetableId;

    @Column(name = "note_id", nullable = false)
    protected Long noteId;

    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @CreatedDate
    protected Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @LastModifiedDate
    protected Instant updatedAt;

    public Long getId() {
        return identity != null ? identity.getId() : null;
    }

    public Long getAccountId() {
        return identity != null ? identity.getAccountId() : null;
    }

    public void setAccountId(Long accountId) {
        if (identity == null) {
            this.identity = Identity.builder().build();
        }
        identity.setAccountId(accountId);
    }
}

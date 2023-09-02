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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "entity_team_users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.INTEGER)
public class EntityTeamUser {
    public EntityTeamUser(Long id, Long entityId, Long designationId, Long userId, Instant createdAt,
                          Instant updatedAt) {
        this.identity = Identity.builder().id(id).build();
        this.entityId = entityId;
        this.designationId = designationId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    @EmbeddedId
    protected Identity identity = Identity.builder().build();

    @Column(name = "entity_id")
    @Setter
    protected Long entityId;

    @Column(name = "designation_id")
    protected Long designationId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    protected Long userId;

    @Column(name = "created_at", nullable = false)
    @Setter
    @CreatedDate
    protected Instant createdAt;

    @Column(name = "updated_at", nullable = false)
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

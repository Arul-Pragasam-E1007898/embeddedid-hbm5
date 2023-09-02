package org.hibernate.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "entity_subscriptions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.INTEGER)
@EqualsAndHashCode
public class EntitySubscription
        implements Serializable{
    public EntitySubscription(Long id, Long entityId, String subscriptionTypes, String unsubscriptionTypes,
                              Instant createdAt, Instant updatedAt) {
        this.identity = Identity.builder().id(id).build();
        this.entityId = entityId;
        this.subscriptionTypes = subscriptionTypes;
        this.unsubscriptionTypes = unsubscriptionTypes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    @EmbeddedId
    protected Identity identity = Identity.builder().build();

    @Column(name = "entity_id")
    @Setter
    protected Long entityId;

    @Lob
    @Column(name = "subscription_types")
    @Setter
    private String subscriptionTypes;

    @Lob
    @Column(name = "unsubscription_types")
    @Setter
    private String unsubscriptionTypes;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @CreatedDate
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @LastModifiedDate
    private Instant updatedAt;

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

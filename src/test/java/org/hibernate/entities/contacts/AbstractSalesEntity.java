package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.entities.Identity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@MappedSuperclass
public abstract class AbstractSalesEntity
        implements Serializable {
    protected AbstractSalesEntity(Identity identity, Long contactId, Instant createdAt, Instant updatedAt) {
        this.identity = identity;
        this.contactId = contactId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    @EmbeddedId
    private Identity identity = Identity.builder().build();

    @Column(name = "contact_id")
    @Setter
    protected Long contactId;

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

    public void setId(Long id) {
        if (identity == null) {
            this.identity = Identity.builder().build();
        }
        identity.setId(id);
    }

    public void setAccountId(Long accountId) {
        if (identity == null) {
            this.identity = Identity.builder().build();
        }
        identity.setAccountId(accountId);
    }
}

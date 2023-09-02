package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.entities.EntityTeamUser;
import org.hibernate.entities.Identity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Where(clause = "entity_type=2")
public class ContactTeamUser  extends EntityTeamUser implements Serializable {
    @Builder
    public ContactTeamUser(Long id, Long entityId, Long designationId, Long userId, Instant createdAt,
                                 Instant updatedAt, SalesContact contact) {
        super(Identity.builder().id(id).build(), entityId,designationId, userId, createdAt, updatedAt);
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setEntityId(salesContact.getId());
    }
}

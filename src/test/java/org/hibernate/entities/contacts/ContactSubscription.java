package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.entities.EntitySubscription;
import org.hibernate.entities.Identity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * EntityTypes
 *   Lead: 1,
 *   Contact: 2,
 *   Deal: 3,
 *   SalesAccount: 4
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Where(clause = "entity_type=2")
@EqualsAndHashCode(callSuper = true)
public class ContactSubscription
        extends EntitySubscription implements Serializable {
    @Builder
    @SuppressWarnings("squid:S107")
    public ContactSubscription(Long accountId, Long entityId, Long id, String subscriptionTypes,
                               String unsubscriptionTypes, Instant createdAt, Instant updatedAt, SalesContact contact) {
        super(Identity.builder().id(id).accountId(accountId).build(), entityId, subscriptionTypes, unsubscriptionTypes,
                createdAt, updatedAt);
        this.contact = contact;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setEntityId(salesContact.getId());
    }
}

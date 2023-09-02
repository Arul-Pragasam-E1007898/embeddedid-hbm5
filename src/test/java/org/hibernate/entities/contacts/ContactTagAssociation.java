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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.entities.EntityTagAssociation;
import org.hibernate.entities.Identity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("Contact")
@NoArgsConstructor
@Where(clause = "targetable_type='Contact'")
@EqualsAndHashCode(callSuper = true)
public class ContactTagAssociation
        extends EntityTagAssociation implements Serializable {
    @Builder
    public ContactTagAssociation(Long id, Long targetableId, Long tagId, Instant createdAt,
                                 Instant updatedAt, SalesContact contact) {
        super(Identity.builder().id(id).build(), targetableId, tagId, createdAt, updatedAt);
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id", insertable = false, updatable = false)
    @JoinColumn(name = "targetable_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setTargetableId(salesContact.getId());
    }
}

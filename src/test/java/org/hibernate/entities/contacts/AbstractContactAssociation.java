package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.entities.Identity;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@MappedSuperclass
public class AbstractContactAssociation
        extends AbstractSalesEntity implements Serializable {
    public AbstractContactAssociation(SalesContact salesContact, Long id, Long contactId, @NotNull Instant createdAt,
                                      @NotNull Instant updatedAt) {
        super(Identity.builder().id(id).build(), contactId, createdAt, updatedAt);
        this.contact = salesContact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setContactId(salesContact.getId());
    }
}

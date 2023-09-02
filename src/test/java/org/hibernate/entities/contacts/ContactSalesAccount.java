package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_sales_accounts")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContactSalesAccount
        extends AbstractContactAssociation implements Serializable {
    @Builder
    public ContactSalesAccount(Long id, Long contactId, @NotNull Instant createdAt,
                               @NotNull Instant updatedAt, Long salesAccountId, SalesContact salesContact) {
        super(salesContact, id, contactId, createdAt, updatedAt);
        this.salesAccountId = salesAccountId;
    }

    @NotNull
    @Column(name = "sales_account_id", nullable = false)
    private Long salesAccountId;

    @Column(name = "is_primary")
    @Setter
    private Boolean isPrimary = Boolean.FALSE;
}

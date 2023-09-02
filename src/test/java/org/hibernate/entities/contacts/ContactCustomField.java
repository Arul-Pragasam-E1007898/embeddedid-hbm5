package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.entities.Identity;
import org.hibernate.entities.custom.BigDecimalCustomFields;
import org.hibernate.entities.custom.BooleanCustomFields;
import org.hibernate.entities.custom.DateTimeCustomFields;
import org.hibernate.entities.custom.LongCustomFields;
import org.hibernate.entities.custom.StringCustomFields;
import org.hibernate.entities.custom.TextCustomFields;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_custom_fields")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class ContactCustomField
        extends AbstractContactCustomField implements Serializable  {
    @Builder
    @SuppressWarnings("squid:S107")
    public ContactCustomField(Long id, Long contactId, Long contactFormId, @NotNull Long accountId,
                              BooleanCustomFields booleanCustomFields, DateTimeCustomFields dateTimeCustomFields,
                              BigDecimalCustomFields bigDecimalCustomFields, LongCustomFields longCustomFields,
                              StringCustomFields stringCustomFields, TextCustomFields textCustomFields,
                              Instant createdAt, Instant updatedAt) {
        super(Identity.builder().id(id).accountId(accountId).build(), contactFormId, contactId);
        this.booleanCustomFields = booleanCustomFields;
        this.dateTimeCustomFields = dateTimeCustomFields;
        this.bigDecimalCustomFields = bigDecimalCustomFields;
        this.longCustomFields = longCustomFields;
        this.stringCustomFields = stringCustomFields;
        this.textCustomFields = textCustomFields;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Embedded
    private BooleanCustomFields booleanCustomFields = new BooleanCustomFields();

    @Embedded
    private DateTimeCustomFields dateTimeCustomFields = new DateTimeCustomFields();

    @Embedded
    private BigDecimalCustomFields bigDecimalCustomFields = new BigDecimalCustomFields();

    @Embedded
    private LongCustomFields longCustomFields = new LongCustomFields();

    @Embedded
    private StringCustomFields stringCustomFields = new StringCustomFields();

    @Embedded
    private TextCustomFields textCustomFields = new TextCustomFields();

    @NotNull
    @Column(name = "created_at", nullable = false)
    @Setter
    @CreatedDate
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @Setter
    @LastModifiedDate
    private Instant updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setContactId(salesContact.getId());
    }
}

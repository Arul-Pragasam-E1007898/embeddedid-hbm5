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

@Table(name = "contact_emails")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContactEmail
            extends AbstractContactAssociation implements Serializable {
    @Builder
    @SuppressWarnings("squid:S107")
    public ContactEmail(Long id, Long contactId, @NotNull Instant createdAt, @NotNull Instant updatedAt,
                        String email, Boolean isPrimary, Long labelId, Boolean unsubscribe, Integer emailStatus,
                        SalesContact salesContact) {
        super(salesContact, id, contactId, createdAt, updatedAt);
        this.email = email;
        this.isPrimary = isPrimary;
        this.labelId = labelId;
        this.unsubscribe = unsubscribe;
        this.emailStatus = emailStatus;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "is_primary")
    @Setter
    private Boolean isPrimary = Boolean.FALSE;

    @Column(name = "label_id")
    @Setter
    private Long labelId;

    @Column(name = "unsubscribe")
    private Boolean unsubscribe = Boolean.FALSE;

    @Column(name = "email_status")
    private Integer emailStatus;
}

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

@Table(name = "contact_phone_numbers")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContactPhoneNumber
        extends AbstractContactAssociation implements Serializable {
    @Builder
    public ContactPhoneNumber(Long id, Long contactId,@NotNull Instant createdAt, @NotNull Instant updatedAt,
                              String phoneNumber, Long labelId, SalesContact salesContact) {
        super(salesContact, id, contactId, createdAt, updatedAt);
        this.phoneNumber = phoneNumber;
        this.labelId = labelId;
    }

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "label_id")
    @Setter
    private Long labelId;
}

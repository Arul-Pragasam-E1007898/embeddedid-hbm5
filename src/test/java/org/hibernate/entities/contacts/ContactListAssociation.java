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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_list_associations")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ContactListAssociation
        extends AbstractContactAssociation implements Serializable {
    @Builder
    public ContactListAssociation(Long id, Long contactId,@NotNull Instant createdAt,
                                  @NotNull Instant updatedAt, Long listId, SalesContact salesContact) {
        super(salesContact, id, contactId, createdAt, updatedAt);
        this.listId = listId;
    }

    @NotNull
    @Column(name = "list_id", nullable = false)
    private Long listId;
}

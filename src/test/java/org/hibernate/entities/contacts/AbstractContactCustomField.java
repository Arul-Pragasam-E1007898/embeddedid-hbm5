package org.hibernate.entities.contacts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.entities.Identity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractContactCustomField
        implements Serializable{
    @JsonIgnore
    @EmbeddedId
    protected Identity identity = Identity.builder().build();

    @Column(name = "contact_form_id")
    private Long contactFormId;

    @Column(name = "contact_id")
    protected Long contactId;

    public Long getId() {
        return identity != null ? identity.getId() : null;
    }

    public Long getAccountId() {
        return identity != null ? identity.getAccountId() : null;
    }

    public void setId(Long id) {
        if (id != null) {
            if (this.identity != null) {
                this.identity.setId(id);
            } else {
                this.identity = Identity.builder().id(id).build();
            }
        }
    }

    public void setAccountId(Long accountId) {
        if (accountId != null) {
            if (this.identity != null) {
                this.identity.setAccountId(accountId);
            } else {
                this.identity = Identity.builder().accountId(accountId).build();
            }
        }
    }
}

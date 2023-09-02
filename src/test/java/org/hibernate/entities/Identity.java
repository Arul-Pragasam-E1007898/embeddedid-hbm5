package org.hibernate.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Identity
        implements Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identity identity = (Identity) o;
        if ((identity.accountId == null) || (identity.id == null)) {
            return false;
        }
        return Objects.equals(id, identity.id) && Objects.equals(accountId, identity.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId);
    }
}

package org.hibernate.entities.user;

import java.io.Serializable;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "fd_multitenant_user_emails")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FdMultitenantUserEmail
        implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_id")
    private Long accountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "primary_email")
    private Boolean primaryEmail;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false, insertable = false)
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private UserProfile userProfile;
}

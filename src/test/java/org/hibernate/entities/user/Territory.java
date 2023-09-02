package org.hibernate.entities.user;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "territories")
public class Territory
        implements Serializable{
    @NotNull
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "va_rule_id")
    private Long vaRuleId;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "position")
    private Integer position;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @NotNull
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "updater_id")
    private Long updaterId;

    @OneToMany(mappedBy = "territory")
    private Set<TerritoryUser> territoryUser;
}

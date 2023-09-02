package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.entities.Identity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contacts")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@SQLInsert( sql = "insert \n" +
    "    into\n" +
    "        contacts\n" +
    "        (active_sales_sequences, additional_info, address, campaign_id, city, completed_sales_sequences, contact_status_id, country, created_at, creater_id, customer_fit, department, display_name, do_not_disturb, email_status, external_id, facebook, first_assigned_at, first_campaign, first_contacted, first_medium, first_name, first_seen_chat, first_source, fullcontact_detail_id, has_authority, import_csv_id, import_id, is_deleted, job_title, keyword, last_assigned_at, last_campaign, last_contacted, last_contacted_mode, last_contacted_sales_activity_mode, last_contacted_via_chat, last_contacted_via_sales_activity, last_medium, last_name, last_seen, last_seen_chat, last_source, latest_campaign, latest_medium, latest_source, lead_score, lead_score_expiry, lead_source_id, lifecycle_stage_id, linkedin, locale, lost_reason_id, mcr_id, mcr_version, medium, merged_to, mobile_number, open_deals_amount, other_unsubscription_reason, owner_id, recent_note, record_type_id, sales_account_id, sms_subscription_status, state, status, subscription_status, territory_id, time_zone, total_sessions, twitter, unsubscription_reason, updated_at, updater_id, web_form_ids, whatsapp_subscription_status, won_deals_amount, work_email, work_number, zipcode, account_id, id) \n" +
    "    values\n" +
    "        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
public class SalesContact
        implements Serializable {
    @NotNull
    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @JsonIgnore
    @EmbeddedId
    private Identity contactId = Identity.builder().build();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "department")
    private String department;

    @Column(name = "sales_account_id")
    private Long salesAccountId;

    @Column(name = "work_number")
    private String workNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "country")
    private String country;

    @Column(name = "lead_score")
    @Builder.Default
    private Integer leadScore = 0;

    @Column(name = "lead_source_id")
    private Long leadSourceId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "creater_id")
    private Long createrId;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "last_contacted_mode")
    private Integer lastContactedMode;

    @Column(name = "last_contacted")
    private Instant lastContacted;

    @Column(name = "last_seen")
    private Instant lastSeen;

    @Column(name = "email_status")
    @Setter private Integer emailStatus;

    @NotNull
    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = false;

    @NotNull
    @Column(name = "has_authority")
    @Builder.Default
    private boolean hasAuthority = false;

    @NotNull
    @Column(name = "do_not_disturb")
    @Builder.Default
    @Setter private boolean doNotDisturb = false;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "linkedin")
    private String linkedin;

    @Lob
    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "fullcontact_detail_id")
    private Long fullcontactDetailId;

    @Column(name = "medium")
    private String medium;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "lead_score_expiry")
    private Instant leadScoreExpiry;

    @Column(name = "open_deals_amount", precision = 15, scale = 4)
    @Builder.Default
    private BigDecimal openDealsAmount = BigDecimal.valueOf(0.0000);

    @Column(name = "display_name")
    @Setter String displayName;

    @Column(name = "import_id")
    private String importId;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "merged_to")
    @Builder.Default
    private Long mergedTo = 0L;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @Column(name = "first_assigned_at")
    @Setter private Instant firstAssignedAt;

    @Column(name = "first_contacted")
    private Instant firstContacted;

    @Column(name = "won_deals_amount", precision = 15, scale = 4)
    @Builder.Default
    private BigDecimal wonDealsAmount = BigDecimal.valueOf(0.0000);

    @Column(name = "contact_status_id")
    private Long contactStatusId;

    @Lob
    @Column(name = "recent_note")
    private String recentNote;

    @Column(name = "last_contacted_via_chat")
    private Instant lastContactedViaChat;

    @Column(name = "last_contacted_via_sales_activity")
    private Instant lastContactedViaSalesActivity;

    @Column(name = "last_contacted_sales_activity_mode")
    private Long lastContactedSalesActivityMode;

    @Column(name = "active_sales_sequences", length = 1000)
    private String activeSalesSequences;

    @Column(name = "completed_sales_sequences", length = 1000)
    private String completedSalesSequences;

    @Lob
    @Column(name = "web_form_ids")
    private String webFormIds;

    @Lob
    @Column(name = "import_csv_id")
    private String importCsvId;

    @Column(name = "territory_id")
    private Long territoryId;

    @Column(name = "last_assigned_at")
    private Instant lastAssignedAt;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "mcr_id")
    private Long mcrId;

    @Column(name = "mcr_version")
    private Long mcrVersion;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "customer_fit")
    @Builder.Default
    private Integer customerFit = 0;

    @Column(name = "subscription_status")
    private Integer subscriptionStatus;

    @Column(name = "lifecycle_stage_id")
    private Long lifecycleStageId;

    @Column(name = "lost_reason_id")
    private Long lostReasonId;

    @Column(name = "record_type_id")
    private Long recordTypeId;

    @Column(name = "whatsapp_subscription_status")
    private Integer whatsappSubscriptionStatus;

    @Column(name = "sms_subscription_status")
    private Integer smsSubscriptionStatus;

    @Column(name = "last_seen_chat")
    private Instant lastSeenChat;

    @Column(name = "first_seen_chat")
    private Instant firstSeenChat;

    @Lob
    @Column(name = "locale")
    private String locale;

    @Column(name = "total_sessions")
    private Integer totalSessions;

    @Lob
    @Column(name = "first_campaign")
    private String firstCampaign;

    @Lob
    @Column(name = "first_medium")
    private String firstMedium;

    @Lob
    @Column(name = "first_source")
    private String firstSource;

    @Lob
    @Column(name = "last_campaign")
    private String lastCampaign;

    @Lob
    @Column(name = "last_medium")
    private String lastMedium;

    @Lob
    @Column(name = "last_source")
    private String lastSource;

    @Lob
    @Column(name = "latest_campaign")
    private String latestCampaign;

    @Lob
    @Column(name = "latest_medium")
    private String latestMedium;

    @Lob
    @Column(name = "latest_source")
    private String latestSource;

    @Column(name = "unsubscription_reason")
    private Integer unsubscriptionReason;

    @Lob
    @Column(name = "other_unsubscription_reason")
    private String otherUnsubscriptionReason;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContactEmail> emails;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContactPhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContactListAssociation> lists;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ContactSalesAccount> salesAccounts;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ContactTagAssociation> tags;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ContactTeamUser> teamUsers;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ContactNoteAssociation> notes;

    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
    private ContactCustomField contactCustomFields = new ContactCustomField();

    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
    private ContactCustomField2 contactCustomField2 = new ContactCustomField2();

    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
    private ContactSubscription contactSubscription = new ContactSubscription();

    @Transient
    private transient SalesContact previousState;

    public void setContactCustomFields(ContactCustomField contactCustomField) {
        contactCustomField.setAccountId(this.getAccountId());
        this.contactCustomFields = contactCustomField;
        this.contactCustomFields.setContact(this);
    }

    public void setContactCustomField2(ContactCustomField2 contactCustomField2) {
        contactCustomField2.setAccountId(this.getAccountId());
        this.contactCustomField2 = contactCustomField2;
        this.contactCustomField2.setContact(this);
    }

    public void setContactSubscription(ContactSubscription contactSubscription) {
        if (contactSubscription != null) {
            contactSubscription.setAccountId(this.getAccountId());
            this.contactSubscription = contactSubscription;
            this.contactSubscription.setContact(this);
        }
    }

    public void addContactEmail(ContactEmail email) {
        if (email != null) {
            emails = emails != null ? emails : new HashSet<>();
            emails.add(email);
            email.setContact(this);
        }
    }

    public void addContactPhone(ContactPhoneNumber phoneNumber) {
        if (phoneNumber != null) {
            phoneNumbers = phoneNumbers != null ? phoneNumbers : new HashSet<>();
            phoneNumbers.add(phoneNumber);
            phoneNumber.setContact(this);
        }
    }

    public void addContactListAssociation(ContactListAssociation contactListAssociation) {
        if (contactListAssociation != null) {
            lists = lists != null ? lists : new HashSet<>();
            lists.add(contactListAssociation);
            contactListAssociation.setContact(this);
        }
    }

    public void addSalesAccount(ContactSalesAccount contactSalesAccount) {
        if (contactSalesAccount != null) {
            salesAccounts = salesAccounts != null ? salesAccounts : new HashSet<>();
            salesAccounts.add(contactSalesAccount);
            contactSalesAccount.setContact(this);
        }
    }

    public void addContactTagAssociation(ContactTagAssociation contactTagAssociation) {
        if (contactTagAssociation != null) {
            tags = tags != null ? tags : new HashSet<>();
            tags.add(contactTagAssociation);
            contactTagAssociation.setContact(this);
        }
    }

    public void addContactNoteAssociation(ContactNoteAssociation contactNoteAssociation) {
        if (contactNoteAssociation != null) {
            notes = notes != null ? notes : new HashSet<>();
            notes.add(contactNoteAssociation);
            contactNoteAssociation.setContact(this);
        }
    }

    public Long getId() {
        return contactId != null ? contactId.getId() : null;
    }

    public Long getAccountId() {
        return contactId != null ? contactId.getAccountId() : null;
    }

    public void setId(Long id) {
        if (contactId == null) {
            this.contactId = Identity.builder().build();
        }
        contactId.setId(id);
    }

    public void setAccountId(Long accountId) {
        if (contactId == null) {
            this.contactId = Identity.builder().build();
        }
        contactId.setAccountId(accountId);
    }


    public static class SalesContactBuilder {
        private Identity contactId;

        public SalesContactBuilder id(Long id) {
            this.contactId = Identity.builder()
                    .id(id)
                    .accountId(this.contactId != null ? this.contactId.getAccountId() : null)
                    .build();
            return this;
        }

        public SalesContactBuilder accountId(Long accountId) {
            this.contactId = Identity.builder()
                    .id(this.contactId != null ? this.contactId.getId() : null)
                    .accountId(accountId)
                    .build();
            return this;
        }
    }
}

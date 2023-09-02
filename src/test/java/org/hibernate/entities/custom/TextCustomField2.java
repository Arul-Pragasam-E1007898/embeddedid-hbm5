package org.hibernate.entities.custom;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("id")
public class TextCustomField2
        implements Serializable {
    @Lob
    @Column(name = "cf_text41")
    private String cfText41;

    @Lob
    @Column(name = "cf_text42")
    private String cfText42;

    @Lob
    @Column(name = "cf_text43")
    private String cfText43;

    @Lob
    @Column(name = "cf_text44")
    private String cfText44;

    @Lob
    @Column(name = "cf_text45")
    private String cfText45;

    @Lob
    @Column(name = "cf_text46")
    private String cfText46;

    @Lob
    @Column(name = "cf_text47")
    private String cfText47;

    @Lob
    @Column(name = "cf_text48")
    private String cfText48;

    @Lob
    @Column(name = "cf_text49")
    private String cfText49;

    @Lob
    @Column(name = "cf_text50")
    private String cfText50;
}

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
public class TextCustomFields
        implements Serializable {
    @Lob
    @Column(name = "cf_text01")
    private String cfText01;

    @Lob
    @Column(name = "cf_text02")
    private String cfText02;

    @Lob
    @Column(name = "cf_text03")
    private String cfText03;

    @Lob
    @Column(name = "cf_text04")
    private String cfText04;

    @Lob
    @Column(name = "cf_text05")
    private String cfText05;

    @Lob
    @Column(name = "cf_text06")
    private String cfText06;

    @Lob
    @Column(name = "cf_text07")
    private String cfText07;

    @Lob
    @Column(name = "cf_text08")
    private String cfText08;

    @Lob
    @Column(name = "cf_text09")
    private String cfText09;

    @Lob
    @Column(name = "cf_text10")
    private String cfText10;

    @Lob
    @Column(name = "cf_text11")
    private String cfText11;

    @Lob
    @Column(name = "cf_text12")
    private String cfText12;

    @Lob
    @Column(name = "cf_text13")
    private String cfText13;

    @Lob
    @Column(name = "cf_text14")
    private String cfText14;

    @Lob
    @Column(name = "cf_text15")
    private String cfText15;

    @Lob
    @Column(name = "cf_text16")
    private String cfText16;

    @Lob
    @Column(name = "cf_text17")
    private String cfText17;

    @Lob
    @Column(name = "cf_text18")
    private String cfText18;

    @Lob
    @Column(name = "cf_text19")
    private String cfText19;

    @Lob
    @Column(name = "cf_text20")
    private String cfText20;

    @Lob
    @Column(name = "cf_text21")
    private String cfText21;

    @Lob
    @Column(name = "cf_text22")
    private String cfText22;

    @Lob
    @Column(name = "cf_text23")
    private String cfText23;

    @Lob
    @Column(name = "cf_text24")
    private String cfText24;

    @Lob
    @Column(name = "cf_text25")
    private String cfText25;

    @Lob
    @Column(name = "cf_text26")
    private String cfText26;

    @Lob
    @Column(name = "cf_text27")
    private String cfText27;

    @Lob
    @Column(name = "cf_text28")
    private String cfText28;

    @Lob
    @Column(name = "cf_text29")
    private String cfText29;

    @Lob
    @Column(name = "cf_text30")
    private String cfText30;

    @Lob
    @Column(name = "cf_text31")
    private String cfText31;

    @Lob
    @Column(name = "cf_text32")
    private String cfText32;

    @Lob
    @Column(name = "cf_text33")
    private String cfText33;

    @Lob
    @Column(name = "cf_text34")
    private String cfText34;

    @Lob
    @Column(name = "cf_text35")
    private String cfText35;

    @Lob
    @Column(name = "cf_text36")
    private String cfText36;

    @Lob
    @Column(name = "cf_text37")
    private String cfText37;

    @Lob
    @Column(name = "cf_text38")
    private String cfText38;

    @Lob
    @Column(name = "cf_text39")
    private String cfText39;

    @Lob
    @Column(name = "cf_text40")
    private String cfText40;
}

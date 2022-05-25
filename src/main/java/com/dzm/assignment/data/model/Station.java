package com.dzm.assignment.data.model;

import lombok.Data;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * A Station
 */
@Entity
@Table
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private Double latitude;

    private Double longitude;

    @Column(name = "company_id", insertable = false, updatable = false)
    private Long companyId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @LazyToOne(LazyToOneOption.PROXY)
    @LazyGroup("company")
    private Company company;
}
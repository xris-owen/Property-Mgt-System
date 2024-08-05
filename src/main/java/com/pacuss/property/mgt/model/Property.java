package com.pacuss.property.mgt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String address;
    private BigDecimal pricePerNight;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean isAvailable;
    private boolean drinkAllowed;
    private boolean petAllowed;
    private int maxCheckoutTimeInNights;
    private BigDecimal extraCharges;

    @OneToMany(mappedBy = "property")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}



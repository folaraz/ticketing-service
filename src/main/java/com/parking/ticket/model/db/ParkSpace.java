package com.parking.ticket.model.db;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "park_spaces")
public class ParkSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private String parkingSpaceName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkSpace")
    private final List<Booking> bookings = new ArrayList<>();

    private boolean isAvailable;


    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getParkingSpaceName() {
        return parkingSpaceName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

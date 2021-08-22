package com.parking.ticket.model.db;

import com.parking.ticket.model.constants.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private int capacity;

    private double ratePerHour;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<ParkSpace> parkSpaces;

    public Long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }
}

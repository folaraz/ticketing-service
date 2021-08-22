package com.parking.ticket.repository;

import com.parking.ticket.model.constants.VehicleType;
import com.parking.ticket.model.db.ParkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkSpaceRepository extends JpaRepository<ParkSpace, Long> {

    @Query("SELECT p FROM ParkSpace p WHERE p.isAvailable = :isAvailable")
    List<ParkSpace> findParkingSpacesByAvailability(@Param("isAvailable") boolean isAvailable);

    @Query("SELECT p FROM ParkSpace p WHERE p.isAvailable = :isAvailable and p.vehicle.vehicleType = :vehicleType")
    List<ParkSpace> findParkingSpacesByVehicleTypeAndAvailability(@Param("vehicleType") VehicleType vehicleType, @Param("isAvailable") boolean isAvailable);

}

package com.parking.ticket.service.parkspace;

import com.parking.ticket.model.constants.VehicleType;
import com.parking.ticket.model.db.ParkSpace;
import com.parking.ticket.repository.ParkSpaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkSpacesRetriever {

    private static final Logger log = LoggerFactory.getLogger(ParkSpacesRetriever.class);

    private final ParkSpaceRepository parkSpaceRepository;

    public ParkSpacesRetriever(ParkSpaceRepository parkSpaceRepository) {
        this.parkSpaceRepository = parkSpaceRepository;
    }


    public List<ParkSpace> getParkingSpacesByAvailability(boolean isAvailable) {
        return parkSpaceRepository.findParkingSpacesByAvailability(isAvailable);
    }

    public List<ParkSpace> getParkingSpacesByAvailabilityAndVehicleType(boolean isAvailable, VehicleType vehicleType) {
        return parkSpaceRepository.findParkingSpacesByVehicleTypeAndAvailability(vehicleType, isAvailable);
    }

    public List<ParkSpace> getAllParkingSpaces() {
        return parkSpaceRepository.findAll();
    }

}

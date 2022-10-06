package parking.rest.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import parking.rest.models.ParkingSpotModel;


@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    boolean existsByLicensePlateCar(String licensePlateCar);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByBlock(String block);
}

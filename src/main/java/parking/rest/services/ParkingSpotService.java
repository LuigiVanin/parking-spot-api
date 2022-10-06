package parking.rest.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import parking.rest.models.ParkingSpotModel;
import parking.rest.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {
    
    final ParkingSpotRepository parkingSpotRepo;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepo) {
        this.parkingSpotRepo = parkingSpotRepo;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepo.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepo.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepo.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByBlock(String block) {
        return parkingSpotRepo.existsByBlock(block);
    }

    public ParkingSpotModel findAll() {
        return null;
    }

    public List<ParkingSpotModel> findAllParkingSpot() {
        return parkingSpotRepo.findAll();
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepo.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepo.delete(parkingSpotModel);
    }

    public void update(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepo.save(parkingSpotModel);
    }
}

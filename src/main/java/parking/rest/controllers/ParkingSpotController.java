package parking.rest.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import parking.rest.dto.ParkingSpotDto;
import parking.rest.models.ParkingSpotModel;
import parking.rest.services.ParkingSpotService;

@RestController
@RequestMapping("/parking")
@CrossOrigin(origins = "*")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> postParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpot) {
        if(parkingSpotService.existsByLicensePlateCar(parkingSpot.getLicensePlateCar())){
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict: License Plate Car is already in use!");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpot.getParkingSpotNumber())){
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict: Parking Spot is already in use!");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpot, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping("/")
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpot(
        @PageableDefault(
            page = 0,
            size = 10, 
            sort = "id", 
            direction = Sort.Direction.ASC) Pageable pageable
        ) {
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(parkingSpotService.findAllParkingSpot(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParkingSpotById(
        @PathVariable(value = "id") UUID id
    ) {
        Optional<ParkingSpotModel> model = parkingSpotService.findById(id);
        if(!model.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Parking Spot not found.");
        }
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(model.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> model = parkingSpotService.findById(id);
        if(!model.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Parking Spot not found.");
        }
        parkingSpotService.delete(model.get());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Item was succefully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(
        @PathVariable(value = "id") UUID id,
        @RequestBody @Valid ParkingSpotDto parkingSpotDto
    ) {
        Optional<ParkingSpotModel> model = parkingSpotService.findById(id);
        if(!model.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)   
                .body("Parking Spot not found.");
        }
        var toUpdate = model.get();
        toUpdate.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        toUpdate.setBlock(parkingSpotDto.getBlock());
        toUpdate.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        toUpdate.setcarColor(parkingSpotDto.getCarColor());
        toUpdate.setcarModel(parkingSpotDto.getCarModel());
        toUpdate.setcarBrand(parkingSpotDto.getCarBrand());
        parkingSpotService.save(toUpdate);
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body("Recurso alterado com sucesso!");
    }
}

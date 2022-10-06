package parking.rest.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "parking_spot")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false, unique = true, length = 10, name = "parking_spot_number")
    private String parkingSpotNumber;

    @Column(nullable = false, unique = true, length = 7, name = "license_plate_car")
    private String licensePlateCar;

    @Column(nullable = false, length = 70, name = "car_brand")
    private String carBrand;

    @Column(nullable = false, length = 70, name = "car_model")
    private String carModel;

    @Column(nullable = false, length = 70, name = "car_color")
    private String carColor;

    @Column(nullable = false, name = "registration_datec")
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 130, name = "responsible_name")
    private String responsibleName;

    @Column(nullable = false, length = 30)
    private String block;
    
    public UUID getId() {    
        return id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getcarBrand() {
        return this.carBrand;
    }

    public void setcarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getcarModel() {
        return carModel;
    }

    public void setcarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getcarColor() {
        return carColor;
    }

    public void setcarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setRegistrationDate(LocalDateTime now) {
        this.registrationDate = now;
    }

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }
}

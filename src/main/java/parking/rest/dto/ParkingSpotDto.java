package parking.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDto {
    @NotBlank
    private String parkingSpotNumber;

    
    @NotBlank
    @Size(min = 7, max = 7)
    private String licensePlateCar;
    
    
    @NotBlank
    private String carBrand;

    
    @NotBlank
    private String carModel;
    
    @NotBlank
    private String carColor;
    
    
    @NotBlank
    private String responsibleName;
    
    
    @NotBlank
    private String block;
    
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
    
    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBlock() {
        return block;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }
    
    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public Object getApartment() {
        return null;
    }
}

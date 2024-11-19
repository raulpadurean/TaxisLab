package org.example.models;

/**
 * Represents a car in the system.
 * A {@code Car} object contains information about the car's brand, model, plate number, and the associated driver.
 * Implements the {@link HasId} interface, which requires a unique identifier for the car.
 */
public class Car implements HasId {

    private int id;
    private String brand;
    private String model;
    private String plateNr;
    private int driverId;

    /**
     * Constructs a new {@code Car} with the specified parameters.
     *
     * @param id        the unique identifier of the car
     * @param brand     the brand of the car
     * @param model     the model of the car
     * @param plateNr   the plate number of the car
     * @param driverId  the ID of the driver associated with the car
     */
    public Car(int id, String brand, String model, String plateNr, int driverId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNr = plateNr;
        this.driverId = driverId;
    }

    /**
     * Returns the unique identifier of the car.
     *
     * @return the unique identifier of the car
     */

    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the car.
     *
     * @param id the unique identifier to set
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the brand of the car.
     *
     * @return the brand of the car
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the car.
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the model of the car.
     *
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the plate number of the car.
     *
     * @return the plate number of the car
     */
    public String getPlateNr() {
        return plateNr;
    }

    /**
     * Sets the plate number of the car.
     *
     * @param plateNr the plate number to set
     */
    public void setPlateNr(String plateNr) {
        this.plateNr = plateNr;
    }

    /**
     * Returns the driver ID associated with the car.
     *
     * @return the driver ID associated with the car
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * Sets the driver ID associated with the car.
     *
     * @param driverId the driver ID to set
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * Returns a string representation of the car object.
     * The string includes the car's ID, brand, model, plate number, and the associated driver ID.
     *
     * @return a string representation of the car object
     */
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plateNr='" + plateNr + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}

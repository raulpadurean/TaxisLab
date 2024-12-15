package org.example.models;

/**
 * Represents a car with attributes such as ID, brand, model, plate number, and the associated driver.
 * This class implements the {@link HasId} interface, meaning each car has a unique identifier.
 */
public class Car implements HasId {

    private Integer id;
    private String brand;
    private String model;
    private String plateNr;
    private Driver driver;

    /**
     * Constructs a new Car object with the specified ID, brand, model, plate number, and driver.
     *
     * @param id        The unique identifier for the car.
     * @param brand     The brand of the car.
     * @param model     The model of the car.
     * @param plateNr   The plate number of the car.
     * @param driver    The driver associated with the car.
     */
    public Car(Integer id, String brand, String model, String plateNr, Driver driver) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNr = plateNr;
        this.driver = driver;
    }

    public Car() {}

    /**
     * Retrieves the unique identifier of the car.
     *
     * @return The ID of the car.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the car.
     *
     * @param id The ID to set for the car.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the brand of the car.
     *
     * @return The brand of the car.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the car.
     *
     * @param brand The brand to set for the car.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Retrieves the model of the car.
     *
     * @return The model of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model The model to set for the car.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Retrieves the plate number of the car.
     *
     * @return The plate number of the car.
     */
    public String getPlateNr() {
        return plateNr;
    }

    /**
     * Sets the plate number of the car.
     *
     * @param plateNr The plate number to set for the car.
     */
    public void setPlateNr(String plateNr) {
        this.plateNr = plateNr;
    }

    /**
     * Retrieves the driver associated with the car.
     *
     * @return The driver associated with the car.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets the driver associated with the car.
     *
     * @param driver The driver to associate with the car.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Returns a string representation of the car in the format:
     * "ID,Brand,Model,PlateNr,Driver".
     *
     * @return A string representation of the car object.
     */
    @Override
    public String toString() {
        return id + ";" + brand + ";" + model + ";" + plateNr + ";" + driver;
    }

    /**
     * Parses a string representing a car object and returns a new Car instance.
     * The string format is expected to be:
     * "ID,Brand,Model,PlateNr,Driver".
     *
     * @param line The string representation of the car object.
     * @return A new Car object created from the string data.
     */

    public static Car parse(String line) {
        String[] fields = line.split(";");


        if (fields.length != 9) {
            throw new IllegalArgumentException("CSV line does not have the expected number of fields. Found: " + fields.length);
        }
        try{
            Integer id = Integer.parseInt(fields[0]); // Review ID

            String brand = fields[1];
            String model = fields[2];
            String plateNr = fields[3];


            Driver driver = new Driver(
                    Integer.parseInt(fields[4]),  // ID
                    fields[5],                    // Name
                    fields[6],                    // Email
                    fields[7],                    // Phone
                    fields[8]                     // Address
            );
            return new Car(id,brand,model,plateNr,driver);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        // Assuming Driver has a parse method to create a Driver object from a string

    }
}

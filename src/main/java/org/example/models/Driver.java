package org.example.models;

/**
 * Represents a driver with basic personal information, including ID, name, email, address, and phone number.
 * Implements the {@link HasId} interface to ensure each driver has a unique identifier.
 */
public class Driver implements HasId {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;

    /**
     * Constructs a new Driver object with the specified parameters.
     *
     * @param id      The unique identifier for the driver.
     * @param name    The name of the driver.
     * @param email   The email address of the driver.
     * @param address The address of the driver.
     * @param phone   The phone number of the driver.
     */
    public Driver(Integer id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Retrieves the unique identifier for the driver.
     *
     * @return The ID of the driver.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the driver.
     *
     * @param id The ID to set for the driver.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the driver.
     *
     * @return The name of the driver.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the driver.
     *
     * @param name The name to set for the driver.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the driver.
     *
     * @return The email address of the driver.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the driver.
     *
     * @param email The email to set for the driver.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the address of the driver.
     *
     * @return The address of the driver.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the driver.
     *
     * @param address The address to set for the driver.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the driver.
     *
     * @return The phone number of the driver.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the driver.
     *
     * @param phone The phone number to set for the driver.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the Driver object in the format:
     * "id;name;email;address;phone".
     *
     * @return A string representation of the Driver instance.
     */
    @Override
    public String toString() {
        return id + ";" + name + ";" + email + ";" + address + ";" + phone;
    }

    /**
     * Parses a string representation of a Driver object and returns a new Driver instance.
     * The expected string format is:
     * "id;name;email;address;phone".
     *
     * @param line The string representation of the Driver object.
     * @return A new Driver object created from the string data.
     * @throws NumberFormatException If the ID is not a valid number.
     */
    public static Driver parse(String line) {
        String[] fields = line.split(";");
        return new Driver(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                fields[4]
        );
    }
}

package org.example.models;

/**
 * Represents a driver in the system.
 * A driver has a unique identifier, personal information such as name and contact details,
 * and is associated with a transportation service.
 */
public class Driver implements HasId {

    /**
     * The unique identifier for the driver.
     */
    private int id;

    /**
     * The name of the driver.
     */
    private String name;

    /**
     * The email address of the driver.
     */
    private String email;

    /**
     * The physical address of the driver.
     */
    private String address;

    /**
     * The phone number of the driver.
     */
    private String phone;

    /**
     * Constructs a new {@code Driver} with the specified attributes.
     *
     * @param id      the unique identifier of the driver
     * @param name    the name of the driver
     * @param email   the email address of the driver
     * @param address the physical address of the driver
     * @param phone   the phone number of the driver
     */
    public Driver(int id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Retrieves the unique identifier of the driver.
     *
     * @return the driver's unique ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the driver.
     *
     * @param id the new ID to set for the driver
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the driver.
     *
     * @return the name of the driver
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the driver.
     *
     * @param name the new name for the driver
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the driver.
     *
     * @return the email address of the driver
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email address of the driver.
     *
     * @param email the new email address for the driver
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the physical address of the driver.
     *
     * @return the address of the driver
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the physical address of the driver.
     *
     * @param address the new address for the driver
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the driver.
     *
     * @return the phone number of the driver
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the phone number of the driver.
     *
     * @param phone the new phone number for the driver
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the {@code Driver} object.
     * The string includes the driver's ID, name, email, address, and phone number.
     *
     * @return a string representation of the {@code Driver} object
     */
    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

package org.example.models;

/**
 * Represents a client in the system.
 * A {@code Client} object contains information about the client's identity, contact details, and address.
 * Implements the {@link HasId} interface, which requires a unique identifier for the client.
 */
public class Client implements HasId {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;

    /**
     * Constructs a new {@code Client} with the specified parameters.
     *
     * @param id       the unique identifier of the client
     * @param name     the name of the client
     * @param email    the email address of the client
     * @param address  the physical address of the client
     * @param phone    the phone number of the client
     */
    public Client(Integer id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Returns the unique identifier of the client.
     *
     * @return the unique identifier of the client
     */

    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id the unique identifier to set
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address of the client.
     *
     * @return the email address of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the physical address of the client.
     *
     * @return the physical address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the physical address of the client.
     *
     * @param address the physical address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the client.
     *
     * @return the phone number of the client
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the client object.
     * The string includes the client's ID, name, email, address, and phone number.
     *
     * @return a string representation of the client object
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

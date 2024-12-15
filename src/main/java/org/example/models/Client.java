package org.example.models;

/**
 * Represents a client with attributes such as ID, name, email, address, and phone number.
 * This class implements the {@link HasId} interface, meaning each client has a unique identifier.
 */
public class Client implements HasId {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;

    /**
     * Constructs a new Client object with the specified ID, name, email, address, and phone number.
     *
     * @param id      The unique identifier for the client.
     * @param name    The name of the client.
     * @param email   The email address of the client.
     * @param address The physical address of the client.
     * @param phone   The phone number of the client.
     */
    public Client(Integer id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }


    public Client() {}

    /**
     * Retrieves the unique identifier of the client.
     *
     * @return The ID of the client.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id The ID to set for the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The name to set for the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the client.
     *
     * @return The email address of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email The email address to set for the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the physical address of the client.
     *
     * @return The address of the client.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the physical address of the client.
     *
     * @param address The address to set for the client.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone The phone number to set for the client.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the client in the format:
     * "ID;Name;Email;Address;Phone".
     *
     * @return A string representation of the client object.
     */
    @Override
    public String toString() {
        return id + ";" + name + ";" + email + ";" + address + ";" + phone;
    }

    /**
     * Parses a string representing a client object and returns a new Client instance.
     * The string format is expected to be:
     * "ID;Name;Email;Address;Phone".
     *
     * @param line The string representation of the client object.
     * @return A new Client object created from the string data.
     */
    public static Client parse(String line) {
        String[] fields = line.split(";");
        return new Client(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                fields[4]
        );
    }
}

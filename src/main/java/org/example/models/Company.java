package org.example.models;

/**
 * Represents a company with attributes such as ID, name, email, address, and phone number.
 * This class implements the {@link HasId} interface, meaning each company has a unique identifier.
 */
public class Company implements HasId {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;

    /**
     * Constructs a new Company object with the specified ID, name, email, address, and phone number.
     *
     * @param id      The unique identifier for the company.
     * @param name    The name of the company.
     * @param email   The email address of the company.
     * @param address The physical address of the company.
     * @param phone   The phone number of the company.
     */
    public Company(Integer id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Retrieves the unique identifier of the company.
     *
     * @return The ID of the company.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the company.
     *
     * @param id The ID to set for the company.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the company.
     *
     * @return The name of the company.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the company.
     *
     * @param name The name to set for the company.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the company.
     *
     * @return The email address of the company.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the company.
     *
     * @param email The email address to set for the company.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the physical address of the company.
     *
     * @return The address of the company.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the physical address of the company.
     *
     * @param address The address to set for the company.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the company.
     *
     * @return The phone number of the company.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the company.
     *
     * @param phone The phone number to set for the company.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the company in the format:
     * "ID;Name;Email;Address;Phone".
     *
     * @return A string representation of the company object.
     */
    @Override
    public String toString() {
        return id + ";" + name + ";" + email + ";" + address + ";" + phone;
    }

    /**
     * Parses a string representing a company object and returns a new Company instance.
     * The string format is expected to be:
     * "ID;Name;Email;Address;Phone".
     *
     * @param line The string representation of the company object.
     * @return A new Company object created from the string data.
     */
    public static Company parse(String line) {
        String[] fields = line.split(";");
        return new Company(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                fields[4]
        );
    }
}

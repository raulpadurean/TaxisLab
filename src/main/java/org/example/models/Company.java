package org.example.models;

/**
 * Represents a company in the system.
 * A {@code Company} object contains information about the company's identity, contact details, and address.
 * Implements the {@link HasId} interface, which ensures a unique identifier for each company.
 */
public class Company implements HasId {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;

    /**
     * Constructs a new {@code Company} with the specified parameters.
     *
     * @param id      the unique identifier of the company
     * @param name    the name of the company
     * @param email   the email address of the company
     * @param address the physical address of the company
     * @param phone   the phone number of the company
     */
    public Company(Integer id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Returns the unique identifier of the company.
     *
     * @return the unique identifier of the company
     */

    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the company.
     *
     * @param id the unique identifier to set
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the company.
     *
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the company.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address of the company.
     *
     * @return the email address of the company
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the company.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the physical address of the company.
     *
     * @return the physical address of the company
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the physical address of the company.
     *
     * @param address the physical address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the company.
     *
     * @return the phone number of the company
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the company.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the company object.
     * The string includes the company's ID, name, email, address, and phone number.
     *
     * @return a string representation of the company object
     */
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

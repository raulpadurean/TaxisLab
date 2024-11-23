package org.example.models;

public class Driver implements HasId {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;

    public Driver(int id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + email + ";" + address + ";" + phone;
    }

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


import org.example.models.Car;
import org.example.models.Client;
import org.example.models.Driver;
import org.example.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {


    Car car;
    Driver driver;
    private static List<Driver> driverList;
    private static Client client;
    private static List<Client> clientList;
    private static BasicService basicService;
    private static CustomService customService;
    private static List<Service> serviceList;
    private static List<Service> serviceCustomList;
    private static Company company;
    private static List<Company> companyList;
    private static CompanyDriver companyDriver;
    private static DriverSchedule driverSchedule;
    private static List<DriverSchedule> scheduleList;
    private static Order order;
    private static List<Order> orderList;
    private static Review review;
    private static Review newReview;



    @BeforeEach
    void setUp() {

        car = new Car(1, "Toyota", "GR", "CJ00BOS", driver);

        driver = new Driver(101, "Raul", "raul@gmail.com", "Dima", "0777");
        driverList = new ArrayList<>();
        driverList.add(driver);

        client = new Client(1, "Ion", "ion@gmail.com", "Dabc", "1234");
        clientList = new ArrayList<>();
        clientList.add(client);

        basicService = new BasicService(1, "Pritax", 2.0);
        serviceList = new ArrayList<>();
        serviceList.add(basicService);

        customService = new CustomService(1, "Exclusiv", 7.5, "Charging USB-C");
        serviceCustomList = new ArrayList<>();
        serviceCustomList.add(customService);

        company = new Company(1, "Pritax", "pritax@gmail.com", "Dabc", "9942");
        companyList = new ArrayList<>();
        companyList.add(company);

        companyDriver = new CompanyDriver(1, company, driver);


        driverSchedule = new DriverSchedule(1, driver, company, LocalDateTime.now().minusHours(2), LocalDateTime.now());
        scheduleList = new ArrayList<>();
        scheduleList.add(driverSchedule);

        order = new Order(1, 100.0, basicService, client, driver, company, LocalDateTime.now());
        orderList = new ArrayList<>();
        orderList.add(order);

        review = new Review(1, 5, "Excelent", company, driver, client);

    }

    @Test
    void testCreateCar() {
        assertNotNull(car);
        assertEquals(1, car.getId());
        assertEquals("Toyota", car.getBrand());
        assertEquals("GR", car.getModel());
        assertEquals("CJ00BOS", car.getPlateNr());
        assertNotNull(driver);
        assertEquals("Raul", driver.getName());
    }

    @Test
    void testReadCarProperties() {
        assertEquals(1, car.getId());
        assertEquals("Toyota", car.getBrand());
        assertEquals("GR", car.getModel());
        assertEquals("CJ00BOS", car.getPlateNr());
        assertEquals("Raul", driver.getName());
    }

    @Test
    void testUpdateCarProperties() {
        car.setId(2);
        car.setBrand("Honda");
        car.setModel("Civic");
        car.setPlateNr("ABC987");
        car.setDriver(new Driver(102, "Jane", "jane@gmail.com", "Doja", "0999"));

        assertEquals(2, car.getId());
        assertEquals("Honda", car.getBrand());
        assertEquals("Civic", car.getModel());
        assertEquals("ABC987", car.getPlateNr());
        assertNotNull(car.getDriver());
        assertEquals("Jane", car.getDriver().getName());
    }

    @Test
    void testDeleteCar() {
        car = null;
        assertNull(car);
    }



    @Test
    void testCreateDriver() {
        // Create a new Driver and add to the list
        Driver newDriver = new Driver(102, "Jane", "jane@gmail.com", "Doja", "0999");
        driverList.add(newDriver);

        // Assert the new driver is added
        assertEquals(2, driverList.size());
        assertEquals(newDriver, driverList.get(1));
    }

    @Test
    void testReadDriverProperties() {
        // Verify the properties of the initial driver
        assertEquals(101, driver.getId());
        assertEquals("Raul", driver.getName());
        assertEquals("raul@gmail.com", driver.getEmail());
        assertEquals("0777", driver.getPhone());
        assertEquals("Dima", driver.getAddress());
    }

    @Test
    void testUpdateDriverProperties() {
        // Update the properties of the driver
        driver.setName("Janet");
        driver.setEmail("janet@gmail.com");
        driver.setPhone("0000");
        driver.setAddress("Duta");

        // Assert the updated properties
        assertEquals("Janet", driver.getName());
        assertEquals("janet@gmail.com", driver.getEmail());
        assertEquals("0000", driver.getPhone());
        assertEquals("Duta", driver.getAddress());
    }

    @Test
    void testDeleteDriver() {
        driverList.remove(driver);

        assertEquals(0, driverList.size());

        driverList.add(driver);
    }

    @Test
    void testCreateClient() {

        Client newClient = new Client(2, "Maria", "maria@gmail.com", "Dxyz", "5678");
        clientList.add(newClient);

        // Assert the new client is added
        assertEquals(2, clientList.size());
        assertEquals(newClient, clientList.get(1));
    }

    @Test
    void testReadClientProperties() {
        // Verify the properties of the initial client
        assertEquals(1, client.getId());
        assertEquals("Ion", client.getName());
        assertEquals("ion@gmail.com", client.getEmail());
        assertEquals("Dabc", client.getAddress());
        assertEquals("1234", client.getPhone());
    }

    @Test
    void testUpdateClientProperties() {
        // Update the properties of the client
        client.setName("Vasile");
        client.setEmail("vasile@gmail.com");
        client.setAddress("Dxyz");
        client.setPhone("8765");

        // Assert the updated properties
        assertEquals("Vasile", client.getName());
        assertEquals("vasile@gmail.com", client.getEmail());
        assertEquals("Dxyz", client.getAddress());
        assertEquals("8765", client.getPhone());
    }

    @Test
    void testDeleteClient() {
        clientList.remove(client);

        assertEquals(0, clientList.size());

        clientList.add(client);
    }

    @Test
    void testCreateBasicService() {
        // Create a new BasicService and add it to the list
        Service newService = new BasicService(2, "Taxi", 3.0);
        serviceList.add(newService);

        // Assert the new service is added
        assertEquals(2, serviceList.size());
        assertEquals(newService, serviceList.get(1));
    }

    @Test
    void testReadBasicServiceProperties() {
        // Verify the properties of the basic service
        assertEquals(1, basicService.getId());
        assertEquals("Pritax", basicService.getName());
        assertEquals(2.0, basicService.getPricePerKm());
        assertEquals(ServiceType.BASIC, basicService.getType());
    }

    @Test
    void testUpdateBasicServiceProperties() {
        // Update the properties of the basic service
        basicService.setName("Premium Taxi");
        basicService.setPricePerKm(3.5);

        // Assert the updated properties
        assertEquals("Premium Taxi", basicService.getName());
        assertEquals(3.5, basicService.getPricePerKm());
        assertEquals(ServiceType.BASIC, basicService.getType()); // Ensure type remains BASIC
    }

    @Test
    void testDeleteBasicService() {
        // Remove the basic service from the list
        serviceList.remove(basicService);

        // Assert the list no longer contains the basic service
        assertEquals(0, serviceList.size());
    }

    @Test
    void testCreateCustomService() {
        // Create a new CustomService and add it to the list
        Service newService = new CustomService(2, "VIP", 10.0, "Wi-Fi");
        serviceCustomList.add(newService);

        // Assert the new service is added
        assertEquals(2, serviceCustomList.size());
        assertEquals(newService, serviceCustomList.get(1));
    }


    @Test
    void testReadCustomServiceProperties() {
        // Verify the properties of the custom service
        assertEquals(1, customService.getId());
        assertEquals("Exclusiv", customService.getName());
        assertEquals(7.5, customService.getPricePerKm());
        assertEquals(ServiceType.CUSTOM, customService.getType());
        assertEquals("Charging USB-C", customService.getExtras());
    }

    @Test
    void testUpdateCustomServiceProperties() {
        // Update the properties of the custom service
        customService.setName("Premium");
        customService.setPricePerKm(9.0);
        customService.setExtras("Wi-Fi, Charging");

        // Assert the updated properties
        assertEquals("Premium", customService.getName());
        assertEquals(9.0, customService.getPricePerKm());
        assertEquals(ServiceType.CUSTOM, customService.getType()); // Ensure type remains CUSTOM
        assertEquals("Wi-Fi, Charging", customService.getExtras());
    }

    @Test
    void testDeleteCustomService() {
        // Remove the custom service from the list
        serviceCustomList.remove(customService);

        // Assert the list no longer contains the custom service
        assertEquals(0, serviceCustomList.size());
    }

    @Test
    void testCreateCompany() {
        // Create a new Company and add it to the list
        Company newCompany = new Company(2, "Maria", "maria@gmail.com", "Dxyz", "5678");
        companyList.add(newCompany);

        assertEquals(2, companyList.size());
        assertEquals(newCompany, companyList.get(1));
    }


    @Test
    void testReadCompanyProperties() {
        // Verify the properties of the company
        assertEquals(1, company.getId());
        assertEquals("Pritax", company.getName());
        assertEquals("pritax@gmail.com", company.getEmail());
        assertEquals("Dabc", company.getAddress());
        assertEquals("9942", company.getPhone());
    }

    @Test
    void testUpdateCompanyProperties() {
        // Update the properties of the company
        company.setName("Alexandru");
        company.setEmail("alexandru@gmail.com");
        company.setAddress("Ddef");
        company.setPhone("4321");

        // Assert the updated properties
        assertEquals("Alexandru", company.getName());
        assertEquals("alexandru@gmail.com", company.getEmail());
        assertEquals("Ddef", company.getAddress());
        assertEquals("4321", company.getPhone());
    }

    @Test
    void testDeleteCompany() {
        // Remove the company from the list
        companyList.remove(company);

        // Assert the list no longer contains the company
        assertEquals(0, companyList.size());
    }


    @Test
    void testCreateCompanyDriver() {
        // Create a new CompanyDriver
        Driver newDriver = new Driver(102, "Maria", "maria@gmail.com", "Daaa", "4567");
        Company newCompany = new Company(2, "Logi", "logi@gmail.com", "Dxyz", "5678");

        CompanyDriver newCompanyDriver = new CompanyDriver(2, newCompany, newDriver);

        // Verify the new CompanyDriver is created with proper references
        assertEquals(2, newCompanyDriver.getId());
        assertEquals(newDriver, newCompanyDriver.getDriver());
        assertEquals(newCompany, newCompanyDriver.getCompany());
    }

    @Test
    void testReadCompanyDriverProperties() {
        // Read the properties of the existing companyDriver and validate
        assertEquals(1, companyDriver.getId());
        assertEquals(driver, companyDriver.getDriver());
        assertEquals(company, companyDriver.getCompany());
    }

    @Test
    void testUpdateCompanyDriverProperties() {
        // Create a new driver and company for updating the companyDriver
        Driver newDriver = new Driver(103, "Ion", "ion@gmail.com", "Dima", "9988");
        Company newCompany = new Company(3, "Eco", "eco@gmail.com", "Dqwe", "8765");

        companyDriver.setDriver(newDriver);
        companyDriver.setCompany(newCompany);

        // Validate the updated properties
        assertEquals(newDriver, companyDriver.getDriver());
        assertEquals(newCompany, companyDriver.getCompany());
    }

    @Test
    void testDeleteCompanyDriver() {
        // Set the companyDriver to null to simulate deletion
        companyDriver = null;

        // Assert that the companyDriver is null (deleted)
        assertNull(companyDriver);
    }

    @Test
    void testCreateDriverSchedule() {
        // Create a new DriverSchedule instance
        Driver newDriver = new Driver(103, "Ion", "ion@gmail.com", "Dima", "9988");
        Company newCompany = new Company(3, "Eco", "eco@gmail.com", "Dqwe", "8765");
        LocalDateTime checkIn = LocalDateTime.now().minusHours(3);
        LocalDateTime checkOut = LocalDateTime.now().minusHours(1);

        DriverSchedule newSchedule = new DriverSchedule(2, newDriver, newCompany, checkIn, checkOut);

        // Add new schedule to the list
        scheduleList.add(newSchedule);

        // Assert the new schedule is added correctly
        assertEquals(2, scheduleList.size());
        assertEquals(newSchedule, scheduleList.get(1));
    }

    @Test
    void testReadDriverScheduleProperties() {
        // Verify the properties of the driverSchedule instance
        assertEquals(1, driverSchedule.getId());
        assertEquals(driver, driverSchedule.getDriver());
        assertEquals(company, driverSchedule.getCompany());
        assertNotNull(driverSchedule.getCheckIn());
        assertNotNull(driverSchedule.getCheckOut());
    }

    @Test
    void testUpdateDriverSchedule() {
        // Update the check-in and check-out times
        LocalDateTime newCheckIn = LocalDateTime.now().minusHours(4);
        LocalDateTime newCheckOut = LocalDateTime.now().minusMinutes(30);

        driverSchedule.setCheckIn(newCheckIn);
        driverSchedule.setCheckOut(newCheckOut);

        // Verify that the updated times are correctly set
        assertEquals(newCheckIn, driverSchedule.getCheckIn());
        assertEquals(newCheckOut, driverSchedule.getCheckOut());
    }

    @Test
    void testDeleteDriverSchedule() {
        // Simulate deleting the driver schedule
        scheduleList.remove(driverSchedule);

        // Verify the schedule is removed from the list
        assertEquals(0, scheduleList.size());
    }

    @Test
    void testCreateOrder() {
        // Create a new Order instance
        Client newClient = new Client(2, "Maria", "maria@gmail.com", "Dxyz", "5678");
        Driver newDriver = new Driver(102, "Vasile", "vasile@gmail.com", "Dddt", "4567");
        Company newCompany = new Company(2, "Daniel", "Daniel@gmail.com", "Dabc", "5678");
        Service newService = new CustomService(2, "Premium", 10.0, "Extras");
        LocalDateTime newDatetime = LocalDateTime.now().plusDays(1);

        Order newOrder = new Order(2, 150.0, newService, newClient, newDriver, newCompany, newDatetime);

        // Add new order to the list
        orderList.add(newOrder);

        // Assert that the new order is added correctly
        assertEquals(2, orderList.size());
        assertEquals(newOrder, orderList.get(1));
    }

    @Test
    void testReadOrderProperties() {
        // Verify the properties of the order instance
        assertEquals(1, order.getId());
        assertEquals(basicService, order.getService());
        assertEquals(100.0, order.getTotalKm());
        assertEquals(client, order.getClient());
        assertEquals(driver, order.getDriver());
        assertEquals(company, order.getCompany());
        assertNotNull(order.getDatetime());
    }

    @Test
    void testUpdateOrder() {
        // Update the totalKm and datetime
        order.setTotalKm(120.0);
        order.setDatetime(LocalDateTime.now().plusHours(2));

        // Verify that the updated values are correctly applied
        assertEquals(120.0, order.getTotalKm());
        assertNotNull(order.getDatetime());
    }

    @Test
    void testDeleteOrder() {
        // Simulate deleting the order
        orderList.remove(order);

        // Verify the order is removed from the list
        assertEquals(0, orderList.size());
    }

    @Test
    void testCreateReview() {
        // Create a new Review instance
        Review newReview = new Review(2, 4, "Good", company, driver, client);

        // Verify the new review has been created successfully
        assertEquals(2, newReview.getId());
        assertEquals(4, newReview.getRating());
        assertEquals("Good", newReview.getDescription());
        assertEquals(company, newReview.getCompany());
        assertEquals(driver, newReview.getDriver());
        assertEquals(client, newReview.getClient());
    }

    @Test
    void testReadReviewProperties() {
        // Verify the properties of the review instance
        assertEquals(1, review.getId());
        assertEquals(5, review.getRating());
        assertEquals("Excelent", review.getDescription());
        assertEquals(company, review.getCompany());
        assertEquals(driver, review.getDriver());
        assertEquals(client, review.getClient());
    }

    @Test
    void testUpdateReview() {
        // Update the rating and description of the review
        review.setRating(3);
        review.setDescription("Ok");

        // Verify that the updated values are correctly applied
        assertEquals(3, review.getRating());
        assertEquals("Ok", review.getDescription());
    }

    @Test
    void testDeleteReview() {
        // Simulate deleting the review
        review = null;

        // Verify the review is deleted (nullified in this case)
        assertNull(review);
    }



}

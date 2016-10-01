package by.pvt.service;

import by.pvt.pojo.Client;
import by.pvt.pojo.Passports;
import by.pvt.pojo.Roles;
import by.pvt.pojo.ClientStatus;
import by.pvt.service.impl.ClientService;
import by.pvt.service.impl.PassportService;
import by.pvt.service.impl.RoleService;
import by.pvt.service.impl.StatusOfClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class ClientServiceTest {
    private Passports passports;
    private Client client;
    private ClientStatus statusOfClient;
    private Roles role;
    @Autowired
    private PassportService passportService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StatusOfClientService statusOfClientService;
    @Autowired
    private ClientService clientService;

    @Before
    public void setUp() throws Exception {
        // create passport data
        passports = new Passports();
        passports.setPassport("fffff");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2322-02-05");
        passports.setPassportEndDate(date);
        passports.setPassportIssueDate(date);
        //create status
        statusOfClient = new ClientStatus();
        statusOfClient.setStatus("CREATE");
        // create role
        role = new Roles();
        role.setName("USER");
        //create client
        client = new Client();
        client.setEmail("vanu@mail.ru");
        client.setName("Vania");
        client.setSurname("Ivanow");
        client.setPassword("1234");
        client.setPassports(passports);
        client.setPhone("+375291236547");
        client.setRole(role);
        client.setStatusOfClient(statusOfClient);
    }

    @After
    public void tearDown() throws Exception {
        passports = null;
        client = null;
        statusOfClient = null;
    }

    @Test
    public void save() throws Exception {
        client.setEmail("v@mail.ru");
        passportService.save(passports);
        roleService.save(role);
        statusOfClientService.save(statusOfClient);
        clientService.save(client);

        Client actual = clientService.get(Client.class, 2);
        assertEquals(actual, client);
    }

    @Test
    public void get() throws Exception {
        roleService.save(role);
        passportService.save(passports);
        statusOfClientService.save(statusOfClient);
        clientService.save(client);

        Passports person = passportService.get(Passports.class, 1);
        Client cv = clientService.get(Client.class, 1);
        ClientStatus st = statusOfClientService.get(ClientStatus.class, 1);
        assertEquals(cv, client);
        assertEquals(st, statusOfClient);
    }

    @Test
    public void testUpdate() throws Exception {
        Client cv = clientService.get(Client.class, 1);
        cv.setPhone("+375291234567");
        clientService.update(cv);
        assertNotSame(cv, client);
    }

    @Test
    public void testDelete() throws Exception {
        Client actual = clientService.get(Client.class, 2);
        clientService.delete(actual);
        assertNull(clientService.get(Client.class, 2));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = clientService.getAll(0, 1);
        assertEquals(list.size(), 1);
    }

    @Test
    public void login() throws Exception {
        Client list = clientService.login("vanu@mail.ru", "1234");
        assertEquals(list.getPassword(), client.getPassword());
        assertEquals(list.getEmail(), client.getEmail());
    }

    @Test
    public void forgotPassword() throws Exception {
        String s = clientService.forgotPassword("vanu@mail.ru");
        assertEquals(s, "1234");
    }
}
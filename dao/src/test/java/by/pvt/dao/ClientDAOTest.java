package by.pvt.dao;

import by.pvt.pojo.Client;
import by.pvt.pojo.Passports;
import by.pvt.pojo.Roles;
import by.pvt.pojo.ClientStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/dao-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class ClientDAOTest {
    private Passports passports;
    private Client client;
    private ClientStatus statusOfClient;
    private Roles role;
    @Autowired
    private DAO baseDAO;
    @Autowired
    private IClientDAO clientDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.NEVER)
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        // create passport data
        passports = new Passports();
        passports.setPassport("fffff");
        passports.setPassportEndDate(new java.util.Date(11111111111111L));
        passports.setPassportIssueDate(new java.util.Date(11111111111111L));
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
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        client.setEmail("v@mail.ru");
        baseDAO.save(passports);
        baseDAO.save(client);
        baseDAO.save(statusOfClient);
        baseDAO.save(role);
        Client actual = (Client) clientDAO.get(Client.class, 2);
        assertEquals(actual, client);
    }

    @Test
    public void get() throws Exception {
        baseDAO.save(role);
        baseDAO.save(passports);
        clientDAO.save(client);
        baseDAO.save(statusOfClient);
        Passports person = (Passports) baseDAO.get(Passports.class, 1);
        Client cv = (Client) clientDAO.get(Client.class, 1);
        ClientStatus st = (ClientStatus) baseDAO.get(ClientStatus.class, 1);
        assertEquals(person, passports);
        assertEquals(cv, client);
        assertEquals(st, statusOfClient);
    }

    @Test
    public void testUpdate() throws Exception {
        Client cv = (Client) clientDAO.get(Client.class, 1);
        cv.setPhone("+375291234567");
        assertNotSame(cv, client);
    }

    @Test
    public void testDelete() throws Exception {
        Client actual = (Client) clientDAO.get(Client.class, 2);
        clientDAO.delete(actual);
        assertNull(clientDAO.get(Client.class, 2));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = clientDAO.getAll(0, 1);
        assertEquals(list.size(), 1);
    }

    @Test
    public void login() throws Exception {
        Client list = clientDAO.login("vanu@mail.ru", "1234");
        assertEquals(list.getPassword(), client.getPassword());
        assertEquals(list.getEmail(), client.getEmail());
    }

    @Test
    public void forgotPassword() throws Exception {
        String s = clientDAO.forgotPassword("vanu@mail.ru");
        assertEquals(s, "1234");
    }
}
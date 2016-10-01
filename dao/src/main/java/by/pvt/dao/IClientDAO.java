package by.pvt.dao;

import by.pvt.pojo.Client;

import java.util.List;

/**
 * This class describe operations with database for POJO Client
 *
 * @param <T>
 */
public interface IClientDAO<T> extends DAO<T> {
    /**
     * return all clients entities
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return - list of all clients
     */
    List<Client> getAll(int page, int perPage);

    /**
     * method for enter to the system
     *
     * @param email    - client email
     * @param password - client password
     * @return object of client
     */
    Client login(String email, String password);

    /**
     * method return password of client
     *
     * @param email -client email
     * @return client password
     */
    String forgotPassword(String email);

}

package by.pvt.dao;

import by.pvt.pojo.Client;

import java.util.List;

/**
 * This class describe operations with database for POJO Client
 *
 * @param <T>
 */
public interface IClientDAO<T> extends DAO<T> {
    //return all clients entities
    List<Client> getAll(int page, int perPage);

    // method for enter to the system
    Client login(String email, String password);

    // method return password of client
    String forgotPassword(String email);

}

package by.pvt.dao;

import by.pvt.pojo.TransmissionType;

import java.util.List;

/**
 * This class describe operations with database for POJO TransmissionType
 *
 * @param <T>
 */
public interface ITransmissionTypeDAO<T> extends DAO<T> {
    /**
     * return all transmission types from database
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return list of transmission types
     */
    List<TransmissionType> getAll(int page, int perPage);
}

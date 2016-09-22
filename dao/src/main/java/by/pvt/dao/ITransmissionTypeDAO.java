package by.pvt.dao;

import by.pvt.pojo.TransmissionType;

import java.util.List;

/**
 * This class describe operations with database for POJO TransmissionType
 *
 * @param <T>
 */
public interface ITransmissionTypeDAO<T> extends DAO<T> {
    //return all transmission types from database
    List<TransmissionType> getAll(int page, int perPage);
}

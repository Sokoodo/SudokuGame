package com.lodz.sudoku.dao;

import com.lodz.sudoku.exceptions.DaoException;
import com.lodz.sudoku.exceptions.DaoRException;
import com.lodz.sudoku.exceptions.DaoWException;

/**
 * Interface DAO, it's generic and it extends AutoCloseable.
 *
 * @param <T> generic parameter.
 */
public interface Dao<T> extends AutoCloseable {

    /**
     * read method.
     *
     * @return T.
     */
    T read() throws DaoRException;

    /**
     * write method.
     *
     * @param obj object to write.
     */
    void write(T obj) throws DaoWException;

    @Override
    void close() throws DaoException;
}

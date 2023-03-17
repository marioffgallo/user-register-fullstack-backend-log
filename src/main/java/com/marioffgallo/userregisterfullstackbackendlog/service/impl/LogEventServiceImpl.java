package com.marioffgallo.userregisterfullstackbackendlog.service.impl;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.repository.LogEventRepository;
import com.marioffgallo.userregisterfullstackbackendlog.services.exceptions.DatabaseException;
import com.marioffgallo.userregisterfullstackbackendlog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marioffgallo.userregisterfullstackbackendlog.service.LogEventService;

import java.util.List;
import java.util.Optional;

/**
 * Service for interactions with the database MySQL
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@Service
public class LogEventServiceImpl implements LogEventService {

    @Autowired
    private LogEventRepository repository;

    /**
     * Retrieves all logs in the repository
     *
     * @param invertOrder boolean specifying the order to retrieve the list
     * @return A list of all logs
     * @throws DatabaseException if an error occurs when retrieving the list
     */
    @Override
    public List<LogEventDB> getAllLogs(boolean invertOrder) throws DatabaseException {
        if(invertOrder){
            try{
                return repository.findAllByOrderByIdDesc();
            } catch (Exception e) {
                throw new DatabaseException(e.getMessage());
            }
        } else {
            try {
                return repository.findAllByOrderByIdAsc();
            } catch (Exception e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    /**
     * Receives ID as a parameter and returns the log if found it
     *
     * @param id integer specifying the number ID of log
     * @return Returns a log if found in the DB
     * @throws ResourceNotFoundException if nothing is found
     */
    @Override
    public LogEventDB getLogById(int id) throws ResourceNotFoundException{
        Optional<LogEventDB> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Receives LogEventDB and saves in the database
     *
     * @param logEventDB a LogEventDB object
     * @return Returns the logEventDB with the ID generated in the database
     * @throws DatabaseException if an error occurs when saving the object
     */
    @Override
    public LogEventDB create(LogEventDB logEventDB) throws DatabaseException{
        try{
            return repository.save(logEventDB);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Receives ID as a parameter and deletes on database
     *
     * @param id integer specifying the number ID of log
     * @throws ResourceNotFoundException if nothing is found
     * @throws DatabaseException if an error occurs when saving the object
     */
    @Override
    public void delete(int id) throws ResourceNotFoundException,DatabaseException  {
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}


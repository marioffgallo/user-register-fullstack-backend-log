package com.marioffgallo.userregisterfullstackbackendlog.service;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.repository.LogEventRepository;
import com.marioffgallo.userregisterfullstackbackendlog.services.exceptions.DatabaseException;
import com.marioffgallo.userregisterfullstackbackendlog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogEventService {

    @Autowired
    private LogEventRepository repository;

    public List<LogEventDB> getAllLogs(){
        return repository.findAll();
    }

    public LogEventDB getLogById(int id) {
        Optional<LogEventDB> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public LogEventDB create(LogEventDB logEventDB) {
        return repository.save(logEventDB);
    }

    public void delete(int id) {
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

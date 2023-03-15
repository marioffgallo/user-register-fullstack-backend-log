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

@Service
public class LogEventServiceImpl implements LogEventService {

    @Autowired
    private LogEventRepository repository;

    @Override
    public List<LogEventDB> getAllLogs(boolean invertOrder) {
        if(invertOrder){
            return repository.findAllByOrderByIdDesc();
        } else {
            return repository.findAllByOrderByIdAsc();
        }
    }

    @Override
    public LogEventDB getLogById(int id) {
        Optional<LogEventDB> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public LogEventDB create(LogEventDB logEventDB) {
        return repository.save(logEventDB);
    }

    @Override
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


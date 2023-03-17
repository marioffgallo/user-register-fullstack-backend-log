package com.marioffgallo.userregisterfullstackbackendlog.controller;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.model.LogEventDTO;
import com.marioffgallo.userregisterfullstackbackendlog.service.LogEventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the REST calls for logs in the application
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/database")
public class LogEventController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    LogEventService logEventService;

    /**
     * Retrieves all logs in the repository
     *
     * @return A list of all logs with an HTTP 200
     */
    @GetMapping(value = "/logs")
    public ResponseEntity<List<LogEventDTO>> findAllLogs(){
        List<LogEventDTO> list = logEventService.getAllLogs(false)
                .stream()
                .map(log -> modelMapper.map(log, LogEventDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    /**
     * Receives a parameter in the body which flags if is to invert the order which the logs are returned
     *
     * @param invertOrder A boolean which informs if is to invert the order
     * @return A list of all logs with the order inverted or not with an HTTP 200
     */
    @PostMapping(value = "/logs")
    public ResponseEntity<List<LogEventDTO>> findAllLogsOrder(@RequestBody boolean invertOrder){
        List<LogEventDTO> list = logEventService.getAllLogs(invertOrder)
                .stream()
                .map(log -> modelMapper.map(log, LogEventDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    /**
     * Receives ID as a parameter in the path to retrieve in the database
     * and returns the log
     *
     * @param id an integer specifying the number ID of log
     * @return Returns a log if found in the DB with an HTTP 200
     */
    @GetMapping(value = "/logs/{id}")
    public ResponseEntity<LogEventDTO> findLogById(@PathVariable int id){
        LogEventDB obj = logEventService.getLogById(id);

        LogEventDTO objDTO = modelMapper.map(obj, LogEventDTO.class);

        return ResponseEntity.ok().body(objDTO);
    }

    /**
     * Receives LogEventDTO in the body of request, saves in the database
     * and returns the object LogEvent with the ID generated
     *
     * @param logEventDTO a LogEventDTO object
     * @return Returns the logEventDTO with the ID generated in the database with an HTTP 201
     */
    @PostMapping(value = "/logs/create")
    public ResponseEntity<LogEventDTO> createLog(@RequestBody LogEventDTO logEventDTO){
        LogEventDB logEventDB = modelMapper.map(logEventDTO, LogEventDB.class);

        logEventDB.setAction(logEventDTO.getAction());
        logEventDB.setDate(new Timestamp(new Date().getTime()));
        logEventDB.setPayload(logEventDTO.getPayload());

        logEventDB = logEventService.create(logEventDB);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(logEventDB.getId()).toUri();

        LogEventDTO createdLogEventDTO = modelMapper.map(logEventDB, LogEventDTO.class);

        return ResponseEntity.created(uri).body(createdLogEventDTO);
    }
}

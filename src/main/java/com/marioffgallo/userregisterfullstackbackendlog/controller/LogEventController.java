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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/database")
public class LogEventController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    LogEventService logEventService;

    @GetMapping(value = "/logs")
    public ResponseEntity<List<LogEventDTO>> findAllLogs(){
        List<LogEventDTO> list = logEventService.getAllLogs(false)
                .stream()
                .map(log -> modelMapper.map(log, LogEventDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/logs")
    public ResponseEntity<List<LogEventDTO>> findAllLogsOrder(@RequestBody boolean invertOrder){
        List<LogEventDTO> list = logEventService.getAllLogs(invertOrder)
                .stream()
                .map(log -> modelMapper.map(log, LogEventDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/logs/{id}")
    public ResponseEntity<LogEventDTO> findLogById(@PathVariable int id){
        LogEventDB obj = logEventService.getLogById(id);

        LogEventDTO objDTO = modelMapper.map(obj, LogEventDTO.class);

        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping(value = "/logs/create")
    public ResponseEntity<LogEventDTO> create(@RequestBody LogEventDTO logEventDTO){
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

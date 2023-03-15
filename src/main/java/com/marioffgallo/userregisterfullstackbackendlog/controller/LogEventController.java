package com.marioffgallo.userregisterfullstackbackendlog.controller;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.model.LogEvent;
import com.marioffgallo.userregisterfullstackbackendlog.service.LogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/database")
public class LogEventController {

    @Autowired
    LogEventService logEventService;

    @GetMapping(value = "/logs")
    public ResponseEntity<List<LogEventDB>> findAllLogs(){
        List<LogEventDB> list = logEventService.getAllLogs();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/logs/{id}")
    public ResponseEntity<LogEventDB> findLogById(@PathVariable int id){
        LogEventDB obj = logEventService.getLogById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/logs/create")
    public ResponseEntity<LogEventDB> create(@RequestBody LogEvent logEvent){
        LogEventDB logEventDB = new LogEventDB();
        logEventDB.setAction("POST");
        logEventDB.setDate(new Timestamp(new Date().getTime()));
        logEventDB.setPayload(logEvent.getPayload().toString());

        logEventDB = logEventService.create(logEventDB);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(logEventDB.getId()).toUri();
        return ResponseEntity.created(uri).body(logEventDB);
    }
}

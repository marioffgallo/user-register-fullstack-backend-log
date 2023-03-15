package com.marioffgallo.userregisterfullstackbackendlog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.model.LogEventDTO;
import com.marioffgallo.userregisterfullstackbackendlog.service.LogEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class MessageConsumer {

    Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    LogEventService logEventService;

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(String logSerial) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            LogEventDTO receivedObject = objectMapper.readValue(logSerial, LogEventDTO.class);
            log.info("Received log='{}'", receivedObject);

            LogEventDB logEventDB = new LogEventDB();
            logEventDB.setAction(receivedObject.getAction());
            logEventDB.setDate(new Timestamp(receivedObject.getDate().getTime()));
            logEventDB.setPayload(receivedObject.getPayload());

            logEventService.create(logEventDB);
        } catch (IOException e) {

        }
    }

}
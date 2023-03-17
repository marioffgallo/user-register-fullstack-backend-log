package com.marioffgallo.userregisterfullstackbackendlog.service;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import com.marioffgallo.userregisterfullstackbackendlog.repository.LogEventRepository;
import com.marioffgallo.userregisterfullstackbackendlog.service.impl.LogEventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogEventServiceTest {

    @Mock
    private LogEventRepository logEventRepository;

    @InjectMocks
    private LogEventServiceImpl logEventServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllLogs() {
        List<LogEventDB> expectedEntities = Arrays.asList(
                new LogEventDB(1, "GET", new Timestamp(2021, 01, 01, 01, 10, 10, 01), "teste"),
                new LogEventDB(2, "POST", new Timestamp(2021, 01, 01, 01, 10, 10, 01), "teste2")
        );

        when(logEventRepository.findAllByOrderByIdAsc()).thenReturn(expectedEntities);

        List<LogEventDB> actualEntities = logEventServiceImpl.getAllLogs(false);

        assertEquals(expectedEntities, actualEntities);
    }

    @Test
    public void testFindLogById() {
        Optional<LogEventDB> expectedLog = Optional.of(new LogEventDB(
                1,
                "GET",
                new Timestamp(2021, 01, 01, 01, 10, 10, 01),
                "Teste"
        ));

        when(logEventRepository.findById(1)).thenReturn(expectedLog);

        Optional<LogEventDB> actualLog = Optional.of(logEventServiceImpl.getLogById(1));

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void testCreateLog() {
        LogEventDB expectedLog = new LogEventDB(
                1,
                "GET",
                new Timestamp(2021, 01, 01, 01, 10, 10, 01),
                "Teste"
        );

        when(logEventRepository.save(expectedLog)).thenReturn(expectedLog);

        LogEventDB responseLog = logEventServiceImpl.create(expectedLog);

        assertEquals(expectedLog, responseLog);
    }

    @Test
    public void testDeleteLog() {
        logEventServiceImpl.delete(1);

        verify(logEventRepository).deleteById((1));
    }
}

package com.marioffgallo.userregisterfullstackbackendlog.service;

import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;

import java.util.List;

public interface LogEventService {

    public List<LogEventDB> getAllLogs(boolean invertOrder);

    public LogEventDB getLogById(int id);

    public LogEventDB create(LogEventDB logEventDB);

    public void delete(int id);
}

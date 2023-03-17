package com.marioffgallo.userregisterfullstackbackendlog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Log Event model for handle data
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEventDTO {
    private int id;
    private String action;
    private Date date;
    private String payload;

}


package com.marioffgallo.userregisterfullstackbackendlog.entity;

import com.marioffgallo.userregisterfullstackbackendlog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "logs")
public class LogEventDB {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_log_seq")
    @SequenceGenerator(name = "id_log_seq", sequenceName = "id_log_seq", initialValue = 1, allocationSize=1)
    private int id;

    private String action;

    @Column
    private Timestamp date;

    @Column
    private String payload;

}

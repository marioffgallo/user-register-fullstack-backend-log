package com.marioffgallo.userregisterfullstackbackendlog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column
    private String action;

    @Column
    private Timestamp date;

    @Column
    private String payload;

}

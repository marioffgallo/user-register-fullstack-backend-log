package com.marioffgallo.userregisterfullstackbackendlog.repository;


import com.marioffgallo.userregisterfullstackbackendlog.entity.LogEventDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LogEventRepository for communication with MySQL
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@Repository
public interface LogEventRepository extends JpaRepository<LogEventDB, Integer> {
    public List<LogEventDB> findAllByOrderByIdAsc();
    public List<LogEventDB> findAllByOrderByIdDesc();
}

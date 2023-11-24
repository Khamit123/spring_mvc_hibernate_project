package DAO;

import model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class tableDAO {
    private  final JdbcTemplate jdbcTemplate;
    @Autowired
    public tableDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<?> index(String tableName){
        return  jdbcTemplate.query("SELECT * FROM "+tableName, new BeanPropertyRowMapper<>(Department.class));
    }

}

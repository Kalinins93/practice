package com.example.demo.database;

import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.h2.jdbcx.JdbcDataSource;
import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories("com.example.demo.database")
@EnableTransactionManagement
public class SpringDAO extends AbstractJdbcConfiguration
{
    @Bean
    public JdbcDataSource dataSource()
    {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:C:\\Users\\user\\Рабочий стол\\demo2\\src\\main\\java\\com\\example\\demo\\database\\dataBase\\shop");
        ds.setUser("vlad");
        return ds;
    }

    @Bean
    public NamedParameterJdbcTemplate namePrepameterJdbcOperations(DataSource jd)
    {
        return new NamedParameterJdbcTemplate(jd);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource jd)
    {
        return new DataSourceTransactionManager(jd);
    }
}

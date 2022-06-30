package com.example.demo.database;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories("com.example.demo.database")
@EnableTransactionManagement
public class SpringDAO extends AbstractJdbcConfiguration
{
    @Bean
    public PGPoolingDataSource dataSource()
    {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("A Data Source");
        source.setServerNames(new String[] {"localhost"});
        source.setDatabaseName("shop");
        source.setUser("vova");
        source.setPassword("123");
        source.setMaxConnections(10);
        source.setURL("jdbc:postgresql://localhost:5432/shop");
        //;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

        return source;
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

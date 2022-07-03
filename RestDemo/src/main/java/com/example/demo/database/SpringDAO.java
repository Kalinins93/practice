package com.example.demo.database;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJdbcRepositories("com.example.demo.database")
@EnableTransactionManagement
public class SpringDAO extends AbstractJdbcConfiguration
{
    @Autowired
    private Environment env;

    @Bean
    public PGSimpleDataSource dataSource()
    {
        PGSimpleDataSource source = new PGSimpleDataSource();

        source.setDatabaseName(env.getProperty("spring.datasource.dataBaseName"));
        source.setUser( env.getProperty("spring.datasource.username") );
        source.setPassword( env.getProperty("spring.datasource.password") );
        source.setURL( env.getProperty("spring.datasource.url") );

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

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
}

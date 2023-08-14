package com.doomole.stockproject.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages= {"com.doomole.stockproject.repository"}, sqlSessionFactoryRef="sqlSessionFactory")
public class DatabaseConfig {
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(DataSource);
        org.apache.ibatis.session.Configuration prop = new org.apache.ibatis.session.Configuration();
        prop.setMapUnderscoreToCamelCase(true);
        prop.setLazyLoadingEnabled(true);
        prop.setUseGeneratedKeys(true);
        sqlSessionFactoryBean.setConfiguration(prop);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:repository/mysql/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}

//package cn.edu.ecnu.config;
//
//
//import org.neo4j.driver.internal.SessionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//
//@Configuration
//@EnableNeo4jRepositories("") // 声明neo4j repository存放地址
//public class Neo4jConfig {
//
//    @Value("${spring.data.neo4j.uri}")
//    private String uri;
//    @Value("${spring.data.neo4j.username}")
//    private String userName;
//    @Value("${spring.data.neo4j.password}")
//    private String password;
//
//    @Bean
//    public Configuration getConfiguration() {
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder().uri(uri).connectionPoolSize(100).credentials(userName, password).withBasePackages("com.xm").build();
//        return configuration;
//    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new SessionFactory(getConfiguration());
//    }
//
//    @Bean("neo4jTransaction")
//    public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {
//        return new Neo4jTransactionManager(sessionFactory);
//    }
//}
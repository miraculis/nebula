package neb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConf {
    @Autowired
    Cluster cluster;

    @Bean
    public QueryLogger queryLogger() {
        QueryLogger queryLogger = QueryLogger.builder()
                .build();
        cluster.register(queryLogger);
        return queryLogger;
    }
}

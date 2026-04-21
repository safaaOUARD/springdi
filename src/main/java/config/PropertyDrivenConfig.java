package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    private final ApplicationContext applicationContext;

    public PropertyDrivenConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Value("${dao.target:dao2}")
    private String target;

    @Bean(name = "selectedDao")
    public IDao selectedDao() {
        return (IDao) applicationContext.getBean(target);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
package bdapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;


@Configuration
@ComponentScan("bdapp")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new SpringSecurityDialect());

        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setContentType("text/html; charset=UTF-8");
        registry.viewResolver(resolver);

    }

}
//<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
//        destroy-method="close">
//<property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
//<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC" />
//<property name="user" value="bestuser" />
//<property name="password" value="bestuser" />
//</bean>
//
//<bean id="sessionFactory"
//class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
//<property name="dataSource" ref="dataSource" />
//<property name="packagesToScan" value="com.zaurtregulov.spring.mvc_hibernate_aop.entity" />
//<property name="hibernateProperties">
//<props>
//<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
//<prop key="hibernate.show_sql">true</prop>
//</props>
//</property>
//</bean>
//
//<bean id="transactionManager"
//class="org.springframework.orm.hibernate5.HibernateTransactionManager">
//<property name="sessionFactory" ref="sessionFactory"/>
//</bean>
//
//<tx:annotation-driven transaction-manager="transactionManager" />
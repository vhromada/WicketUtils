package cz.vhromada.web.wicket;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * A class represents Spring configuration.
 *
 * @author Vladimir Hromada
 */
@Configuration
@ComponentScan("cz.vhromada.web.wicket")
public class WicketUtilsConfiguration implements ServletContextInitializer {

    @Value("${wicket.configuration:#{null}}")
    private String configuration;

    @Override
    public void onStartup(final ServletContext servletContext) {
        final FilterRegistration filter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        filter.setInitParameter("applicationBean", "catalogApplication");
        filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filter.setInitParameter("configuration", StringUtils.isEmpty(configuration) ? "development" : configuration);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}

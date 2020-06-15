package cn.codeforfun.config;

import cn.codeforfun.generator.mapper.PropertyMapper;
import cn.codeforfun.generator.model.Property;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigServer
public class ConfigServerConfiguration implements EnvironmentRepository {
    @Resource
    private PropertyMapper propertyMapper;

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, profile, label, null, null);
        if (!application.contains("_") || "app".equals(application)) {
            return environment;
        }

        String[] strings = application.split("_");
        String projectName = strings[0];
        String appName = strings[1];
        String[] profiles = profile.split(",");

        List<Property> propertyList = propertyMapper.find(projectName, appName, profiles);

        Map<String, Object> source = new HashMap<>(10);
        environment.add(new PropertySource(application, source));
        for (Property property : propertyList) {
            source.put(property.getPropertyKey(), property.getPropertyValue());
        }
        return environment;
    }
}

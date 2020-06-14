package cn.codeforfun.config;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigServer
public class ConfigServerConfiguration implements EnvironmentRepository {
    @Override
    public Environment findOne(String application, String profile, String label) {
        return null;
    }
}

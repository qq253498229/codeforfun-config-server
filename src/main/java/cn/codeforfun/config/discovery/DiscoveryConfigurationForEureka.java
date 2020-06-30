package cn.codeforfun.config.discovery;

import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author wangbin
 */
@Configuration
@Profile("eureka")
@EnableAutoConfiguration
@Import(DiscoveryConfigurationForEureka.ConfigurationForEureka.class)
public class DiscoveryConfigurationForEureka {
    public static class ConfigurationForEureka extends AutoConfigurationImportSelector {
        @Override
        protected Set<String> getExclusions(AnnotationMetadata metadata, AnnotationAttributes attributes) {
            Set<String> exclusions = super.getExclusions(metadata, attributes);
            DiscoveryConfigurationForUrl.ConfigurationForUrl.ignoreConsulAutoConfiguration(exclusions);
            return exclusions;
        }
    }
}

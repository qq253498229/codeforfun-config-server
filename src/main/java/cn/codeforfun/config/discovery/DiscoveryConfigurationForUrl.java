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
@Profile("url")
@EnableAutoConfiguration
@Import(DiscoveryConfigurationForUrl.ConfigurationForUrl.class)
public class DiscoveryConfigurationForUrl {
    public static class ConfigurationForUrl extends AutoConfigurationImportSelector {
        public static void ignoreEurekaAutoConfiguration(Set<String> exclusions) {
            exclusions.add("org.springframework.cloud.netflix.eureka.config.EurekaClientConfigServerAutoConfiguration");
            exclusions.add("org.springframework.cloud.netflix.eureka.config.DiscoveryClientOptionalArgsConfiguration");
            exclusions.add("org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration");
            exclusions.add("org.springframework.cloud.netflix.ribbon.eureka.RibbonEurekaAutoConfiguration");
            exclusions.add("org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration");
            exclusions.add("org.springframework.cloud.netflix.eureka.reactive.EurekaReactiveDiscoveryClientConfiguration");
            exclusions.add("org.springframework.cloud.netflix.eureka.loadbalancer.LoadBalancerEurekaAutoConfiguration");
        }

        public static void ignoreConsulAutoConfiguration(Set<String> exclusions) {
            exclusions.add("org.springframework.cloud.consul.ConsulAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.discovery.RibbonConsulAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.discovery.configclient.ConsulConfigServerAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistryAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.discovery.ConsulDiscoveryClientConfiguration");
            exclusions.add("org.springframework.cloud.consul.discovery.reactive.ConsulReactiveDiscoveryClientConfiguration");
            exclusions.add("org.springframework.cloud.consul.discovery.ConsulCatalogWatchAutoConfiguration");
            exclusions.add("org.springframework.cloud.consul.support.ConsulHeartbeatAutoConfiguration");
        }

        @Override
        protected Set<String> getExclusions(AnnotationMetadata metadata, AnnotationAttributes attributes) {
            Set<String> exclusions = super.getExclusions(metadata, attributes);

            ignoreEurekaAutoConfiguration(exclusions);
            ignoreConsulAutoConfiguration(exclusions);

            return exclusions;
        }
    }
}

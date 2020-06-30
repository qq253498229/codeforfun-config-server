package cn.codeforfun.config.monitor;

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
@EnableAutoConfiguration
@Import(MonitorConfigurationForNone.ConfigurationForNone.class)
@Profile("none")
public class MonitorConfigurationForNone {
    public static class ConfigurationForNone extends AutoConfigurationImportSelector {
        @Override
        protected Set<String> getExclusions(AnnotationMetadata metadata, AnnotationAttributes attributes) {
            Set<String> exclusions = super.getExclusions(metadata, attributes);
            exclusions.add("org.springframework.cloud.config.monitor.EnvironmentMonitorAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.config.ChannelBindingAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.config.BindersHealthIndicatorAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.config.ChannelsEndpointAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.config.BindingsEndpointAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.config.BindingServiceConfiguration");
            exclusions.add("org.springframework.cloud.stream.function.FunctionConfiguration");
            exclusions.add("org.springframework.cloud.stream.binder.rabbit.config.ExtendedBindingHandlerMappingsProviderConfiguration");
            exclusions.add("org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration");
            exclusions.add("org.springframework.cloud.stream.test.binder.MessageCollectorAutoConfiguration");
            exclusions.add("org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration");
            exclusions.add("org.springframework.boot.actuate.autoconfigure.amqp.RabbitHealthContributorAutoConfiguration");
            exclusions.add("org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration");
            return exclusions;
        }
    }
}

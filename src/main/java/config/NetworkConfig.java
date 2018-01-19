package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringComponentScan")
@Configuration
@ComponentScan("Models,Commands,services,DB")
public class NetworkConfig {

}

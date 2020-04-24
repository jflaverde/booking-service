package com.way2learnonline.booking.infrastructure.infrastructure.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDatasourceConfig {

}

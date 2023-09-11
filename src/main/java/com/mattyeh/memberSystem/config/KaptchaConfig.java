package com.mattyeh.memberSystem.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha DefaultKaptchaService() {

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.image.width", "65");
        properties.put("kaptcha.image.height", "30");
        properties.put("kaptcha.textproducer.font.size", "25");
        properties.put("kaptcha.textproducer.font.color", "red");
        properties.put("kaptcha.textproducer.font.names", "Arial");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

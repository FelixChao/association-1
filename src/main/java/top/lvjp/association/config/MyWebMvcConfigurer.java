package top.lvjp.association.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "upload")
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${image-path}")
    private String imagePath;

    @Value("${video-path}")
    private String videoPath;

    public static final String IMAGE_ACCESS_PATH = "/image/";
    public static final String VIDEO_ACCESS_PATH = "/video/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMAGE_ACCESS_PATH + "**")
                .addResourceLocations("file:" + imagePath + "**");
        registry.addResourceHandler(VIDEO_ACCESS_PATH + "**")
                .addResourceLocations("file:" + videoPath + "**");
    }
}

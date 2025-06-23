package com.eric.ericjarvasianpexelimagemcp;

import com.eric.ericjarvasianpexelimagemcp.tools.ImageSearchTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EricJarvasianPexelImageMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(EricJarvasianPexelImageMcpApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(ImageSearchTool imageSearchTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(imageSearchTool)
                .build();
    }

}

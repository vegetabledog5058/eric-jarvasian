package com.eric.ericjarvasian;

import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@SpringBootApplication(exclude = {
        PgVectorStoreAutoConfiguration.class,
        DataSourceAutoConfiguration.class // 禁用数据源
})
// 排除demo包下的类
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com\\.eric\\.ericjarvasian\\.demo\\..*"
        )
)
public class EricJarvasianApplication {

    public static void main(String[] args) {
        SpringApplication.run(EricJarvasianApplication.class, args);
    }
}

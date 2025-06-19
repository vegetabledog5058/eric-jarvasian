package com.eric.ericjarvasian.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class WebSearchToolTest {
@Value("${search-api.key}")
private String searchApiKey;

    @Test
    void searchWeb() {
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        String result = webSearchTool.searchWeb("南京好玩的地方?");
        Assertions.assertNotNull(result);
    }
}
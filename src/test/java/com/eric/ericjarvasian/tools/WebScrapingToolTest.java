package com.eric.ericjarvasian.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WebScrapingToolTest {

    @Test
    void scrapeWebPage() {
        WebScrapingTool webScrapingTool = new WebScrapingTool();
        String result = webScrapingTool.scrapeWebPage("https://docs.spring.io/spring-ai/reference/api/tools.html");
        Assertions.assertNotNull(result);
    }
}
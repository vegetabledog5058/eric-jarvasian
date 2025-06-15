package com.eric.ericjarvasian.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CpAppRagCloudAdvisorConfig {
    private static final String API_KEY = "sk-34bb799af25340cb9bf73b84d6887fbd";

    @Bean
    public RetrievalAugmentationAdvisor CpAppRagCloudAdvisorConfig() {
        var dashScopeApi = new DashScopeApi(API_KEY);
        DocumentRetriever retriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .withIndexName("计算机专业职业规划")
                        .build());
        return RetrievalAugmentationAdvisor.builder().documentRetriever(retriever).build();

    }

}

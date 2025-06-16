package com.eric.ericjarvasian.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;

@Slf4j
public class CpAppRagCustomAdvisorFactory {
    public static Advisor createLoveAppRagCustomAdvisor(VectorStore vectorStore, String grade) {
        Filter.Expression expression = new FilterExpressionBuilder().eq("grade", grade).build();
        DocumentRetriever retriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .filterExpression(expression)
                .similarityThreshold(0.)
                .topK(3)
                .build();
        return RetrievalAugmentationAdvisor
                .builder()
                .documentRetriever(retriever)
                // 自定义上下文查询增强
                .queryAugmenter(CpAppContextualQueryAugmenterFactory.createCpAppContextualQueryAugmenter())
                .build();
    }
}

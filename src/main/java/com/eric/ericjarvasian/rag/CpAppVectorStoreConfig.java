package com.eric.ericjarvasian.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CpAppVectorStoreConfig {
    @Resource
    private CpAppDocumentLoader cpAppDocumentLoader;


    @Bean
    VectorStore cpAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        //加载 文档
        VectorStore vectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        vectorStore.add(cpAppDocumentLoader.loadMarkdowns());
        return vectorStore;
    }
}

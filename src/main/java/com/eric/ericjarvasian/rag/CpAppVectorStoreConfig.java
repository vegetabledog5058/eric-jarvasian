package com.eric.ericjarvasian.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CpAppVectorStoreConfig {
    @Resource
    private CpAppDocumentLoader cpAppDocumentLoader;
    @Resource
    private CpAppTokenTextSplitter cpAppTokenTextSplitter;
    @Resource
    private CpAppKeywordEnricher cpAppKeywordEnricher;


    @Bean
    VectorStore cpAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        //加载 文档
        VectorStore vectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        List<Document> documents = cpAppDocumentLoader.loadMarkdowns();
        //自定义分词器
//        List<Document> splitDocumentsByArg = cpAppTokenTextSplitter.splitCustomized(documents);
//关键词丰富
//        List<Document> documentsEnricked = cpAppKeywordEnricher.enrichDocuments(documents);
        vectorStore.add(documents);
        return vectorStore;
    }

}

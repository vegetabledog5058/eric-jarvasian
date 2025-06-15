package com.eric.ericjarvasian.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CpAppDocumentLoader {
    private final ResourcePatternResolver resourcePatternResolver;

    CpAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns() {


        List<Document> documents = new ArrayList<>();
        try {
            Resource[] sqlTemplates = resourcePatternResolver.getResources("classpath:document/*.md");

            for (Resource resource : sqlTemplates) {
                String filename = resource.getFilename();
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
//                    添加元数据
                        .withAdditionalMetadata("filename", filename)
                        .build();


                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                documents.addAll(reader.get());
            }
        } catch (
                IOException e) {
            log.error("Error loading markdown files", e);
        }
        return documents;
    }
}

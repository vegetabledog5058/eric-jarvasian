package com.eric.ericjarvasian.rag;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CpAppKeywordEnricher {

    private final ChatModel chatModel;

    CpAppKeywordEnricher(ChatModel dashscopeChatModel) {
        this.chatModel = dashscopeChatModel;
    }

    List<Document> enrichDocuments(List<Document> documents) {
        KeywordMetadataEnricher enricher = new KeywordMetadataEnricher(this.chatModel, 5);
        return enricher.apply(documents);
    }

}

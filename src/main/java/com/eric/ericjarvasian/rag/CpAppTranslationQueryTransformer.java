package com.eric.ericjarvasian.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.stereotype.Component;

@Component
public class CpAppTranslationQueryTransformer {
    private final TranslationQueryTransformer translationQueryTransformer;

    public CpAppTranslationQueryTransformer(ChatModel dashscopeChatModel) {
        ChatClient.Builder chatClientBuilder = ChatClient.builder(dashscopeChatModel);
        this.translationQueryTransformer = TranslationQueryTransformer.builder()
                .chatClientBuilder(chatClientBuilder)
                .targetLanguage("en")
                .build();

    }

    public String translate(String userQuery) {
        Query query = new Query(userQuery);
        // 执行查询重写
        Query transformedQuery = translationQueryTransformer.transform(query);
        return transformedQuery.text();
    }
}

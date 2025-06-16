package com.eric.ericjarvasian.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.CompressionQueryTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CpAppCompressionQueryTransformer {
    private final CompressionQueryTransformer compressionQueryTransformer;

    public CpAppCompressionQueryTransformer(ChatModel dashscopeChatModel) {
        ChatClient.Builder chatClientBuilder = ChatClient.builder(dashscopeChatModel);
        this.compressionQueryTransformer = CompressionQueryTransformer.builder()
                .chatClientBuilder(chatClientBuilder)
                .build();

    }

    public Query compress(String userQuery, List<Message> history) {
        Query query = Query.builder()
                .text(userQuery)
                .history(history)
                .build();
        return compressionQueryTransformer.transform(query);
    }

    public Query compress(String userQuery, Message... history) {
        Query query = Query.builder()
                .text(userQuery)
                .history(history)
                .build();
        return compressionQueryTransformer.transform(query);
    }
}

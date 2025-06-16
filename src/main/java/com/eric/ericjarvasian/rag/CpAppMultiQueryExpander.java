package com.eric.ericjarvasian.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CpAppMultiQueryExpander {
    private final MultiQueryExpander multiQueryExpander;

    public CpAppMultiQueryExpander(ChatModel dashscopeChatModel) {
        ChatClient.Builder chatClientBuilder = ChatClient.builder(dashscopeChatModel);
        this.multiQueryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(chatClientBuilder)
                .numberOfQueries(3)
                .build();

    }

    public List<Query> expand(String userQuery) {
        Query query = new Query(userQuery);
        // 执行查询扩展
        List<Query> expand = multiQueryExpander.expand(query);
        return expand;
    }
}

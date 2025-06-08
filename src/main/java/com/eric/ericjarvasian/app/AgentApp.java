package com.eric.ericjarvasian.app;

import com.eric.ericjarvasian.advisor.MyLoggerAdvisor;
import com.eric.ericjarvasian.advisor.ReReadingAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AgentApp {

    private final ChatClient chatClient;

    public AgentApp(ChatModel dashscopeChatModel) {
        ChatMemory chatMemory = new InMemoryChatMemory();
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory),
                        new MyLoggerAdvisor(),
                        //reReadingAdvisor
                        new ReReadingAdvisor()

                ).build();
    }

    public String chat(String message, String conversantId) {
        ChatResponse chatResponse = chatClient.prompt()
//                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
//                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))

                .user(message)
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();

        return content;
    }
}

package com.eric.ericjarvasian.demo.invoke;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
public class SpringAiTongYiInvoke implements CommandLineRunner {

    DashScopeChatOptions customOptions = DashScopeChatOptions.builder()
            .withTopP(0.7)
            .withTopK(50)
            .withTemperature(0.8)
            .withModel("deepseek-v3")
            .build();
    @Autowired
    private ChatModel dashscopeChatModel;
//基本使用
//    @Override
//    public void run(String... args) throws Exception {
//        String input = "你好,你是谁?使用什么模型?";
//        ChatResponse response = dashscopeChatModel.call(new Prompt(input));
//        System.out.println(
//                response.getResult().getOutput().getText()
//        );
//
//    }

//    自定义配置使用
//    @Override
//    public void run(String... args) throws Exception {
//        String input = "你好,你使用什么模型，在百炼平台的模型code是什么？";
//        ChatResponse response = dashscopeChatModel.call(new Prompt(input, customOptions));
//        System.out.println(
//                response.getResult().getOutput().getText()
//        );
//
//    }

//    @Override
//    public void run(String... args) throws Exception {
//        String input = "你好,你使用什么模型，在百炼平台的模型code是什么？";
//        ChatClient chatClient = ChatClient.builder(dashscopeChatModel)
//                .defaultOptions(customOptions)
//                .defaultSystem("当我问你是谁时,回答{ bark }")
//                .build();

    //create a chat client
//        ChatClient.create(dashscopeChatModel).prompt()
//                .system(sp->sp.param("bark", "我是百炼"))
//                .user("你是谁?")
//                .call();

//        ChatResponse chatResponse = chatClient.prompt()
//                .system(sp->sp.param("bark", "我是百炼"))
//                .user("你是谁?")
//                .call()
//                .chatResponse();
//        System.out.println(
//                chatResponse.getResult().getOutput().getText()
//        );
//
//        ChatResponse chatResponse2 = chatClient.prompt()
//                .system(sp->sp.param("bark", "我是千练"))
//                .user("你是谁?")
//                .call()
//                .chatResponse();
//        System.out.println(
//                chatResponse2.getResult().getOutput().getText()
//        );

//    }

    //    基于上下文聊天
    @Override
    public void run(String... args) throws Exception {

        ChatMemory chatMemory = new InMemoryChatMemory();

        ChatClient chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
        String conversantId = UUID.randomUUID().toString();

        ChatResponse chatResponse1 = chatClient.prompt()
                .user("你好，我叫Eric")

                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = chatResponse1.getResult().getOutput().getText();

        System.out.println(
                content
        );

        ChatResponse chatResponse2 = chatClient.prompt()
                .user("可以给我介绍一些风景吗?")
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call().chatResponse();
        String content2 = chatResponse2.getResult().getOutput().getText();
        System.out.println(
                content2
        );
        ChatResponse chatResponse3 = chatClient.prompt()
                .user("我是谁?")
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call().chatResponse();
        String content3 = chatResponse3.getResult().getOutput().getText();
        System.out.println(
                content3
        );


    }

}

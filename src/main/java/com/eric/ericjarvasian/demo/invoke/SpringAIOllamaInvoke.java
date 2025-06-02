package com.eric.ericjarvasian.demo.invoke;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringAIOllamaInvoke implements CommandLineRunner {
    @Autowired
    private ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        String input = "你好,你是谁?使用什么模型?";
        ChatResponse response = ollamaChatModel.call(new Prompt(input));
        System.out.println(
                response.getResult().getOutput().getText()
        );

    }
}

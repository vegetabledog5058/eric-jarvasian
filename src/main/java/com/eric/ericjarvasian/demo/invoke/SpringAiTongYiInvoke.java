package com.eric.ericjarvasian.demo.invoke;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

//    @Override
//    public void run(String... args) throws Exception {
//        String input = "你好,你是谁?使用什么模型?";
//        ChatResponse response = dashscopeChatModel.call(new Prompt(input));
//        System.out.println(
//                response.getResult().getOutput().getText()
//        );
//
//    }

    @Override
    public void run(String... args) throws Exception {
        String input = "你好,你使用什么模型，在百炼平台的模型code是什么？";
        ChatResponse response = dashscopeChatModel.call(new Prompt(input, customOptions));
        System.out.println(
                response.getResult().getOutput().getText()
        );

    }

}

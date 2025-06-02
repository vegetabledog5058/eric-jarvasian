package com.eric.ericjarvasian.demo.invoke;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;


public class LangChain4JTongYiInvoke {
    private static final String API_KEY = "sk-34bb799af25340cb9bf73b84d6887fbd";

    public static void main(String[] args) {
//chat
//        ChatLanguageModel qwenModel = QwenChatModel.builder()
//                .apiKey(API_KEY)
//                .modelName("qwen-max")
//                .enableSearch(true)
//                .temperature(0.7F)
//                .maxTokens(4096)
//                .stops(List.of("Hello"))
//                .build();
//        System.out.println(qwenModel.chat("你好"));

//生成图片
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .modelName("wanx2.1-t2i-plus")
                .apiKey(API_KEY)
                .build();
        Response<Image> response = wanxImageModel.generate("美女");
        System.out.println(response.content().url());
    }
}

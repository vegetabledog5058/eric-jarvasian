package com.eric.ericjarvasian.rag;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.generation.augmentation.QueryAugmenter;

public class CpAppContextualQueryAugmenterFactory {
    public static QueryAugmenter createCpAppContextualQueryAugmenter() {
        PromptTemplate promptTemplate = new PromptTemplate("""
                你应该输出下面的内容：Add commentMore actions
                                抱歉，我未在知识库中找到相关知识，请联系管理员添加知识^_^
                """);
        QueryAugmenter queryAugmenter = ContextualQueryAugmenter.builder()
                //当检索的上下文未检索到时是否回答,默认不回答false
                .allowEmptyContext(false)
                .emptyContextPromptTemplate(promptTemplate)
                .build();

        return queryAugmenter;
    }
}

package com.eric.ericjarvasian.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.rag.Query;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CpAppCompressionQueryTransformerTest {

@Resource
private CpAppCompressionQueryTransformer cpAppCompressionQueryTransformer;;
    @Test
    void compress() {

        Query compress = cpAppCompressionQueryTransformer.compress("And what is its second largest city?", new UserMessage("What is the capital of Denmark?"),
                new AssistantMessage("Copenhagen is the capital of Denmark."));
        Assertions.assertNotNull( compress);

    }
}
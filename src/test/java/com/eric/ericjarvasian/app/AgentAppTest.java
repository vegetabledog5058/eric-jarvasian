package com.eric.ericjarvasian.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class AgentAppTest {

    @Autowired
    private AgentApp agentApp;

    @Test
    void chat() {
        String conversantId = UUID.randomUUID().toString();
        String response = agentApp.chat("你好,你是谁?", conversantId);

        assertNotNull(response);
    }

    @Test
    void chatToRetBean() {
        AgentApp.placeList response = agentApp.chatToRetBean("给我南京最受欢迎的3个游玩好去处", null);
        assertNotNull(response);
    }

    @Test
    void chatForPrompt() {
        String conversantId = UUID.randomUUID().toString();
        List<Generation> response = agentApp.chatForPrompt("hello", conversantId);
        assertNotNull(response);
    }
}
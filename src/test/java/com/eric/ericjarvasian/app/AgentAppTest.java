package com.eric.ericjarvasian.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
}
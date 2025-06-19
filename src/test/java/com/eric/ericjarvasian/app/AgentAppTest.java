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

    @Test
    void chatWithRag() {
        String conversantId = UUID.randomUUID().toString();
        String response1 = agentApp.chatWithRag("大一职业规划怎么学？", conversantId);
//        String response2 = agentApp.chatWithRag("我是谁，我的擅长学科是什么？", conversantId);
        assertNotNull(response1);
//        assertNotNull(response2);

    }

    @Test
    void chatWithCloudModel() {
        String conversantId = UUID.randomUUID().toString();
        String response = agentApp.chatWithCloudModel("计算机专业大三应该怎么规划?",  conversantId);
        assertNotNull(response);
    }

    @Test
    void chatWithTool() {
        // 测试联网搜索问题的答案
        testMessage("想出去旅游，百度推荐几个南京好玩的地方");

        // 测试网页抓取：恋爱案例分析
        testMessage("最近不知道去哪玩，看看小红书平台上有什么建议");

        // 测试资源下载：图片下载
        testMessage("下载一个好看的南京风景壁纸");

        // 测试终端操作：执行代码
        testMessage("执行 Python3 脚本来生成数据分析报告");

        // 测试文件操作：保存用户档案
        testMessage("保存我的旅游建议为文件");

        // 测试 PDF 生成
        testMessage("生成一份旅游攻略PDF");
    }
    private void testMessage(String message){
        UUID conversantId = UUID.randomUUID();
        String res = agentApp.chatWithTool(message, conversantId.toString());
        assertNotNull( res);
    }
}
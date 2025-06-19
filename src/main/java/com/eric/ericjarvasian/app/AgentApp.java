package com.eric.ericjarvasian.app;

import com.eric.ericjarvasian.advisor.MyLoggerAdvisor;
import com.eric.ericjarvasian.chatmemory.FileChatMemory;
import com.eric.ericjarvasian.rag.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.rag.Query;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class AgentApp {
    @Autowired
    private ChatModel dashscopeChatModel;
    private final ChatClient chatClient;

    public AgentApp(ChatModel dashscopeChatModel) {
//        ChatMemory chatMemory = new InMemoryChatMemory();
        String directory = System.getProperty("user.dir") + "/tem/chat_memory";
        ChatMemory chatMemory = new FileChatMemory(directory);
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultAdvisors(
//                        new MessageChatMemoryAdvisor(chatMemory),
                        new MyLoggerAdvisor()
                        //reReadingAdvisor
//                        new ReReadingAdvisor()

                ).build();
    }

    record placeList(String place, List<String> content) {
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

    //    prompt示例
    public List<Generation> chatForPrompt(String message, String conversantId) {
        //        prompt示例1
//        PromptTemplate promptTemplate = new PromptTemplate("Tell me {num} joke about {topic}");
//        Prompt prompt = promptTemplate.create(Map.of("num", "1", "topic", "weather"));
//        prompt示例2
        String userText = """
                Tell me about three famous pirates from the Golden Age of Piracy and why they did.
                Write at least a sentence for each pirate.
                """;

        Message userMessage = new UserMessage(userText);

        String systemText = """
                You are a helpful AI assistant that helps people find information.
                Your name is {name}
                You should reply to the user's request with your name and also in the style of a {voice}.
                """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "jarvasian", "voice", "warmly"));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        List<Generation> results = dashscopeChatModel.call(prompt).getResults();
        log.info("results: {}", results);

        List<Generation> response = chatClient.prompt(prompt)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(message)
                .call()

                .chatResponse()
                .getResults();
        return response;


    }


    public placeList chatToRetBean(String message, String conversantId) {
        placeList chatResponse = chatClient.prompt()
//                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
//                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .system("每次对话后生成地名，具体游玩地方")
                .user(message)
                .call()
                .entity(placeList.class);


        return chatResponse;
    }

    @Autowired
    private VectorStore vectorStore;
    @Resource
    private CpAppTranslationQueryTransformer cpAppTranslationQueryTransformer;
    @Resource
    private CpAppCompressionQueryTransformer cpAppCompressionQueryTransformer;
    @Resource
    private CpAppMultiQueryExpander cpAppMultiQueryExpander;

    public String chatWithRag(String message, String conversantId) {
        //重写
//        message = queryRewriter.doQueryRewrite(message);
        // 压缩
        String directory = System.getProperty("user.dir") + "/tem/chat_memory";
        ChatMemory chatMemory = new FileChatMemory(directory);
        List<Message> messages = chatMemory.get(conversantId, 10);
        Query compress = cpAppCompressionQueryTransformer.compress(message, messages);


        //查询拓展
//        List<Query> expand = cpAppMultiQueryExpander.expand(message);

        //翻译
//        message = cpAppTranslationQueryTransformer.translate(message);
        ChatResponse chatResponse = chatClient.prompt()
//                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
//                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10)
//                )
                .advisors(CpAppRagCustomAdvisorFactory.createLoveAppRagCustomAdvisor(
                        vectorStore, "大一"))
//                .advisors((new QuestionAnswerAdvisor(vectorStore)))
                .user(message)
                .call()
                .chatResponse();

        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    //    云AI模型调用示例

    @Resource
    private RetrievalAugmentationAdvisor retrievalAugmentationAdvisor;
    @Resource
    private QueryRewriter queryRewriter;

    public String chatWithCloudModel(String message, String conversantId) {

        //查询重写
        message = queryRewriter.doQueryRewrite(message);
        String answer = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .advisors(retrievalAugmentationAdvisor)
                .user(message)
                .call().content();
        return answer;
    }

    @Resource
    private ToolCallback[] allTools;

    public String chatWithTool(String message, String conversantId) {
        ChatResponse chatResponse = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversantId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(message)
                .tools(allTools)
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        return content;
    }


}

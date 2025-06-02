package com.eric.ericjarvasian.demo.invoke;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTongYiInvoke {
    private static final String API_KEY = "sk-34bb799af25340cb9bf73b84d6887fbd";

    public static void main(String[] args) throws Exception {

        String apiUrl = "https://dashscope.aliyuncs.com/api/v1/apps/qwen-turbo/completion";
        String apiKey = API_KEY;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法和 headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // 构建 JSON 请求体
        String jsonInputString = "{"
                + "\"input\": {\"prompt\": \"你是谁？\"},"
                + "\"parameters\": {},"
                + "\"debug\": {}"
                + "}";

        // 发送请求体
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 获取响应码
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

    }
}

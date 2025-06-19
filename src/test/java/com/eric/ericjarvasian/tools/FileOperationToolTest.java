package com.eric.ericjarvasian.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileOperationToolTest {

    @Test
    void readFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String result = fileOperationTool.readFile("test.txt");
        Assertions.assertNotNull( result);
    }

    @Test
    void writeFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String result = fileOperationTool.writeFile("test.txt", "这里是简单的旅游建议!");
        Assertions.assertNotNull( result);
    }
}
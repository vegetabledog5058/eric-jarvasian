package com.eric.ericjarvasian.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourceDownloadToolTest {

    @Test
    void downloadResource() {
        ResourceDownloadTool resourceDownloadTool = new ResourceDownloadTool();
        String result = resourceDownloadTool.downloadResource("https://bkimg.cdn.bcebos.com/pic/f3d3572c11dfa9ec8a13fb131a9ae003918fa0ec6bd6?x-bce-process=image/format,f_auto/watermark,image_d2F0ZXIvYmFpa2UyNzI,g_7,xp_5,yp_5,P_20/resize,m_lfit,limit_1,h_1080", "xhs.png");
        Assertions.assertNotNull( result);
    }
}
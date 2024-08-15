package com.telegram.telegram.controller;

import com.telegram.telegram.request.BroadcastRequestVO;
import com.telegram.telegram.service.TelegramBot;
import com.telegram.telegram.service.TelegramImageCaption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telegram")
public class TelegramController {

    @Autowired
    private TelegramImageCaption telegramImageCaption;

    @Autowired
    private TelegramBot telegramBot;

    private static final String mockUrl = "https://dfhajuscyucpz.cloudfront.net/stage/khqr/market/bjpa3/inv-242210006747648.png";
    private static final String mockContent = "{\n" +
            "    \"order_reference_no\": \"INV-242210006742312\",\n" +
            "    \"invoice_id\": \"INV-242210006742312\",\n" +
            "    \"amount\": \"99.0\",\n" +
            "    \"currency\": \"USD\",\n" +
            "    \"merchant_name\": \"តាំងហៃ\",\n" +
            "    \"integration_type\": \"MOBAPP\",\n" +
            "    \"platform\": \"MARKET\",\n" +
            "    \"item_name\": null\n" +
            "}";

    @PostMapping("/group")
    public void groupBroadcast(@RequestBody BroadcastRequestVO broadcastRequestVO) {
        String url = broadcastRequestVO.getUrl().isEmpty() ? mockUrl : broadcastRequestVO.getUrl();
        String content = broadcastRequestVO.getContent().isEmpty() ? mockContent : broadcastRequestVO.getContent();
        this.telegramImageCaption.broadcast(url, content);
    }

    @PostMapping("/channel")
    public void channelBroadcast(@RequestBody BroadcastRequestVO broadcastRequestVO) {
        String url = broadcastRequestVO.getUrl().isEmpty() ? mockUrl : broadcastRequestVO.getUrl();
        String content = broadcastRequestVO.getContent().isEmpty() ? mockContent : broadcastRequestVO.getContent();
        this.telegramBot.sendImageToTelegram(url, content);
    }
}

package com.telegram.telegram.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramImageCaption {

    //works
    @Async
    public void broadcast(String imgUrl, String caption) {
        RestTemplate restTemplate = new RestTemplate();

        final String telegramBotToken = "7329223244:AAE-jHtXyYgEdrguw6jpX7gnqbMVf3tw5Vs";
        final String chatId = "@announcementInternal";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId);
        body.add("caption", caption);
        body.add("photo", imgUrl);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.postForObject("https://api.telegram.org/bot" + telegramBotToken + "/sendPhoto", requestEntity, String.class);
    }

//    public static void main(String[] args) {
//        final String imgUrl = "https://dfhajuscyucpz.cloudfront.net/stage/khqr/market/bjpa3/inv-242210006747648.png";
//        final String caption = "{\n" +
//                "    \"order_reference_no\": \"INV-242210006742312\",\n" +
//                "    \"invoice_id\": \"INV-242210006742312\",\n" +
//                "    \"amount\": \"99.0\",\n" +
//                "    \"currency\": \"USD\",\n" +
//                "    \"merchant_name\": \"តាំងហៃ\",\n" +
//                "    \"integration_type\": \"MOBAPP\",\n" +
//                "    \"platform\": \"MARKET\",\n" +
//                "    \"item_name\": null\n" +
//                "}";
//        broadcast(imgUrl,caption);
//    }
}

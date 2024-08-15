package com.telegram.telegram.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramBot extends TelegramLongPollingBot {

    //base on user that join in group channel
    @Override
    public void onUpdateReceived(Update update) {
        // Process incoming messages
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            // Process the message and generate a response
//            String response = processMessage(messageText);
            String response = "";
            // Send the response back to the user
            try {
                execute(new SendMessage(chatId.toString(), response));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
//    public String processMessage(String msg) {
//        return "TEST";
//    }

    public void sendImageToTelegram(String imageUrl, String content) {

//        TelegramBot telegramBot = new TelegramBot();
//
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        telegramBotsApi.registerBot(telegramBot);

        final String telegramBotToken = "7329223244:AAE-jHtXyYgEdrguw6jpX7gnqbMVf3tw5Vs";
        final String telegramChatId = "678134373";
        RestTemplate restTemplate = new RestTemplate();

        // Download the image from the URL
        byte[] imageContents = restTemplate.getForObject(imageUrl, byte[].class);

        // Send the image to Telegram along with a caption
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource imageResource = new ByteArrayResource(imageContents) {
            @Override
            public String getFilename() {
                return "image.jpg"; // Set the filename here
            }
        };


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", telegramChatId);
        body.add("photo", imageResource);
        body.add("caption", content);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.postForObject("https://api.telegram.org/bot" + telegramBotToken + "/sendPhoto", requestEntity, String.class);
    }
    @Override
    public String getBotUsername() {
        return "tanghaiiBOT";
    }
    @Override
    public String getBotToken() {
        return "7329223244:AAE-jHtXyYgEdrguw6jpX7gnqbMVf3tw5Vs";
    }
}
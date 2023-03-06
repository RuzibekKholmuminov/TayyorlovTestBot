package com.company;

import com.company.controller.KeyboardController;
import com.company.controller.MainController;
import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyTelegramBot extends TelegramLongPollingBot {
    private MainController mainController;
    private KeyboardController keyboardController;


    public MyTelegramBot() {
        this.mainController = new MainController(this);
        this.keyboardController = new KeyboardController(this);

    }
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        try {
            if (update.hasMessage()) {
                mainController.handle(update.getMessage().getText(), message);
                log(message.getFrom().getFirstName(), message.getFrom().getLastName(), message.getFrom().getId(), message.getText());
            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                Message message1 = callbackQuery.getMessage();
                keyboardController.handle(data, message1);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e); //
        }
    }

    private void log(String first_name, String last_name, Long user_id, String txt) {
        try {
            System.out.println("\n --------------------------------------------------------");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println(dateFormat.format(LocalDateTime.now()));
            System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "TayyorlovTest_bot";
    }

    @Override
    public String getBotToken() {
        return "6051614991:AAHZ1BU6Dm6dSLqkeIiXwiTslCLAAbIGG8o";
    }
}

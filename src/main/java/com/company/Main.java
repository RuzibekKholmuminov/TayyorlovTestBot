package com.company;

import db.DataBase;
import org.example.VocabBuilder;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
//               DataBase.initTable();
//               DataBase.create_profile_table();
//       DataBase.getOrigin(3);
//       DataBase.getTranslator(5);
//
//        DataBase.getOrigin(5);
//        VocabBuilder vocabBuilder = new VocabBuilder();
//        vocabBuilder.start();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
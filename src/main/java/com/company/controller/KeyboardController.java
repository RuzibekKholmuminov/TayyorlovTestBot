package com.company.controller;

import com.company.MyTelegramBot;
import com.company.util.InlineButton;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Random;

import static db.DataBase.getOrigin;

public class KeyboardController {
    static int min = 1;
    static int max = 1000;
    static int index = 1;

    private MyTelegramBot myTelegramBot ;
    public KeyboardController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(String data, Message message) {
        if (data.equals("menu")){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("              =======\uD835\uDD38\uD835\uDD64\uD835\uDD60\uD835\uDD64\uD835\uDD5A\uD835\uDD6A \uD835\uDD5E\uD835\uDD56\uD835\uDD5F\uD835\uDD66======");
            sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(InlineButton.row(
                    InlineButton.button("\uD835\uDCE2\uD835\uDCFD\uD835\uDCEA\uD835\uDCFB\uD835\uDCFD \uD835\uDCFD\uD835\uDCEE\uD835\uDCFC\uD835\uDCFD","test")
            ))));
            myTelegramBot.send(sendMessage);
        }else if (data.equals("test")){
            Random random1 = new Random();

            int int_random = random1.nextInt(max - min + 1) + min;

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText( "  \t\t\t\t\t\t\t"+index+"-\uD835\uDD96\uD835\uDD9A\uD835\uDD8A\uD835\uDD98\uD835\uDD99\uD835\uDD8E\uD835\uDD94\uD835\uDD93: \n" +
                    " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+getOrigin(int_random));
            sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList
                    (InlineButton.row(InlineButton.button(".          n͎e͎x͎t          .", "test"),
                            InlineButton.button(".          b͎a͎c͎k͎          .", "menu")
                    ))));
            index++;
            myTelegramBot.send(sendMessage);
        }
    }
}

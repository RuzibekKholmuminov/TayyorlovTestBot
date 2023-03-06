package com.company;

import com.company.util.InlineButton;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.Random;

import static db.DataBase.getOrigin;

public class DogManager {
    static int min = 1;
    static int max = 1000;
    static int index = 1;

    private MyTelegramBot myTelegramBot;

    public DogManager(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }

    public void handle(Message message) throws IOException {
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


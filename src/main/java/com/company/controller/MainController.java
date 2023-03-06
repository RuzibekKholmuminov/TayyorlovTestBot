package com.company.controller;

import com.company.MyTelegramBot;
import com.company.dto.Profile;
import com.company.repository.ProfileRepository;
import com.company.step.Profile_Step;
import com.company.util.InlineButton;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MainController {
    private MyTelegramBot myTelegramBot;
    private ProfileController profileController;
    private ProfileRepository profileRepository;

    public MainController(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
        this.profileController = new ProfileController(myTelegramBot);
        this.profileRepository = new ProfileRepository();
    }

    public void handle(String text, Message message) {
        Profile profile = profileRepository.getProfile(message.getChatId());
        if (message.hasText()) {
            if (message.getText().equals("/start")
                    || profile.getStep().equals(Profile_Step.ENTER_NAME)
                    || profile.getStep().equals(Profile_Step.ENTER_SURNAME)) {

                profileController.handle(text, message);
            }
        } else if (message.hasContact()){
            Contact contact = message.getContact();
            profileController.handle(contact);
        }

    }

    public void handCallBack(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("              =======\uD835\uDD38\uD835\uDD64\uD835\uDD60\uD835\uDD64\uD835\uDD5A\uD835\uDD6A \uD835\uDD5E\uD835\uDD56\uD835\uDD5F\uD835\uDD66======");
        sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(InlineButton.row(
                InlineButton.button("\uD835\uDCE2\uD835\uDCFD\uD835\uDCEA\uD835\uDCFB\uD835\uDCFD \uD835\uDCFD\uD835\uDCEE\uD835\uDCFC\uD835\uDCFD","test")
        ))));
        myTelegramBot.send(sendMessage);
    }
}

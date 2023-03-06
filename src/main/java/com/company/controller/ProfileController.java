package com.company.controller;

import com.company.MyTelegramBot;
import com.company.dto.Profile;
import com.company.repository.ProfileRepository;
import com.company.step.Profile_Step;
import com.company.util.InlineButton;
import com.company.util.ReplyKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;

public class ProfileController {
    private final ProfileRepository profileRepository;
    private final MyTelegramBot myTelegramBot;

    public ProfileController(MyTelegramBot myTelegramBot) {
        profileRepository = new ProfileRepository();
        this.myTelegramBot = myTelegramBot;
    }


    public void handle(String text, Message message) {
        Profile profile = profileRepository.getProfile(message.getChatId());


        if (text.equals("/start")) {
            if (profile == null) {
                createUser(message);
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());;
                sendMessage.setText("\uD835\uDCE6\uD835\uDCEE\uD835\uDCF5\uD835\uDCEC\uD835\uDCF8\uD835\uDCF6\uD835\uDCEE");
                sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(InlineButton.row(
                        InlineButton.button("\uD835\uDC06\uD835\uDC28 \uD835\uDC2D\uD835\uDC28 \uD835\uDC26\uD835\uDC1E\uD835\uDC27\uD835\uDC2E", "menu")
                ))));

                myTelegramBot.send(sendMessage);
            }
        } else if (profile.getStep().equals(Profile_Step.ENTER_NAME)) {
            profile.setName(text);
            profile.setStep(Profile_Step.ENTER_SURNAME);
            profileRepository.updateName(profile);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());

            sendMessage.setText("<b>Familyangizni kiriting</b>");
            sendMessage.setParseMode("HTML");

            myTelegramBot.send(sendMessage);
        } else if (profile.getStep().equals(Profile_Step.ENTER_SURNAME)) {
            profile.setSurname(text);
            profile.setStep(Profile_Step.ENTER_PHONE);

            profileRepository.updateSurname(profile);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());

            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("""
                    \uD83D\uDCDERo'yxatdan o'tish uchun telefon raqamizni kiriting

                    Raqamni +998********* shaklida yuboring""");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.phoneKeyboard());

            myTelegramBot.send(sendMessage);
        }
    }


    public void createUser(Message message) {
        Profile profile = new Profile(message.getChatId());
        profile.setStep(Profile_Step.ENTER_NAME);
        profileRepository.save(profile, message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("<b>Ismingizni kiriting</b>");
        sendMessage.setParseMode("HTML");

        myTelegramBot.send(sendMessage);
    }

    public void handle(Contact contact) {
        Profile profile = profileRepository.getProfile(contact.getUserId());
        profile.setPhone(contact.getPhoneNumber());
        profile.setStep(Profile_Step.REG_DONE);
        profile.setDate(LocalDate.now());
        profileRepository.updatePhone(profile);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(contact.getUserId());
        sendMessage.setText("              =======\uD835\uDD38\uD835\uDD64\uD835\uDD60\uD835\uDD64\uD835\uDD5A\uD835\uDD6A \uD835\uDD5E\uD835\uDD56\uD835\uDD5F\uD835\uDD66======");
        sendMessage.setReplyMarkup(InlineButton.keyboardMarkup(InlineButton.rowList(InlineButton.row(
                InlineButton.button("\uD835\uDCE2\uD835\uDCFD\uD835\uDCEA\uD835\uDCFB\uD835\uDCFD \uD835\uDCFD\uD835\uDCEE\uD835\uDCFC\uD835\uDCFD","test")
        ))));
        myTelegramBot.send(sendMessage);
    }
}

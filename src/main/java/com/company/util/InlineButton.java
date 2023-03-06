package com.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButton {
    public static InlineKeyboardButton button(String text, String callBack) { // text - sarlavha callback -ushlab olish uchun ko'rinmaydi
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(callBack);
        return inlineKeyboardButton;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons) {
        List<InlineKeyboardButton> row = new LinkedList<>();
        row.addAll(Arrays.asList(inlineKeyboardButtons));
        return row;
    }

    public static List<List<InlineKeyboardButton>> rowList(List<InlineKeyboardButton>... row) {
        List<List<InlineKeyboardButton>> rowlis = new LinkedList<>();
        rowlis.addAll(Arrays.asList(row));
        return rowlis;

    }

    public static InlineKeyboardMarkup keyboardMarkup(List<List<InlineKeyboardButton>> rowlis) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowlis);
        return inlineKeyboardMarkup;
    }

}

package org.example.engine;

import org.example.buttons.InlineButtonsStorage;
import org.example.buttons.InlineKeyboardsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramBotLogic {

    public void processTextMessageFromUser(String textFromUser, SendMessage messageToUser) {
        String textToUser = "";

        if (textFromUser.equals("/start")) {
            textToUser = "Привет я твой бот помощник. Выбери действие по кнопке.";

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getFirstMessageKeyboard());
        } else {
            textToUser = "Команда не распознана. Для активации бота напишите /start";
        }

        messageToUser.setText(textToUser);
    }

    public void processInlineButtonClickFromUser(String textFromUser, SendMessage messageToUser) {
        String textToUser = "";

        if (textFromUser.equals(InlineButtonsStorage.TellJoke.getCallBackData())) {
            textToUser = "Типа шутка ха ха";
        } else if (textFromUser.equals(InlineButtonsStorage.ShowWeather.getCallBackData())) {
            textToUser = "На улице срань :)))";
        } else if (textFromUser.equals(InlineButtonsStorage.PlayMusic.getCallBackData())) {
            textToUser = "Ппослушай меня https://music.yandex.ru/";
        }

        messageToUser.setText(textToUser);
    }
}

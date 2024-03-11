package org.example.engine;

import org.example.buttons.InlineButtonsStorage;
import org.example.buttons.InlineKeyboardsStorage;
import org.example.db.Joke;
import org.example.db.JokesRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.Random;

public class TelegramBotLogic {

    private Random random = new Random();
    private JokesRepository jokesRepository = new JokesRepository();

    public void processTextMessageFromUser(String textFromUser, SendMessage messageToUser) {
        String textToUser = "";

        if (textFromUser.equals("/start")) {
            try {
                textToUser = "Привет я твой бот помощник. Выбери действие по кнопке";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getFirstMessageKeyboard());
        } else {
            textToUser = "Команда не распознана. Для активации бота напишите /start";
        }

        messageToUser.setText(textToUser);
    }

    public void processInlineButtonClickFromUser(String textFromUser, SendMessage messageToUser) {
        String textToUser = "";

        if (textFromUser.equals(InlineButtonsStorage.TellJoke.getCallBackData())) {

            ArrayList<Joke> jokes = jokesRepository.getAllJokes();

            int index = random.nextInt(jokes.size());

            textToUser = jokes.get(index).getJokeText();
        } else if (textFromUser.equals(InlineButtonsStorage.ShowWeather.getCallBackData())) {
            textToUser = "На улице срань :)))";
        } else if (textFromUser.equals(InlineButtonsStorage.PlayMusic.getCallBackData())) {
            textToUser = "Ппослушай меня https://music.yandex.ru/";
        }

        messageToUser.setText(textToUser);
    }
}

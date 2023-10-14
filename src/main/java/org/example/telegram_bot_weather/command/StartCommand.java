package org.example.telegram_bot_weather.command;

import javacore.Task13.telegram_bot_weather.weatherAPI.WeatherCity;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "Привіт\uD83D\uDC4B\uD83C\uDFFB\nВибери місто, щоб я міг тобі допомогти дізнатись інформацію про погоду☺️";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chat.getId());

        List<List<InlineKeyboardButton>> collectButtons = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (WeatherCity weatherCity : WeatherCity.values()){
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(weatherCity.getCityName())
                    .callbackData(weatherCity.getCityName())
                    .build();
            row.add(button);
            if (row.size() >= 2){
                collectButtons.add(row);
                row = new ArrayList<>();
            }
        }

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
                .keyboard(collectButtons)
                .build();
        message.setReplyMarkup(keyboard);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

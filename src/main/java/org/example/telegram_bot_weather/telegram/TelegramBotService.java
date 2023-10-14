package org.example.telegram_bot_weather.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotService {
    private WeatherTelegramBot weatherTelegramBot;
    public TelegramBotService() {
        weatherTelegramBot = new WeatherTelegramBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(weatherTelegramBot);
        }catch (TelegramApiException exception){
            exception.printStackTrace();
        }
    }
}

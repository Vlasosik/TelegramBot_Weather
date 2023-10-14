package org.example.telegram_bot_weather.telegram;

import javacore.Task13.telegram_bot_weather.command.HelpCommand;
import javacore.Task13.telegram_bot_weather.command.StartCommand;
import javacore.Task13.telegram_bot_weather.weatherAPI.WeatherCity;
import javacore.Task13.telegram_bot_weather.weatherAPI.WeatherService;
import javacore.Task13.telegram_bot_weather.weatherAPI.WeatherServiceAPI;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Pattern;

import static javacore.Task13.telegram_bot_weather.telegram.BotConstants.BOT_NAME;
import static javacore.Task13.telegram_bot_weather.telegram.BotConstants.BOT_TOKEN;

public class WeatherTelegramBot extends TelegramLongPollingCommandBot {

    private WeatherService weatherService;
    private final Pattern commandPattern = Pattern.compile("/\\w+");

    public WeatherTelegramBot() {
        weatherService = new WeatherServiceAPI();
        register(new StartCommand());
        register(new HelpCommand());
        registerDefaultAction((absSender, message) -> {
            SendMessage responseMessage = new SendMessage();
            responseMessage.setText("Ви ввели текст який бот не може розпізнати." +
                    "Це бот знає ось такі команди: " +
                    "/start і /help");
            responseMessage.setChatId(message.getChatId());
            try {
                absSender.execute(responseMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        System.out.println("Received update: " + update.toString());
        System.out.println("=====================================");
        if (update.hasCallbackQuery()) {
            String callBackQuery = update.getCallbackQuery().getData();

            WeatherCity weatherCity = WeatherCity.valueOf(callBackQuery);

            String weatherRate = weatherService.getRate(weatherCity);

            SendMessage responseMessage = new SendMessage();
            responseMessage.setText(weatherRate);

            long chatId = update.getCallbackQuery().getMessage().getChatId();
            responseMessage.setChatId(chatId);
            execute(responseMessage);
        }else if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (!commandPattern.matcher(text).matches()) {
                SendMessage responseMessage = new SendMessage();
                responseMessage.setText("Ви ввели текст який бот не може розпізнати🤷🏼‍♂️\n"
                        + "Це бот знає ось такі команди: \n"
                        + "/start ~ /help");
                responseMessage.setChatId(update.getMessage().getChatId());
                execute(responseMessage);
            }
        }
    }
}

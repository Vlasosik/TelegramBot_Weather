package org.example.telegram_bot_weather.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {

    public HelpCommand() {
        super("help", "help command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage helpText = new SendMessage();
        helpText.setText("Якщо вам потрібна допомога або є питання які ви хочете уточнити, напишіть сюди👇🏻\n" +
                "@R3qp1o");
        helpText.setChatId(chat.getId());
        try {
            absSender.execute(helpText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

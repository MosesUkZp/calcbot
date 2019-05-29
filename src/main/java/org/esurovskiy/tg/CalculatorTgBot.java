package org.esurovskiy.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CalculatorTgBot extends TelegramLongPollingBot {
    public void onUpdateReceived(final Update update) {
        String text = update.getMessage().getText();
        System.out.println(text);
        Long chatId = update.getMessage().getChatId();

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName(
                "JavaScript");
        try {
            final Object eval = engine.eval(text);
            SendMessage sendMessage = new SendMessage(
                    chatId,
                    eval.toString());
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Miroslav_Makarov_Bot";
    }

    public String getBotToken() {
        return "752468125:AAHXllSYQAz0_zImB-jd8U5Fa93TuWYGSBo";
    }
}

package org.esurovskiy.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class SuperEncryptorBot extends TelegramLongPollingBot {
    private Encryptor encryptor = new SimpleEncryptor();
    private Encryptor encryptor2 = new SimpleEncryptor2();

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasInlineQuery()) {
            String query = update.getInlineQuery().getQuery();

            AnswerInlineQuery answer =
                    new AnswerInlineQuery();
            answer.setInlineQueryId(
                    update.getInlineQuery().getId());
            InlineQueryResultArticle result1 =
                    getinlineQueryResultArticle(query, encryptor);
            InlineQueryResultArticle result2 =
                    getinlineQueryResultArticle(query, encryptor2);
            answer.setResults(result1,result2);
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            System.out.println(query);
        } /*else if (update.hasMessage()) {
            String text = update.getMessage().getText();
            String enctypted = encryptor.encrypt(text);
            Long chatId = update.getMessage().getChatId();
            SendMessage message =
                    new SendMessage(chatId, enctypted);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }*/
    }
    private InlineQueryResultArticle getinlineQueryResultArticle (String text,
    Encryptor encryptor ){

        String encrypted = encryptor.encrypt(text);
        Random random = new Random();

        InlineQueryResultArticle inlineQueryResult =
                new InlineQueryResultArticle();
        inlineQueryResult
                .setId(String.valueOf(random.nextInt()))
                .setTitle(encrypted)
                .setInputMessageContent(
                        new InputTextMessageContent()
                                .setMessageText(encrypted));
        return inlineQueryResult;
    }

    public String getBotUsername() {
        return "Miroslav_Makarov_Bot";
    }

    public String getBotToken() {
        return "752468125:AAHXllSYQAz0_zImB-jd8U5Fa93TuWYGSBo";
    }
}

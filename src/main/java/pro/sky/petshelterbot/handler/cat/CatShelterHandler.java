package pro.sky.petshelterbot.handler.cat;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.petshelterbot.handler.VolunteerChatHandler;

/**
 * Handles user's pressing a button and sends a suitable menu to the user
 */
@Component
public class CatShelterHandler {
    final private Logger logger = LoggerFactory.getLogger(CatShelterHandler.class);
    final private TelegramBot telegramBot;
    final private CatShelterInfoHandler catShelterInfoHandler;
    final private CatAdoptionInfoHandler catadoptionInfoHandler;
    final private VolunteerChatHandler volunteerChatHandler;

    public CatShelterHandler(TelegramBot telegramBot,
                             CatShelterInfoHandler catShelterInfoHandler,
                             CatAdoptionInfoHandler catadoptionInfoHandler,
                             VolunteerChatHandler volunteerChatHandler) {
        this.telegramBot = telegramBot;
        this.catShelterInfoHandler = catShelterInfoHandler;
        this.catadoptionInfoHandler = catadoptionInfoHandler;
        this.volunteerChatHandler = volunteerChatHandler;
    }

    public void processCallbackQuery(CallbackQuery callbackQuery) {
        logger.info("processCallbackQuery({})-Method", callbackQuery);

        String data = callbackQuery.data();
        Message message = callbackQuery.message();
        long chatId = message.chat().id();

        switch (data) {
            case "cat_shelter_info":
                catShelterInfoHandler.sendShelterInfo(chatId);
                break;
            case "cat_shelter":
                handleShelterCommand(chatId);
                break;
            case "cat_schedule_info":
                catShelterInfoHandler.sendScheduleInfo(chatId);
                break;
            case "cat_security_info":
                catShelterInfoHandler.sendSecurityInfo(chatId);
                break;
            case "cat_safety_info":
                catShelterInfoHandler.sendSafetyInfo(chatId);
                break;
            case "cat_contact_info":
                catShelterInfoHandler.sendContactInfo(chatId);
                break;
            case "cat_adoption_info":
                catadoptionInfoHandler.sendAdoptionInfo(chatId);
                break;
            case "cat_adoption_steps":
                catadoptionInfoHandler.sendAdoptionSteps(chatId);
                break;
            case "cat_requirements_info":
                catadoptionInfoHandler.sendRequirements(chatId);
                break;
            case "cat_documents_info":
                catadoptionInfoHandler.sendDocuments(chatId);
                break;
            case "cat_transfer_info":
                catadoptionInfoHandler.sendTransfer(chatId);
                break;
            case "cat_house_info":
                catadoptionInfoHandler.sendHouseInfo(chatId);
                break;
            case "cat_house_adult_info":
                catadoptionInfoHandler.sendHouseInfoAdult(chatId);
                break;
            case "cat_house_disabled_info":
                catadoptionInfoHandler.sendHouseInfoDisabled(chatId);
                break;
            default:
                // Handle unknown data
                break;
        }
    }

    public void handleShelterCommand(Long chatId) {
        // Create buttons
        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте").callbackData("cat_shelter_info");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять животное из приюта").callbackData("cat_adoption_info");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("cat_report_info");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера").callbackData("cat_volunteer_info");


        // Create InlineKeyboardMarkup and give it buttons
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[] {button1},
                new InlineKeyboardButton[] {button2},
                new InlineKeyboardButton[] {button3},
                new InlineKeyboardButton[] {button4}
        );

        // Send buttons to user
        telegramBot.execute(new SendMessage(chatId, "Выберите действие:")
                .replyMarkup(markup));
    }

}
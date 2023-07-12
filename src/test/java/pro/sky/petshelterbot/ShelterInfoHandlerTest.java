package pro.sky.petshelterbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import pro.sky.petshelterbot.entity.Shelter;
import pro.sky.petshelterbot.handler.ShelterInfoHandler;
import pro.sky.petshelterbot.repository.ButtonsRepository;
import pro.sky.petshelterbot.repository.ShelterRepository;
import pro.sky.petshelterbot.repository.UserMessageRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@WebMvcTest
public class ShelterInfoHandlerTest {

    private ShelterInfoHandler shelterInfoHandler;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private ButtonsRepository buttonsRepository;

    @Mock
    private UserMessageRepository userMessageRepository;

    @Mock
    private ShelterRepository shelterRepository;

    @Mock
    private Message message;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        shelterInfoHandler = new ShelterInfoHandler(telegramBot, buttonsRepository, userMessageRepository, shelterRepository);
    }

    @Test
    public void testSendOpeningHours() {
        Long chatId = 123456789L;
        Long shelterId = 1L;
        Shelter shelter = new Shelter();
        shelter.setWorkTime("9:00 - 18:00");
        shelter.setAddress("123 Main Street");

        when(shelterRepository.findById(shelterId)).thenReturn(java.util.Optional.of(shelter));

        shelterInfoHandler.sendOpeningHours(chatId, shelterId);

        verify(telegramBot).execute(new SendMessage(chatId, "Расписание работы и адрес приюта:\n" +
                "9:00 - 18:00\n" +
                "Адрес: 123 Main Street"));
    }

    @Test
    public void testSendSecurityInfo() {
        Long chatId = 123456789L;
        Long shelterId = 1L;
        Shelter shelter = new Shelter();
        shelter.setTel("1234567890");
        shelter.setEmail("shelter@example.com");

        when(shelterRepository.findById(shelterId)).thenReturn(java.util.Optional.of(shelter));

        shelterInfoHandler.sendSecurityInfo(chatId, shelterId);

        verify(telegramBot).execute(new SendMessage(chatId, "Контактные данные охраны приюта:\n" +
                "Телефон: 1234567890\n" +
                "Email: shelter@example.com"));
    }
}
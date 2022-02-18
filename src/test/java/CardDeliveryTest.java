import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;


public class CardDeliveryTest {
    String planningDate = generateDate(3);

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldBeSuccess() {

        open("http://localhost:9999");
//        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
        $("[placeholder='Город']").setValue("Ульяновск");
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name='name']").setValue("Иванова Ольга");
        $("[name='phone']").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на "+ planningDate),
                Duration.ofSeconds(15));
    }
}
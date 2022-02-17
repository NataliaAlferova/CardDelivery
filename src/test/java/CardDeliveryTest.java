import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;

public class CardDeliveryTest {
    @Test
    public void shouldBeSuccess () {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(System.currentTimeMillis() + 3*24*60*60*1000);

        open("http://localhost:9999");
//        Configuration.browser ="chrome";
//        Configuration.holdBrowserOpen=true;
        $ ("[placeholder='Город']").setValue("Ульяновск");
        $ ("[placeholder='Дата встречи']").doubleClick();
        $ ("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $ ("[placeholder='Дата встречи']").setValue(formatter.format(date));
        $ ("[name='name']").setValue("Иванова Ольга");
        $ ("[name='phone']").setValue("+79999999999");
        $ ("[data-test-id='agreement']").click();
        $ ("[class='button__text']").click();
        $ ("div[class *= 'visible']").shouldBe(Visible.appear, Duration.ofSeconds(15));
    }
}
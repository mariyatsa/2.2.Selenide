package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class CardWithDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void enteringValidData() { // Ввод валидных данных
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Казань");
        String currentDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        // $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        // $("[data-test-id='date'] input").setValue(meetingDateString);
        $("[data-test-id='name'] input").setValue("Мария Хоружевская");
        $("[data-test-id='phone'] input").setValue("+79290055810");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
    }
}
/*   @Test
    void shouldSchedule() {
        open("http://localhost:9999");
        val user = generateByFaker("ru");
        $("span[data-test-id='city'] input").setValue(user.getCity().substring(0, 2));
        $$("div.menu div.menu-item").find(exactText(user.getCity())).click();
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(getDate(5));
        $("span[data-test-id='name'] input").setValue(user.getName());
        $("span[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("div.notification__content").waitUntil(text("Встреча успешно запланирована на " + getDate(5)),
                20000);
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(getDate(7));
        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='replan-notification'] button").waitUntil(visible, 20000).click();
        $("div.notification__content").waitUntil(text("Встреча успешно запланирована на " + getDate(7)),
                20000);
                */


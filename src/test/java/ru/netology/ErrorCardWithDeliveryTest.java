package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class ErrorCardWithDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void test2() {// не верно введен город
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Моссква");
        String currentDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Мария");
        $("[data-test-id='phone'] input").setValue("+79613908874");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void test3() {// неверный номер телефона
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Мария");
        $("[data-test-id='phone'] input").setValue("+779613908874");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void test4() { // имя на латинице
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Mariya");
        $("[data-test-id='phone'] input").setValue("+79613908874");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void test5() { // нет голочки согласия
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Мария Хоружевская");
        $("[data-test-id='phone'] input").setValue("+79613908874");
        //$("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible); // Для проверки видимости в Selenide
        // Вы можете использовать ассерт shouldBe и условие visible.

    }
}



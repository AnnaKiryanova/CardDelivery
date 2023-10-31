package ru.netology.delivery;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String planningDate = DataGenerator.generateDate(4, "dd.MM.yyyy");
    String futureDate = DataGenerator.generateDate(5, "dd.MM.yyyy");


    @Test
    void shouldSomethingTest() {
        String cityName = DataGenerator.generateCityName();
        String fullName = DataGenerator.generateFullName();
        String phoneNumber = DataGenerator.generatePhoneNumber();
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(cityName);
        $("[data-test-id=date] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue(fullName);
        $("[data-test-id=phone] input").setValue(phoneNumber);
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(new ByText("Успешно!")).shouldBe(visible);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + planningDate));
        $(By.className("button")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(futureDate);
        $(By.className("button")).click();
        $(new ByText("Перепланировать")).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + futureDate));

    }

}

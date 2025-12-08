package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should Be Default Date More Then Current Date For Three Days")
    void shouldBeDefaultDateMoreThenCurrentDateForThreeDays() {
        $("[data-test-id='date'] input.input__control").should(Condition.value(DataGenerator.generateDate(3)));
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        //первично заполняем форму валидными данными и датой из переменной firstMeetingDate
        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='success-notification'] div.notification__title").should(Condition.text("Успешно!")).should(Condition.visible);
        $("[data-test-id='success-notification'] div.notification__content").should(Condition.text("Встреча успешно запланирована на " + firstMeetingDate)).should(Condition.visible);

        //после успешно запланированной первой встречи меняем дату на значение переменной secondMeetingDate
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(secondMeetingDate);
        $("button.button").click();
        $("[data-test-id='replan-notification'] div.notification__title").should(Condition.text("Необходимо подтверждение")).should(Condition.visible);
        $("[data-test-id='replan-notification'] div.notification__content").should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).should(Condition.visible);

        //после получения предложения перепланировать встречу нажимаем Перепланировать
        $("[data-test-id='replan-notification'] button.button").click();
        $("[data-test-id='success-notification'] div.notification__title").should(Condition.text("Успешно!")).should(Condition.visible);
        $("[data-test-id='success-notification'] div.notification__content").should(Condition.text("Встреча успешно запланирована на " + secondMeetingDate)).should(Condition.visible);
    }

    @Test
    @DisplayName("Should successful plan meeting for name with special symbol ё")
    void shouldBeRegisterByAccountNumberWithSpecialSymbol_ё() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        String planningDate = DataGenerator.generateDate(5);

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(planningDate);
        $("[data-test-id='name'] input.input__control").setValue("Петров-Водкин Артём");
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='success-notification'] div.notification__title").should(Condition.text("Успешно!")).should(Condition.visible);
        $("[data-test-id='success-notification'] div.notification__content").should(Condition.text("Встреча успешно запланирована на " + planningDate)).should(Condition.visible);
    }
}
package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryNegativeTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBeFailedRegisterByEmptyCity() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeFailedRegisterByEmptyDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE);
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Неверно введена дата"));
    }

    @Test
    void shouldBeFailedRegisterByEmptyName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeFailedRegisterByEmptyPhone() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeFailedRegisterByEmptyCheckbox() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid span.checkbox__text").should(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldBeFailedRegisterByInvalidCity() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue("Котлас");
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid span.input__sub").should(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldBeFailedRegisterByDateLessThenThreeDaysAwayFromCurrentDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        String planningDate = DataGenerator.generateDate(2);

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(planningDate);
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldBeFailedRegisterByInvalidDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue("30.02.2025");
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Неверно введена дата"));
    }

    @Test
    void shouldBeFailedRegisterByInvalidName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue("Petrov");
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid span.input__sub").should(Condition.text("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldBeFailedRegisterByInvalidPhone() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue("+790012345");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid span.input__sub").should(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

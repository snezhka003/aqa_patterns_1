package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.util.Locale;

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
        $("[data-test-id='city'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения")).should(Condition.visible);
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
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Неверно введена дата")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByEmptyName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения")).should(Condition.visible);
    }
//почему-то в CI этот тест упал, хотя локально работает корректно
    @Test
    void shouldBeFailedRegisterByEmptyPhone() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid span.input__sub").should(Condition.text("Поле обязательно для заполнения")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByEmptyCheckbox() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid span.checkbox__text").should(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByInvalidCity() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(DataGenerator.generateInvalidCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid span.input__sub").should(Condition.text("Доставка в выбранный город недоступна")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByDateLessThenThreeDaysAwayFromCurrentDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        String invalidPlanningDate = DataGenerator.generateDate(2);

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(invalidPlanningDate);
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Заказ на выбранную дату невозможен")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByInvalidDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input.input__control")
                .doubleClick()
                .press(Keys.DELETE)
                .setValue(DataGenerator.generateInvalidDate());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] span.input_invalid span.input__sub").should(Condition.text("Неверно введена дата")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByInvalidName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(DataGenerator.generateName(new Faker(new Locale("en"))));
        $("[data-test-id='phone'] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid span.input__sub").should(Condition.text("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы.")).should(Condition.visible);
    }

    @Test
    void shouldBeFailedRegisterByInvalidPhone() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        $("[data-test-id='city'] input.input__control").setValue(validUser.getCity());
        $("[data-test-id='name'] input.input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] input.input__control").setValue(DataGenerator.generateInvalidPhone(new Faker(new Locale("ru"))));
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid span.input__sub").should(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).should(Condition.visible);
    }
}

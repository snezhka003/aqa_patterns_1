package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] validCities = new String[]{ "Майкоп", "Горно-Алтайск", "Барнаул", "Благовещенск", "Архангельск", "Астрахань", "Уфа", "Белгород", "Брянск", "Улан-Удэ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала", "Биробиджан", "Чита", "Иваново", "Магас", "Иркутск", "Нальчик", "Калининград", "Элиста", "Калуга", "Петропавловск-Камчатский", "Черкесск", "Петрозаводск", "Кемерово", "Киров", "Сыктывкар", "Кострома", "Краснодар", "Красноярск", "Симферополь", "Курган", "Курск", "Липецк", "Магадан", "Йошкар-Ола", "Саранск", "Москва", "Мурманск", "Нарьян-Мар", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Пермь", "Владивосток", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Санкт-Петербург", "Саратов", "Якутск", "Южно-Сахалинск", "Екатеринбург", "Севастополь", "Владикавказ", "Смоленск", "Ставрополь", "Тамбов", "Казань", "Тверь", "Томск", "Тула", "Кызыл", "Тюмень", "Ижевск", "Ульяновск", "Хабаровск", "Абакан", "Ханты-Мансийск", "Челябинск", "Грозный", "Чебоксары", "Анадырь", "Салехард", "Ярославль" };

        return validCities[new Random().nextInt(validCities.length)];
    }

    public static String generateName(Faker faker) {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generateNameWithSpecialSymbol_ё(Faker faker) {
        return faker.name().lastName() + " " + faker.name().firstName() + "ё";
    }

    public static String generatePhone(Faker faker) {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateInvalidDate() {
        int dayAndMonth = new Random().nextInt(99 - 32) + 32; //генерируем случайное двухзначное число для дня и месяца даты
        int year = new Random().nextInt(9999 - 3000) + 3000; //генерируем случайное четырехзначное число для года даты

        return dayAndMonth + "." + dayAndMonth + "." + year; //возвращаем строку, собранную из сгенерированных ранее чисел
    }

    public static String generateInvalidCity() {
        String[] invalidCities = new String[]{ "Котлас", "Ухта", "Тихорецк", "Сызрань", "Энгельс", "Орехово-Зуево", "Тольятти", "Сочи" };

        return invalidCities[new Random().nextInt(invalidCities.length)];
    }

    public static String generateInvalidPhone(Faker faker) {
        String firstPart = String.valueOf(new Random().nextInt(999_999 - 100_000) + 100_000); //генерируем первую часть номера из 6-ти цифр
        String secondPart = String.valueOf(new Random().nextInt(99_999 - 10_000) + 10_000); //генерируем вторую часть номера из 5-ти цифр
        String firstNumber = firstPart + secondPart; //формируем первый вариант номера из 11-ти цифр без "+" в начале
        String secondNumber = "+" + firstPart; //формируем второй вариант номера из 6-ти цифр с "+" в начале
        String[] invalidNumbers = new String[]{ firstNumber, secondNumber }; //формируем массив из двух вариантов номеров

        return invalidNumbers[new Random().nextInt(invalidNumbers.length)]; //возвращаем выбранный рандомно вариант номера из двух вариантов
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));

            return new UserInfo(generateCity(), generateName(faker), generatePhone(faker));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

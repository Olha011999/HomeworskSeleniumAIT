package demo.utils;

import demo.model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {"portishead20@gmail.com", "Password@81"},
                {"portishead20@gmail.com", "Password@81"}//можно и другие валидные данные
        };
    }

    @DataProvider(name = "addItemToCartDataProvider")
    public static Object[][] addItemToCart() {
        return new Object[][]{
                {"Computers", "Desktops", 2}, // Компьютеры → Десктопы → 2-й товар
                {"Computers", "Notebooks", 1}, // Компьютеры → Ноутбуки → 1-й товар
                {"Electronics", "Cell phones", 3} // Электроника → Мобильные телефоны → 3-й товар
        };
    }


    @DataProvider
    public Iterator<Object[]> createAccountDataProvaiderFromCsv()throws IOException {
        // Создаем список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();
        // Открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contactshw .csv"));
        // Читаем первую строку из файла
        String line = reader.readLine();
        // Обрабатываем каждую строку файла до конца
        while (line != null) {
            // Разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // Создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{new Contact()
                    .setFirstName(split[0])
                    .setLastName(split[1])
                    .setEmail(split[2])
                    .setPassword(split[3])
            });
            // Читаем следующую строку из файла
            line = reader.readLine();
        }
        // Закрываем файл после чтения всех данных
        reader.close();
        // Возвращаем итератор для списка объектов
        return list.iterator();
    }
}

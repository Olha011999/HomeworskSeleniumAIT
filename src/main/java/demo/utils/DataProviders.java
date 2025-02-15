package demo.utils;

import org.testng.annotations.DataProvider;

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
}

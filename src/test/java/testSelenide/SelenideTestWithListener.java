package testSelenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideTestWithListener {

    @Test
    public void testGithub() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $(".header-search-input").as("Поисковая строка").click();
        $(".header-search-input").as("Ввод репозитория").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").as("Отправка поискового запроса").submit();

        $(linkText("eroshenkoam/allure-example")).as("Переход по ссылке репозитория").click();
        $(partialLinkText("Issues")).as("Переход на вкладку Issues").click();
        $("#issue_68").as("Видимость Issues №68").should(Condition.visible);
        $("#issue_68_link").as("Проверка названия issues 68").shouldHave(text("Listeners NamedBy"));
    }

}

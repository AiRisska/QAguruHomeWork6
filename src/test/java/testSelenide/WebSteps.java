package testSelenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {

        open("https://github.com");
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);
        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);
        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Step("Переходим в репозиторий {repository}")
    public void goToRepository(String repository) {
        $(linkText(repository)).click();
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);

        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Step("Открываем разедел Issues")
    public void openIssueTab() {
        $(partialLinkText("Issues")).click();
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);
        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Step("Проверяем наличие Issue с номером {number}")
    public void shouldSeeIssueWithNumber(int number) {
        $("#issue_" + number).should(Condition.visible);
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);
        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Step("Проверяем, что Issue с номером {number} имеет название {title}")
    public void shouldSeeTitleIssueWithNumber(int number, String title) {
        $("#issue_" + number + "_link").shouldHave(text(title));
        step("Скриншот страницы",
                (Allure.ThrowableRunnableVoid) this::annotatedAttachment);
        String source = WebDriverRunner.getWebDriver().getPageSource();
        Allure.attachment("Исходный код страницы", source);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] annotatedAttachment() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}

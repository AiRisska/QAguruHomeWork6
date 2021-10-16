import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelenideTestWithAnnotatedStep {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static Integer ISSUE_NUMBER = 68;

    @Test
    @Feature("Issue")
    @Owner("eroshenkoam")
    @Story("Поиск по Issues")
    @Link(name = "Главная страница", url = "https://github.com")
    @DisplayName("Поискать конкретной Issue на странице")
    @Severity(SeverityLevel.BLOCKER)
    public void testGithub() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}

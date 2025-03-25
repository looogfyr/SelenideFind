import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertions{

    String snippetJUnit5 = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1450x700";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void softAssertions() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(".js-wiki-sidebar-toggle-display").shouldHave(text("SoftAssertions"));
        $(".js-wiki-sidebar-toggle-display").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text(snippetJUnit5));
    }
}

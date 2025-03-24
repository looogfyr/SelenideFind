import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsForMyFriend {

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
    static void beforeall() {
        Configuration.browserSize = "1450x700";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void SoftAssertions() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("span[data-view-component='true'] a.truncate-text").shouldHave(text("SoftAssertions"));
        $("span[data-view-component='true'] a.truncate-text").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text(snippetJUnit5));
    }
}

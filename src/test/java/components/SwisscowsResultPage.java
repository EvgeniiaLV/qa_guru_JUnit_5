package components;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwisscowsResultPage {

    private final List<SelenideElement> searchResultList = $$(".web-results article"),
            menuButtons = $$("nav.menu-summary a");
    private final SelenideElement searchField = $(".input-search"),
            firstVideoElement = $$("article.item--video p.description").get(0),
            firstPreviewLink = $$("article.item-web button.preview-link span").get(0);

    public SwisscowsResultPage searchText(String searchQuery) {
        searchField.setValue(searchQuery).pressEnter();
        return this;
    }

    public SwisscowsResultPage checkSearchResultURL(String expectedLink) {
        searchResultList.get(0).$("a").shouldHave(attribute("href", expectedLink));
        return this;
    }

    public SwisscowsResultPage checkSearchResultVideoText(String expectedVideoName) {
        firstVideoElement.shouldHave(text(expectedVideoName));
        return this;
    }

    public SwisscowsResultPage clickOnVideoButton() {
        menuButtons.get(2).click();

        return this;
    }

    public SwisscowsResultPage checkResultIsNotEmpty() {
        firstPreviewLink.shouldHave(text("Preview"));

        return this;
    }
}

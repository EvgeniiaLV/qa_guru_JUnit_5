package tests;

import components.SwisscowsResultPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class SwisscowsTests {
    SwisscowsResultPage swisscowsPage = new SwisscowsResultPage();

    static Stream<Arguments> swisscowsFirstFoundVideoShouldHaveExpectedText() {
        return Stream.of(
                Arguments.of("Griselle Ponce mambo",
                        "Eddie Torres & Griselle Ponce performing with The Mambo Legends Orchestra"
                ),
                Arguments.of(
                        "Amneris Martines salsa on 2",
                        "Juan Matos & Amneris Martine"
                )
        );
    }

    @BeforeEach
    void setUp() {
        open("https://swisscows.com/");
    }

    @CsvSource(value = {
            "A Guide to JUnit 5 Extensions , https://www.baeldung.com/junit-5-extensions",
            "A Guide to JUnit 5 , https://www.baeldung.com/junit-5",
            "JUnit 5 Parallel test execution , https://www.baeldung.com/junit-5-parallel-tests"
    })
    @ParameterizedTest(name = "For search request {0} first result should have link {1}")
    @Tag("WEB")
    void searchResultShouldShowLinkToJUnitGuideOnBaeldung(String searchQuery, String expectedLink) {
        swisscowsPage.searchText(searchQuery);
        swisscowsPage.checkSearchResultURL(expectedLink);
    }

    @MethodSource
    @DisplayName("First found video should have expected text")
    @ParameterizedTest
    @Tag("WEB")
    void swisscowsFirstFoundVideoShouldHaveExpectedText(String searchQuery, String expectedVideoText) {
        swisscowsPage.searchText(searchQuery);
        swisscowsPage.clickOnVideoButton();
        swisscowsPage.checkSearchResultVideoText(expectedVideoText);
    }

    @ValueSource(strings = {
            "Selenide", "JUnit 5", "Allure"
    })
    @ParameterizedTest(name = "Check that first found item has preview")
    @Tag("BLOCKER")
    void searchResultShouldHavePreview(String searchQuery) {
        swisscowsPage.searchText(searchQuery);
        swisscowsPage.checkResultIsNotEmpty();
    }
}

package viceCity.models.players;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;


class BasePlayerTest {
    @Disabled("not implemented yet!")
    @Test
    void test() {

    }

    @Tags({
            @Tag("b"),
            @Tag("a")
    })
    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};

        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }

    @Test
    void givenTwoLists_whenAssertingIterables_thenEquals() {
        Iterable<String> al = new ArrayList<>(asList("Java", "Junit", "Test"));
        Iterable<String> ll = new LinkedList<>(asList("Java", "Junit", "Test"));

        assertIterableEquals(al, ll);
    }

    @Test
    void whenAssertingEqualityListOfStrings_thenEqual() {
        List<String> expected = asList("Java", "\\d+", "JUnit");
        List<String> actual = asList("Java", "11", "JUnit");

        assertLinesMatch(expected, actual);
    }

    @Test
    void whenAssertingTimeout_thenNotExceeded() {
        assertTimeout(
                ofSeconds(2),
                () -> {
                    // code that requires less then 2 minutes to execute
                    Thread.sleep(1000);
                }
        );
    }
}
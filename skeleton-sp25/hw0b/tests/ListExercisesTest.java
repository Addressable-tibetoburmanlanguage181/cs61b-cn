import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListExercisesTest {

    @Test
    @Order(0)
    @DisplayName("测试 sum 的正确性")
    public void testSum() {
        List<Integer> lst1 = List.of(1, 2, 3, 4);
        List<Integer> lst2 = new ArrayList<>();

        assertThat(ListExercises.sum(lst1)).isEqualTo(10);
        assertThat(ListExercises.sum(lst2)).isEqualTo(0);
    }

    @Test
    @Order(1)
    @DisplayName("测试 evens 的正确性")
    public void testEvens() {
        List<Integer> lst = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> lstExpected = List.of(2, 4, 6);

        List<Integer> res = ListExercises.evens(lst);

        assertThat(res).isEqualTo(lstExpected);
    }

    @Test
    @Order(2)
    @DisplayName("测试 common 的正确性")
    public void testCommon() {
        List<Integer> lst1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> lst2 = List.of(4, 5, 6, 7, 8, 9);
        List<Integer> lst3 = new ArrayList<>();
        List<Integer> lstExpected = List.of(4, 5, 6);

        List<Integer> res1 = ListExercises.common(lst1, lst2);
        List<Integer> res2 = ListExercises.common(lst2, lst3);

        assertThat(res1).isEqualTo(lstExpected);
        assertThat(res2).isEmpty();
    }

    @Test
    @Order(3)
    @DisplayName("测试 countOccurrencesOfC 的正确性")
    public void testCountOccurrencesOfC() {
        List<String> lst = List.of("hello", "world", "welcome");

        assertThat(ListExercises.countOccurrencesOfC(lst, 'o')).isEqualTo(3);
        assertThat(ListExercises.countOccurrencesOfC(lst, 'a')).isEqualTo(0);
        assertThat(ListExercises.countOccurrencesOfC(lst, 'l')).isEqualTo(4);
    }

}

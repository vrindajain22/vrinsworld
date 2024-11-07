import com.google.common.truth.Truth;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    public void toListTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        testarray.addFirst(5); // after this call we expect 5 to be the first element
        assertThat(testarray.toList().get(0)).isEqualTo(5);
    }

    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();


        testarray.addFirst(5); // after this call we expect 5 to be the first element
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.addFirst(10); // after this call we expect: [10,5]
        assertThat(testarray.get(1)).isEqualTo(5);
        assertThat(testarray.get(0)).isEqualTo(10);

        testarray.addFirst(20); // after this call we expect: [20,10,5]
        assertThat(testarray.get(2)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(0)).isEqualTo(20);
    }

    @Test
    public void addLastTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        testarray.addLast(5); // after this call we expect 5 to be the first last element
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.addLast(10); // after this call we expect: [5, 10]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);

        testarray.addLast(20); // after this call we expect: [5, 10, 20]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(2)).isEqualTo(20);
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        testarray.addLast(5); // after this call we expect 5 to be the first element
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.addLast(10); // after this call we expect: [5, 10]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);

        testarray.addLast(20); // after this call we expect: [5, 10, 20]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(2)).isEqualTo(20);
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        testarray.addLast(5); // after this call we expect 5 to be the first element
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.addLast(10); // after this call we expect: [5, 10]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);

        testarray.addLast(20); // after this call we expect: [5, 10, 20]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(2)).isEqualTo(20);

        testarray.removeFirst(); // after this call we expect [10,20], after removing 5
        assertThat(testarray.get(0)).isEqualTo(10);
        assertThat(testarray.get(1)).isEqualTo(20);

        testarray.removeFirst(); // after this call we expect [20], after removing 5, 10
        assertThat(testarray.get(0)).isEqualTo(20);

        testarray.removeFirst(); // after this call we expect null after removing all items
        assertThat(testarray.get(0)).isEqualTo(null);
        assertThat(testarray.get(1)).isEqualTo(null);
        assertThat(testarray.get(2)).isEqualTo(null);

    }

    @Test
    public void removeLastTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        testarray.addLast(5); // after this call we expect 5 to be the first element
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.addLast(10); // after this call we expect: [5, 10]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);

        testarray.addLast(20); // after this call we expect: [5, 10, 20]
        assertThat(testarray.get(0)).isEqualTo(5);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(2)).isEqualTo(20);

        testarray.removeLast();
        assertThat(testarray.get(2)).isEqualTo(null);
        assertThat(testarray.get(1)).isEqualTo(10);
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.removeLast();
        assertThat(testarray.get(2)).isEqualTo(null);
        assertThat(testarray.get(1)).isEqualTo(null);
        assertThat(testarray.get(0)).isEqualTo(5);

        testarray.removeLast();
        assertThat(testarray.get(2)).isEqualTo(null);
        assertThat(testarray.get(1)).isEqualTo(null);
        assertThat(testarray.get(0)).isEqualTo(null);
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) { //Testing for a loop of 100 elements
            testarray.addLast(5); //keeps adding 5 to the end
        }
        assertThat(testarray.size()).isEqualTo(100); //expecting the size to be 100
    }

    @Test
    public void remove90() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            testarray.addLast(5);
        }
        for (int i = 0; i < 90; i++) {
            testarray.removeLast(); //removing the 10 elements, now checking the size
        }
        assertThat(testarray.size()).isEqualTo(10);
    }

    @Test
    public void addToTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        testarray.addFirst(0);
        testarray.addLast(1);
        Integer x = testarray.removeLast();

        assertThat(x).isEqualTo(1);
    }

    @Test
    public void getNull() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        Integer x = testarray.get(-1);

        assertThat(x).isEqualTo(null);
    }

    @Test
    public void removeEmpty() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        Integer x = testarray.removeLast();

        assertThat(testarray.size()).isEqualTo(0);
    }

    @Test
    public void isEmptyTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();

        assertThat(testarray.isEmpty()).isEqualTo(true);
    }

    @Test
    public void isNotEmptyTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        testarray.addLast(0);

        assertThat(testarray.isEmpty() == false);
    }

    @Test
    public void toListEmpty() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        assertThat(testarray.isEmpty() == true);
        assertThat(testarray.toList().equals(new ArrayList<>()));
    }

    @Test
    public void toListNotEmpty() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        testarray.addLast(5);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        assertThat(testarray.toList().equals(list));
    }

    @Test
    public void getrandomtest() {
        ArrayDeque<Integer> testarray = new ArrayDeque<>();
        testarray.addFirst(0);
        testarray.addLast(1); //[0,1]
        testarray.addFirst(2); //[2,0,1]
        testarray.addFirst(3); //[3,2,0,1]
        testarray.addFirst(4); //[4,3,2,0,1]
        assertThat(testarray.get(4)).isEqualTo(1);
        assertThat(testarray.get(3)).isEqualTo(0);
        testarray.addFirst(7);//[7, 4,3,2,0,1]
        testarray.addLast(8); //[7, 4,3,2,0,1, 8]
        testarray.addFirst(9); //[9, 7, 4,3,2,0,1, 8]
        testarray.addLast(10); //[9, 7, 4,3,2,0,1, 8, 10]
        testarray.addLast(11);//[9, 7, 4,3,2,0,1, 8, 10, 11]
        System.out.println(testarray);
        testarray.addFirst(12);//[12, 9, 7, 4,3,2,0,1, 8, 10, 11]
        System.out.println(testarray);
        assertThat(testarray.get(6)).isEqualTo(0);
    }

    @Test
    public void removeFirstEmptyTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque();
        testarray.addLast(5);
        assertThat((Integer) testarray.get(0)).isEqualTo(5);
        testarray.addLast(10);
        assertThat((Integer) testarray.get(1)).isEqualTo(10);
        assertThat((Integer) testarray.get(0)).isEqualTo(5);
        testarray.addLast(20);
        assertThat((Integer) testarray.get(1)).isEqualTo(10);
        assertThat((Integer) testarray.get(0)).isEqualTo(5);
        assertThat((Integer) testarray.get(2)).isEqualTo(20);
        testarray.removeFirst();
        assertThat(testarray.get(0)).isEqualTo(10);
        assertThat(testarray.get(1)).isEqualTo(20);
    }

    @Test
    public void anotherRandomTest() {
        ArrayDeque<Integer> testarray = new ArrayDeque();
        testarray.addLast(0);
        testarray.addLast(1);
        assertThat(testarray.get(1)).isEqualTo(1);
        assertThat(testarray.get(0)).isEqualTo(0);
        testarray.addLast(4);
        testarray.addFirst(5);
        testarray.addFirst(6);
        testarray.addFirst(7);
        testarray.addLast(8);
        assertThat(testarray.removeFirst()).isEqualTo(7);
    }

    @Test
    public void addingandremoving() {
        ArrayDeque<Integer> testarray = new ArrayDeque();
        testarray.addLast(0);
        testarray.addLast(1);
        testarray.removeLast();
        assertThat(testarray.get(1)).isEqualTo(null);
        testarray.removeLast();
        assertThat(testarray.get(0)).isEqualTo(null);
        testarray.addLast(5);
        assertThat(testarray.get(0)).isEqualTo(5);
    }

    @Test
    public void addingandremovingfirst() {
        ArrayDeque<Integer> testarray = new ArrayDeque();
        testarray.addFirst(0);
        testarray.addFirst(1);
        testarray.removeLast();
        assertThat(testarray.get(1)).isEqualTo(null);
        testarray.removeLast();
        assertThat(testarray.get(0)).isEqualTo(null);
        testarray.addFirst(5);
        assertThat(testarray.get(0)).isEqualTo(5);
    }
        // @Test
        // @DisplayName("ArrayDeque has no fields besides backing array and primitives")
        // void noNonTrivialFields() {
        //     List<Field> badFields = Reflection.getFields(ArrayDeque.class)
        //             .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
        //             .toList();

        //     assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
        // }
    @Test
    public void randomget() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(0);
        System.out.println(deque.get(0));  // Should print 0
    }
}

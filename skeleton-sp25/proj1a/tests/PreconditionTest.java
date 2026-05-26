import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.fail;

/** 测试 LinkedListDeque61B 类的结构是否正确。
 *  @author Noah Adhikari */
public class PreconditionTest {

    /** 返回 lld 的内部类。断言恰好只有一个内部类。 */
    private static Class<?> getLldInnerClass() {
        Class<?>[] innerClasses = LinkedListDeque61B.class.getDeclaredClasses();
        assertWithMessage("LinkedListDeque61B 应该恰好有一个内部类").that(innerClasses).hasLength(1);
        return innerClasses[0];
    }

    /** 返回 c 中所有不是基本类型、合成字段、泛型（Object）或 nodeClass 类型的字段流。 */
    private static Stream<Field> getBadFields(Class<?> c, Class<?> nodeClass) {
        return Reflection.getFields(c)
                .filter(f -> !(f.getType().isPrimitive()
                                || f.getType().equals(nodeClass)
                                || f.isSynthetic()
                                || f.getType().equals(Object.class)));
    }

    @Test
    @Order(0)
    @DisplayName("LinkedListDeque61B 结构正确且已正确泛化")
    public void genericTest() {
        Class<?> lldClass = LinkedListDeque61B.class;
        int lldNumParams = lldClass.getTypeParameters().length;
        assertWithMessage("LinkedListDeque61B 应使用一个类型参数进行泛化")
                .that(lldNumParams).isEqualTo(1);
        Class<?>[] innerClasses = lldClass.getDeclaredClasses();
        assertWithMessage("LinkedListDeque61B 应该恰好有一个内部类").that(innerClasses).hasLength(1);
        Class<?> nodeClass = innerClasses[0];
        assertWithMessage("LinkedListDeque61B 的内部类不应该是泛型的。 " +
                "（应使用外部类的泛型类型？）")
                .that(nodeClass.getTypeParameters()).isEmpty();

        // 复杂的检查：确保节点的 value 字段实际是泛型类型而不是 Object
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();
        Field[] fields = lld.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().equals(nodeClass)) {
                try {
                    f.setAccessible(true);
                    Object node = f.get(lld);
                    for (Field innerField : node.getClass().getDeclaredFields())
                        if (innerField.getType().equals(Object.class)) { // value 字段
                            innerField.setAccessible(true);
                            assertWithMessage("节点的 value 字段应该是泛型类型")
                                    .that(innerField.getGenericType())
                                    .isNotEqualTo(Object.class);
                        }
                } catch (IllegalAccessException e) {
                    fail(e.getMessage());
                }
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("LinkedListDeque61B 遵循严格的双向链表拓扑结构")
    public void doublyLinkedTest() {
        Class<?> nodeClass = getLldInnerClass();
        Map<Class<?>, Integer> typeCounts = new TreeMap<>(Comparator.comparing(Class::getSimpleName));
        for (Field f : nodeClass.getDeclaredFields()) {
            if (!f.isSynthetic()) {
                typeCounts.merge(f.getType(), 1, Integer::sum);
            }
        }
        assertWithMessage("Node 类没有恰好包含两个 Node 类型的字段")
                .that(typeCounts.get(nodeClass)).isEqualTo(2);
        assertWithMessage("Node 类没有恰好包含一个泛型 value 字段")
                .that(typeCounts.get(Object.class)).isEqualTo(1);
    }

    @Test
    @Order(2)
    @DisplayName("LinkedListDeque61B 除了节点和基本类型外没有其他字段")
    public void noNonTrivialFieldsTest() {
        Class<?> nodeClass = getLldInnerClass();
        Stream<Field> badLldFields = getBadFields(LinkedListDeque61B.class, nodeClass);
        Stream<Field> badNodeFields = getBadFields(nodeClass, nodeClass);
        List<Field> badFields = Stream.concat(badLldFields, badNodeFields).toList();


        String msg = badFields.stream()
                .map(f -> f.getType().getSimpleName() + " " + f.getName())
                .reduce("", (a, b) -> a + "\n\t" + b);

        assertWithMessage("发现了不是节点或基本类型的字段，或包含不是节点或基本类型字段的字段:" + msg).that(badFields).isEmpty();
    }

    @Test
    @Order(3)
    @DisplayName("LinkedListDeque61B 只有一个无参构造函数")
    public void noNonTrivialConstructorsTest() {
        Constructor<?>[] ctors = LinkedListDeque61B.class.getConstructors();
        assertWithMessage("在 LinkedListDeque61B 中发现了多个构造函数").that(ctors).hasLength(1);
        assertWithMessage("LinkedListDeque61B 的构造函数参数个数不为零").that(ctors[0].getParameterCount()).isEqualTo(0);
    }
}

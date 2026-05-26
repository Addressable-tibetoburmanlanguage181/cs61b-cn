package hashmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static hashmap.MyHashMapFactory.createBucketedMap;

/**
 * Brendan Hu 的测试，2015 年春季
 * Josh Hug 于 2016 年修订
 * Neil Kulkarni 于 2021 年修订
 * Noah Adhikari 于 2023 年修订
 */

/**
 * 此类使用从 java.util 导入的不同桶类型测试 MyHashMap。
 * 在尝试这些测试之前，你应该通过 TestMyHashMap 中的测试。
 */
public class TestMyHashMapBuckets {

    @DisplayName("泛型")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanityGenericsTest(Class<? extends Collection<?>> bucketType) {
        MyHashMap<String, Integer> ignored1 = createBucketedMap(bucketType);
        MyHashMap<String, Integer> ignored2 = createBucketedMap(bucketType);
        MyHashMap<Integer, String> ignored3 = createBucketedMap(bucketType);
        MyHashMap<Boolean, Integer> ignored4 = createBucketedMap(bucketType);
    }

    @DisplayName("清除")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanityClearTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.sanityClearTest(createBucketedMap(bucketType));
    }

    @DisplayName("containsKey")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void containsKeyTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.containsKeyTest(createBucketedMap(bucketType));
    }

    @DisplayName("获取")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanityGetTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.sanityGetTest(createBucketedMap(bucketType));
    }

    @DisplayName("大小")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanitySizeTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.sanitySizeTest(createBucketedMap(bucketType));
    }

    @DisplayName("放入")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanityPutTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.sanityPutTest(createBucketedMap(bucketType));
    }

    @DisplayName("功能")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void functionalityTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.functionalityTest(createBucketedMap(bucketType),
                                        createBucketedMap(bucketType));
    }

    @DisplayName("调整大小")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void resizeTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.sanityResizeTest(createBucketedMap(bucketType), 16, 0.75);
    }

    @DisplayName("边界情况")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void edgeCasesTest(Class<? extends Collection<?>> bucketType) {
        TestMyHashMap.edgeCasesTest(createBucketedMap(bucketType));
    }

    private static Stream<Arguments> bucketArguments() {
        return Stream.of(
                Arguments.of(Named.of("LinkedList", LinkedList.class)),
                Arguments.of(Named.of("ArrayList", ArrayList.class)),
                Arguments.of(Named.of("HashSet", HashSet.class)),
                Arguments.of(Named.of("Stack", Stack.class)),
                Arguments.of(Named.of("ArrayDeque", ArrayDeque.class))
        );
    }
}

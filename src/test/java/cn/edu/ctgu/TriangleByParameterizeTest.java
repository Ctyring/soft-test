package cn.edu.ctgu;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TriangleByParameterizeTest {
    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}, expected={3}")
    @CsvSource({
            "1, 2, 3, 非三角形",
            "2, 2, 3, 等腰三角形",
            "2, 2, 2, 等边三角形",
            "3, 4, 5, 不等边三角形",
            "50, 50, 50, 等边三角形",
            "100, 100, 100, 等边三角形",
            "1, 1, 100, 非三角形",
            "10, 20, 100, 非三角形"
    })
    public void testTriangleClassify(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        Assertions.assertEquals(expected, triangle.classify(a, b, c));
    }
    
}
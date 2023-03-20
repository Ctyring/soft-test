package cn.edu.ctgu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    @DisplayName("输入错误")
    void parameters_error_test() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(0, 4, 5);
        assertEquals("输入错误", type);
    }

    @Test
    @DisplayName("不等边三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 4, 6);
        assertEquals("不等边三角形", type);
    }

    @Test
    @DisplayName("等腰三角形")
    void isosceles_test() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 3, 5);
        assertEquals("等腰三角形", type);
    }

    @Test
    @DisplayName("等边三角形")
    void equilateral_test() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 3, 3);
        assertEquals("等边三角形", type);
    }

    @Test
    @DisplayName("非三角形")
    void notTriangle() {
        Triangle triangle = new Triangle();

        String type = triangle.classify(3, 3, 6);
        assertEquals("非三角形", type);
    }

}
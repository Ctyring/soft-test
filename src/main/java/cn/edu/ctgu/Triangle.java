package cn.edu.ctgu;

public class Triangle {
    public String classify(int a, int b, int c) {
        if (a <1 || a>100 || b<1 || b>100 || c<1 || c> 100) {
            return "输入错误";
        }
        if (!((a + b > c) && (a + c > b) && (b + c > a))) {
            return "非三角形";
        } else if (a == b && a == c && b == c) {
            return "等边三角形";
        } else if (a != b && a != c && b != c) {
            return "不等边三角形";
        } else {
            return "等腰三角形";
        }
    }
}

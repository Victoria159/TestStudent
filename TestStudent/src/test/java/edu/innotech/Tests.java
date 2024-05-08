package edu.innotech;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;

public class Tests {

    @Test
    @DisplayName("Проверка наличия имени")
    public void checkName() {
        Student st = new Student("Виктория");
        Assertions.assertEquals(st.getName(), "Виктория");
    }

    @Test
    @DisplayName("Проверка отсутствия имени")
    public void checkNameisNull() {
        Student st = new Student(null);
        Assertions.assertEquals(st.getName(), null);
    }

    @Test
    @DisplayName("Проверка изменения имени")
    public void checkChangeName() {
        Student st = new Student(null);
        st.setName("Ivan");
        Assertions.assertEquals(st.getName(), "Ivan");
    }

    @Test
    @DisplayName("Проверка вывода на экран")
    public void checkOutput() {
        Student st = new Student("Ivan");
        st.addGrade(3);
        Assertions.assertEquals(st.toString(), "Student{name=Ivan, marks=[3]}");
    }

    @Test
    @DisplayName("Проверка формирования идентичных хэш-кодов")
    public void testHashCodeIdentical() {
        Student student1 = new Student("Vika");
        Student student2 = new Student("Vika");
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Проверка на формирования разных хэш-кодов")
    public void testHashCodeDiff() {
        Student student1 = new Student("Vika");
        Student student2 = new Student("Ivan");
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Проверка на равенство объектов")
    public void testOdjIdent() {
        Student student1 = new Student("Semen");
        Student student2 = student1;
        Assertions.assertEquals(student1, student2);
    }

    @Test
    @DisplayName("Проверка на неравенство объектов")
    public void testOdjDiff() {
        Student student1 = new Student("Ivan");
        Student student2 = new Student("Semen");
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    @DisplayName("Проверка инкапсуляции оценок")
    public void checkEncapsulationGrades() {
        Student st = new Student("Ivan");
        st.addGrade(2);
        st.getGrades().set(0, 4);
        Assertions.assertEquals(2, st.getGrades().get(0));
    }

    @RepeatedTest(value = 4, name = "Корректные оценки добавляются в список")
    public void marksInRange(RepetitionInfo repetitionInfo) {
        Student student = new Student("Pete");
        int num = repetitionInfo.getCurrentRepetition() + 1;
        student.addGrade(num);
        Assertions.assertEquals(student.getGrades().get(0), num);
    }

    @ParameterizedTest(name = "Некорректные оценки не добавляются в список")
    @MethodSource("edu.innotech.GradesGenerator#numbers")
    public void marksNotInRange(int x) {
        Student student = new Student("Ivan");
        Assertions.assertThrows(IllegalArgumentException.class, () -> student.addGrade(x));
    }
}



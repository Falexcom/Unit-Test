import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testPersonCreation() {
        // given:
        String name = "John";
        String family = "Doe";
        int age = 30;
        Sex sex = Sex.MAN;
        Education education = Education.HIGHER;

        // when:
        Person person = new Person(name, family, age, sex, education);

        // then:
        assertEquals(name, person.getName());
        assertEquals(family, person.getFamily());
        assertEquals(age, person.getAge());
        assertEquals(sex, person.getSex());
        assertEquals(education, person.getEducation());
    }

    @Test
    public void testPersonAgeRange() {
        // given:
        Person person = new Person("John", "Doe", 20, Sex.MAN, Education.HIGHER);

        // when:
        boolean isAdult = person.getAge() >= 18 && person.getAge() <= 65;

        // then:
        assertTrue(isAdult);
    }

    @Test
    public void testPersonEducationLevel() {
        // given:
        Person person = new Person("John", "Doe", 25, Sex.MAN, Education.HIGHER);

        // when:
        boolean isHigherEducation = person.getEducation() == Education.HIGHER;

        // then:
        assertTrue(isHigherEducation);
    }
}

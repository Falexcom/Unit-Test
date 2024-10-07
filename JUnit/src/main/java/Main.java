import java.util.*;
import java.util.stream.Collectors;

enum Sex {
    MAN,
    WOMAN
}

enum Education {
    ELEMENTARY,
    SECONDARY,
    FURTHER,
    HIGHER
}

class Person {
    private String name;
    private String family;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Random random = new Random();

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            String name = names.get(random.nextInt(names.size()));
            String family = families.get(random.nextInt(families.size()));
            int age = random.nextInt(100); // Возраст от 0 до 99
            Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
            Education education = Education.values()[random.nextInt(Education.values().length)];

            persons.add(new Person(name, family, age, sex, education));
        }

        long countUnderage = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + countUnderage);

        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников: " + conscripts.size());

        List<Person> employablePersons = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> {
                    if (person.getSex() == Sex.WOMAN) {
                        return person.getAge() >= 18 && person.getAge() <= 60;
                    } else {
                        return person.getAge() >= 18 && person.getAge() <= 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + employablePersons.size());

    }
}
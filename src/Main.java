import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //количество несовершеннолетних (т.е. людей младше 18 лет).
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних (т.е. людей младше 18 лет) - " + count);
        System.out.println("___________________________________________________________________________________________");
        //список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> maleRecruit = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников (т.е. мужчин от 18 и до 27 лет) - " + count);
        System.out.println(maleRecruit);
        System.out.println("___________________________________________________________________________________________");
        // отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<Person> listHigherEducationManAndWoman = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER && person.getAge() >= 18)
                .filter((person) -> (person.getAge() <= 65 && person.getSex() == Sex.MAN) || (person.getAge() <= 60 && person.getSex() == Sex.WOMAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Список потенциально работоспособных людей с высшим образованием - " + listHigherEducationManAndWoman);
        System.out.println("___________________________________________________________________________________________");
    }
}


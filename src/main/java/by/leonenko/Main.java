package by.leonenko;

import by.leonenko.model.Animal;
import by.leonenko.model.Car;
import by.leonenko.model.Flower;
import by.leonenko.model.House;
import by.leonenko.model.Person;
import by.leonenko.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        //task1();
        //task2();
        //task3();
        //task4();
        //task5();
        //task6();
        //task7();
        //task8();
        //task9();
        //task10();
        //task11();
        //task12();
        task13();
        //task14();
        //task15();
    }

    /**
     * Задача №1 -
     * Из представленных животных отобрать все молодые особи от 10 до 20 лет и отсортировать по возрасту (по возрастанию)
     * далее - распределить по 7 на каждый зоопарк. Зоопарков неограниченное кол-во а вы - директор 3-го по счёту зоопарка.
     * Полученных животных вывести в консоль.
     */
    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int lowerBorderAge = 10;
        int upperBorderAge = 20;
        int numberMyZoo = 3;
        int capacityZoo = 7;
        animals.stream()
                .filter(animal -> animal.getAge() >= lowerBorderAge && animal.getAge() <= upperBorderAge)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip((numberMyZoo - 1) * capacityZoo).limit(capacityZoo).forEach(System.out::println);
    }


    /**
     * Задача №2 -
     * Отобрать всех животных из Японии (Japanese) и записать породу UPPER_CASE в если пол Female
     * преобразовать к строкам породы животных и вывести в консоль
     */
    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String country = "Japanese";
        String gender = "Female";
        animals.stream()
                .filter(animal -> country.equals(animal.getOrigin()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> gender.equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    /**
     * Задача №3 -
     * Отобрать всех животных старше 30 лет и вывести все страны происхождения без дубликатов начинающиеся на "A"
     */
    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int age = 30;
        String prefix = "A";
        animals.stream()
                .filter(animal -> animal.getAge() > age)
                .map(Animal::getOrigin)
                .distinct()
                .filter(country -> country.startsWith(prefix))
                .forEach(System.out::println);
    }

    /**
     * Задача №4 -
     * Подсчитать количество всех животных пола = Female. Вывести в консоль
     */
    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String gender = "Female";
        long count = animals.stream()
                .filter(animal -> gender.equals(animal.getGender()))
                .count();
        System.out.println(count);
    }

    /**
     * Задача №5 -
     * Взять всех животных возрастом 20 - 30 лет. Есть ли среди нах хоть один из страны Венгрия (Hungarian)?
     * Ответ вывести в консоль
     */
    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int lowerBorderAge = 20;
        int upperBorderAge = 30;
        String country = "Hungarian";
        boolean result = animals.stream()
                .filter(animal -> animal.getAge() >= lowerBorderAge && animal.getAge() <= upperBorderAge)
                .anyMatch(animal -> country.equals(animal.getOrigin()));
        System.out.println(result);
    }

    /**
     * Задача №6 -
     * Взять всех животных. Все ли они пола Male и Female ?
     * Ответ вывести в консоль
     */
    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String genderFemale = "Female";
        String genderMale = "Male";
        boolean result = animals.stream()
                .allMatch(animal -> genderFemale.equals(animal.getGender()) || genderMale.equals(animal.getGender()));
        System.out.println(result);
    }

    /**
     * Задача №7 -
     * Взять всех животных. Узнать что ни одно из них не имеет страну происхождения Oceania.
     * Ответ вывести в консоль
     */
    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String country = "Oceania";
        boolean result = animals.stream()
                .noneMatch(animal -> country.equals(animal.getOrigin()));
        System.out.println(result);
    }

    /**
     * Задача №8 -
     * Взять всех животных. Отсортировать их породу в стандартном порядке и взять первые 100.
     * Вывести в консоль возраст самого старого животного
     */
    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int quantity = 100;
        int age = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .skip(quantity).max(Comparator.comparingInt(Animal::getAge))
                .get().getAge();
        System.out.println(age);
    }

    /**
     * Задача №9 -
     * Взять всех животных. Преобразовать их в породы, а породы в []char
     * Вывести в консоль длину самого короткого массива
     */
    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int length = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(array -> array.length))
                .get().length;
        System.out.println(length);
    }

    /**
     * Задача №10 -
     * Взять всех животных. Подсчитать суммарный возраст всех животных. Вывести результат в консоль
     */
    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int totalAge = animals.stream().mapToInt(Animal::getAge).sum();
        System.out.println(totalAge);
    }

    /**
     * Задача №11 -
     * Взять всех животных. Подсчитать средний возраст всех животных из индонезии (Indonesian). Вывести результат в консоль
     */
    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String country = "Indonesian";
        double average = animals.stream()
                .filter(animal -> country.equals(animal.getOrigin()))
                .mapToInt(Animal::getAge).average().getAsDouble();
        System.out.println(average);
    }

    /**
     * Задача №12 -
     * Во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины)
     * возраст от 18 до 27 лет. Преимущество отдаётся людям военной категории 1, на втором месте - военная категория 2,
     * и на третьем месте военная категория 3. Отсортировать всех подходящих кандидатов в порядке их
     * приоритета по военной категории. Однако взять на обучение академия может только 200 человек. Вывести их в консоль.
     */
    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        int lowerAge = 18;
        int upperAge = 27;
        String gender = "Male";
        int limit = 200;
        LocalDate currentDate = LocalDate.now();
        people.stream()
                .filter(person -> gender.equals(person.getGender()))
                .filter(person -> Period.between(person.getDateOfBirth(), currentDate).getYears() >= lowerAge
                        && Period.between(person.getDateOfBirth(), currentDate).getYears() <= upperAge)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(limit).forEach(System.out::println);
    }


    /**
     * Задача №13 -
     * Надвигается цунами и в районе эвакуации требуется в первую очередь обойти дома и эвакуировать больных и раненых (из Hospital),
     * во вторую очередь детей и стариков (до 18 и пенсионного возраста) а после всех остальных. В первый этап эвакуации мест
     * в эвакуационном транспорте только 500. Вывести всех людей попадающих в первый этап эвакуации в порядке приоритета (в консоль).
     */
    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        String buildingType = "Hospital";
        int childrenAge = 18;
        int retirementAgeMale = 63;
        int retirementAgeFemale = 58;
        String genderMale = "Male";
        String genderFemale = "Female";
        int limit = 500;
        LocalDate currentDate = LocalDate.now();
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(buildingType.equals(house.getBuildingType()) ? 1 :
                                (Period.between(person.getDateOfBirth(), currentDate).getYears() < childrenAge ||
                                        (genderMale.equals(person.getGender()) &&
                                                Period.between(person.getDateOfBirth(), currentDate).getYears() >= retirementAgeMale) ||
                                        (genderFemale.equals(person.getGender()) &&
                                                Period.between(person.getDateOfBirth(), currentDate).getYears() >= retirementAgeFemale) ? 2 : 3), person)))
                .sorted(Map.Entry.comparingByKey())
                .map(o -> o.getValue())
                .limit(limit)
                .forEach(System.out::println);
    }

    /**
     * Задача №14 -
     * Из перечня автомобилей приходящих на рынок Азии логистическому агентству предстоит отсортировать их в порядке следования
     * 1.Туркменистан - 2.Узбекистан - 3.Казахстан - 4.Кыргызстан - 5.Россия - 6.Монголия.
     * Все автомобили марки "Jaguar" а так же все авто цвета White идут в первую страну.
     * Из оставшихся все автомобили с массой до 1500 кг и марок BMW, Lexus, Chrysler и Toyota идут во второй эшелон.
     * Из оставшихся все автомобили Черного цвета с массой более 4000 кг и все GMC и Dodge идут в третий эшелон.
     * Из оставшихся все автомобили года выпуска до 1982 или все модели "Civic" и "Cherokee" идут в четвёртый эшелон.
     * Из оставшихся все автомобили цветов НЕ Yellow, Red, Green и Blue или же по стоимости дороже 40000 в пятый эшелон
     * Из оставшиеся все автомобили в vin номере которых есть цифра "59" идут в последний шестой эшелон.
     * Оставшиеся автомобили отбрасываем, они никуда не идут.
     * Измерить суммарные массы автомобилей всех эшелонов для каждой из стран и подсчитать сколько для каждой страны
     * будет стоить транспортные расходы если учесть что на 1 тонну транспорта будет потрачено 7.14 $.
     * Вывести суммарные стоимости в консоль. Вывести общую выручку логистической кампании.
     */
    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    /**
     * Задача №15 -
     * Для оранжереи нужно подобрать растения соответствующие требованиям.
     * Во-первых, нужно произвести сложную сортировку каталога растений. Отсортировать их по странам происхождения в обратном порядке
     * После по стоимости и еще по водопотреблению в обратном порядке. Из этого списка взять растения название которых
     * от буквы "S" до буквы "C". Если растения тенелюбивые и им подходит горшок из стекла, алюминия или стали - то выбираем их.
     * Далее на каждое растение надо рассчитать стоимость растения + стоимость потребления воды за 5 лет c учётом того
     * что кубометр воды стоит 1.39 $. Суммировать общую стоимость обслуживания всех растений. Во сколько это обойдётся бюджету?
     */
    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}
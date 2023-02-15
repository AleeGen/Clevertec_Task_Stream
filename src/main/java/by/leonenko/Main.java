package by.leonenko;

import by.leonenko.model.Animal;
import by.leonenko.model.Car;
import by.leonenko.model.Flower;
import by.leonenko.model.House;
import by.leonenko.model.Person;
import by.leonenko.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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
        //task13();
        task14();
        //task15();
    }

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

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String gender = "Female";
        long count = animals.stream()
                .filter(animal -> gender.equals(animal.getGender()))
                .count();
        System.out.println(count);
    }

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

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String genderFemale = "Female";
        String genderMale = "Male";
        boolean result = animals.stream()
                .allMatch(animal -> genderFemale.equals(animal.getGender()) || genderMale.equals(animal.getGender()));
        System.out.println(result);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String country = "Oceania";
        boolean result = animals.stream()
                .noneMatch(animal -> country.equals(animal.getOrigin()));
        System.out.println(result);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int quantity = 100;
        int age = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .skip(quantity).max(Comparator.comparingInt(Animal::getAge))
                .get().getAge();
        System.out.println(age);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int length = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(array -> array.length))
                .get().length;
        System.out.println(length);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int totalAge = animals.stream().mapToInt(Animal::getAge).sum();
        System.out.println(totalAge);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String country = "Indonesian";
        double average = animals.stream()
                .filter(animal -> country.equals(animal.getOrigin()))
                .mapToInt(Animal::getAge).average().getAsDouble();
        System.out.println(average);
    }

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
                .map(Map.Entry::getValue)
                .limit(limit)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        Set<String> filter2 = Set.of("BMW", "Lexus", "Chrysler", "Toyota");
        Set<String> filter3 = Set.of("GMC", "Dodge");
        Set<String> filter4 = Set.of("Civic", "Cherokee");
        Set<String> filter5 = Set.of("Yellow", "Red", "Green", "Blue");
        AtomicInteger revenue = new AtomicInteger();
        Stream.of(cars)
                .map(o -> goToCountry(o, car -> "Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor()), revenue))
                .map(o -> goToCountry(o, car -> car.getMass() < 1500 && filter2.contains(car.getCarMake()), revenue))
                .map(o -> goToCountry(o, car -> (car.getMass() > 4000 && "Black".equals(car.getColor())) ||
                        filter3.contains(car.getCarMake()), revenue))
                .map(o -> goToCountry(o, car -> car.getReleaseYear() < 1982 || filter4.contains(car.getCarMake()), revenue))
                .map(o -> goToCountry(o, car -> !filter5.contains(car.getColor()) || car.getPrice() > 40000, revenue))
                .map(o -> goToCountry(o, car -> car.getVin().contains("59"), revenue)).anyMatch(o -> true);
        System.out.println("Выручка компании: " + revenue);
    }

    private static List<Car> goToCountry(List<Car> cars, Predicate<Car> predicate, AtomicInteger total) {
        return Stream.of(cars).flatMap(list -> {
                    int expenses = (int) (list.stream().mapToInt(Car::getMass).sum() / 1000 * 7.14);
                    System.out.println("Расходы страны: " + expenses);
                    total.addAndGet(expenses);
                    return list.stream();
                })
                .collect(Collectors.partitioningBy(predicate))
                .entrySet().stream()
                .<List<Car>>mapMulti((entry, consumer) -> {
                    if (!entry.getKey()) consumer.accept(entry.getValue());
                }).flatMap(Collection::stream).toList();
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        Set<String> materials = Set.of("Aluminum", "Glass", "Steel");
        String filterPattern = "^[C-S].*";
        int numberDaysInFiveYears = 1826;
        double costWater = 1.39;
        int literVsCube = 1000;
        int total = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed().thenComparing(Flower::getPrice).reversed()
                        .thenComparing(Flower::getWaterConsumptionPerDay))
                .filter(f -> Pattern.compile(filterPattern).matcher(f.getCommonName()).matches())
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().stream().anyMatch(materials::contains))
                .reduce(BigDecimal.valueOf(0), (cost, flower) -> cost.add(BigDecimal.valueOf(flower.getPrice() +
                                (flower.getWaterConsumptionPerDay() / literVsCube * numberDaysInFiveYears * costWater))),
                        BigDecimal::add).intValue();
        System.out.println(total);
    }
}
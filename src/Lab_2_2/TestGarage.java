package Lab_2_2;

public class TestGarage {
    public static void main(String[] args) {
        GarageCar myGarage = new GarageCar(); // создаем новый гараж
        Car myCar1 = new Car("Ford", 200, "Mustang", 2, false, "П186ФЕ"); // создаем легковую машину
        myGarage.addCar(myCar1); // добавляем ее в гараж
        myGarage.addCar(new Car("LADA", 140, "Kalina", 4, false, "У387ЫВ"));// добавляем еще одну машину
        Truck myTruck = new Truck("Dove", 160, "DTS", 700, true, "Е113ВК");// создаем грузовик
        myGarage.addCar(myTruck); // добавляем его в гараж
        myGarage.printGarage(); // выводи на экран содержимое гаража
        if (myGarage.findCar(myCar1)) { // ищем машину
            System.out.println("Да");
        } else {
            System.out.println("Нет");
        }
        myGarage.removeCar(myTruck);
        System.out.println("\nПосле удаления грузовика");
        myGarage.printGarage(); // выводи на экран содержимое гаража
    }
}

package Lab_2_1;

/**
 * Класс Холдинг тест - класс для тестирования объектов класса {@link Holding}
 * 
 * @author Иванов И. И.
 */
public class TestHolding {
    public static void main(String[] args) {
        Holding holding = new Holding();

        System.out.println("------------First Addition------------\n");
        Newspaper newNewspaper = new Newspaper("Time", 900000000, "Mixed", 3500000);
        holding.addSubsidiary(newNewspaper);
        holding.printAll();

        System.out.println("------------Second Addition-----------\n");
        TVChannel newTVChannel = new TVChannel(
                "New in Science",
                4000000,
                "Schoolchildren, Students, Teachers",
                "Science");
        holding.addSubsidiary(newTVChannel);
        holding.printAll();

        System.out.println("----------------Counter---------------\n");
        holding.countSubsidiaryTypes();
        System.out.println("\n------------Third Addition-----------\n");

        TVChannel newTVChannel2 = new TVChannel(
                "Music RN",
                10000000,
                "Mixed",
                "Music");
        holding.addSubsidiary(newTVChannel2);
        holding.printAll();

        System.out.println("------------First Removal-----------\n");
        holding.removeSubsidiary(newNewspaper);
        holding.printAll();

        System.out.println("----------------Counter---------------\n");
        holding.countSubsidiaryTypes();
    }
}

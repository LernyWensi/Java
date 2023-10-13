package Lab_2_1;

/**
 * Класс Газета - производный от класса СМИ {@link MassMedia} класс
 * для газет
 * 
 * @author Иванов И. И.
 */
public class Newspaper extends MassMedia {
    /** Поле для хранения ежегодного тиража газеты */
    private int annuallyCirculation;

    /**
     * Создает газету с названием "Без названия", приблизительной аудиторий
     * равной 0, типом целевой аудитории - "Неизвестно",
     * и ежегодным тиражом равным 0
     */
    public Newspaper() {
        super();
        annuallyCirculation = 0;
    }

    /**
     * Создает газету с заданными значениями названия, приблизительным
     * количеством аудитории, ее типом и ежегодным тиражом
     * 
     * @param name                - название газеты
     * @param approximateAudience - приблизительное количество аудитории
     * @param targetAudience      - тип целевой аудитории
     * @param annuallyCirculation - ежегодный тираж газеты
     * 
     */
    public Newspaper(
            String name,
            int approximateAudience,
            String targetAudience,
            int annuallyCirculation) {

        super(name, approximateAudience, targetAudience);
        this.annuallyCirculation = annuallyCirculation;
    }

    /**
     * Устанавливает значение поля {@link Newspaper#annuallyCirculation}
     * 
     * @param annuallyCirculation - ежегодный тираж газеты
     */
    public void setAnnuallyCirculation(int annuallyCirculation) {
        this.annuallyCirculation = annuallyCirculation;
    }

    /**
     * Возвращает значение поля {@link Newspaper#annuallyCirculation}
     * 
     * @return целое значение ежегодного тиража газеты
     */
    public int getAnnuallyCirculation() {
        return annuallyCirculation;
    }

    /**
     * Выводит значения всех полей экземпляра класса:
     * название {@link MassMedia#name},
     * приблизительное количество аудитории {@link MassMedia#approximateAudience},
     * тип целевой аудитории {@link MassMedia#targetAudience},
     * ежегодный тираж {@link Newspaper#annuallyCirculation}
     */
    @Override
    public void print() {
        super.print();
        System.out.println(String.format("\tAnnually circulation: %s\n", getAnnuallyCirculation()));
    }
}

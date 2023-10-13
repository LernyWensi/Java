package Lab_2_1;

/**
 * Класс СМИ - базовый класс для объектов средств массовой информации
 * 
 * @author Иванов И. И.
 */
public class MassMedia {
    /** Поле для хранения названия газеты или ТВ канала */
    private String name;

    /** Поле для хранения типа целевой аудитории */
    private String targetAudience;

    /** Поле для хранения приблизительного объема аудитории */
    private int approximateAudience;

    /**
     * Создает СМИ с названием "Без названия", приблизительной аудиторий, равной 0 и
     * типом целевой аудитории - "Неизвестно"
     */
    public MassMedia() {
        name = "Без названия";
        approximateAudience = 0;
        targetAudience = "Неизвестно";
    }

    /**
     * Создает СМИ с заданными значениями названия, приблизительным количеством
     * аудитории и ее типом
     * 
     * @param name                - название газеты или ТВ канала
     * @param approximateAudience - приблизительное количество аудитории
     * @param targetAudience      - тип целевой аудитории
     */
    public MassMedia(String name, int approximateAudience, String targetAudience) {
        this.name = name;
        this.approximateAudience = approximateAudience;
        this.targetAudience = targetAudience;
    }

    /**
     * Выводит значения всех полей экземпляра класса:
     * название {@link MassMedia#name},
     * приблизительное количество аудитории {@link MassMedia#approximateAudience},
     * тип целевой аудитории {@link MassMedia#targetAudience
     */
    public void print() {
        System.out.println(
                String.format(
                        "Name: %s\n\tApproximate audience: %s\n\tTarget audience: %s",
                        getName(),
                        getApproximateAudience(),
                        getTargetAudience()));
    }

    /**
     * Устанавливает значение поля {@link MassMedia#name}
     * 
     * @param name - название газеты или ТВ канала
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает значение поля {@link MassMedia#approximateAudience}
     * 
     * @param approximateAudience - приблизительный объем аудитории
     */
    public void setApproximateAudience(int approximateAudience) {
        this.approximateAudience = approximateAudience;
    }

    /**
     * Устанавливает значение поля {@link MassMedia#targetAudience}
     * 
     * @param targetAudience - тип целевой аудитория
     */
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    /**
     * Возвращает значение поля {@link MassMedia#name}
     * 
     * @return строку с названием газеты или Тв канала
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает значение поля {@link MassMedia#approximateAudience}
     * 
     * @return целое значение приблизительного количеством аудитории
     */
    public int getApproximateAudience() {
        return approximateAudience;
    }

    /**
     * Возвращает значение поля {@link MassMedia#targetAudience}
     * 
     * @return строку с типом целевой аудитории
     */
    public String getTargetAudience() {
        return targetAudience;
    }
}
package Lab_2_1;

/**
 * Класс Телеканал - производный от класса СМИ {@link MassMedia} класс
 * для телеканалов
 * 
 * @author Иванов И. И.
 */
public class TVChannel extends MassMedia {
    /** Поле для хранения типа контента телеканала */
    private String type;

    /**
     * Создает телеканал с названием "Без названия", приблизительной аудиторий
     * равной 0, типом целевой аудитории - "Неизвестно"
     * и типом контента телеканал - "Неизвестно"
     */
    public TVChannel() {
        super();
        type = "Неизвестно";
    }

    /**
     * Создает телеканал с заданными значениями названия, приблизительным
     * количеством аудитории, ее типом и типом контента телеканала
     * 
     * @param name                - ТВ канала
     * @param approximateAudience - приблизительное количество аудитории
     * @param targetAudience      - тип целевой аудитории
     * @param type                - тип контента телеканала
     * 
     */
    public TVChannel(
            String name,
            int approximateAudience,
            String targetAudience,
            String type) {

        super(name, approximateAudience, targetAudience);
        this.type = type;
    }

    /**
     * Устанавливает значение поля {@link TVChannel#type}
     * 
     * @param type - тип контента телеканала
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Возвращает значение поля {@link TVChannel#type}
     * 
     * @return строку с типом контента телеканала
     */
    public String getType() {
        return type;
    }

    /**
     * Выводит значения всех полей экземпляра класса:
     * название {@link MassMedia#name},
     * приблизительное количество аудитории {@link MassMedia#approximateAudience},
     * тип целевой аудитории {@link MassMedia#targetAudience},
     * тип контента телеканала {@link TVChannel#type}
     */
    @Override
    public void print() {
        super.print();
        System.out.println(String.format("\tType: %s\n", getType()));
    }
}
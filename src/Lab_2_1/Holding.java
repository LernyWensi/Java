package Lab_2_1;

import java.util.ArrayList;

/**
 * Класс Холдинг - класс для объектов включающая в себя материнскую компанию и
 * ряд более мелких дочерних компаний
 * 
 * @author Иванов И. И.
 */
public class Holding {
    /** Поле для хранения названия холдинга */
    private String name;

    /** Поле для хранения списка дочерних компаний */
    private ArrayList<MassMedia> subsidiaries = new ArrayList<MassMedia>();

    /**
     * Создает холдинг с названием "Без названия" и пустым списком дочерних компаний
     */
    public Holding() {
        this.name = "Без названия";
    }

    /**
     * Создает холдинг с заданными значениями названия и списком дочерних компаний
     * 
     * @param name         - название холдинга
     * @param subsidiaries - список дочерних компаний
     */
    public Holding(String name, ArrayList<MassMedia> subsidiaries) {
        this.name = name;
        this.subsidiaries = subsidiaries;
    }

    /**
     * Устанавливает значение поля {@link Holding#name}
     * 
     * @param name - название холдинга
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает значение поля {@link Holding#name}
     * 
     * @return строку с название холдинга
     */
    public String getName() {
        return this.name;
    }

    /**
     * Добавляет элемент в поле {@link Holding#subsidiaries}
     * 
     * @param subsidiary экземпляр класса {@link MassMedia}
     */
    public void addSubsidiary(MassMedia subsidiary) {
        subsidiaries.add(subsidiary);
    }

    /**
     * Удаляет элемент из поля {@link Holding#subsidiaries}, если данного элемента
     * нет в списке - выводит сообщение об этом
     * 
     * 
     * @param subsidiary экземпляр класс {@link MassMedia}
     */
    public void removeSubsidiary(MassMedia subsidiary) {
        if (!subsidiaries.contains(subsidiary)) {
            System.out.println("There is no such subsidiary");
            return;
        }

        subsidiaries.remove(subsidiary);
    }

    /**
     * Отдельно выводит две строчки с количество хранимых в поле
     * {@link Holding#subsidiaries} экземпляров классов:
     * {@link TVChannel}, {@link Newspaper}
     */
    public void countSubsidiaryTypes() {
        int newspaperCounter = 0;
        int tvChannelCounter = 0;

        for (MassMedia subsidiary : subsidiaries) {
            if (subsidiary instanceof TVChannel) {
                tvChannelCounter++;
            } else {
                newspaperCounter++;
            }
        }

        System.out.println(String.format("Newspapers: %s", newspaperCounter));
        System.out.println(String.format("TV channels: %s", tvChannelCounter));
    }

    /**
     * Выводит значение всех полей дочерних классов {@link MassMedia}
     * хранимых в поле {@link Holding#subsidiaries}
     * вызывая на них метод {@link MassMedia#print()}
     */
    public void printAll() {
        subsidiaries.forEach(s -> s.print());
    }
}
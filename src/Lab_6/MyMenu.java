package Lab_6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyMenu extends JFrame implements ActionListener {
    private JTextField myText;

    public static void main(String[] args) {
        new MyMenu("Пример окна с меню");// создаем окно с меню
    }

    public MyMenu(String name) {// конструктор окна с меню, параметр – заголовок окна
        super(name); // передаем заголовок окна в конструктор класса-родителя – в JFrame
        JMenuBar myMenuBar = new JMenuBar();// создаем строку меню
        JMenu menu1 = new JMenu("Пункт1");// создаем первый пункт меню
        JMenu first = new JMenu("Пункт1_1"); // создаем подпункт меню
        menu1.add(first);// добавляем подпункт в первый пункт меню
        JMenuItem[] first_1 = new JMenuItem[3];// создаем массив из трех подпунктов меню
        for (int i = 0; i < 3; i++) { // в цикле создается каждый подпункт, добавляется в нужное место меню
            first_1[i] = new JMenuItem("Пункт1_1_" + (i + 1));
            first.add(first_1[i]);
            first_1[i].addActionListener(this);// и к нему подключаем слушатель, описанный в конце
        } // класса
        JMenu second = new JMenu("Пункт1_2"); // создаем подпункт меню
        menu1.add(second); // добавляем его в меню
        JMenuItem[] second_1 = new JMenuItem[3]; // создаем массив из трех подпунктов меню
        for (int i = 0; i < 3; i++) { // и т.д.
            second_1[i] = new JMenuItem("Пункт1_2_" + (i + 1));
            second.add(second_1[i]);
            second_1[i].addActionListener(this);
        }
        JMenu menu2 = new JMenu("Пункт2"); // создаем второй пункт меню и далее аналогично первому
        JMenu first2 = new JMenu("Пункт2_1");
        menu2.add(first2);
        JMenuItem[] first2_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++) {
            first2_1[i] = new JMenuItem("Пункт2_1_" + (i + 1));
            first2.add(first2_1[i]);
            first2_1[i].addActionListener(this);
        }
        JMenu second2 = new JMenu("Пункт2_2");
        menu2.add(second2);
        JMenuItem[] second2_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++) {
            second2_1[i] = new JMenuItem("Пункт2_2_" + (i + 1));
            second2.add(second2_1[i]);

            second2_1[i].addActionListener(this);
        }
        myMenuBar.add(menu1); // в строку меню добавляем главные пункты меню
        myMenuBar.add(menu2);
        myText = new JTextField();
        setJMenuBar(myMenuBar); // устанавливаем для окна созданное меню
        add(myText, BorderLayout.SOUTH);
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {// описываем метод слушателя, отвечающий за действия
        myText.setText(e.getActionCommand()); // при выборе пункта меню
    } // e.getActionCommand() возвращает название пункта меню, который был выбран
}

package Lab_6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGraph extends JFrame implements ActionListener {
    private JMyPanel myPanel = new JMyPanel();// объявляем и создаем нашу панель для рисования

    public static void main(String[] args) {
        new MyGraph("Окно с графикой");// создаем окно
    }

    public MyGraph(String s) { // конструктор с параметром для заголовка окна
        super(s);// вызываем конструктор суперкласса и передаем ему параметр
        // Box myBox = new Box(BoxLayout.X_AXIS);// создаем компоновку в виде
        // горизонтального ящика
        // JButton[] figs = new JButton[5]; // массив кнопок
        // for (int i = 0; i < 5; i++) {
        // // каждая кнопка создается с параметром надписи на ней, надпись берется из
        // // перечисления,
        // // объявленного в классе JMyPanel, values()[i].toString() переводит в текст
        // // название i-го
        // // параметра из Figure
        // figs[i] = new JButton(JMyPanel.Figure.values()[i].toString());
        // figs[i].addActionListener(this); // добавляем слушатель, который реализуется
        // в конце

        // // описания класса
        // myBox.add(figs[i]);// добавляем кнопку в компоновку
        // if (i != 4) { // для всех кнопок кроме последней вставляем пружину после
        // кнопки
        // myBox.add(Box.createHorizontalGlue());
        // }
        // }
        // myBox.setAlignmentX(CENTER_ALIGNMENT);// устанавливаем для компоновки
        // выравнивание по центру

        // хотя в нашем случае это не важно, т.к. мы используем пружины
        JMenuBar myMenuBar = new JMenuBar();

        JMenu draw = new JMenu("Draw".toUpperCase());
        JMenu figures = new JMenu("Figures".toUpperCase());
        JMenuItem secondName = new JMenuItem(JMyPanel.Figure.SECOND_NAME.toString());
        JMenuItem clear = new JMenuItem(JMyPanel.Figure.CLEAR.toString());

        draw.add(figures);
        draw.add(secondName);

        secondName.addActionListener(this);
        clear.addActionListener(this);

        JMenuItem[] figuresItems = new JMenuItem[JMyPanel.Figure.values().length - 1];
        for (int i = 0; i < figuresItems.length; i++) {
            figuresItems[i] = new JMenuItem(JMyPanel.Figure.values()[i].toString());
            figuresItems[i].addActionListener(this);
            figures.add(figuresItems[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add(myBox, BorderLayout.NORTH);
        add(myPanel, BorderLayout.CENTER);
        myMenuBar.add(draw);
        myMenuBar.add(clear);
        setJMenuBar(myMenuBar);
        Dimension size = getSize();// записываем в переменную size текущий размер окна
        size.setSize(size.width + 800, size.height + 300);// устанавливаем новый размер окна, увеличивая

        // текущий по высоте на 200

        setMinimumSize(size);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) { // при нажатии на одну из кнопок
        myPanel.ris(e.getActionCommand());// вызываем метод ris нашей панели (см. JMyPanel.java)
    } // и передаем в качестве параметра название нажатой кнопки
      // (e.getActionCommand())
}

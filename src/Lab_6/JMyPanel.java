package Lab_6;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class JMyPanel extends JPanel { // наш класс является наследником класса JPanel
    // создаем перечисление используемых параметров
    public static enum Figure {
        LINE, OVAL, RECT, ROUNDRECT, SECOND_NAME, CLEAR
    };

    private Figure vibor = Figure.CLEAR; // объявляем переменную типа созданного перечисления

    // и присваиваем ей значение CLEAR

    public JMyPanel() {
    } // конструктор нашего класса

    public void ris(String s) {// метод, вызов которого приводит к перерисовке панели
        // параметр s принимает значение во время вызова данного метода (см.
        // MyGraph.java)
        vibor = Figure.valueOf(s); // устанавливаем, что нужно нарисовать
        repaint(); // перерисовываем нашу панель, т.е. вызываем метод paintComponent
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        BasicStroke pen;// создаем перо, параметры которого будут определять стиль линий
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch (vibor) {
            case SECOND_NAME:
                // ПО
                pen = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g.setStroke(pen);
                g.setColor(new Color(0xbb8ac1));

                g.drawLine(20, 20, 20, 100);
                g.drawLine(20, 20, 70, 20);
                g.drawLine(70, 20, 70, 100);

                g.drawOval(100, 20, 60, 80);

                // ХИ
                pen = new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5, new float[] { 7, 7 }, 0);
                g.setStroke(pen);
                g.setColor(new Color(0x7aa2f7));

                g.drawLine(190, 20, 230, 100);
                g.drawLine(230, 20, 190, 100);

                g.drawLine(260, 20, 260, 100);
                g.drawLine(260, 100, 300, 20);
                g.drawLine(300, 20, 300, 100);

                // ЛЬ
                pen = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g.setStroke(pen);
                g.setColor(new Color(0xce3939));

                g.drawLine(330, 100, 370, 20);
                g.drawLine(370, 20, 410, 100);

                g.drawLine(440, 20, 440, 80);
                g.drawRoundRect(440, 57, 45, 45, 40, 20);

                // КО
                pen = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g.setStroke(pen);
                g.setPaint(new GradientPaint(10, 10, new Color(0x2f9d8b), 20, 20, new Color(0x9798f7), true));

                g.drawLine(512, 20, 512, 100);
                g.drawLine(512, 55, 525, 55);
                g.drawLine(525, 55, 560, 20);
                g.drawLine(525, 55, 560, 100);

                g.drawOval(590, 20, 60, 80);

                break;

            case LINE:
                // определяем перо толщиной 20 точек, с закругленными концами линий и
                // закругленными стыками линий

                pen = new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g.setStroke(pen);// делаем текущим пером созданное нами
                g.setColor(Color.blue);// задаем цвет пера
                g.drawLine(20, 20, 100, 100);
                break;
            case OVAL:

                // задаем массив, определяющий вид линии
                // элементы массива с четными индексами задают длину штриха в пикселах, элементы
                // с нечетными
                // индексами — длину промежутка; массив перебирается циклически;

                float[] dash = { 10, 30 };

                // определяем перо толщиной 10 точек, с квадратными концами линий, закругленными
                // стыками линий,
                // расстоянием в 10 точек, с которого начинает действовать закругление, массив,
                // определяющий вид
                // линии, и с какого элемента массива начинать узор

                pen = new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 10, dash, 0);
                g.setStroke(pen);
                g.setColor(Color.red);

                // устанавливаем стиль заливки, в качестве параметра задаем градиент от красного
                // к зеленому,
                // 30, 30 – начальная точка первого цвета, 50, 50 – начальная точка второго
                // цвета, true –
                // цикличность градиента

                g.setPaint(new GradientPaint(30, 30, Color.red, 50, 50, Color.green, true));

                // g.fill – создание объекта с заливкой, в качестве параметра задается объект из
                // пакета Graphics2D,
                // в нашем случае – эллипс

                g.fill(new Ellipse2D.Double(20, 20, 100, 100));
                break;
            case RECT:
                float[] dash2 = { 20, 20 };
                pen = new BasicStroke(5, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1, dash2, 0);
                g.setStroke(pen);
                g.setColor(Color.magenta);
                g.drawRect(20, 20, 100, 100);
                break;
            case ROUNDRECT:
                float[] dash3 = { 20, 20, 2, 20, 2, 20 };
                pen = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 1, dash3, 0);
                g.setStroke(pen);
                g.setColor(Color.yellow);
                g.drawRoundRect(20, 20, 100, 100, 60, 60);
                break;
            case CLEAR:
                g.clearRect(0, 0, getSize().width, getSize().height);
                break;
        }
    }
}

package Lab_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class App {
    public static void main(String s[]) {
        final JLabel northLabel = new JLabel("Label 1", SwingConstants.CENTER);
        final JLabel southLabel = new JLabel("Label 2", SwingConstants.CENTER);

        final JTextArea textArea = new JTextArea("TextArea");
        textArea.setColumns(20);
        textArea.setLineWrap(true);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                northLabel.setText("Text area is in focus");
            }

            @Override
            public void focusLost(FocusEvent e) {
                northLabel.setText("Text area just lost focus");
            }
        });

        textArea.addMouseWheelListener(new MouseWheelListener() {
            private int rgbValue = 0;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                final int scrollAmountScale = 5;
                final int scrollAmount = e.getScrollAmount() * scrollAmountScale;

                if (rgbValue + scrollAmount > 255) {
                    rgbValue = 0;
                    return;
                }

                rgbValue += scrollAmount;

                textArea.setBackground(new Color(255 - rgbValue, 255 - rgbValue, 255 - rgbValue));
                textArea.setForeground(new Color(rgbValue, rgbValue, rgbValue));
            }

        });

        final GridLayout gridLayout = new GridLayout(5, 3, 1, 1);
        final JPanel ButtonsGrid = new JPanel(gridLayout);

        final ArrayList<JLabel> gridLabelList = new ArrayList<JLabel>();
        final ArrayList<JButton> gridButtonsList = new ArrayList<JButton>();

        final int gridLength = gridLayout.getColumns() * gridLayout.getRows();

        for (int i = 0; i < gridLength; i++) {
            if (i % 2 == 0) {
                final JButton newButton = new JButton(String.format("Button %s", i));
                gridButtonsList.add(newButton);

                newButton.addActionListener(new ActionListener() {
                    private int counter = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newButton.setText(Integer.toString(counter));
                        counter++;
                    }
                });

                ButtonsGrid.add(newButton);
                continue;
            }

            final JLabel newLabel = new JLabel();
            newLabel.setBorder(BorderFactory.createLineBorder(new Color(0x7a8a99), 1, false));
            gridLabelList.add(newLabel);
            ButtonsGrid.add(newLabel);
        }

        final JFrame frame = new JFrame("Laboratory work - 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(700, 600);
        frame.setVisible(true);

        frame.add(northLabel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.WEST);
        frame.add(ButtonsGrid, BorderLayout.CENTER);
        frame.add(southLabel, BorderLayout.SOUTH);
    }
}
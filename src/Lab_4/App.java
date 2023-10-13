package Lab_4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    public static void main(String s[]) {
        final JLabel northLabel = new JLabel("Label 1", SwingConstants.CENTER);
        final JLabel southLabel = new JLabel("Label 2", SwingConstants.CENTER);

        final JTextArea textArea = new JTextArea("TextArea");
        textArea.setColumns(20);
        textArea.setLineWrap(true);

        final GridLayout gridLayout = new GridLayout(5, 3, 1, 1);
        final JPanel ButtonsGrid = new JPanel(gridLayout);

        final ArrayList<JLabel> gridLabelList = new ArrayList<JLabel>();
        final ArrayList<JButton> gridButtonsList = new ArrayList<JButton>();

        final int gridLength = gridLayout.getColumns() * gridLayout.getRows();

        for (int i = 0; i < gridLength; i++) {
            if (i % 2 == 0) {
                final JButton newButton = new JButton(String.format("Button %s", i));
                gridButtonsList.add(newButton);
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
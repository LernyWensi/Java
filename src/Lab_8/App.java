package Lab_8;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class App {
    private static final Database db = new Database(System.getProperty("user.dir"));

    private static final JFrame frame = new JFrame();
    private static String currentTable = "Newspapers";
    private static JTable table = db.getTable(currentTable);
    private static JScrollPane tableScrollPane = new JScrollPane(table);

    private static void setListenerForTableRow() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    if (currentTable == "Newspapers" || currentTable == "TVChannels")
                        return;

                    System.out.println(currentTable);
                    final String rowName = table.getValueAt(table.getSelectedRow(), 0).toString();
                    JFrame externalTableWindow = new JFrame(rowName);
                    externalTableWindow.setLayout(new BorderLayout());
                    externalTableWindow.setSize(1000, 300);
                    externalTableWindow.setMinimumSize(externalTableWindow.getSize());

                    final JTable table = db.getTable(rowName);
                    final JScrollPane tableScrollPane = new JScrollPane(table);

                    externalTableWindow.setVisible(true);
                    externalTableWindow.add(tableScrollPane, BorderLayout.CENTER);
                    externalTableWindow.pack();
                }
            }
        });
    }

    private static Component setMenu(String[] buttonNames, HashMap<String, String> buttonForTableMap) {
        Box menu = new Box(BoxLayout.X_AXIS);

        for (int i = 0; i < buttonNames.length; i++) {
            JButton buttonTemplate = new JButton(buttonNames[i]);

            buttonTemplate.addActionListener(e -> {
                currentTable = buttonForTableMap.get(e.getActionCommand());
                table = db.getTable(currentTable);

                setListenerForTableRow();

                frame.remove(tableScrollPane);
                tableScrollPane = new JScrollPane(table);
                frame.add(tableScrollPane, BorderLayout.CENTER);

                frame.pack();
            });

            menu.add(buttonTemplate);
        }

        return menu;
    }

    public static void main(String[] args) {
        String buttonNames[] = { "Show Holdings", "Show Newspapers", "Show TVChannels" };
        String tableNames[] = { "Holdings", "Newspapers", "TVChannels" };

        HashMap<String, String> buttonForTableMap = new HashMap<>();
        for (int i = 0; i < buttonNames.length; i++) {
            buttonForTableMap.put(buttonNames[i], tableNames[i]);
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit");
                db.closeConnection();
                e.getWindow().dispose();
            }
        });

        setListenerForTableRow();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(setMenu(buttonNames, buttonForTableMap), BorderLayout.NORTH);
        frame.setSize(1000, 500);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);

        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.pack();
    }
}
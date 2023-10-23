package Lab_9;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class App {
    static FileDialog folderDialog;
    static String curTableName = "";

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

    private static Component setMenu(String[] buttonNames, HashMap<String, String> buttonForTableMap,
            HashMap<String, String> mapForTablesRus) {
        Box menu = new Box(BoxLayout.X_AXIS);

        for (int i = 0; i < buttonNames.length; i++) {
            JButton buttonTemplate = new JButton(buttonNames[i]);

            buttonTemplate.addActionListener(e -> {
                currentTable = buttonForTableMap.get(e.getActionCommand());
                table = db.getTable(currentTable);
                curTableName = mapForTablesRus.get(e.getActionCommand());
                setListenerForTableRow();

                frame.remove(tableScrollPane);
                tableScrollPane = new JScrollPane(table);
                frame.add(tableScrollPane, BorderLayout.CENTER);

                frame.pack();

                BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();

                Box southBox = (Box) layout.getLayoutComponent(BorderLayout.SOUTH);

                for (int j = 0; j < southBox.getComponentCount(); j++) {
                    southBox.getComponent(j).setEnabled(true);
                }

            });

            menu.add(buttonTemplate);
        }

        return menu;
    }

    private static Component setBottom() {
        Box bottom = new Box(BoxLayout.X_AXIS);
        JButton toWord = new JButton("toWord");
        toWord.addActionListener(e -> {
            String[] columnNames = getTableHeader();
            String[][] data = getTableData();
            File file = getFile("Save to Word file", "docx");

            if (!file.getName().contains("nullnull"))
                ToOffice.toWordDocx(columnNames, data, file, curTableName);
        });

        toWord.setEnabled(false);
        bottom.add(toWord);
        bottom.add(Box.createHorizontalGlue());
        JButton toExcel = new JButton("toExcel");
        toExcel.addActionListener(e -> {
            String[] columnNames = getTableHeader();
            String[][] data = getTableData();
            File file = getFile("Save to Excel file", "xls");
            if (!file.getName().contains("nullnull"))
                ToOffice.toExcel(columnNames, data, file,
                        curTableName);
        });
        toExcel.setEnabled(false);
        bottom.add(toExcel);
        return bottom;
    }

    private static File getFile(String caption, String ext) {
        System.out.println(caption);
        folderDialog.setTitle(caption);
        folderDialog.setFile("*." + ext);

        folderDialog.setVisible(true);
        String fileName = folderDialog.getDirectory() + folderDialog.getFile();

        if (!fileName.contains("." + ext))
            fileName = fileName.concat("." + ext);
        File file = new File(fileName);
        System.out.println("file = " + file);
        return file;
    }

    private static String[] getTableHeader() {
        JViewport viewPort = (JViewport) tableScrollPane.getComponent(0);
        JTable tempTable = (JTable) viewPort.getComponent(0);
        TableModel tableModel = tempTable.getModel();
        int colCount = tableModel.getColumnCount();
        String[] columnNames = new String[colCount];
        for (int i = 0; i < colCount; i++) {
            columnNames[i] = tableModel.getColumnName(i);
        }
        System.out.println(" " + Arrays.asList(columnNames));
        return columnNames;
    }

    private static String[][] getTableData() {
        JViewport viewPort = (JViewport) tableScrollPane.getComponent(0);
        JTable tempTable = (JTable) viewPort.getComponent(0);
        TableModel tableModel = tempTable.getModel();
        int colCount = tableModel.getColumnCount();
        int rowCount = tableModel.getRowCount();
        String[][] data = new String[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = (String) tableModel.getValueAt(i, j);
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.out.println(Arrays.asList(data[i]));
        }
        return data;
    }

    public static void main(String[] args) {
        String buttonNames[] = { "Show Holdings", "Show Newspapers", "Show TVChannels" };
        String tableNames[] = { "Holdings", "Newspapers", "TVChannels" };
        String tableNamesRus[] = { "Холдинги", "Газеты", "ТВ-Каналы" };

        HashMap<String, String> mapForTablesRus = new HashMap<>();
        HashMap<String, String> buttonForTableMap = new HashMap<>();
        for (int i = 0; i < buttonNames.length; i++) {
            buttonForTableMap.put(buttonNames[i], tableNames[i]);
            mapForTablesRus.put(buttonNames[i], tableNamesRus[i]);
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

        frame.add(setMenu(buttonNames, buttonForTableMap, mapForTablesRus), BorderLayout.NORTH);
        frame.add(setBottom(), BorderLayout.SOUTH);
        frame.setSize(1000, 500);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);

        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.pack();

        folderDialog = new FileDialog(frame, "");
        folderDialog.setMode(FileDialog.SAVE);
    }
}
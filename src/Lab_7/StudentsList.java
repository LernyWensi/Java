package Lab_7;

import java.awt.*;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentsList {
    private static JFrame frame;

    private static Path pathToFile = null;

    private static final HashMap<String, String> studentsMap = new HashMap<String, String>();
    private static final DefaultListModel<String> listModel = new DefaultListModel<String>();

    private static final JList<String> studentsList = new JList<String>();
    private static final String studentInfoFiller = "Nothing to show";
    private static final JLabel studentInfo = new JLabel(studentInfoFiller, SwingConstants.CENTER);

    private static void initUI() {
        final Box titleBox = Box.createVerticalBox();

        final JTextField textField = new JTextField();
        final Box buttonBar = Box.createHorizontalBox();

        final JButton loadFileButton = new JButton("Load List");
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPathToFile();
                fillHashMapFromFile();
                refreshListModel();
            }
        });

        final JButton addButton = new JButton("Add student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (pathToFile == null)
                        return;

                    if (textField.getText().isEmpty())
                        return;

                    Files.writeString(pathToFile, textField.getText() + "\n", StandardCharsets.UTF_8,
                            StandardOpenOption.APPEND);

                    fillHashMapFromFile();
                    refreshListModel();
                    textField.setText("");
                } catch (IOException error) {
                    return;
                }
            }

        });

        final JButton removeButton = new JButton("Removes student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (pathToFile == null)
                        return;

                    if (studentsList.getSelectedValue() == null)
                        return;

                    final String selectedStudent = studentsList.getSelectedValue().toString();
                    studentsMap.remove(selectedStudent);

                    refreshListModel();
                    studentInfo.setText(studentInfoFiller);

                    List<String> lines = new ArrayList<String>();
                    studentsMap.forEach((key, value) -> {
                        lines.add(key + " " + value);
                    });

                    Files.write(pathToFile, lines, StandardCharsets.UTF_8);
                } catch (IOException error) {
                    return;
                }

            }
        });

        final JButton clearButton = new JButton("Clear list");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pathToFile = null;
                listModel.removeAllElements();
                studentInfo.setText(studentInfoFiller);
            }
        });

        buttonBar.add(loadFileButton);
        buttonBar.add(addButton);
        buttonBar.add(removeButton);
        buttonBar.add(clearButton);

        titleBox.add(textField);
        titleBox.add(buttonBar);

        final JSplitPane listViewBox = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        final JScrollPane scrollPane = new JScrollPane();

        studentsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (studentsList.getSelectedValue() == null)
                    return;

                final String[] studentValue = studentsMap
                        .get(studentsList.getSelectedValue().toString())
                        .trim()
                        .split(" ");

                studentInfo.setText(
                        String.format("<html>Phone: %s<br>Address: %s</html>", studentValue[0], studentValue[1]));
            }
        });

        scrollPane.setViewportView(studentsList);
        studentsList.setModel(listModel);

        listViewBox.setDividerLocation(0.5);
        listViewBox.setLeftComponent(scrollPane);
        listViewBox.setRightComponent(studentInfo);

        frame = new JFrame("Students list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setResizable(false);
        frame.setVisible(true);

        frame.add(titleBox, BorderLayout.NORTH);
        frame.add(listViewBox, BorderLayout.CENTER);

        frame.pack();
    }

    private static void refreshListModel() {
        listModel.removeAllElements();
        studentsMap.forEach((key, value) -> {
            listModel.addElement(key);
        });
    }

    private static void setPathToFile() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Open list with students", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
            pathToFile = Paths.get(fileDialog.getDirectory() + fileDialog.getFile());
        }
    }

    private static void fillHashMapFromFile() {
        if (pathToFile == null)
            return;

        try {
            Scanner scanner = new Scanner(pathToFile);
            scanner.useDelimiter(" ");

            studentsMap.clear();
            while (scanner.hasNext()) {
                final String key = scanner.next();
                final String value = scanner.nextLine();
                studentsMap.put(key, value);
            }

            scanner.close();
        } catch (IOException error) {
            return;
        }
    }

    public static void main(String[] args) {
        initUI();
    }
}

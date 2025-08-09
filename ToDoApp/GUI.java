package ToDoApp;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JList<String> todoList;
    private DefaultListModel<String> listModel;
    private JTextField inputField;
    private JButton addButton;
    private JButton deleteButton;

    public GUI () {
        setTitle("My ToDo-App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        initComponents();


        setupListeners();

        setVisible(true);
    }

    private void initComponents() {
        todoList = new JList<>();
        listModel = new DefaultListModel<>();
        todoList.setModel(listModel);
        add(new JScrollPane(todoList), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 30));
        add(inputField, BorderLayout.NORTH);

        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        buttonpanel.add(addButton);
        buttonpanel.add(deleteButton);

        add(buttonpanel, BorderLayout.SOUTH);


    }

    private void setupListeners() {
        addButton.addActionListener(e -> {
        String text = inputField.getText().trim();
        if(!text.isEmpty()) {
            listModel.addElement(text);
            inputField.setText("");
        }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = todoList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            } else {
             JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Warning",
             JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }

}

package ru.shift.view;

import javax.swing.*;
import java.awt.*;

public class NameWindow extends JDialog {
    private final NameWindowListener nameWindowListener;

    public NameWindow(JFrame frame, NameWindowListener nameWindowListener) {
        super(frame, "Enter your name", true);
        this.nameWindowListener = nameWindowListener;

        JTextField nameField = new JTextField();

        JPanel jPanel = new JPanel(new GridLayout(3, 0, 5, 5));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jPanel.add(new JLabel("Enter your name"));
        jPanel.add(nameField);
        jPanel.add(createOkButton(nameField));

        add(jPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(200, 150));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton createOkButton(JTextField nameField) {
        JButton button = new JButton("Send");

        button.addActionListener(e -> {
            dispose();
            if (nameWindowListener != null) {
                nameWindowListener.sendName(nameField.getText());
            }
        });

        return button;
    }
}

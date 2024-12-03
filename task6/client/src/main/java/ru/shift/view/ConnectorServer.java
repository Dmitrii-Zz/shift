package ru.shift.view;

import javax.swing.*;
import java.awt.*;

public class ConnectorServer extends JDialog {
    private final ConnectorServerListener connectorServerListener;

    public ConnectorServer(JFrame frame, ConnectorServerListener connectorServerListener) {
        super(frame, "Connect to server", true);
        this.connectorServerListener = connectorServerListener;

        JTextField hostField = new JTextField();
        JTextField portField = new JTextField();

        JPanel jPanel = new JPanel(new GridLayout(5, 0, 5, 5));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jPanel.add(new JLabel("Enter host"));
        jPanel.add(hostField);
        jPanel.add(new JLabel("Enter port"));
        jPanel.add(portField);
        jPanel.add(createOkButton(hostField, portField));

        add(jPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 230));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createOkButton(JTextField hostField, JTextField portField) {
        JButton button = new JButton("Connect");

        button.addActionListener(e -> {
            dispose();
            if (connectorServerListener != null) {
                connectorServerListener.connect(hostField.getText(), portField.getText());
            }
        });

        return button;
    }
}

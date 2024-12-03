package ru.shift.view;

import javax.swing.*;
import java.awt.*;

public class WrongNameWindow extends JDialog {
    public WrongNameWindow(JFrame frame) {
        super(frame, "Attention", true);

        JPanel jPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jPanel.add(new JLabel("Wrong name"));

        add(jPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(200, 200));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
}

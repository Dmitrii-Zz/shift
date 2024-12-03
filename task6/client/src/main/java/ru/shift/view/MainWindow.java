package ru.shift.view;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame {
    private final Config config = ConfigFactory.load();
    private final Container contentPane;
    private final GridBagLayout mainLayout;

    private TextFieldListener textFieldListener;
    private LabelInformationListener labelInformationListener;

    public MainWindow() {
        super("Shift-Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);

        contentPane = getContentPane();
        mainLayout = new GridBagLayout();
        contentPane.setLayout(mainLayout);

        contentPane.setBackground(new Color(114, 72, 161));
    }

    public void setTextFieldListener(TextFieldListener textFieldListener) {
        this.textFieldListener = textFieldListener;
    }

    public void setLabelInformationListener(LabelInformationListener labelInformationListener) {
        this.labelInformationListener = labelInformationListener;
    }

    public void create() {
        addButtonsPanel(createButton());
        addTextField(createTextField());
        addTextAreaMessage(createTextAreaMessage());
        addLabelListClient();
        addTextClientNames(createFieldClientNames());
        addInformingLabel();
        setVisible(true);
    }

    private JPanel createButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(114, 72, 161));
        buttonPanel.setPreferredSize(new Dimension(5, 5));

        JButton sendButton = new JButton("Отправить");
        sendButton.addActionListener(e -> {
            postMessage();
        });

        buttonPanel.add(sendButton);
        return buttonPanel;
    }

    private void postMessage() {
        Component[] components = contentPane.getComponents();
        JTextField textField = (JTextField) components[1];
        String message = textField.getText();
        textField.setText("");
        textFieldListener.sendMessage(message);
    }

    private void addButtonsPanel(JPanel buttonsPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(3, 3, 3, 3);
        mainLayout.setConstraints(buttonsPanel, gbc);
        contentPane.add(buttonsPanel);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(5, 3));

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    postMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        return textField;
    }

    private void addTextField(JTextField jTextField) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.07;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(3, 3, 3, 3);
        mainLayout.setConstraints(jTextField, gbc);
        contentPane.add(jTextField);
    }

    private JScrollPane createTextAreaMessage() {
        JTextArea textArea = new JTextArea(3, 2);
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    private void addTextAreaMessage(JScrollPane textArea) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(3, 3, 3, 3);
        mainLayout.setConstraints(textArea, gbc);
        contentPane.add(textArea);
    }

    private void addLabelListClient() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(255, 255, 255));
        jPanel.add(new JLabel("Участики"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.02;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(3, 3, 3, 3);
        mainLayout.setConstraints(jPanel, gbc);
        contentPane.add(jPanel);

    }

    private void addInformingLabel() {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(300, 27));
        jPanel.setBackground(new Color(255, 255, 255));
        jPanel.add(new JLabel("Сервер не подключен. Нажмите здесь, чтобы создать новое подключение"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.02;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(3, 3, 3, 3);

        jPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labelInformationListener.clickLabel();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        mainLayout.setConstraints(jPanel, gbc);
        contentPane.add(jPanel);
    }

    private JScrollPane createFieldClientNames() {
        JTextArea textArea = new JTextArea(10, 2);
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    private void addTextClientNames(JScrollPane textArea) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.15;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(3, 3, 3, 3);
        mainLayout.setConstraints(textArea, gbc);
        contentPane.add(textArea);
    }

    public void setNewMessage(String message) {
        setText(message, config.getInt("field.message"));
    }

    public void setClientList(String clientList) {
        setText(clientList, config.getInt("field.names"));
    }

    private void setText(String message, int component) {
        Component[] components = contentPane.getComponents();
        JScrollPane scrollPane = (JScrollPane) components[component];
        Component view = scrollPane.getViewport().getView();
        JTextArea text = (JTextArea) view;

        if (component == config.getInt("field.names")) {
            text.setText("");
        }

        text.append(message + "\n");
        text.setCaretPosition(text.getDocument().getLength());
    }

    public void setInformationServer(String text) {
        Component[] components = contentPane.getComponents();
        JPanel jPanel = (JPanel) components[config.getInt("field.server")];
        JLabel jLabel = (JLabel) jPanel.getComponent(0);
        jLabel.setText(text);
    }
}
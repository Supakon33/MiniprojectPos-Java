package coffeeshopmanager;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    // ตัวอย่าง credentials
    private final String USERNAME = "cai";
    private final String PASSWORD = "67";

    public LoginGUI() {
        super("Login - Coffee Shop Manager");

        tfUsername = new JTextField(15);
        pfPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 240, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(tfUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(pfPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        btnLogin.setBackground(new Color(33, 150, 243));
        btnLogin.setForeground(Color.WHITE);
        panel.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> checkLogin());

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void checkLogin() {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new MainGUI();  
            dispose();     
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            tfUsername.setText("");
            pfPassword.setText("");
        }
    }
}

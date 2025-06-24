/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login Form");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        lblUsername.setBounds(50, 50, 100, 30);
        lblPassword.setBounds(50, 100, 100, 30);
        txtUsername.setBounds(150, 50, 180, 30);
        txtPassword.setBounds(150, 100, 180, 30);
        btnLogin.setBounds(150, 160, 100, 30);

        add(lblUsername);
        add(lblPassword);
        add(txtUsername);
        add(txtPassword);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                try {
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scientificdb", "root", "")) {
                        String sql = "SELECT * FROM users WHERE username=? AND password=?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, username);
                        ps.setString(2, password);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            String role = rs.getString("role");
                            JOptionPane.showMessageDialog(LoginForm.this, "Login successful as " + role);
                            switch (role) {
                                case "Student" -> new StudentDashboard().setVisible(true);
                                case "Admin" -> {
                                }
                                case "Lecturer" -> {
                                }
                                case "Researcher" -> {
                                }
                                case "Teacher" -> {
                                }
                            }
                            // new AdminDashboard().setVisible(true);
                            // new LecturerDashboard().setVisible(true);
                            // new ResearcherDashboard().setVisible(true);
                            // new TeacherDashboard().setVisible(true);
                            LoginForm.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(LoginForm.this, "Invalid credentials");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Database error");
                }
            }
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }
}


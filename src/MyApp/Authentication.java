package MyApp;

import MyLib.Admin;
import MyLib.Agent;
import MyLib.Customer;
import MyLib.PropertyManager;
import MyLib.Session;
import MyLib.User;
import MyLib.UserManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Authentication extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Authentication.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private ButtonGroup roleGroup;
    
    public Authentication(UserManager userManager, PropertyManager propertyManager) {
        this.userManager = userManager;
        this.propertyManager = propertyManager;
        initComponents();
        
        // INJECT DYNAMIC LOGO TO MAIN PANEL
        setupMainPanelBranding();
        setupLoginPanelBranding();
        setupRegisterPanelBranding();
        
        setLocationRelativeTo(null);
        this.roleGroup = new ButtonGroup();
        roleGroup.add(rbCustomer);
        roleGroup.add(rbAgent);
        applyTheme();
    }

    private void setupMainPanelBranding() {
    MainPanel.removeAll();
    // GridBagLayout is best for perfectly centering a single component
    MainPanel.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

    // 1. Create a "Card" container
    JPanel card = new JPanel();
    card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);
    // Subtle line and comfortable padding
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(40, 50, 40, 50)
    ));

    // 2. Add the Integrated Logo (Image Version)
    javax.swing.JLabel logoLabel = new javax.swing.JLabel();
    try {
        java.net.URL imgURL = getClass().getResource("/logo/logo.png"); 
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            // Scale to 200px width
            Image scaledImg = icon.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImg));
        } else {
            logoLabel.setText("FIND A LOT"); // Text fallback
            logoLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 36));
            logoLabel.setForeground(ThemeEngine.TEXT_PRIMARY);
        }
    } catch (Exception e) { logoLabel.setText("FIND A LOT"); }
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // 3. Add Buttons with spacing
    card.add(logoLabel);
    // Uses Box Strut for clean, intentional spacing, not arbitrary pixels
    card.add(javax.swing.Box.createVerticalStrut(35)); 

    loginPanelButton.setMaximumSize(new java.awt.Dimension(250, 45));
    loginPanelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(loginPanelButton);
    
    card.add(javax.swing.Box.createVerticalStrut(15));

    registerPanelButton.setMaximumSize(new java.awt.Dimension(250, 45));
    registerPanelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(registerPanelButton);

    // Add the card to MainPanel (which is using GridBagLayout)
    MainPanel.add(card, gbc);
    MainPanel.revalidate();
    MainPanel.repaint();
}
    // --- NEW: Applies the ThemeEngine to the Login and Register screens ---
    private void applyTheme() {
        this.getContentPane().setBackground(ThemeEngine.BG_MAIN);
        Parent.setBackground(ThemeEngine.BG_MAIN);
        MainPanel.setBackground(ThemeEngine.BG_MAIN);
        LoginPanel.setBackground(ThemeEngine.BG_MAIN);
        RegisterPanel.setBackground(ThemeEngine.BG_MAIN);

        // Center the cards using a matte border to simulate "Elevation"
        javax.swing.border.Border line = BorderFactory.createLineBorder(new Color(229, 231, 235), 1);
        javax.swing.border.Border padding = BorderFactory.createEmptyBorder(30, 40, 30, 40);
        
        // Apply to the internal form containers
        // Note: You'll need to wrap your forms in a sub-panel in the designer or use the code below
        
        authenticationLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 32));
        authenticationLabel.setForeground(ThemeEngine.TEXT_PRIMARY);

        JTextField[] fields = {usernameTextField, usernameTextField1, passwordField, passwordField1};
        for (JTextField field : fields) {
            ThemeEngine.styleTextField(field); // Use the central style
        }

        ThemeEngine.stylePrimaryButton(loginPanelButton);
        ThemeEngine.stylePrimaryButton(registerPanelButton);
        ThemeEngine.stylePrimaryButton(loginButton);
        ThemeEngine.stylePrimaryButton(registerButton);
        ThemeEngine.styleSecondaryButton(homeButton);
        ThemeEngine.styleSecondaryButton(homeButton1);

        rbCustomer.setOpaque(false);
        rbAgent.setOpaque(false);
        rbCustomer.setForeground(ThemeEngine.TEXT_PRIMARY);
        rbAgent.setForeground(ThemeEngine.TEXT_PRIMARY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Parent = new javax.swing.JPanel();
        MainPanel = new javax.swing.JPanel();
        registerPanelButton = new javax.swing.JButton();
        loginPanelButton = new javax.swing.JButton();
        authenticationLabel = new javax.swing.JLabel();
        LoginPanel = new javax.swing.JPanel();
        homeButton1 = new javax.swing.JButton();
        passwordLabel1 = new javax.swing.JLabel();
        usernameLabel1 = new javax.swing.JLabel();
        passwordField1 = new javax.swing.JPasswordField();
        usernameTextField1 = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        RegisterPanel = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        rbCustomer = new javax.swing.JRadioButton();
        rbAgent = new javax.swing.JRadioButton();
        registerButton = new javax.swing.JButton();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Find-A-Lot - Authentication");
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setResizable(true);

        Parent.setBackground(new java.awt.Color(240, 248, 255));
        Parent.setLayout(new java.awt.CardLayout());

        registerPanelButton.setText("Register");
        registerPanelButton.setBackground(new java.awt.Color(70, 130, 180));
        registerPanelButton.setForeground(java.awt.Color.WHITE);
        registerPanelButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        registerPanelButton.setPreferredSize(new java.awt.Dimension(200, 50));
        registerPanelButton.addActionListener(this::registerPanelButtonActionPerformed);

        loginPanelButton.setText("Login");
        loginPanelButton.setBackground(new java.awt.Color(70, 130, 180));
        loginPanelButton.setForeground(java.awt.Color.WHITE);
        loginPanelButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        loginPanelButton.setPreferredSize(new java.awt.Dimension(200, 50));
        loginPanelButton.addActionListener(this::loginPanelButtonActionPerformed);

        authenticationLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28)); // NOI18N
        authenticationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        authenticationLabel.setText("Welcome to Find-A-Lot");
        authenticationLabel.setToolTipText("");

        MainPanel.setLayout(new java.awt.BorderLayout());

        // Center panel for content
        javax.swing.JPanel centerPanel = new javax.swing.JPanel();
        centerPanel.setLayout(new javax.swing.BoxLayout(centerPanel, javax.swing.BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Title
        javax.swing.JPanel titlePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        titlePanel.add(authenticationLabel);
        centerPanel.add(titlePanel);

        // Spacer
        centerPanel.add(javax.swing.Box.createVerticalStrut(50));

        // Login button
        javax.swing.JPanel loginBtnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        loginBtnPanel.setOpaque(false);
        loginBtnPanel.add(loginPanelButton);
        centerPanel.add(loginBtnPanel);

        // Spacer
        centerPanel.add(javax.swing.Box.createVerticalStrut(30));

        // Register button
        javax.swing.JPanel registerBtnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        registerBtnPanel.setOpaque(false);
        registerBtnPanel.add(registerPanelButton);
        centerPanel.add(registerBtnPanel);

        MainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        Parent.add(MainPanel, "MainPanel");

        homeButton1.setText("Home");
        homeButton1.setBackground(new java.awt.Color(70, 130, 180));
        homeButton1.setForeground(java.awt.Color.WHITE);
        homeButton1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        homeButton1.setPreferredSize(new java.awt.Dimension(100, 40));
        homeButton1.addActionListener(this::homeButton1ActionPerformed);

        passwordLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)); // NOI18N
        passwordLabel1.setText("Password");

        usernameLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)); // NOI18N
        usernameLabel1.setText("Username");

        passwordField1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        passwordField1.setPreferredSize(new java.awt.Dimension(300, 40));
        passwordField1.setOpaque(true);

        usernameTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        usernameTextField1.setPreferredSize(new java.awt.Dimension(300, 40));
        usernameTextField1.setOpaque(true);

        loginButton.setText("Login");
        loginButton.setBackground(new java.awt.Color(70, 130, 180));
        loginButton.setForeground(java.awt.Color.WHITE);
        loginButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        loginButton.setPreferredSize(new java.awt.Dimension(200, 50));
        loginButton.addActionListener(this::loginButtonActionPerformed);

        LoginPanel.setLayout(new java.awt.BorderLayout());

        // Home button at top
        javax.swing.JPanel homePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        homePanel.setOpaque(false);
        homePanel.add(homeButton1);
        LoginPanel.add(homePanel, java.awt.BorderLayout.NORTH);

        // Center form panel
        javax.swing.JPanel formPanel = new javax.swing.JPanel();
        formPanel.setLayout(new javax.swing.BoxLayout(formPanel, javax.swing.BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        // Username row
        javax.swing.JPanel usernamePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        usernamePanel.setOpaque(false);
        usernamePanel.add(usernameLabel1);
        usernamePanel.add(usernameTextField1);
        formPanel.add(usernamePanel);

        // Password row
        javax.swing.JPanel passwordPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordLabel1);
        passwordPanel.add(passwordField1);
        formPanel.add(passwordPanel);

        // Button row
        javax.swing.JPanel buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        formPanel.add(buttonPanel);

        LoginPanel.add(formPanel, java.awt.BorderLayout.CENTER);

        Parent.add(LoginPanel, "LoginPanel");

        homeButton.setText("Home");
        homeButton.setBackground(new java.awt.Color(70, 130, 180));
        homeButton.setForeground(java.awt.Color.WHITE);
        homeButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        homeButton.setPreferredSize(new java.awt.Dimension(100, 40));
        homeButton.addActionListener(this::homeButtonActionPerformed);

        passwordLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)); // NOI18N
        passwordLabel.setText("Password");

        usernameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)); // NOI18N
        usernameLabel.setText("Username");

        rbCustomer.setText("Customer");
        rbCustomer.setFont(new java.awt.Font("Segoe UI", 0, 16));

        rbAgent.setText("Agent");
        rbAgent.setFont(new java.awt.Font("Segoe UI", 0, 16));

        registerButton.setText("Register");
        registerButton.setBackground(new java.awt.Color(70, 130, 180));
        registerButton.setForeground(java.awt.Color.WHITE);
        registerButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        registerButton.setPreferredSize(new java.awt.Dimension(200, 50));
        registerButton.addActionListener(this::registerButtonActionPerformed);

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18));
        usernameTextField.setPreferredSize(new java.awt.Dimension(300, 40));
        usernameTextField.setOpaque(true);

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 18));
        passwordField.setPreferredSize(new java.awt.Dimension(300, 40));
        passwordField.setOpaque(true);

        RegisterPanel.setLayout(new java.awt.BorderLayout());

        // Home button at top
        javax.swing.JPanel homePanelReg = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        homePanelReg.setOpaque(false);
        homePanelReg.add(homeButton);
        RegisterPanel.add(homePanelReg, java.awt.BorderLayout.NORTH);

        // Center form panel
        javax.swing.JPanel formPanelReg = new javax.swing.JPanel();
        formPanelReg.setLayout(new javax.swing.BoxLayout(formPanelReg, javax.swing.BoxLayout.Y_AXIS));
        formPanelReg.setOpaque(false);

        // Username row
        javax.swing.JPanel usernamePanelReg = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        usernamePanelReg.setOpaque(false);
        usernamePanelReg.add(usernameLabel);
        usernamePanelReg.add(usernameTextField);
        formPanelReg.add(usernamePanelReg);

        // Password row
        javax.swing.JPanel passwordPanelReg = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        passwordPanelReg.setOpaque(false);
        passwordPanelReg.add(passwordLabel);
        passwordPanelReg.add(passwordField);
        formPanelReg.add(passwordPanelReg);

        // Role selection row
        javax.swing.JPanel rolePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        rolePanel.setOpaque(false);
        rolePanel.add(rbCustomer);
        rolePanel.add(rbAgent);
        formPanelReg.add(rolePanel);

        // Button row
        javax.swing.JPanel buttonPanelReg = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));
        buttonPanelReg.setOpaque(false);
        buttonPanelReg.add(registerButton);
        formPanelReg.add(buttonPanelReg);

        RegisterPanel.add(formPanelReg, java.awt.BorderLayout.CENTER);

        Parent.add(RegisterPanel, "RegisterPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerPanelButtonActionPerformed
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "RegisterPanel");
    }//GEN-LAST:event_registerPanelButtonActionPerformed

    private void loginPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPanelButtonActionPerformed
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "LoginPanel");
    }//GEN-LAST:event_loginPanelButtonActionPerformed

    private void homeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButton1ActionPerformed
        goHome();
    }//GEN-LAST:event_homeButton1ActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        goHome();
    }//GEN-LAST:event_homeButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String username = usernameTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        int role = -1;
        if (rbCustomer.isSelected()) {
            role = 1;
        }
        else if(rbAgent.isSelected()) {
            role = 2;
        }
        
        if(username.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Username cannot be empty.",
                    "Registration Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Password cannot be empty.",
                    "Registration Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(password.length() < 6) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Password must be at least 6 characters long.",
                    "Registration Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(role == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Please select a role (Customer or Agent).",
                    "Registration Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = userManager.registerUser(propertyManager, role, username, password);
        if(success) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Registration successful!",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Clear input fields
            usernameTextField.setText("");
            passwordField.setText("");
            roleGroup.clearSelection();
            
            goHome(); // Return to main panel
        } 
        else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Registration failed. Username may already exist.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameTextField1.getText().trim();
        String password = new String(passwordField1.getPassword());
        
        if(username.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Username cannot be empty.",
                    "Login Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Password cannot be empty.",
                    "Login Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = Session.login(username, password, userManager);
        
        if(success) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Login successful!",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Clear input fields
            usernameTextField1.setText("");
            passwordField1.setText("");
            
            User currentUser = Session.getCurrentUser(); 
            if(currentUser instanceof Customer) {
                new CustomerDashboard(userManager, propertyManager, (Customer) currentUser).setVisible(true);
            }
            else if(currentUser instanceof Agent) {
                new AgentDashboard(userManager, propertyManager, (Agent) currentUser).setVisible(true);
            }
            else if(currentUser instanceof Admin) {
                new AdminDashboard(userManager, propertyManager, (Admin) currentUser).setVisible(true);
            }
            
            this.dispose(); // Closes Authentication JFrame
        } 
        else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Login failed. User doesn't exist or wrong password.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            
            usernameTextField1.setText("");
            passwordField1.setText("");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void goHome() {
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "MainPanel");
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel RegisterPanel;
    private javax.swing.JLabel authenticationLabel;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton homeButton1;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton loginPanelButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel1;
    private javax.swing.JRadioButton rbAgent;
    private javax.swing.JRadioButton rbCustomer;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton registerPanelButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JTextField usernameTextField1;
    // End of variables declaration//GEN-END:variables

private void setupLoginPanelBranding() {
    LoginPanel.removeAll();
    // GridBagLayout centers the card
    LoginPanel.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

    // 1. The Container Card
    JPanel card = new JPanel();
    card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(30, 40, 30, 40)
    ));

    // 2. Add the Logo to the top of the Login card
    javax.swing.JLabel miniLogo = new javax.swing.JLabel();
    try {
        java.net.URL imgURL = getClass().getResource("/logo/logo.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            // Scale smaller for the form (150px)
            Image img = icon.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH);
            miniLogo.setIcon(new ImageIcon(img));
        }
    } catch (Exception e) {}
    miniLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    JLabel subtitle = new JLabel("Hello!");
    subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    subtitle.setForeground(new Color(100, 100, 100));
    subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    // 3. Re-organize the existing fields
    // Set fixed sizes so BoxLayout respects them
    Dimension fieldSize = new Dimension(300, 40);
    usernameTextField1.setMaximumSize(fieldSize);
    passwordField1.setMaximumSize(fieldSize);
    
    // Aligns the labels to the center of the vertical axis
    usernameLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // 4. Build the Card
    card.add(miniLogo);
    card.add(Box.createVerticalStrut(10));
    card.add(subtitle);
    card.add(Box.createVerticalStrut(30));
    
    // Grouping labels and fields
    card.add(usernameLabel1);
    card.add(Box.createVerticalStrut(5));
    card.add(usernameTextField1);
    card.add(Box.createVerticalStrut(15));
    
    card.add(passwordLabel1);
    card.add(Box.createVerticalStrut(5));
    card.add(passwordField1);
    
    card.add(Box.createVerticalStrut(30));
    
    // Primary action button (Match the size of the text fields)
    loginButton.setMaximumSize(new Dimension(300, 45));
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(loginButton);

    // 5. Place the Card and the Home Button
    // We add the card to the centered GridBagLayout
    gbc.gridx = 0;
    gbc.gridy = 0;
    LoginPanel.add(card, gbc);
    
    // Now add the Home Button at the absolute top-left, separately from the card
    JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topBar.setOpaque(false);
    topBar.add(homeButton1);
    
    // Temporarily switch LoginPanel back to BorderLayout to place the topBar and card
    LoginPanel.setLayout(new BorderLayout());
    LoginPanel.add(topBar, BorderLayout.NORTH);
    LoginPanel.add(card, BorderLayout.CENTER);

    LoginPanel.revalidate();
    LoginPanel.repaint();
}

private void setupRegisterPanelBranding() {
    RegisterPanel.removeAll();
    RegisterPanel.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

    javax.swing.JPanel card = new javax.swing.JPanel();
    card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
    card.setBackground(java.awt.Color.WHITE);
    card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1),
        javax.swing.BorderFactory.createEmptyBorder(30, 45, 30, 45)
    ));

    javax.swing.JLabel miniLogo = new javax.swing.JLabel();
    try {
        java.net.URL imgURL = getClass().getResource("/logo/logo.png"); // Path to your new logo.png
        if (imgURL != null) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
            java.awt.Image img = icon.getImage().getScaledInstance(140, -1, java.awt.Image.SCALE_SMOOTH);
            miniLogo.setIcon(new javax.swing.ImageIcon(img));
        } else {
            miniLogo.setText("CREATE ACCOUNT");
            miniLogo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
            miniLogo.setForeground(ThemeEngine.ACCENT_COLOR);
        }
    } catch (Exception e) {
        miniLogo.setText("CREATE ACCOUNT");
    }
    miniLogo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

    javax.swing.JLabel subtitle = new javax.swing.JLabel("Join Find-A-Lot");
    subtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
    subtitle.setForeground(new java.awt.Color(120, 120, 120));
    subtitle.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

    java.awt.Dimension fieldSize = new java.awt.Dimension(300, 40);
    usernameTextField.setMaximumSize(fieldSize);
    passwordField.setMaximumSize(fieldSize);
    usernameLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    passwordLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

    card.add(miniLogo);
    card.add(javax.swing.Box.createVerticalStrut(10));
    card.add(subtitle);
    card.add(javax.swing.Box.createVerticalStrut(30));

    card.add(usernameLabel);
    card.add(javax.swing.Box.createVerticalStrut(5));
    card.add(usernameTextField);
    card.add(javax.swing.Box.createVerticalStrut(15));

    card.add(passwordLabel);
    card.add(javax.swing.Box.createVerticalStrut(5));
    card.add(passwordField);
    card.add(javax.swing.Box.createVerticalStrut(15));

    javax.swing.JPanel roleRow = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
    roleRow.setOpaque(false);
    roleRow.add(rbCustomer);
    roleRow.add(rbAgent);
    card.add(roleRow);

    card.add(javax.swing.Box.createVerticalStrut(25));

    registerButton.setMaximumSize(new java.awt.Dimension(300, 45));
    registerButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    card.add(registerButton);

    javax.swing.JPanel topBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    topBar.setOpaque(false);
    topBar.add(homeButton);

    RegisterPanel.setLayout(new java.awt.BorderLayout());
    RegisterPanel.add(topBar, java.awt.BorderLayout.NORTH);
    RegisterPanel.add(card, java.awt.BorderLayout.CENTER);

    RegisterPanel.revalidate();
    RegisterPanel.repaint();
}
}
package MyApp;

import MyLib.Admin;
import MyLib.Agent;
import MyLib.Customer;
import MyLib.PropertyManager;
import MyLib.Session;
import MyLib.User;
import MyLib.UserManager;
import java.awt.CardLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private Admin admin;
    private User selectedUser = null;

    private int selectedBlock = 0;

    private javax.swing.JComboBox<String> blockSelector = new javax.swing.JComboBox<>(new String[] { "All", "1", "2", "3", "4", "5" });
    private javax.swing.JTextField minPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField minSizeField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxSizeField = new javax.swing.JTextField(7);
    
    public AdminDashboard(UserManager userManager, PropertyManager propertyManager, Admin admin) {
        this.userManager = userManager;
        this.propertyManager = propertyManager;
        this.admin = admin;
        
        initComponents();
        this.setSize(1200, 750);
        
        restructureLayout();
        injectFilters();
        injectAccountsPanel();

        setLocationRelativeTo(null);
        setupTable();
        
        loadUsersToTable();
        loadPropertiesToTable();
        
        applyTheme();
    }

    private void restructureLayout() {
        this.getContentPane().removeAll();
        this.getContentPane().setLayout(new java.awt.BorderLayout(0, 0)); 

        NavigatorPanel.setPreferredSize(new java.awt.Dimension(220, 0)); 
        NavigatorPanel.setLayout(new javax.swing.BoxLayout(NavigatorPanel, javax.swing.BoxLayout.Y_AXIS));
        NavigatorPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 20, 15));
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); 

        javax.swing.JLabel logoLabel = new javax.swing.JLabel();
        try {
            java.net.URL imgURL = getClass().getResource("/logo/logo.png"); 
            if (imgURL != null) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
                java.awt.Image scaledImg = icon.getImage().getScaledInstance(160, -1, java.awt.Image.SCALE_SMOOTH);
                logoLabel.setIcon(new javax.swing.ImageIcon(scaledImg));
            } else {
                logoLabel.setText("FIND A LOT");
                logoLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
                logoLabel.setForeground(java.awt.Color.WHITE);
            }
        } catch (Exception e) {
            logoLabel.setText("FIND A LOT");
        }
        logoLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        NavigatorPanel.add(logoLabel);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(40)); 

        java.awt.Dimension btnSize = new java.awt.Dimension(190, 45);
        javax.swing.JButton[] navButtons = {accountsPanelButton, propertiesPanelButton};

        for (javax.swing.JButton btn : navButtons) {
            btn.setMaximumSize(btnSize);
            btn.setPreferredSize(btnSize);
            btn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            NavigatorPanel.add(btn);
            NavigatorPanel.add(javax.swing.Box.createVerticalStrut(15)); 
        }

        NavigatorPanel.add(javax.swing.Box.createVerticalGlue());

        javax.swing.JPanel profileCard = new javax.swing.JPanel();
        profileCard.setLayout(new javax.swing.BoxLayout(profileCard, javax.swing.BoxLayout.Y_AXIS));
        profileCard.setBackground(new java.awt.Color(45, 55, 72)); 
        profileCard.setMaximumSize(new java.awt.Dimension(190, 70));
        profileCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        profileCard.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        javax.swing.JLabel nameLabel = new javax.swing.JLabel(admin.getUsername().toUpperCase());
        nameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        nameLabel.setForeground(java.awt.Color.WHITE);
        nameLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        javax.swing.JLabel roleLabel = new javax.swing.JLabel("SYSTEM ADMIN");
        roleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 10));
        roleLabel.setForeground(ThemeEngine.ACCENT_COLOR); 
        roleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        profileCard.add(nameLabel);
        profileCard.add(javax.swing.Box.createVerticalStrut(3));
        profileCard.add(roleLabel);

        NavigatorPanel.add(profileCard);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(15));

        logoutButton.setMaximumSize(btnSize);
        logoutButton.setPreferredSize(btnSize);
        logoutButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        NavigatorPanel.add(logoutButton);

        this.getContentPane().add(NavigatorPanel, java.awt.BorderLayout.WEST);
        this.getContentPane().add(Parent, java.awt.BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    private void applyTheme() {
        this.getContentPane().setBackground(ThemeEngine.BG_MAIN);
        Parent.setBackground(ThemeEngine.BG_MAIN);
        PropertiesPanel.setBackground(ThemeEngine.BG_MAIN);
        AccountsPanel.setBackground(ThemeEngine.BG_MAIN);
        
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); 

        ThemeEngine.stylePrimaryButton(propertiesPanelButton);
        ThemeEngine.stylePrimaryButton(accountsPanelButton);
        ThemeEngine.styleSecondaryButton(logoutButton);
        
        logoutButton.setBackground(ThemeEngine.TEXT_PRIMARY);
        logoutButton.setForeground(ThemeEngine.ACCENT_COLOR);

        ThemeEngine.styleTable(propertyTable, propertyScrollPane);
        ThemeEngine.styleTable(accountsTable, jScrollPane1);

        javax.swing.JTextField[] filterFields = {minPriceField, maxPriceField, minSizeField, maxSizeField};
        for (javax.swing.JTextField field : filterFields) {
            ThemeEngine.styleTextField(field);
        }
    }

    private void initComponents() {
        NavigatorPanel = new javax.swing.JPanel();
        propertiesPanelButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        accountsPanelButton = new javax.swing.JButton();
        Parent = new javax.swing.JPanel();
        AccountsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        addAccountButton = new javax.swing.JButton();
        removeAccountButton = new javax.swing.JButton();
        PropertiesPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        propertyScrollPane = new javax.swing.JScrollPane();
        propertyTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        propertiesPanelButton.setText("Properties");
        propertiesPanelButton.addActionListener(this::propertiesPanelButtonActionPerformed);
        
        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        accountsPanelButton.setText("Accounts");
        accountsPanelButton.addActionListener(this::accountsPanelButtonActionPerformed);

        Parent.setLayout(new java.awt.CardLayout());

        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Username", "Role" }
        ) {
            boolean[] canEdit = new boolean [] { false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        jScrollPane1.setViewportView(accountsTable);

        addAccountButton.setText("Add Account");
        addAccountButton.addActionListener(this::addAccountButtonActionPerformed);

        removeAccountButton.setText("Remove Account");
        removeAccountButton.addActionListener(this::removeAccountButtonActionPerformed);

        Parent.add(AccountsPanel, "AccountsPanel");

        propertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Property ID", "Block Number", "Property Number", "Status", "Owner", "Property Price", "Property Size", "Property Floors", "Property Type" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        propertyScrollPane.setViewportView(propertyTable);

        Parent.add(PropertiesPanel, "PropertiesPanel");
    }                        

    private void propertiesPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "PropertiesPanel");
        loadPropertiesToTable();
    }                                                     

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Session.logout();
        new Authentication(userManager, propertyManager).setVisible(true);
        this.dispose();
    }                                            

    private void accountsPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "AccountsPanel");
        loadUsersToTable();
    }                                                   

    private void removeAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        if(selectedUser == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a user from the table first.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(selectedUser instanceof Admin) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cannot remove admin accounts.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        admin.removeUser(selectedUser);
        loadUsersToTable();
        selectedUser = null;
    }                                                   

    private void addAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new java.awt.GridLayout(4, 2, 5, 5));
        
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JRadioButton rbCustomer = new JRadioButton("Customer");
        JRadioButton rbAgent = new JRadioButton("Agent");
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(rbCustomer);
        roleGroup.add(rbAgent);
        
        registerPanel.add(new JLabel("Username:"));
        registerPanel.add(usernameField);
        registerPanel.add(new JLabel("Password:"));
        registerPanel.add(passwordField);
        registerPanel.add(new JLabel("Role:"));
        JPanel rolePanel = new JPanel();
        rolePanel.add(rbCustomer);
        rolePanel.add(rbAgent);
        registerPanel.add(rolePanel);
        
        int result = JOptionPane.showConfirmDialog(this, registerPanel, 
            "Add New Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            int role = -1;
            if(rbCustomer.isSelected()) {
                role = 1;
            }
            else if(rbAgent.isSelected()) {
                role = 2;
            }

            if(username.isEmpty() || password.isEmpty() || role == -1) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields and select a role.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = userManager.registerUser(propertyManager, role, username, password);
            if(success) {
                JOptionPane.showMessageDialog(this, "Account added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadUsersToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add account. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                                

    private void injectFilters() {
        PropertiesPanel.removeAll();
        PropertiesPanel.setLayout(new java.awt.BorderLayout(10, 10));
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10));
        topArea.setBackground(ThemeEngine.BG_MAIN);
        
        javax.swing.JLabel title = new javax.swing.JLabel("Property Database");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        topArea.add(title, java.awt.BorderLayout.NORTH);

        blockSelector.addActionListener(e -> {
            String selected = (String) blockSelector.getSelectedItem();
            if (selected == null || selected.equals("All")) {
                selectedBlock = 0;
            } else {
                selectedBlock = Integer.parseInt(selected);
            }
        });

        javax.swing.JPanel filterBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        filterBar.setBackground(ThemeEngine.BG_MAIN);

        filterBar.add(new javax.swing.JLabel("Block:"));
        filterBar.add(blockSelector);
        filterBar.add(javax.swing.Box.createHorizontalStrut(10));
        filterBar.add(new javax.swing.JLabel("Min Price:"));
        filterBar.add(minPriceField);
        filterBar.add(new javax.swing.JLabel("Max Price:"));
        filterBar.add(maxPriceField);
        filterBar.add(javax.swing.Box.createHorizontalStrut(10));
        filterBar.add(new javax.swing.JLabel("Min Size:"));
        filterBar.add(minSizeField);
        filterBar.add(new javax.swing.JLabel("Max Size:"));
        filterBar.add(maxSizeField);

        javax.swing.JButton filterBtn = new javax.swing.JButton("Filter");
        ThemeEngine.stylePrimaryButton(filterBtn);
        filterBtn.addActionListener(e -> loadPropertiesToTable());

        javax.swing.JPanel wrapper = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        wrapper.setBackground(ThemeEngine.BG_MAIN);
        wrapper.add(filterBar, java.awt.BorderLayout.CENTER); 
        wrapper.add(filterBtn, java.awt.BorderLayout.EAST); 

        topArea.add(wrapper, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(propertyScrollPane, java.awt.BorderLayout.CENTER);

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    private void injectAccountsPanel() {
        AccountsPanel.removeAll();
        AccountsPanel.setLayout(new java.awt.BorderLayout(10, 15));
        AccountsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JLabel title = new javax.swing.JLabel("Account Management");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        AccountsPanel.add(title, java.awt.BorderLayout.NORTH);

        AccountsPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.JPanel buttonBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
        buttonBar.setBackground(ThemeEngine.BG_MAIN);

        ThemeEngine.stylePrimaryButton(addAccountButton);
        ThemeEngine.styleSecondaryButton(removeAccountButton); 

        buttonBar.add(removeAccountButton);
        buttonBar.add(addAccountButton);

        AccountsPanel.add(buttonBar, java.awt.BorderLayout.SOUTH);

        AccountsPanel.revalidate();
        AccountsPanel.repaint();
    }

    private Double parseDoubleOrNull(String text) {
        if (text == null || text.isEmpty()) return null;
        if (!text.matches("\\d+(\\.\\d+)?")) return null;
        return Double.parseDouble(text);
    }

    private void loadPropertiesToTable() {
        if (propertyTable == null) return;
        DefaultTableModel model = (DefaultTableModel) propertyTable.getModel();
        model.setRowCount(0);

        Integer blockFilter = (selectedBlock == 0) ? null : selectedBlock;
        Double minPrice = parseDoubleOrNull(minPriceField.getText());
        Double maxPrice = parseDoubleOrNull(maxPriceField.getText());
        Double minSize = parseDoubleOrNull(minSizeField.getText());
        Double maxSize = parseDoubleOrNull(maxSizeField.getText());

        java.util.ArrayList<MyLib.Property> filteredProperties = propertyManager.filterProperties(blockFilter, minPrice, maxPrice);
        if (filteredProperties != null) {
            for (MyLib.Property property : filteredProperties) {
                if (minSize != null && property.getPropertySize() < minSize) continue;
                if (maxSize != null && property.getPropertySize() > maxSize) continue;

                String type = property.getClass().getSimpleName();
                String ownerName = (property.getOwner() != null) ? property.getOwner().getUsername() : "None";

                model.addRow(new Object[] {
                    property.getPropertyId(),
                    property.getBlockNumber(),
                    property.getPropertyNumber(),
                    property.getStatus(),
                    ownerName,
                    property.getContactPrice(),
                    property.getPropertySize(),
                    property.getFloors(),
                    type
                });
            }
        }
    }

    private void loadUsersToTable() {
        DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
        model.setRowCount(0); 
        
        for(User userItem : admin.getAllUsers()) {
            String role = "";
            if(userItem instanceof Customer) role = "Customer";
            else if(userItem instanceof Agent) role = "Agent";
            else if(userItem instanceof Admin) role = "Admin";
            
            model.addRow(new Object[] {
               userItem.getUsername(),
               role
            });
        }
    }
    
    private void setupTable() {
        accountsTable.setRowSelectionAllowed(true);
        accountsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accountsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = accountsTable.getSelectedRow();
                if (row >= 0) { 
                    String username = (String) accountsTable.getValueAt(row, 0); 
                    selectedUser = admin.getUser(username);
                } else {
                    selectedUser = null;
                }
            }
        });
    }

    // Variables declaration                     
    private javax.swing.JPanel AccountsPanel;
    private javax.swing.JPanel NavigatorPanel;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel PropertiesPanel;
    private javax.swing.JButton accountsPanelButton;
    private javax.swing.JTable accountsTable;
    private javax.swing.JButton addAccountButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane propertyScrollPane;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton propertiesPanelButton;
    private javax.swing.JTable propertyTable;
    private javax.swing.JButton removeAccountButton;
    // End of variables declaration                   
}
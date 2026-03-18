package MyApp;

import MyLib.Agent;
import MyLib.Block;
import MyLib.Booking;
import MyLib.Customer;
import MyLib.Property;
import MyLib.PropertyManager;
import MyLib.Session;
import MyLib.UserManager;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class CustomerDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerDashboard.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private Customer customer;
    private Property selectedProperty = null;
    private int selectedBlock = 0;
    
    public CustomerDashboard(UserManager userManager, PropertyManager propertyManager, Customer customer) {
        this.userManager = userManager;
        this.propertyManager = propertyManager;
        this.customer = customer;
        initComponents();
        
        
        restructureLayout();
        injectFilters();
        injectOwnedPropertiesPanel();
        
        setLocationRelativeTo(null);
        setupTable();
        
        jButton1.addActionListener(this::confirmPurchaseActionPerformed);
        
        loadPropertiesToTable();
        loadOwnedPropertiesToTable();
        
        applyTheme();
    }

    private void restructureLayout() {
    this.getContentPane().removeAll();
    
    this.getContentPane().setLayout(new java.awt.BorderLayout(0, 0)); 

    NavigatorPanel.setPreferredSize(new java.awt.Dimension(220, 0)); 
    NavigatorPanel.setLayout(new javax.swing.BoxLayout(NavigatorPanel, javax.swing.BoxLayout.Y_AXIS));
    NavigatorPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 20, 15));
    NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); // Dark Sidebar

    javax.swing.JLabel logoLabel = new javax.swing.JLabel();
    try {
        // Looks for the image in your src folder
        java.net.URL imgURL = getClass().getResource("/logo/logo.png"); 
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImg = icon.getImage().getScaledInstance(160, -1, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImg));
        } else {
            logoLabel.setText("FIND A LOT");
            logoLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
            logoLabel.setForeground(Color.WHITE);
        }
    } catch (Exception e) {
        logoLabel.setText("FIND A LOT");
    }
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    NavigatorPanel.add(logoLabel);
    NavigatorPanel.add(javax.swing.Box.createVerticalStrut(40)); 

    // 4. Navigation Buttons (Properties & Owned)
    Dimension btnSize = new Dimension(190, 45);
    JButton[] navButtons = {propertiesPanelButton, ownedPropertiesPanelButton};

    for (JButton btn : navButtons) {
        btn.setMaximumSize(btnSize);
        btn.setPreferredSize(btnSize);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        NavigatorPanel.add(btn);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(15)); 
    }

    // 5. Pushes Profile Card to the Bottom
    NavigatorPanel.add(javax.swing.Box.createVerticalGlue());

    // 6. Account Identification Card
    JPanel profileCard = new JPanel();
    profileCard.setLayout(new javax.swing.BoxLayout(profileCard, javax.swing.BoxLayout.Y_AXIS));
    profileCard.setBackground(new java.awt.Color(45, 55, 72)); // Modern slate
    profileCard.setMaximumSize(new Dimension(190, 75));
    profileCard.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
    profileCard.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel nameLabel = new JLabel(customer.getUsername().toUpperCase());
    nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel roleLabel = new JLabel("CLIENT PORTAL");
    roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    roleLabel.setForeground(ThemeEngine.ACCENT_COLOR); 
    roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    profileCard.add(nameLabel);
    profileCard.add(Box.createVerticalStrut(3));
    profileCard.add(roleLabel);

    NavigatorPanel.add(profileCard);
    NavigatorPanel.add(javax.swing.Box.createVerticalStrut(15));

    // 7. Logout Button (Pinned to bottom)
    logoutButton.setMaximumSize(btnSize);
    logoutButton.setPreferredSize(btnSize);
    logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    NavigatorPanel.add(logoutButton);

    // 8. Re-attach to JFrame
    this.getContentPane().add(NavigatorPanel, java.awt.BorderLayout.WEST);
    this.getContentPane().add(Parent, java.awt.BorderLayout.CENTER); 

    this.revalidate();
    this.repaint();
}

    // --- NEW: Applies the ThemeEngine to all static components ---
    private void applyTheme() {
        this.getContentPane().setBackground(ThemeEngine.BG_MAIN);
        Parent.setBackground(ThemeEngine.BG_MAIN);
        PropertiesPanel.setBackground(ThemeEngine.BG_MAIN);
        OwnedPropertiesPanel.setBackground(ThemeEngine.BG_MAIN);
        
        // Sidebar color
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); 

        // Apply Button Styles
        ThemeEngine.stylePrimaryButton(propertiesPanelButton);
        ThemeEngine.stylePrimaryButton(ownedPropertiesPanelButton);
        ThemeEngine.styleSecondaryButton(logoutButton);
        
        // Custom override for Logout to match dark sidebar
        logoutButton.setBackground(ThemeEngine.TEXT_PRIMARY);
        logoutButton.setForeground(ThemeEngine.ACCENT_COLOR);

        // Apply Table Styles
        ThemeEngine.styleTable(propertyTable, jScrollPane1);
        ThemeEngine.styleTable(ownedPropertiesTable, jScrollPane2);

        javax.swing.JTextField[] filterFields = {minPriceField, maxPriceField, minSizeField, maxSizeField};
        for (javax.swing.JTextField field : filterFields) {
            ThemeEngine.styleTextField(field);
        }
    }

    // Helper method: Safely find a property across all blocks
    private Property findPropertyById(int id) {
        for (Block b : propertyManager.getAllBlocks()) {
            for (Property p : b.getProperties()) {
                if (p.getPropertyId() == id) {
                    return p;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NavigatorPanel = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        ownedPropertiesPanelButton = new javax.swing.JButton();
        propertiesPanelButton = new javax.swing.JButton();
        Parent = new javax.swing.JPanel();
        PropertiesPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        propertyTable = new javax.swing.JTable();
        bookButton = new javax.swing.JButton();
        blockSelector = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        minSizeField = new javax.swing.JTextField();
        maxSizeField = new javax.swing.JTextField();
        minPriceField = new javax.swing.JTextField();
        maxPriceField = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        OwnedPropertiesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ownedPropertiesTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NavigatorPanel.setBackground(new java.awt.Color(30, 42, 54));
        NavigatorPanel.setLayout(new javax.swing.BoxLayout(NavigatorPanel, javax.swing.BoxLayout.Y_AXIS));
        NavigatorPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        NavigatorPanel.setPreferredSize(new java.awt.Dimension(220, 0));

        javax.swing.JLabel navTitle = new javax.swing.JLabel("CUSTOMER");
        navTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        navTitle.setForeground(java.awt.Color.WHITE);
        navTitle.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        NavigatorPanel.add(navTitle);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(24));

        propertiesPanelButton.setText("Properties");
        propertiesPanelButton.addActionListener(this::propertiesPanelButtonActionPerformed);
        propertiesPanelButton.setMaximumSize(new java.awt.Dimension(200, 50));
        propertiesPanelButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        propertiesPanelButton.setBackground(new java.awt.Color(46, 125, 50));
        propertiesPanelButton.setForeground(java.awt.Color.WHITE);
        propertiesPanelButton.setFocusPainted(false);
        NavigatorPanel.add(propertiesPanelButton);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(12));

        ownedPropertiesPanelButton.setText("Owned Properties");
        ownedPropertiesPanelButton.addActionListener(this::ownedPropertiesPanelButtonActionPerformed);
        ownedPropertiesPanelButton.setMaximumSize(new java.awt.Dimension(200, 50));
        ownedPropertiesPanelButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        ownedPropertiesPanelButton.setBackground(new java.awt.Color(46, 125, 50));
        ownedPropertiesPanelButton.setForeground(java.awt.Color.WHITE);
        ownedPropertiesPanelButton.setFocusPainted(false);
        NavigatorPanel.add(ownedPropertiesPanelButton);
        NavigatorPanel.add(javax.swing.Box.createVerticalStrut(24));

        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);
        logoutButton.setMaximumSize(new java.awt.Dimension(200, 50));
        logoutButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        logoutButton.setBackground(new java.awt.Color(211, 47, 47));
        logoutButton.setForeground(java.awt.Color.WHITE);
        logoutButton.setFocusPainted(false);
        NavigatorPanel.add(logoutButton);

        Parent.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Properties");

        propertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Property ID", "Block Number", "Property Number", "Status", "Owner", "Property Price", "Property Size", "Property Floors", "Property Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(propertyTable);

        bookButton.setText("Book Property");
        bookButton.addActionListener(this::bookButtonActionPerformed);

        blockSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "1", "2", "3", "4", "5" }));
        blockSelector.addActionListener(this::blockSelectorActionPerformed);

        jLabel3.setText("Block Number: ");

        jLabel4.setText("Size:");

        jLabel5.setText("Price: ");

        filterButton.setText("Filter");
        filterButton.addActionListener(this::filterButtonActionPerformed);

        javax.swing.GroupLayout PropertiesPanelLayout = new javax.swing.GroupLayout(PropertiesPanel);
        PropertiesPanel.setLayout(PropertiesPanelLayout);
        PropertiesPanelLayout.setHorizontalGroup(
            PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookButton, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PropertiesPanelLayout.setVerticalGroup(
            PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addComponent(bookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Parent.add(PropertiesPanel, "PropertiesPanel");

        jLabel1.setText("Owned Properties");

        ownedPropertiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Property ID", "Block Number", "Property Number", "Status", "Property Price", "Property Size", "Property Floors", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ownedPropertiesTable);

        jButton1.setText("Confirm Purchase");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout OwnedPropertiesPanelLayout = new javax.swing.GroupLayout(OwnedPropertiesPanel);
        OwnedPropertiesPanel.setLayout(OwnedPropertiesPanelLayout);
        OwnedPropertiesPanelLayout.setHorizontalGroup(
            OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        OwnedPropertiesPanelLayout.setVerticalGroup(
            OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGroup(OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        Parent.add(OwnedPropertiesPanel, "OwnedPropertiesPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NavigatorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NavigatorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Session.logout();
        new Authentication(userManager, propertyManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void ownedPropertiesPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownedPropertiesPanelButtonActionPerformed
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "OwnedPropertiesPanel");
        loadOwnedPropertiesToTable(); 
    }//GEN-LAST:event_ownedPropertiesPanelButtonActionPerformed

    private void propertiesPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "PropertiesPanel");
        loadPropertiesToTable(); 
    }                                                     

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
        if (selectedProperty == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a property from the table first.");
            return;
        }
        
        if (!selectedProperty.getStatus().equalsIgnoreCase("For Sale")) {
            javax.swing.JOptionPane.showMessageDialog(this, "This property is no longer available for booking.");
            return;
        }

        selectedProperty.setOwner(this.customer);
        selectedProperty.updateStatus("Booked");     
        
        javax.swing.JOptionPane.showMessageDialog(this, "Property Booked! Awaiting Agent Approval.");
        
        loadPropertiesToTable();
        loadOwnedPropertiesToTable();
    }//GEN-LAST:event_bookButtonActionPerformed

    private void confirmPurchaseActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = ownedPropertiesTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a booked property from the table to confirm.");
            return;
        }
        
        int propertyId = (int) ownedPropertiesTable.getValueAt(selectedRow, 0);
        Property propToBuy = findPropertyById(propertyId);
        
        if (propToBuy != null && propToBuy.getStatus().equalsIgnoreCase("Booked")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Payment details sent to Agent. Please wait for them to confirm the sale.");
            loadOwnedPropertiesToTable();
            loadPropertiesToTable();
        } else if (propToBuy != null && propToBuy.getStatus().equalsIgnoreCase("Sold")) {
            javax.swing.JOptionPane.showMessageDialog(this, "You already own this property!");
        }
    }

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        filterProperties();
    }//GEN-LAST:event_filterButtonActionPerformed

    private void blockSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockSelectorActionPerformed
        String selected = (String) blockSelector.getSelectedItem();
        if(selected == null || selected.equals("All")) {
            selectedBlock = 0;
        } else {
            selectedBlock = Integer.parseInt(selected);
        }
    }//GEN-LAST:event_blockSelectorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    // TODO add your handling code here:                                      
int row = ownedPropertiesTable.getSelectedRow();
        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a property from the table.");
            return;
        }

        int propertyId = (int) ownedPropertiesTable.getValueAt(row, 0);
        final Property property = customer.getProperty(propertyId);

        if (property == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Property data not found.");
            return;
        }

        final double contactPrice = property.getContactPrice();
        final double downpayment = contactPrice * 0.05;
        final double reservationFee = 20000;
        final double taxes = (contactPrice * 0.06) + (contactPrice * 0.015) + (contactPrice * 0.005) + (contactPrice * 0.02);
        final double totalCashOut = downpayment + reservationFee + taxes;
        final double loanAmount = contactPrice - downpayment;

        javax.swing.JPanel mainPanel = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new java.awt.Dimension(450, 280));

        javax.swing.JPanel optionsPanel = new javax.swing.JPanel();
        optionsPanel.setLayout(new javax.swing.BoxLayout(optionsPanel, javax.swing.BoxLayout.Y_AXIS));
        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Plan"));

        javax.swing.JRadioButton cashOption = new javax.swing.JRadioButton("Full Cash", true);
        final javax.swing.JRadioButton installmentOption = new javax.swing.JRadioButton("Bank Financing");
        javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
        group.add(cashOption); 
        group.add(installmentOption);

        final javax.swing.JComboBox<String> bankBox = new javax.swing.JComboBox<>(new String[]{"Pag-IBIG (6.25%)", "RCBC (6.60%)", "SBC (6.80%)", "BDO (6.88%)", "CTS (10.50%)"});
        final javax.swing.JComboBox<Integer> yearsBox = new javax.swing.JComboBox<>();
        for (int i = 5; i <= 30; i += 5) {
            yearsBox.addItem(i);
        }

        bankBox.setEnabled(false);
        yearsBox.setEnabled(false);

        optionsPanel.add(cashOption);
        optionsPanel.add(installmentOption);
        optionsPanel.add(new javax.swing.JLabel("Bank:"));
        optionsPanel.add(bankBox);
        optionsPanel.add(new javax.swing.JLabel("Years:"));
        optionsPanel.add(yearsBox);

        final javax.swing.JLabel receiptLabel = new javax.swing.JLabel();
        receiptLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        // --- THE TRADITIONAL JAVA FIX ---
        java.awt.event.ActionListener updateUI = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                boolean isInstallment = installmentOption.isSelected();
                bankBox.setEnabled(isInstallment);
                yearsBox.setEnabled(isInstallment);

                double rate = 0;
                String bank = (String) bankBox.getSelectedItem();
                if (bank.contains("6.25")) rate = 0.0625;
                else if (bank.contains("6.60")) rate = 0.066;
                else if (bank.contains("6.80")) rate = 0.068;
                else if (bank.contains("6.88")) rate = 0.0688;
                else if (bank.contains("10.50")) rate = 0.105;

                double monthly = 0;
                if (isInstallment && yearsBox.getSelectedItem() != null) {
                    int years = (Integer) yearsBox.getSelectedItem();
                    monthly = computeMonthlyAmortization(loanAmount, rate, years);
                }
                double gmi = monthly / 0.35;

                String html = "<html><div style='width:250px; font-family:sans-serif;'>" +
                    "<h2 style='color:#2E7D32;'>Receipt Summary</h2>" +
                    "<table style='width:100%;'>" +
                    "<tr><td>Contact Price:</td><td align='right'><b>" + String.format("%,.2f", contactPrice) + "</b></td></tr>" +
                    "<tr><td>Downpayment (5%):</td><td align='right'>" + String.format("%,.2f", downpayment) + "</td></tr>" +
                    "<tr><td>Reservation Fee:</td><td align='right'>" + String.format("%,.2f", reservationFee) + "</td></tr>" +
                    "<tr><td>Taxes & Fees:</td><td align='right'>" + String.format("%,.2f", taxes) + "</td></tr>" +
                    "<tr style='border-top:1px solid black;'><td style='padding-top:5px;'><b>Total Cash Out:</b></td>" +
                    "<td align='right' style='padding-top:5px; color:#D32F2F;'><b>" + String.format("₱%,.2f", totalCashOut) + "</b></td></tr>" +
                    "</table>" +
                    (isInstallment ? 
                        "<div style='margin-top:10px; padding:5px; background-color:#F5F5F5;'>" +
                        "<b>Monthly: ₱" + String.format("%,.2f", monthly) + "</b><br>" +
                        "<small>Req. Income: ₱" + String.format("%,.2f", gmi) + "</small></div>" : 
                        "<p style='margin-top:10px; text-align:center;'><b>MODE: FULL CASH</b></p>") +
                    "</div></html>";
                receiptLabel.setText(html);
            }
        };

        cashOption.addActionListener(updateUI);
        installmentOption.addActionListener(updateUI);
        bankBox.addActionListener(updateUI);
        yearsBox.addActionListener(updateUI);
        updateUI.actionPerformed(null);

        mainPanel.add(optionsPanel, java.awt.BorderLayout.WEST);
        mainPanel.add(receiptLabel, java.awt.BorderLayout.CENTER);

        int result = javax.swing.JOptionPane.showConfirmDialog(this, mainPanel, "Confirm Purchase", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (result == javax.swing.JOptionPane.OK_OPTION) {
            property.setReservedBy(null);
            property.updateStatus("Buy");
            property.setOwner(customer);
            javax.swing.JOptionPane.showMessageDialog(this, "Purchase Confirmed!");
            loadPropertiesToTable();
            loadOwnedPropertiesToTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loadPropertiesToTable() {
        DefaultTableModel model = (DefaultTableModel) propertyTable.getModel();
        model.setRowCount(0); 
        
        Integer blockFilter = (selectedBlock == 0) ? null : selectedBlock;
        Double minPrice = parseDoubleOrNull(minPriceField.getText());
        Double maxPrice = parseDoubleOrNull(maxPriceField.getText());
        Double minSize = parseDoubleOrNull(minSizeField.getText());
        Double maxSize = parseDoubleOrNull(maxSizeField.getText());
        
        java.util.ArrayList<Property> filteredProperties = propertyManager.filterProperties(blockFilter, minPrice, maxPrice);
        
        if (filteredProperties != null) {
            for(Property property : filteredProperties) { 
                if (minSize != null && property.getPropertySize() < minSize) continue;
                if (maxSize != null && property.getPropertySize() > maxSize) continue;

                String type = property.getClass().getSimpleName();
                String ownerName = (property.getOwner() != null) ? property.getOwner().getUsername() : "None";

                model.addRow(new Object[]{
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
    
    private Double parseDoubleOrNull(String text){
        if (text == null || text.isEmpty()) return null;
        if (!text.matches("\\d+(\\.\\d+)?")) return null;
        return Double.parseDouble(text);
    }
    
    private boolean isInvalidPrice(String text, Double price) {
        return text != null && !text.isEmpty() && price == null;
    }
    
    private void filterProperties(){
        double minPrice = parseDoubleOrNull(minPriceField.getText());
        double maxPrice = parseDoubleOrNull(maxPriceField.getText());
        
        if (isInvalidPrice(minPriceField.getText(), minPrice)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Enter a valid numeric value for minimum price.");
            return;
        }

        if (isInvalidPrice(maxPriceField.getText(), maxPrice)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Enter a valid numeric value for maximum price.");
            return;
        }
        
        loadPropertiesToTable();
    }
        
    private void loadOwnedPropertiesToTable() { 
        DefaultTableModel ownedModel = (DefaultTableModel) ownedPropertiesTable.getModel();
        ownedModel.setRowCount(0);
        
        for(Block block : propertyManager.getAllBlocks()) { 
            for(Property property : block.getProperties()) { 
                
                // Check if the customer is either the official Owner or the one who Reserved it
                boolean isOwner = property.getOwner() != null && property.getOwner().equals(this.customer);
                
                if(isOwner) { 
                    String type = property.getClass().getSimpleName();
                    String statusText;
                    
                    // Logic to show detailed status to the customer
                    if ("Sold".equalsIgnoreCase(property.getStatus()) || "Buy".equalsIgnoreCase(property.getStatus())) {
                        statusText = "Owned";
                    } else if ("Booked".equalsIgnoreCase(property.getStatus()) || "Book".equalsIgnoreCase(property.getStatus())) {
                        statusText = "Pending Approval";
                    } else {
                        statusText = property.getStatus();
                    }

                    ownedModel.addRow(new Object[] {
                        property.getPropertyId(),
                        property.getBlockNumber(), 
                        property.getPropertyNumber(), 
                        statusText,
                        property.getContactPrice(),
                        property.getPropertySize(),
                        property.getFloors(),
                        type
                    });
                }
            }
        }
    }
    
    private void setupTable() {
        propertyTable.setRowSelectionAllowed(true);
        propertyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        propertyTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = propertyTable.getSelectedRow();
                if (row >= 0) { 
                    int propertyId = (int) propertyTable.getValueAt(row, 0); 
                    selectedProperty = findPropertyById(propertyId);
                } else {
                    selectedProperty = null;
                }
            }
        });
    }

    private void injectFilters() {
        PropertiesPanel.removeAll();
        PropertiesPanel.setLayout(new java.awt.BorderLayout(10, 10));
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10));
        
        javax.swing.JLabel title = new javax.swing.JLabel("Available Properties");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        topArea.add(title, java.awt.BorderLayout.NORTH);

        javax.swing.JPanel filterBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        filterBar.setBackground(ThemeEngine.BG_MAIN);
        topArea.setBackground(ThemeEngine.BG_MAIN);

        minPriceField.setColumns(7);
        maxPriceField.setColumns(7);
        minSizeField.setColumns(7);
        maxSizeField.setColumns(7);

        // Style both buttons
        ThemeEngine.stylePrimaryButton(filterButton);
        ThemeEngine.stylePrimaryButton(bookButton);
        
        // Add only the inputs to the top bar
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
        // NOTE: Filter button removed from here so it stops hiding!

        topArea.add(filterBar, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        // NEW BOTTOM AREA: Puts both buttons safely at the bottom right
        javax.swing.JPanel bottomArea = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 10));
        bottomArea.setBackground(ThemeEngine.BG_MAIN);
        
        bottomArea.add(filterButton); // Filter is now down here!
        bottomArea.add(bookButton);
        
        PropertiesPanel.add(bottomArea, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    private void injectOwnedPropertiesPanel() {
        OwnedPropertiesPanel.removeAll();
        OwnedPropertiesPanel.setLayout(new java.awt.BorderLayout(10, 15));
        OwnedPropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JLabel title = new javax.swing.JLabel("My Owned Properties");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        OwnedPropertiesPanel.add(title, java.awt.BorderLayout.NORTH);

        OwnedPropertiesPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        // A horizontal bar to keep the Confirm button neatly at the bottom right
        javax.swing.JPanel buttonBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));
        buttonBar.setBackground(ThemeEngine.BG_MAIN);

        ThemeEngine.stylePrimaryButton(jButton1); 
        buttonBar.add(jButton1);

        OwnedPropertiesPanel.add(buttonBar, java.awt.BorderLayout.SOUTH);

        OwnedPropertiesPanel.revalidate();
        OwnedPropertiesPanel.repaint();
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
    
    private double computeMonthlyAmortization(double loanAmount, double annualRate, int years) {
        if (loanAmount <= 0 || annualRate <= 0 || years <= 0) return 0;
        double monthlyRate = annualRate / 12;
        int numberOfPayments = years * 12;
        
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) 
               / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NavigatorPanel;
    private javax.swing.JPanel OwnedPropertiesPanel;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel PropertiesPanel;
    private javax.swing.JComboBox<String> blockSelector;
    private javax.swing.JButton bookButton;
    private javax.swing.JButton filterButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField maxPriceField;
    private javax.swing.JTextField maxSizeField;
    private javax.swing.JTextField minPriceField;
    private javax.swing.JTextField minSizeField;
    private javax.swing.JButton ownedPropertiesPanelButton;
    private javax.swing.JTable ownedPropertiesTable;
    private javax.swing.JButton propertiesPanelButton;
    private javax.swing.JTable propertyTable;
    // End of variables declaration//GEN-END:variables
}

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
import java.awt.event.ActionListener;
import java.util.List;

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
        
        // 1. Restructure the layout to match the modern Sidebar look
        restructureLayout();
        injectFilters();
        injectOwnedPropertiesPanel();
        
        setLocationRelativeTo(null);
        setupTable();
        
        jButton1.addActionListener(this::confirmPurchaseActionPerformed);
        
        // 2. Load the data
        loadPropertiesToTable();
        loadOwnedPropertiesToTable();
        
        // 3. Apply the ThemeEngine
        applyTheme();
    }

    // --- NEW: Forces the JFrame into a strict Sidebar Layout ---
    private void restructureLayout() {
        this.getContentPane().removeAll();
        this.getContentPane().setLayout(new java.awt.BorderLayout());
        
        // Setup the Sidebar (West)
        NavigatorPanel.setPreferredSize(new java.awt.Dimension(220, 0));
        NavigatorPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));
        
        // Re-add components to JFrame
        this.getContentPane().add(NavigatorPanel, java.awt.BorderLayout.WEST);
        this.getContentPane().add(Parent, java.awt.BorderLayout.CENTER);
        
        // Add a stylized Logo Label to the top of the Sidebar
        javax.swing.JLabel logoLabel = new javax.swing.JLabel("FIND A LOT");
        logoLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        logoLabel.setForeground(ThemeEngine.BG_PANEL); // White text
        NavigatorPanel.add(logoLabel, 0);
        
        // Re-add buttons nicely
        propertiesPanelButton.setPreferredSize(new java.awt.Dimension(180, 45));
        ownedPropertiesPanelButton.setPreferredSize(new java.awt.Dimension(180, 45));
        logoutButton.setPreferredSize(new java.awt.Dimension(180, 45));
        
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
        selectedProperty.updateStatus("Book");
        
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

    if(row < 0){
        JOptionPane.showMessageDialog(this, "Please select a property.");
        return;
    }

    int propertyId = (int) ownedPropertiesTable.getValueAt(row, 0);
    Property property = customer.getProperty(propertyId);

    if(property == null){
        JOptionPane.showMessageDialog(this, "Invalid property.");
        return;
    }

    double contactPrice = property.getContactPrice();
    double downpayment = contactPrice * 0.05;
    double reservationFee = 20000;
    double capitalTax = contactPrice * 0.06;
    double docStamp = contactPrice * 0.015;
    double transferTax = contactPrice * 0.005;
    double notarial = contactPrice * 0.02;

    double otherFees = capitalTax + docStamp + transferTax + notarial;
    double totalCashOut = downpayment + reservationFee + otherFees;
    double loanAmount = contactPrice - downpayment;

    // PANEL UI
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel infoLabel = new JLabel("Receipt Summary:");
    JLabel receiptLabel = new JLabel();

    // PAYMENT TYPE
    JRadioButton cashOption = new JRadioButton("Cash", true);
    JRadioButton installmentOption = new JRadioButton("Installment");

    ButtonGroup group = new ButtonGroup();
    group.add(cashOption);
    group.add(installmentOption);

    // INSTALLMENT OPTIONS
    JComboBox<String> bankBox = new JComboBox<>(new String[]{
        "Pag-IBIG (6.25%)",
        "RCBC (6.60%)",
        "SBC (6.80%)",
        "BDO (6.88%)",
        "CTS (10.50%)"
    });

    JComboBox<Integer> yearsBox = new JComboBox<>();
    for(int i = 1; i <= 30; i++){
        yearsBox.addItem(i);
    }

    bankBox.setEnabled(false);
    yearsBox.setEnabled(false);

    // LISTENERS
    ActionListener updateReceipt = e -> {
        double monthly = 0;
        double gmi = 0;

        if(installmentOption.isSelected()){
            bankBox.setEnabled(true);
            yearsBox.setEnabled(true);

            double rate = 0;

            String bank = (String) bankBox.getSelectedItem();
            int years = (int) yearsBox.getSelectedItem();

            if(bank.contains("6.25")) rate = 0.0625;
            else if(bank.contains("6.60")) rate = 0.066;
            else if(bank.contains("6.80")) rate = 0.068;
            else if(bank.contains("6.88")) rate = 0.0688;
            else if(bank.contains("10.50")) rate = 0.105;

            monthly = computeMonthlyAmortization(loanAmount, rate, years);
            gmi = monthly / 0.35;

        } else {
            bankBox.setEnabled(false);
            yearsBox.setEnabled(false);
        }

        receiptLabel.setText(
            "<html>" +
            "Contact Price: " + contactPrice + "<br>" +
            "Downpayment (5%): " + downpayment + "<br>" +
            "Reservation Fee: " + reservationFee + "<br>" +
            "Other Fees: " + otherFees + "<br>" +
            "Total Cash Out: " + totalCashOut + "<br>" +
            "Loan Amount: " + loanAmount + "<br>" +
            (installmentOption.isSelected() ? 
                "Monthly Amortization: " + String.format("%.2f", monthly) + "<br>" +
                "Minimum GMI: " + String.format("%.2f", gmi) + "<br>" : 
                "Mode: CASH<br>"
            ) +
            "</html>"
        );
    };

    cashOption.addActionListener(updateReceipt);
    installmentOption.addActionListener(updateReceipt);
    bankBox.addActionListener(updateReceipt);
    yearsBox.addActionListener(updateReceipt);

    // INITIAL COMPUTE
    updateReceipt.actionPerformed(null);

    // ADD COMPONENTS
    panel.add(infoLabel);
    panel.add(cashOption);
    panel.add(installmentOption);
    panel.add(new JLabel("Bank:"));
    panel.add(bankBox);
    panel.add(new JLabel("Years:"));
    panel.add(yearsBox);
    panel.add(receiptLabel);
    
    JButton confirmBtn = new JButton("Confirm Purchase");
    panel.add(confirmBtn);

    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Confirm Purchase",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if(result == JOptionPane.OK_OPTION){
        property.setReservedBy(null);
        property.updateStatus("Buy");
        property.setOwner(customer);
        JOptionPane.showMessageDialog(this, "Purchase Confirmed!");
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

    // --- REPLACED: GroupLayout with FlowLayout for Consistency ---
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

        ThemeEngine.stylePrimaryButton(filterButton);
        
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
        filterBar.add(javax.swing.Box.createHorizontalStrut(10));
        filterBar.add(filterButton);

        topArea.add(filterBar, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        // Put the Book button neatly at the bottom right
        javax.swing.JPanel bottomArea = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));
        bottomArea.setBackground(ThemeEngine.BG_MAIN);
        ThemeEngine.stylePrimaryButton(bookButton);
        bottomArea.add(bookButton);
        PropertiesPanel.add(bottomArea, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    // --- NEW: Fixes the Owned Properties Layout ---
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

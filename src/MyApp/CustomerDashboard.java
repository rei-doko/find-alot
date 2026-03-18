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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        this.setSize(1200, 750);
        
        restructureLayout();
        injectFilters();
        injectOwnedPropertiesPanel();
        
        setLocationRelativeTo(null);
        setupTable();
        
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
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY);

        javax.swing.JLabel logoLabel = new javax.swing.JLabel();
        try {
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

        Dimension btnSize = new Dimension(190, 45);
        JButton[] navButtons = {propertiesPanelButton, ownedPropertiesPanelButton};

        for (JButton btn : navButtons) {
            btn.setMaximumSize(btnSize);
            btn.setPreferredSize(btnSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            NavigatorPanel.add(btn);
            NavigatorPanel.add(javax.swing.Box.createVerticalStrut(15)); 
        }

        NavigatorPanel.add(javax.swing.Box.createVerticalGlue());

        JPanel profileCard = new JPanel();
        profileCard.setLayout(new javax.swing.BoxLayout(profileCard, javax.swing.BoxLayout.Y_AXIS));
        profileCard.setBackground(new java.awt.Color(45, 55, 72)); 
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

        logoutButton.setMaximumSize(btnSize);
        logoutButton.setPreferredSize(btnSize);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        OwnedPropertiesPanel.setBackground(ThemeEngine.BG_MAIN);
        
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); 

        ThemeEngine.stylePrimaryButton(propertiesPanelButton);
        ThemeEngine.stylePrimaryButton(ownedPropertiesPanelButton);
        ThemeEngine.styleSecondaryButton(logoutButton);
        
        logoutButton.setBackground(ThemeEngine.TEXT_PRIMARY);
        logoutButton.setForeground(ThemeEngine.ACCENT_COLOR);

        ThemeEngine.styleTable(propertyTable, jScrollPane1);
        ThemeEngine.styleTable(ownedPropertiesTable, jScrollPane2);

        javax.swing.JTextField[] filterFields = {minPriceField, maxPriceField, minSizeField, maxSizeField};
        for (javax.swing.JTextField field : filterFields) {
            ThemeEngine.styleTextField(field);
        }
    }

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

        propertiesPanelButton.setText("Properties");
        propertiesPanelButton.addActionListener(this::propertiesPanelButtonActionPerformed);
        
        ownedPropertiesPanelButton.setText("Owned Properties");
        ownedPropertiesPanelButton.addActionListener(this::ownedPropertiesPanelButtonActionPerformed);
        
        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        Parent.setLayout(new java.awt.CardLayout());

        propertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Property ID", "Block Number", "Property Number", "Status", "Owner", "Property Price", "Property Size", "Property Floors", "Property Type" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        jScrollPane1.setViewportView(propertyTable);

        bookButton.setText("Book Property");
        bookButton.addActionListener(this::bookButtonActionPerformed);

        blockSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "1", "2", "3", "4", "5" }));
        
        Parent.add(PropertiesPanel, "PropertiesPanel");

        ownedPropertiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Property ID", "Block Number", "Property Number", "Status", "Property Price", "Property Size", "Property Floors", "Type" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        jScrollPane2.setViewportView(ownedPropertiesTable);

        jButton1.setText("Confirm Purchase");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        Parent.add(OwnedPropertiesPanel, "OwnedPropertiesPanel");
    }                        

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Session.logout();
        new Authentication(userManager, propertyManager).setVisible(true);
        this.dispose();
    }                                            

    private void ownedPropertiesPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "OwnedPropertiesPanel");
        loadOwnedPropertiesToTable(); 
    }                                                          

    private void propertiesPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        CardLayout cl = (CardLayout)(Parent.getLayout());
        cl.show(Parent, "PropertiesPanel");
        loadPropertiesToTable(); 
    }                                                     

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
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
    }                                          

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int row = ownedPropertiesTable.getSelectedRow();
        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a property from the table.");
            return;
        }

        int propertyId = (int) ownedPropertiesTable.getValueAt(row, 0);
        final Property property = findPropertyById(propertyId); 

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
        mainPanel.setPreferredSize(new java.awt.Dimension(450, 320)); 

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
        for (int i = 5; i <= 30; i += 5) yearsBox.addItem(i);

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

                double monthly = (isInstallment && yearsBox.getSelectedItem() != null) ? 
                                 computeMonthlyAmortization(loanAmount, rate, (Integer)yearsBox.getSelectedItem()) : 0;
                double gmi = monthly / 0.35;

                String html = "<html><div style='width:250px; font-family:sans-serif;'>" +
                    "<h2 style='color:#2E7D32; margin-bottom:5px;'>Receipt Summary</h2>" +
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
                        "<p style='margin-top:10px; text-align:center; color:#2E7D32;'><b>MODE: FULL CASH</b></p>") +
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
            property.updateStatus("Sold");
            property.setOwner(customer);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Purchase Confirmed!");
            loadPropertiesToTable();
            loadOwnedPropertiesToTable();
        }
    }                                    

    private void injectFilters() {
        PropertiesPanel.removeAll();
        PropertiesPanel.setLayout(new java.awt.BorderLayout(10, 10));
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10));
        topArea.setBackground(ThemeEngine.BG_MAIN);
        
        javax.swing.JLabel title = new javax.swing.JLabel("Available Properties");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        topArea.add(title, java.awt.BorderLayout.NORTH);

        blockSelector.addActionListener(e -> {
            String selected = (String) blockSelector.getSelectedItem();
            if(selected == null || selected.equals("All")) {
                selectedBlock = 0;
            } else {
                selectedBlock = Integer.parseInt(selected);
            }
        });

        javax.swing.JPanel filterBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        filterBar.setBackground(ThemeEngine.BG_MAIN);

        minPriceField.setColumns(7);
        maxPriceField.setColumns(7);
        minSizeField.setColumns(7);
        maxSizeField.setColumns(7);

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

        ThemeEngine.stylePrimaryButton(filterButton);
        ThemeEngine.stylePrimaryButton(bookButton);
        filterButton.addActionListener(e -> loadPropertiesToTable());

        javax.swing.JPanel wrapper = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        wrapper.setBackground(ThemeEngine.BG_MAIN);
        wrapper.add(filterBar, java.awt.BorderLayout.CENTER); 
        wrapper.add(filterButton, java.awt.BorderLayout.EAST); 

        topArea.add(wrapper, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.JPanel bottomArea = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));
        bottomArea.setBackground(ThemeEngine.BG_MAIN);
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

        javax.swing.JPanel buttonBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));
        buttonBar.setBackground(ThemeEngine.BG_MAIN);

        ThemeEngine.stylePrimaryButton(jButton1); 
        buttonBar.add(jButton1);

        OwnedPropertiesPanel.add(buttonBar, java.awt.BorderLayout.SOUTH);

        OwnedPropertiesPanel.revalidate();
        OwnedPropertiesPanel.repaint();
    }

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
    
    private void loadOwnedPropertiesToTable() { 
        DefaultTableModel ownedModel = (DefaultTableModel) ownedPropertiesTable.getModel();
        ownedModel.setRowCount(0);
        
        for(Block block : propertyManager.getAllBlocks()) { 
            for(Property property : block.getProperties()) { 
                
                boolean isOwner = property.getOwner() != null && property.getOwner().equals(this.customer);
                
                if(isOwner) { 
                    String type = property.getClass().getSimpleName();
                    String statusText;
                    
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

    private Double parseDoubleOrNull(String text){
        if (text == null || text.isEmpty()) return null;
        if (!text.matches("\\d+(\\.\\d+)?")) return null;
        return Double.parseDouble(text);
    }
    
    private double computeMonthlyAmortization(double loanAmount, double annualRate, int years) {
        if (loanAmount <= 0 || annualRate <= 0 || years <= 0) return 0;
        double monthlyRate = annualRate / 12;
        int numberOfPayments = years * 12;
        
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) 
               / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    // Variables declaration                     
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
}
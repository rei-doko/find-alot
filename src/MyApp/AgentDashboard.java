package MyApp;

import MyLib.TownHouse;
import MyLib.SemiDetached;
import MyLib.Detached;
import MyLib.Agent;
import MyLib.Block;
import MyLib.Booking;
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

public class AgentDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgentDashboard.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private Agent agent;
    private Property selectedProperty = null;

    private int selectedBlock = 0;
    private javax.swing.JComboBox<String> blockSelector = new javax.swing.JComboBox<>(new String[] { "All", "1", "2", "3", "4", "5" });
    
    private javax.swing.JTextField minPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField minSizeField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxSizeField = new javax.swing.JTextField(7);
    
    private javax.swing.JLabel detailBuyerLabel = new javax.swing.JLabel("Buyer: -");
    private javax.swing.JLabel detailPropertyLabel = new javax.swing.JLabel("Property: -");
    private javax.swing.JLabel detailStatusLabel = new javax.swing.JLabel("Status: -");
    
    public AgentDashboard(UserManager userManager, PropertyManager propertyManager, Agent agent) {
        this.userManager = userManager;
        this.propertyManager = propertyManager;
        this.agent = agent;
        
        initComponents();
        this.setSize(1200, 750);
        
        restructureLayout();
        injectFilters();
        injectRequestPanel(); 
        
        setLocationRelativeTo(null);
        
        setupTable();
        setupRequestTable();
        loadPropertiesToTable();
        loadRequestsToTable(); 
        
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
        JButton[] navButtons = {propertiesButton, requestsButton}; 

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
        profileCard.setMaximumSize(new Dimension(190, 70));
        profileCard.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        profileCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        String userName = (agent != null) ? agent.getUsername().toUpperCase() : "AGENT";
        JLabel nameLabel = new JLabel(userName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLabel = new JLabel("LOGGED IN AS AGENT");
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
        RequestPanel.setBackground(ThemeEngine.BG_MAIN);
        
        NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); 

        ThemeEngine.stylePrimaryButton(propertiesButton);
        ThemeEngine.stylePrimaryButton(requestsButton);
        ThemeEngine.styleSecondaryButton(logoutButton);
        
        logoutButton.setBackground(ThemeEngine.TEXT_PRIMARY);
        logoutButton.setForeground(ThemeEngine.ACCENT_COLOR);

        ThemeEngine.styleTable(propertyTable, jScrollPane1);
        ThemeEngine.styleTable(requestTable, jScrollPane2);

        javax.swing.JTextField[] filterFields = {minPriceField, maxPriceField, minSizeField, maxSizeField};
        for (javax.swing.JTextField field : filterFields) {
            ThemeEngine.styleTextField(field);
        }
    }

    private void initComponents() {
        NavigatorPanel = new javax.swing.JPanel();
        requestsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        propertiesButton = new javax.swing.JButton();
        Parent = new javax.swing.JPanel();
        PropertiesPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        propertyTable = new javax.swing.JTable();
        RequestPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        requestsButton.setText("Requests");
        requestsButton.addActionListener(this::requestsButtonActionPerformed);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        propertiesButton.setText("Properties");
        propertiesButton.addActionListener(this::propertiesButtonActionPerformed);

        Parent.setLayout(new java.awt.CardLayout());

        propertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Property ID", "Block Number", "Property Number", "Status", "Owner", "Property Price", "Property Size", "Property Floors", "Property Type" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        jScrollPane1.setViewportView(propertyTable);
        Parent.add(PropertiesPanel, "PropertiesPanel");

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Property ID", "Block Number", "Property Number", "Status", "Booked By", "Property Price", "Property Size", "Property Floors", "Property Type" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        jScrollPane2.setViewportView(requestTable);
        Parent.add(RequestPanel, "RequestPanel");
    }

    private void requestsButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
       CardLayout cl = (CardLayout)(Parent.getLayout());
       cl.show(Parent, "RequestPanel");
       loadRequestsToTable(); 
    }                                              

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Session.logout();
        new Authentication(userManager, propertyManager).setVisible(true);
        this.dispose();
    }                                            

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       CardLayout cl = (CardLayout)(Parent.getLayout());
       cl.show(Parent, "PropertiesPanel");
       loadPropertiesToTable();
    }                                                

    private void injectFilters() {
        PropertiesPanel.removeAll();
        PropertiesPanel.setLayout(new java.awt.BorderLayout(10, 10));
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10));
        topArea.setBackground(ThemeEngine.BG_MAIN);
        
        javax.swing.JLabel title = new javax.swing.JLabel("Properties");
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
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER); 

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    private void injectRequestPanel() {
        RequestPanel.removeAll();
        RequestPanel.setLayout(new java.awt.BorderLayout(10, 15));
        RequestPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JLabel title = new javax.swing.JLabel("Pending Customer Requests");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        RequestPanel.add(title, java.awt.BorderLayout.NORTH);

        RequestPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER); 
        
        javax.swing.JPanel detailsCard = new javax.swing.JPanel(new java.awt.BorderLayout());
        detailsCard.setBackground(ThemeEngine.BG_PANEL);
        detailsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 231, 235), 2),
            javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        detailsCard.setPreferredSize(new java.awt.Dimension(0, 160));

        javax.swing.JPanel infoGrid = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 10, 10));
        infoGrid.setBackground(ThemeEngine.BG_PANEL);
        
        detailBuyerLabel.setFont(ThemeEngine.FONT_BODY);
        detailPropertyLabel.setFont(ThemeEngine.FONT_BODY);
        detailStatusLabel.setFont(ThemeEngine.FONT_BODY);
        
        infoGrid.add(detailBuyerLabel);
        infoGrid.add(detailPropertyLabel);
        infoGrid.add(detailStatusLabel);
        infoGrid.add(new javax.swing.JLabel(""));

        javax.swing.JPanel buttonBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
        buttonBar.setBackground(ThemeEngine.BG_PANEL);
        
        javax.swing.JButton approveBtn = new javax.swing.JButton("Confirm Reservation");
        javax.swing.JButton rejectBtn = new javax.swing.JButton("Cancel Request");

        ThemeEngine.stylePrimaryButton(approveBtn);
        ThemeEngine.styleSecondaryButton(rejectBtn);

        approveBtn.addActionListener(e -> handleRequest(true));
        rejectBtn.addActionListener(e -> handleRequest(false));

        buttonBar.add(rejectBtn);
        buttonBar.add(approveBtn);

        detailsCard.add(new javax.swing.JLabel("DETAILS"), java.awt.BorderLayout.NORTH);
        detailsCard.add(infoGrid, java.awt.BorderLayout.CENTER);
        detailsCard.add(buttonBar, java.awt.BorderLayout.SOUTH);

        RequestPanel.add(detailsCard, java.awt.BorderLayout.SOUTH);
        
        RequestPanel.revalidate();
        RequestPanel.repaint();
    }

    private Double parseDoubleOrNull(String text){
        if (text == null || text.isEmpty()) return null;
        if (!text.matches("\\d+(\\.\\d+)?")) return null;
        return Double.parseDouble(text);
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

    private void loadRequestsToTable() {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0);

        if (agent.getAllBlocks() == null || agent.getAllBlocks().isEmpty()) {
            return;
        }

        for (Block block : agent.getAllBlocks()) {
            for (Property property : block.getProperties()) {
                
                String status = property.getStatus();
                if (status != null && (status.equalsIgnoreCase("Booked") || status.equalsIgnoreCase("Book"))) {
                    
                    String type = property.getClass().getSimpleName();
                    String ownerName = "Unknown";
                    if (property.getOwner() != null) {
                        ownerName = property.getOwner().getUsername();
                    } else if (property.getReservedBy() != null) {
                        ownerName = property.getReservedBy().getUsername();
                    }
                    
                    model.addRow(new Object[]{
                        property.getPropertyId(),
                        property.getBlockNumber(),
                        property.getPropertyNumber(),
                        "Pending Approval", 
                        ownerName,
                        property.getContactPrice(),
                        property.getPropertySize(),
                        property.getFloors(),
                        type
                    });
                }
            }
        }
        
        detailBuyerLabel.setText("Buyer: -");
        detailPropertyLabel.setText("Property: -");
        detailStatusLabel.setText("Status: -");
    }

    private void handleRequest(boolean isApprove) {
        int selectedRow = requestTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a request from the table first.");
            return;
        }

        int propertyId = (int) requestTable.getValueAt(selectedRow, 0);
        Property selectedReq = agent.getProperty(propertyId);

        if (selectedReq != null && selectedReq.getStatus().equalsIgnoreCase("Booked")) {
            if (isApprove) {
                selectedReq.updateStatus("Buy"); 
                agent.confirmSale(selectedReq, selectedReq.getOwner());
                javax.swing.JOptionPane.showMessageDialog(this, "Sale successfully Approved!");
            } else {
                selectedReq.updateStatus("Available"); 
                selectedReq.setOwner(null); 
                javax.swing.JOptionPane.showMessageDialog(this, "Sale successfully Rejected.");
            }
            
            loadRequestsToTable();
            loadPropertiesToTable();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "This request is no longer valid.");
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
                    selectedProperty = agent.getProperty(propertyId);
                } else {
                    selectedProperty = null;
                }
            }
        });
    }

    private void setupRequestTable() {
        requestTable.setRowSelectionAllowed(true);
        requestTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        requestTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = requestTable.getSelectedRow();
                if (row >= 0) {
                    String block = requestTable.getValueAt(row, 1).toString();
                    String lot = requestTable.getValueAt(row, 2).toString();
                    String status = requestTable.getValueAt(row, 3).toString();
                    String buyer = requestTable.getValueAt(row, 4).toString();

                    detailBuyerLabel.setText("Buyer: " + buyer);
                    detailPropertyLabel.setText("Property: Block " + block + " | Lot " + lot);
                    detailStatusLabel.setText("Status: " + status);
                } else {
                    detailBuyerLabel.setText("Buyer: -");
                    detailPropertyLabel.setText("Property: -");
                    detailStatusLabel.setText("Status: -");
                }
            }
        });
    }

    // Variables declaration                     
    private javax.swing.JPanel NavigatorPanel;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel PropertiesPanel;
    private javax.swing.JPanel RequestPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTable propertyTable;
    private javax.swing.JTable requestTable;
    private javax.swing.JButton requestsButton;               
}
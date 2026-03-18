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
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AgentDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgentDashboard.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private Agent agent;
    private Property selectedProperty = null;
    private Booking selectedBooking = null;

    private int selectedBlock = 0;
    private javax.swing.JComboBox<String> blockSelector = new javax.swing.JComboBox<>(new String[] { "All", "1", "2", "3", "4", "5" });
    
    // Injected textfields
    private javax.swing.JTextField minPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField minSizeField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxSizeField = new javax.swing.JTextField(7);
    
    // Details Pane Labels
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
        
        this.pack(); 
        this.setVisible(true);
    }

    private void restructureLayout() {
    this.getContentPane().removeAll();
    this.getContentPane().setLayout(new java.awt.BorderLayout(0, 0)); 

    NavigatorPanel.setPreferredSize(new java.awt.Dimension(220, 0)); 
    NavigatorPanel.setLayout(new javax.swing.BoxLayout(NavigatorPanel, javax.swing.BoxLayout.Y_AXIS));
    NavigatorPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 20, 15));
    NavigatorPanel.setBackground(ThemeEngine.TEXT_PRIMARY); // Ensures sidebar matches theme

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

    // 6. Logout Button
    logoutButton.setMaximumSize(btnSize);
    logoutButton.setPreferredSize(btnSize);
    logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    NavigatorPanel.add(logoutButton);

    // 7. Attach to Frame
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        removeReservationButton = new javax.swing.JButton();
        confirmBookingButton = new javax.swing.JButton();
        removeBookingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NavigatorPanel.setLayout(null);

        requestsButton.setText("Requests");
        requestsButton.addActionListener(this::requestsButtonActionPerformed);
        NavigatorPanel.add(requestsButton);
        requestsButton.setBounds(30, 120, 170, 60);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);
        NavigatorPanel.add(logoutButton);
        logoutButton.setBounds(30, 680, 170, 60);

        propertiesButton.setText("Properties");
        propertiesButton.addActionListener(this::propertiesButtonActionPerformed);
        NavigatorPanel.add(propertiesButton);
        propertiesButton.setBounds(30, 40, 170, 60);

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

        javax.swing.GroupLayout PropertiesPanelLayout = new javax.swing.GroupLayout(PropertiesPanel);
        PropertiesPanel.setLayout(PropertiesPanelLayout);
        PropertiesPanelLayout.setHorizontalGroup(
            PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        PropertiesPanelLayout.setVerticalGroup(
            PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        Parent.add(PropertiesPanel, "PropertiesPanel");

        jLabel3.setText("Requests");

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Property ID", "Block Number", "Property Number", "Status", "Booked By", "Property Price", "Property Size", "Property Floors", "Property Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(requestTable);

        removeReservationButton.setText("Remove Reservation");
        removeReservationButton.addActionListener(this::removeReservationButtonActionPerformed);

        confirmBookingButton.setText("Confirm Booking");
        confirmBookingButton.addActionListener(this::confirmBookingButtonActionPerformed);

        removeBookingButton.setText("Remove Booking");
        removeBookingButton.addActionListener(this::removeBookingButtonActionPerformed);

        javax.swing.GroupLayout RequestPanelLayout = new javax.swing.GroupLayout(RequestPanel);
        RequestPanel.setLayout(RequestPanelLayout);
        RequestPanelLayout.setHorizontalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RequestPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(removeReservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RequestPanelLayout.createSequentialGroup()
                    .addContainerGap(688, Short.MAX_VALUE)
                    .addComponent(confirmBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)))
            .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RequestPanelLayout.createSequentialGroup()
                    .addContainerGap(686, Short.MAX_VALUE)
                    .addComponent(removeBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        RequestPanelLayout.setVerticalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RequestPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(RequestPanelLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(removeReservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RequestPanelLayout.createSequentialGroup()
                    .addGap(179, 179, 179)
                    .addComponent(confirmBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(539, Short.MAX_VALUE)))
            .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RequestPanelLayout.createSequentialGroup()
                    .addGap(252, 252, 252)
                    .addComponent(removeBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(466, Short.MAX_VALUE)))
        );

        Parent.add(RequestPanel, "RequestPanel");

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

    private void requestsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestsButtonActionPerformed
       CardLayout cl = (CardLayout)(Parent.getLayout());
       cl.show(Parent, "RequestPanel");
       loadRequestsToTable(); 
    }//GEN-LAST:event_requestsButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Session.logout();
        new Authentication(userManager, propertyManager).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesButtonActionPerformed
       CardLayout cl = (CardLayout)(Parent.getLayout());
       cl.show(Parent, "PropertiesPanel");
       loadPropertiesToTable();
    }//GEN-LAST:event_propertiesButtonActionPerformed

    private void injectFilters() {
        PropertiesPanel.removeAll();
        PropertiesPanel.setLayout(new java.awt.BorderLayout(10, 10));
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout(0, 10));
        
        javax.swing.JLabel title = new javax.swing.JLabel("Properties");
        title.setFont(ThemeEngine.FONT_HEADER);
        title.setForeground(ThemeEngine.TEXT_PRIMARY);
        topArea.add(title, java.awt.BorderLayout.NORTH);

        // The blockSelector ActionListener is already set up in your constructor/variables, 
        // but we'll ensure it maps correctly if changed here.
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
        topArea.setBackground(ThemeEngine.BG_MAIN);

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

        topArea.add(filterBar, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER); 
        
        // --- NEW BOTTOM AREA: Anchors the Filter Button to the bottom right ---
        javax.swing.JPanel bottomArea = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 10));
        bottomArea.setBackground(ThemeEngine.BG_MAIN);
        
        javax.swing.JButton filterBtn = new javax.swing.JButton("Filter");
        ThemeEngine.stylePrimaryButton(filterBtn); 
        filterBtn.addActionListener(e -> loadPropertiesToTable());
        
        bottomArea.add(filterBtn);

        PropertiesPanel.add(bottomArea, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }
    private void injectRequestPanel() {
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

        // 1. The Inputs (Left side)
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

        // 2. The Button (Right side)
        javax.swing.JButton filterBtn = new javax.swing.JButton("Filter");
        ThemeEngine.stylePrimaryButton(filterBtn); 
        filterBtn.addActionListener(e -> loadPropertiesToTable());

        // 3. THE FIX: Pin the button to the EAST wall so it can never be pushed down
        javax.swing.JPanel wrapper = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        wrapper.setBackground(ThemeEngine.BG_MAIN);
        wrapper.add(filterBar, java.awt.BorderLayout.CENTER); // Inputs fill the center
        wrapper.add(filterBtn, java.awt.BorderLayout.EAST);   // Button is locked to the right

        topArea.add(wrapper, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER); 

        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    private Double parseDoubleOrNull(String text){
        if (text == null || text.isEmpty()) return null;
        if (!text.matches("\\d+(\\.\\d+)?")) return null;
        return Double.parseDouble(text);
    }
    
    private Booking getSelectedBooking() {
        int selectedRow = requestTable.getSelectedRow();
        if (selectedRow < 0) return null;

        int propertyId = (int) requestTable.getValueAt(selectedRow, 0);
        String customerName = (String) requestTable.getValueAt(selectedRow, 4);

        for (Booking booking : propertyManager.getBookingForAgent(agent)) {
            if (booking.getProperty().getPropertyId() == propertyId &&
                booking.getCustomer().getUsername().equals(customerName)) {
                return booking;
            }
        }

        return null;
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
            System.out.println("DEBUG: Agent " + agent.getUsername() + " has NO blocks assigned!");
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
        
        // Reset the Details Pane so it doesn't show old data
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
    
    private void loadBookingsToTable() {
        DefaultTableModel requestModel = (DefaultTableModel) requestTable.getModel();
        requestModel.setRowCount(0); // Clear existing rows

        for(Booking bookingRequest : propertyManager.getBookingForAgent(agent)) {
            
            Property property = bookingRequest.getProperty();
            String type;
            if (property instanceof TownHouse) {
                type = "Town House";
            } else if (property instanceof SemiDetached) {
                type = "Semi-Detached";
            } else if (property instanceof Detached) {
                type = "Detached";
            } else {
                type = "";
            }

            requestModel.addRow(new Object[] {
                property.getPropertyId(),
                property.getBlockNumber(), 
                property.getPropertyNumber(),   
                property.getStatus(),
                bookingRequest.getCustomer().getUsername(),
                property.getContactPrice(),
                property.getPropertySize(),
                property.getFloors(),
                type
            });
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
    private javax.swing.JPanel NavigatorPanel;
    private javax.swing.JPanel Parent;
    private javax.swing.JPanel PropertiesPanel;
    private javax.swing.JPanel RequestPanel;
    private javax.swing.JButton confirmBookingButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTable propertyTable;
    private javax.swing.JButton removeBookingButton;
    private javax.swing.JButton removeReservationButton;
    private javax.swing.JTable requestTable;
    private javax.swing.JButton requestsButton;
    // End of variables declaration//GEN-END:variables

    private void removeReservationButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void confirmBookingButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void removeBookingButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
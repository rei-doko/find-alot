package MyApp;

import MyLib.Agent;
import MyLib.Block;
import MyLib.Property;
import MyLib.PropertyManager;
import MyLib.Session;
import MyLib.UserManager;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;

public class AgentDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgentDashboard.class.getName());
    private UserManager userManager;
    private PropertyManager propertyManager;
    private Agent agent;
    private Property selectedProperty = null;

    private int selectedBlock = 0;
    private javax.swing.JComboBox<String> blockSelector = new javax.swing.JComboBox<>(new String[] { "All", "1", "2", "3", "4", "5" });
    
    // Injected textfields
    private javax.swing.JTextField minPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxPriceField = new javax.swing.JTextField(7);
    private javax.swing.JTextField minSizeField = new javax.swing.JTextField(7);
    private javax.swing.JTextField maxSizeField = new javax.swing.JTextField(7);
    
    public AgentDashboard(UserManager userManager, PropertyManager propertyManager, Agent agent) {
        this.userManager = userManager;
        this.propertyManager = propertyManager;
        this.agent = agent;
        initComponents();
        
        injectFilters();
        injectRequestButtons(); 
        
        setLocationRelativeTo(null);
        setupTable();
        loadPropertiesToTable();
        loadRequestsToTable(); 
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
        requestTable1 = new javax.swing.JTable();

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

        requestTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(requestTable1);

        javax.swing.GroupLayout RequestPanelLayout = new javax.swing.GroupLayout(RequestPanel);
        RequestPanel.setLayout(RequestPanelLayout);
        RequestPanelLayout.setHorizontalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        RequestPanelLayout.setVerticalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
        PropertiesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout());
        
        javax.swing.JLabel title = new javax.swing.JLabel("Properties");
        title.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        topArea.add(title, java.awt.BorderLayout.NORTH);

        blockSelector.addActionListener(e -> {
            String selected = (String) blockSelector.getSelectedItem();
            if(selected == null || selected.equals("All")) {
                selectedBlock = 0;
            } else {
                selectedBlock = Integer.parseInt(selected);
            }
        });

        javax.swing.JPanel filterBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        filterBar.add(new javax.swing.JLabel("Block:"));
        filterBar.add(blockSelector);
        filterBar.add(new javax.swing.JLabel("Min Price:"));
        filterBar.add(minPriceField);
        filterBar.add(new javax.swing.JLabel("Max Price:"));
        filterBar.add(maxPriceField);
        filterBar.add(new javax.swing.JLabel("Min Size:"));
        filterBar.add(minSizeField);
        filterBar.add(new javax.swing.JLabel("Max Size:"));
        filterBar.add(maxSizeField);

        javax.swing.JButton filterBtn = new javax.swing.JButton("Filter");
        filterBtn.addActionListener(e -> loadPropertiesToTable());
        filterBar.add(filterBtn);

        topArea.add(filterBar, java.awt.BorderLayout.SOUTH);

        PropertiesPanel.add(topArea, java.awt.BorderLayout.NORTH);
        PropertiesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER); 
        
        PropertiesPanel.revalidate();
        PropertiesPanel.repaint();
    }

    private void injectRequestButtons() {
        RequestPanel.removeAll();
        RequestPanel.setLayout(new java.awt.BorderLayout(10, 10));
        RequestPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        javax.swing.JPanel topArea = new javax.swing.JPanel(new java.awt.BorderLayout());
        
        javax.swing.JLabel title = new javax.swing.JLabel("Pending Customer Requests");
        title.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24));
        topArea.add(title, java.awt.BorderLayout.NORTH);

        javax.swing.JPanel buttonBar = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        javax.swing.JButton approveBtn = new javax.swing.JButton("Approve Sale");
        javax.swing.JButton rejectBtn = new javax.swing.JButton("Reject Sale");

        approveBtn.addActionListener(e -> handleRequest(true));
        rejectBtn.addActionListener(e -> handleRequest(false));

        buttonBar.add(approveBtn);
        buttonBar.add(rejectBtn);

        topArea.add(buttonBar, java.awt.BorderLayout.SOUTH);

        RequestPanel.add(topArea, java.awt.BorderLayout.NORTH);
        RequestPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER); 
        
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

    //updated NO CLASSES NEEDED. JUST FINDS "BOOKED"
    private void loadRequestsToTable() {
        DefaultTableModel model = (DefaultTableModel) requestTable1.getModel();
        model.setRowCount(0);

        for (Block block : agent.getAllBlocks()) {
            for (Property property : block.getProperties()) {
                if (property.getStatus().equalsIgnoreCase("Booked")) {
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
    }

    // --- NEW: APPROVE UPDATES THE STATUS TO "SOLD" ---
    private void handleRequest(boolean isApprove) {
        int selectedRow = requestTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a request from the table first.");
            return;
        }

        int propertyId = (int) requestTable1.getValueAt(selectedRow, 0);
        Property selectedReq = agent.getProperty(propertyId);

        if (selectedReq != null && selectedReq.getStatus().equalsIgnoreCase("Booked")) {
            if (isApprove) {
                // Changing to "Buy" runs the Property.java logic to turn it into "Sold"
                selectedReq.updateStatus("Buy"); 
                agent.confirmSale(selectedReq, selectedReq.getOwner());
                javax.swing.JOptionPane.showMessageDialog(this, "Sale successfully Approved!");
            } else {
                selectedReq.updateStatus("Available"); // Resets to available
                selectedReq.setOwner(null); // Removes the customer
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
                    // NO CRASH: Now properly using your team's updated agent.getProperty!
                    selectedProperty = agent.getProperty(propertyId);
                } else {
                    selectedProperty = null;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JTable propertyTable;
    private javax.swing.JTable requestTable1;
    private javax.swing.JButton requestsButton;
    // End of variables declaration//GEN-END:variables
}
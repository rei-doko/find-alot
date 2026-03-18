package MyApp;

import MyLib.Block;
import MyLib.Customer;
import MyLib.Property;
import MyLib.PropertyManager;
import MyLib.Session;
import MyLib.UserManager;
import java.awt.CardLayout;
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
        
        // Custom additions after NetBeans init
        setLocationRelativeTo(null);
        setupTable();
        
        // Manually wiring the Confirm Purchase button since it missed the GUI builder
        jButton1.addActionListener(this::confirmPurchaseActionPerformed);
        
        loadPropertiesToTable();
        loadOwnedPropertiesToTable();
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

        NavigatorPanel.setLayout(null);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(this::logoutButtonActionPerformed);
        NavigatorPanel.add(logoutButton);
        logoutButton.setBounds(30, 680, 170, 60);

        ownedPropertiesPanelButton.setText("Owned Properties");
        ownedPropertiesPanelButton.addActionListener(this::ownedPropertiesPanelButtonActionPerformed);
        NavigatorPanel.add(ownedPropertiesPanelButton);
        ownedPropertiesPanelButton.setBounds(40, 110, 150, 50);

        propertiesPanelButton.setText("Properties");
        propertiesPanelButton.addActionListener(this::propertiesPanelButtonActionPerformed);
        NavigatorPanel.add(propertiesPanelButton);
        propertiesPanelButton.setBounds(40, 30, 150, 50);

        Parent.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Properties");

        propertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookButton, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(blockSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(minPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(maxPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(minSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(maxSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37)
                                .addComponent(filterButton)))))
                .addContainerGap())
        );
        PropertiesPanelLayout.setVerticalGroup(
            PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropertiesPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blockSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(minSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PropertiesPanelLayout.createSequentialGroup()
                        .addComponent(filterButton)
                        .addGap(2, 2, 2)))
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(minPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PropertiesPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(bookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        Parent.add(PropertiesPanel, "PropertiesPanel");

        jLabel1.setText("Owned Properties");

        ownedPropertiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
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

        javax.swing.GroupLayout OwnedPropertiesPanelLayout = new javax.swing.GroupLayout(OwnedPropertiesPanel);
        OwnedPropertiesPanel.setLayout(OwnedPropertiesPanelLayout);
        OwnedPropertiesPanelLayout.setHorizontalGroup(
            OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        OwnedPropertiesPanelLayout.setVerticalGroup(
            OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGroup(OwnedPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OwnedPropertiesPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
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
        loadOwnedPropertiesToTable(); // Refresh the table when opening the panel
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

        // Assign the customer as the owner temporarily and update status
        // This alerts the AgentDashboard that there is a pending request!
        selectedProperty.setOwner(this.customer);
        selectedProperty.updateStatus("Book");
        
        javax.swing.JOptionPane.showMessageDialog(this, "Property Booked! Awaiting Agent Approval.");
        
        // Refresh the UI to reflect changes
        loadPropertiesToTable();
        loadOwnedPropertiesToTable();
    }//GEN-LAST:event_bookButtonActionPerformed

    // Note: If you have a separate Payment screen later, this button can just pop that screen open.
    // Right now, if the Agent approves it, it automatically becomes "Sold".
    private void confirmPurchaseActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = ownedPropertiesTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a booked property from the table to confirm.");
            return;
        }
        
        int propertyId = (int) ownedPropertiesTable.getValueAt(selectedRow, 0);
        Property propToBuy = findPropertyById(propertyId);
        
        if (propToBuy != null && propToBuy.getStatus().equalsIgnoreCase("Booked")) {
            // Customer confirms their intent, but agent still has the final say in the system
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
                    property.getBlockNumber(), // Directly from Property, exactly like Agent
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
                
                if(property.getOwner() != null && property.getOwner().equals(this.customer)) { 
                    String type = property.getClass().getSimpleName();
                    
                    ownedModel.addRow(new Object[] {
                        property.getPropertyId(),
                        property.getBlockNumber(), 
                        property.getPropertyNumber(), 
                        property.getStatus(),
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
package MyApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class ThemeEngine {

    
    // Backgrounds
    public static final Color BG_MAIN = new Color(250, 250, 250);     // Very soft off-white for the main app
    public static final Color BG_PANEL = new Color(255, 255, 255);    // Pure white for cards and sidebars
    
    // Typography
    public static final Color TEXT_PRIMARY = new Color(31, 41, 55);   // Dark Charcoal (easy on the eyes)
    public static final Color TEXT_SECONDARY = new Color(107, 114, 128); // Medium Gray for labels
    
    // The Accent (Terracotta Orange)
    public static final Color ACCENT_COLOR = new Color(226, 125, 96); // #E27D60
    public static final Color ACCENT_HOVER = new Color(209, 105, 75); // Slightly darker for when the mouse hovers

    // --- 2. THE TYPOGRAPHY ---
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBHEADER = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);



    // --- 3. UI INJECTION COMPONENTS ---

    /**
     * Styles a button to look like a primary action (Solid Terracotta, White Text)
     */
    public static void stylePrimaryButton(JButton btn) {
        btn.setFont(FONT_BUTTON);
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- THE ALIGNMENT LOCK ---
        // Forces all buttons to be exactly 180px wide and 40px tall
        java.awt.Dimension rigidSize = new java.awt.Dimension(180, 40);
        btn.setPreferredSize(rigidSize);
        btn.setMinimumSize(rigidSize);
        btn.setMaximumSize(rigidSize);

        // Add Web-Like Hover Effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(ACCENT_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(ACCENT_COLOR);
            }
        });
    }

    /**
     * Styles a button to be a secondary action (Hollow, Terracotta Outline)
     * Now includes a strict size lock for perfect alignment.
     */
    public static void styleSecondaryButton(JButton btn) {
        btn.setFont(FONT_BUTTON);
        btn.setBackground(BG_PANEL);
        btn.setForeground(ACCENT_COLOR);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 2));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- THE ALIGNMENT LOCK ---
        // Matches the primary button perfectly
        java.awt.Dimension rigidSize = new java.awt.Dimension(180, 40);
        btn.setPreferredSize(rigidSize);
        btn.setMinimumSize(rigidSize);
        btn.setMaximumSize(rigidSize);

        // Invert colors on hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(ACCENT_COLOR);
                btn.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BG_PANEL);
                btn.setForeground(ACCENT_COLOR);
            }
        });
    }

    /**
     * Styles a text field to look modern and prevents it from shrinking on small screens.
     */
    public static void styleTextField(javax.swing.JTextField field) {
        field.setFont(FONT_BODY);
        field.setBackground(BG_PANEL);
        field.setForeground(TEXT_PRIMARY);
        
        // This is the magic line that stops them from squishing!
        field.setPreferredSize(new java.awt.Dimension(120, 36)); 
        field.setMinimumSize(new java.awt.Dimension(120, 36));
        
        // Adds a clean, modern gray outline with inner padding
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(209, 213, 219), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    /**
     * 
     */
    public static void styleTable(JTable table, JScrollPane scrollPane) {
        // Table Body Styling
        table.setFont(FONT_BODY);
        table.setForeground(TEXT_PRIMARY);
        table.setBackground(BG_PANEL);
        table.setRowHeight(35); // Gives the text room to breathe
        table.setShowVerticalLines(false); // Removes ugly grid lines
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(229, 231, 235)); // Very faint gray horizontal lines
        table.setSelectionBackground(new Color(254, 235, 227)); // Very light terracotta selection
        table.setSelectionForeground(ACCENT_COLOR);
        
        // Remove borders from the scroll pane holding the table
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(BG_PANEL);

        // Header Styling
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_SUBHEADER);
        header.setBackground(BG_PANEL);
        header.setForeground(TEXT_SECONDARY);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(229, 231, 235))); // Only bottom border
        
        // Custom Renderer to left-align header text and add padding
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
                return this;
            }
        };
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        header.setDefaultRenderer(headerRenderer);
    }
}
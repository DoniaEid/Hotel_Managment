package hotel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import hotelsystem.managers.*;
import hotelsystem.models.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import javax.swing.border.Border;

public class Hotel {

    public static class AppColors {
            public static final Color PRIMARY_DARK = new Color(15, 30, 70); // أزرق أغمق للخلفيات

        public static final Color PRIMARY = new Color(0, 30, 60);
        public static final Color PRIMARY_LIGHT = new Color(10, 50, 90);
        public static final Color PRIMARY_LIGHTER = new Color(20, 70, 120);
        public static final Color DARK = new Color(0, 20, 40);
        public static final Color LIGHT = new Color(200, 220, 240);
        public static final Color WHITE = Color.WHITE;
        public static final Color TEXT_DARK = new Color(0, 20, 40);
        public static final Color TEXT_LIGHT = new Color(60, 80, 100);
        public static final Color GOLD = new Color(255, 193, 7);
        public static final Color DANGER = new Color(180, 30, 30);
        public static final Color SUCCESS = new Color(30, 100, 40);
        public static final Color INFO = new Color(30, 80, 150);
        public static final Color WARNING = new Color(255, 152, 0);
        public static final Color PURPLE = new Color(106, 27, 154);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

class LoginFrame extends JFrame {
    private EmployeeManager employeeManager;

    public LoginFrame() {
        employeeManager = new EmployeeManager();
        try {
            employeeManager.loadFromFile();
        } catch (Exception e) {
            System.out.println("No employees loaded");
        }

        setTitle("Azure Paradise Hotel - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Hotel.AppColors.PRIMARY);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel hotelIcon = new JLabel("HOTEL");
        hotelIcon.setFont(new Font("Arial", Font.BOLD, 80));
        hotelIcon.setForeground(Hotel.AppColors.WHITE);
        hotelIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome to");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Hotel.AppColors.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel hotelNameLabel = new JLabel("AZURE PARADISE");
        hotelNameLabel.setFont(new Font("Arial", Font.BOLD, 36));
        hotelNameLabel.setForeground(Hotel.AppColors.GOLD);
        hotelNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel taglineLabel = new JLabel("Luxury Hotel Management");
        taglineLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        taglineLabel.setForeground(Hotel.AppColors.LIGHT);
        taglineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(hotelIcon);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(welcomeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(hotelNameLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(taglineLabel);
        leftPanel.add(Box.createVerticalGlue());

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Hotel.AppColors.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginTitle = new JLabel("Employee Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 28));
        loginTitle.setForeground(Hotel.AppColors.TEXT_DARK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(loginTitle, gbc);

        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setForeground(Hotel.AppColors.TEXT_DARK);
        gbc.gridy = 1;
        rightPanel.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        idField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridy = 2;
        rightPanel.add(idField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(Hotel.AppColors.TEXT_DARK);
        gbc.gridy = 3;
        rightPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridy = 4;
        rightPanel.add(passwordField, gbc);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBackground(Hotel.AppColors.PRIMARY);
        loginBtn.setForeground(Hotel.AppColors.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 10, 10, 10);
        rightPanel.add(loginBtn, gbc);

        JButton registerBtn = new JButton("Register New Employee");
        registerBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        registerBtn.setForeground(Hotel.AppColors.PRIMARY);
        registerBtn.setBorderPainted(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        rightPanel.add(registerBtn, gbc);

        loginBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(Hotel.AppColors.PRIMARY_LIGHT);
            }

            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(Hotel.AppColors.PRIMARY);
            }
        });

        ActionListener loginAction = e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String password = new String(passwordField.getPassword());

                Employee emp = employeeManager.findEmployeeById(id);
                if (emp != null && emp.getEmployee_password().equals(password)) {
                    JOptionPane.showMessageDialog(this, "Welcome, " + emp.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new HotelMainFrame(emp).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        loginBtn.addActionListener(loginAction);
        passwordField.addActionListener(loginAction);
        registerBtn.addActionListener(e -> new RegistrationDialog(this, employeeManager).setVisible(true));

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }
}

class RegistrationDialog extends JDialog {
    public RegistrationDialog(JFrame parent, EmployeeManager empManager) {
        super(parent, "Register New Employee", true);
        setSize(500, 450);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Hotel.AppColors.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField idField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField jobField = new JTextField(20);

        addField(panel, gbc, "Employee ID:", idField, 0);
        addField(panel, gbc, "Full Name:", nameField, 1);
        addField(panel, gbc, "Phone:", phoneField, 2);
        addField(panel, gbc, "Password:", passwordField, 3);
        addField(panel, gbc, "Job Title:", jobField, 4);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(Hotel.AppColors.PRIMARY);
        registerBtn.setForeground(Hotel.AppColors.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(registerBtn, gbc);

        registerBtn.addActionListener(e -> {
            try {
                Employee emp = new Employee(
                    Integer.parseInt(idField.getText().trim()),
                    nameField.getText().trim(),
                    phoneField.getText().trim(),
                    new String(passwordField.getPassword()),
                    jobField.getText().trim()
                );
                empManager.add(emp);
                JOptionPane.showMessageDialog(this, "Employee registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Hotel.AppColors.TEXT_DARK);
        panel.add(lbl, gbc);
        gbc.gridx = 1;

        if (field instanceof JTextField || field instanceof JPasswordField) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
            ));
        }
        panel.add(field, gbc);
    }
}

class HotelMainFrame extends JFrame {
    private Employee currentEmployee;
    private CustomerManager customerManager;
    private EmployeeManager employeeManager;
    private ServiceManager serviceManager;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private Object[][] roomSampleData = {
        {"101", "Single", "$120.00", "Available", "1st", "2", "WiFi, TV"},
        {"102", "Double", "$180.00", "Occupied", "1st", "4", "WiFi, TV, Mini-bar"},
        {"103", "Suite", "$350.00", "Reserved", "2nd", "3", "WiFi, TV, Jacuzzi"},
        {"104", "Single", "$120.00", "Available", "1st", "2", "WiFi, TV"},
        {"105", "Deluxe", "$280.00", "Occupied", "3rd", "2", "WiFi, TV, Balcony"},
        {"106", "Executive", "$420.00", "Available", "4th", "2", "WiFi, TV, Office"},
        {"201", "Double", "$180.00", "Available", "2nd", "4", "WiFi, TV, Mini-bar"},
        {"202", "Suite", "$350.00", "Maintenance", "2nd", "3", "WiFi, TV, Jacuzzi"},
        {"203", "Single", "$120.00", "Available", "2nd", "2", "WiFi, TV"},
        {"301", "Deluxe", "$280.00", "Occupied", "3rd", "2", "WiFi, TV, Balcony"}
    };

    public HotelMainFrame(Employee employee) {
        this.currentEmployee = employee;
        customerManager = new CustomerManager();
        employeeManager = new EmployeeManager();
        serviceManager = new ServiceManager();
        hotelsystem.managers.RoomManager roomManager = new hotelsystem.managers.RoomManager();
        roomManager.loadFromFile(); 
        customerManager.loadFromFile();
        employeeManager.loadFromFile();
        serviceManager.loadFromFile();

        setTitle("Azure Paradise Hotel - " + employee.getName());
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createDashboard(), "DASHBOARD");
        mainPanel.add(createCustomerPanel(), "CUSTOMER");
        mainPanel.add(createEmployeePanel(), "EMPLOYEE");
        mainPanel.add(createRoomPanel(), "ROOM");
        mainPanel.add(createReservationPanel(), "RESERVATION");
        mainPanel.add(createServicePanel(), "SERVICE");

        add(mainPanel);
        cardLayout.show(mainPanel, "DASHBOARD");
    }

    private JPanel createDashboard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Hotel.AppColors.LIGHT);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Hotel.AppColors.DARK);
        topBar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("AZURE PARADISE DASHBOARD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Hotel.AppColors.WHITE);
        topBar.add(titleLabel, BorderLayout.WEST);

        JLabel userLabel = new JLabel(currentEmployee.getName() + " (" + currentEmployee.getJob_title() + ")");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Hotel.AppColors.WHITE);
        topBar.add(userLabel, BorderLayout.EAST);

        panel.add(topBar, BorderLayout.NORTH);

        JPanel menuGrid = new JPanel(new GridLayout(2, 3, 20, 20));
        menuGrid.setBackground(Hotel.AppColors.LIGHT);
        menuGrid.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        menuGrid.add(createDashboardCard("Customers", "Manage customer records", Hotel.AppColors.PRIMARY, "CUSTOMER"));
        menuGrid.add(createDashboardCard("Employees", "Manage staff members", Hotel.AppColors.PRIMARY_LIGHT, "EMPLOYEE"));
        menuGrid.add(createDashboardCard("Rooms", "Room management", Hotel.AppColors.PRIMARY_LIGHTER, "ROOM"));
        menuGrid.add(createDashboardCard("Reservations", "Booking system", Hotel.AppColors.PRIMARY_LIGHT, "RESERVATION"));
        menuGrid.add(createDashboardCard("Services", "Hotel services", Hotel.AppColors.PRIMARY_LIGHT, "SERVICE"));
        menuGrid.add(createDashboardCard("Logout", "Exit system", Hotel.AppColors.DANGER, "LOGOUT"));

        panel.add(menuGrid, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDashboardCard(String title, String subtitle, Color color, String action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Hotel.AppColors.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 3),
            BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel(subtitle);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subLabel.setForeground(Hotel.AppColors.TEXT_LIGHT);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(subLabel);
        card.add(Box.createVerticalGlue());

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (action.equals("LOGOUT")) {
                    int result = JOptionPane.showConfirmDialog(HotelMainFrame.this, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        dispose();
                        new LoginFrame().setVisible(true);
                    }
                } else {
                    cardLayout.show(mainPanel, action);
                }
            }

            public void mouseEntered(MouseEvent e) {
                card.setBackground(Hotel.AppColors.LIGHT);
            }

            public void mouseExited(MouseEvent e) {
                card.setBackground(Hotel.AppColors.WHITE);
            }
        });

        return card;
    }

    private JPanel createHeader(String title, Color color) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(color);
        header.setPreferredSize(new Dimension(0, 80));
        header.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.WEST);

        JButton backBtn = new JButton("← Back to Dashboard");
        backBtn.setFont(new Font("Arial", Font.BOLD, 12));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(color);
        backBtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "DASHBOARD"));

        header.add(backBtn, BorderLayout.EAST);
        return header;
    }


private JPanel createRoomPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Hotel.AppColors.WHITE);
    panel.add(createHeader("Room Management", Hotel.AppColors.PRIMARY_LIGHTER), BorderLayout.NORTH);

    JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
    contentPanel.setBackground(Hotel.AppColors.WHITE);
    contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JPanel topContainer = new JPanel();
    topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
    topContainer.setBackground(Hotel.AppColors.WHITE);

    // Filter Panel
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    filterPanel.setBackground(Hotel.AppColors.WHITE);
    filterPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHTER, 2),
        " Filter Rooms ",
        0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHTER
    ));
    filterPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

    JLabel statusLabel = new JLabel("Status:");
    statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
    String[] statusOptions = {"All", "Available", "Occupied", "Reserved", "Maintenance"};
    JComboBox<String> statusFilter = new JComboBox<>(statusOptions);
    statusFilter.setBackground(Hotel.AppColors.WHITE);
    statusFilter.setPreferredSize(new Dimension(120, 30));

    JLabel typeLabel = new JLabel("Type:");
    typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
    String[] typeOptions = {"All", "Single", "Double", "Suite", "Deluxe", "Executive"};
    JComboBox<String> typeFilter = new JComboBox<>(typeOptions);
    typeFilter.setBackground(Hotel.AppColors.WHITE);
    typeFilter.setPreferredSize(new Dimension(120, 30));

    JLabel floorLabel = new JLabel("Floor:");
    floorLabel.setFont(new Font("Arial", Font.BOLD, 12));
    String[] floorOptions = {"All", "1st", "2nd", "3rd", "4th"};
    JComboBox<String> floorFilter = new JComboBox<>(floorOptions);
    floorFilter.setBackground(Hotel.AppColors.WHITE);
    floorFilter.setPreferredSize(new Dimension(100, 30));

    JButton filterBtn = createButton("Apply Filter", Hotel.AppColors.PRIMARY_LIGHTER);
    filterBtn.setPreferredSize(new Dimension(120, 30));

    filterPanel.add(statusLabel);
    filterPanel.add(statusFilter);
    filterPanel.add(Box.createRigidArea(new Dimension(10, 0)));
    filterPanel.add(typeLabel);
    filterPanel.add(typeFilter);
    filterPanel.add(Box.createRigidArea(new Dimension(10, 0)));
    filterPanel.add(floorLabel);
    filterPanel.add(floorFilter);
    filterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    filterPanel.add(filterBtn);

    // Form Panel
    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(Hotel.AppColors.WHITE);
    formPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHTER, 2),
        " Room Information ",
        0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHTER
    ));
    formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Form Fields
    JTextField roomNumberField = createTextField();
    JComboBox<String> roomTypeField = new JComboBox<>(new String[]{"Single", "Double", "Suite", "Deluxe", "Executive"});
    roomTypeField.setBackground(Hotel.AppColors.WHITE);
    roomTypeField.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
    roomTypeField.setPreferredSize(new Dimension(200, 30));

    JTextField priceField = createTextField();
    JComboBox<String> statusField = new JComboBox<>(new String[]{"Available", "Occupied", "Reserved", "Maintenance"});
    statusField.setBackground(Hotel.AppColors.WHITE);
    statusField.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
    statusField.setPreferredSize(new Dimension(200, 30));

    JComboBox<String> floorField = new JComboBox<>(new String[]{"1st", "2nd", "3rd", "4th"});
    floorField.setBackground(Hotel.AppColors.WHITE);
    floorField.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
    floorField.setPreferredSize(new Dimension(200, 30));

    JTextField capacityField = createTextField();
    JTextField amenitiesField = createTextField();

    // Form Layout
    addFormRow(formPanel, gbc, 0, "Room Number:", roomNumberField, "Room Type:", roomTypeField);
    addFormRow(formPanel, gbc, 1, "Price per Night:", priceField, "Status:", statusField);
    addFormRow(formPanel, gbc, 2, "Floor:", floorField, "Capacity:", capacityField);
    
    // Amenities row (full width)
    gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.15;
    JLabel amenitiesLabel = new JLabel("Amenities:");
    amenitiesLabel.setFont(new Font("Arial", Font.BOLD, 12));
    formPanel.add(amenitiesLabel, gbc);

    gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 0.85;
    formPanel.add(amenitiesField, gbc);

    // Create buttons
    JButton addBtn = createStyledButton("Add Room", Hotel.AppColors.SUCCESS);
    JButton updateBtn = createStyledButton("Update Room", Hotel.AppColors.PRIMARY);
    JButton deleteBtn = createStyledButton("Delete Room", Hotel.AppColors.DANGER);
    JButton searchBtn = createStyledButton("Search", Hotel.AppColors.INFO);
    JButton clearBtn = createStyledButton("Clear", new Color(96, 125, 139));
    JButton loadBtn = createStyledButton("Load All", Hotel.AppColors.PRIMARY_LIGHTER);

    // Buttons Panel
    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    btnPanel.setBackground(Hotel.AppColors.WHITE);
    btnPanel.add(addBtn);
    btnPanel.add(updateBtn);
    btnPanel.add(deleteBtn);
    btnPanel.add(searchBtn);
    btnPanel.add(clearBtn);
    btnPanel.add(loadBtn);

    gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(btnPanel, gbc);

    topContainer.add(filterPanel);
    topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
    topContainer.add(formPanel);

    // Statistics Panel
    JPanel statsPanel = createStatisticsPanel();

    // Table
    String[] columns = {"Room No.", "Type", "Price", "Status", "Floor", "Capacity", "Amenities"};
    DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JTable roomTable = new JTable(tableModel);
    roomTable.setFont(new Font("Arial", Font.PLAIN, 12));
    roomTable.setRowHeight(30);
    roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    roomTable.getTableHeader().setBackground(Hotel.AppColors.PRIMARY_LIGHTER);
    roomTable.getTableHeader().setForeground(Hotel.AppColors.DARK);
    roomTable.getTableHeader().setReorderingAllowed(false);

    // Table selection listener
    roomTable.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting() && roomTable.getSelectedRow() != -1) {
            int row = roomTable.getSelectedRow();
            roomNumberField.setText(roomTable.getValueAt(row, 0).toString());
            roomTypeField.setSelectedItem(roomTable.getValueAt(row, 1).toString());
            
            String priceText = roomTable.getValueAt(row, 2).toString();
            priceField.setText(priceText.replace("$", "").trim());
            
            statusField.setSelectedItem(roomTable.getValueAt(row, 3).toString());
            floorField.setSelectedItem(roomTable.getValueAt(row, 4).toString());
            capacityField.setText(roomTable.getValueAt(row, 5).toString());
            amenitiesField.setText(roomTable.getValueAt(row, 6).toString());
        }
    });

    JScrollPane scrollPane = new JScrollPane(roomTable);
    scrollPane.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHTER, 2),
        " Room Records (" + tableModel.getRowCount() + " rooms)",
        0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHTER
    ));
    scrollPane.setPreferredSize(new Dimension(1300, 300));

    contentPanel.add(topContainer, BorderLayout.NORTH);
    contentPanel.add(statsPanel, BorderLayout.CENTER);
    contentPanel.add(scrollPane, BorderLayout.SOUTH);
    panel.add(contentPanel, BorderLayout.CENTER);

    // Get references to statistic labels
    JLabel totalRoomsLabel = getStatLabel(statsPanel, 0);
    JLabel availableRoomsLabel = getStatLabel(statsPanel, 1);
    JLabel occupiedRoomsLabel = getStatLabel(statsPanel, 2);
    JLabel revenueLabel = getStatLabel(statsPanel, 3);

    // === إضافة RoomManager ===
    hotelsystem.managers.RoomManager roomManager = new hotelsystem.managers.RoomManager();

    // === Helper Functions ===
    Function<String, Boolean> isValidPrice = priceText -> {
        if (priceText == null || priceText.trim().isEmpty()) {
            return false;
        }
        try {
            String clean = priceText.replaceAll("[^\\d.]", "");
            if (clean.isEmpty()) return false;
            double price = Double.parseDouble(clean);
            return price > 0;
        } catch (Exception ex) {
            return false;
        }
    };

    Function<String, Boolean> isValidCapacity = capacityText -> {
        if (capacityText == null || capacityText.trim().isEmpty()) {
            return false;
        }
        try {
            String clean = capacityText.replaceAll("[^\\d]", "");
            if (clean.isEmpty()) return false;
            int capacity = Integer.parseInt(clean);
            return capacity > 0;
        } catch (Exception ex) {
            return false;
        }
    };

    Function<String, Double> parsePrice = priceText -> {
        try {
            String clean = priceText.replaceAll("[^\\d.]", "");
            return Double.parseDouble(clean);
        } catch (Exception ex) {
            return 0.0;
        }
    };

    Function<String, Integer> parseCapacity = capacityText -> {
        try {
            String clean = capacityText.replaceAll("[^\\d]", "");
            return Integer.parseInt(clean);
        } catch (Exception ex) {
            return 0;
        }
    };

    // === Action Listeners ===
    
    // Load initial data
    roomManager.loadFromFile();
    loadAllRooms(tableModel, roomManager, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);

    // Add Button Action
    addBtn.addActionListener(e -> {
        try {
            String roomNumberStr = roomNumberField.getText().trim();
            String type = (String) roomTypeField.getSelectedItem();
            String priceText = priceField.getText().trim();
            String status = (String) statusField.getSelectedItem();
            String floor = (String) floorField.getSelectedItem();
            String capacityText = capacityField.getText().trim();
            String amenities = amenitiesField.getText().trim();
            
            if (roomNumberStr.isEmpty()) {
                showError("Please enter room number!");
                roomNumberField.requestFocus();
                return;
            }
            
            if (!isValidPrice.apply(priceText)) {
                showError("Please enter a valid price!\nExample: 120.50");
                priceField.requestFocus();
                return;
            }
            
            if (!isValidCapacity.apply(capacityText)) {
                showError("Please enter a valid capacity!\nExample: 2");
                capacityField.requestFocus();
                return;
            }
            
            int roomNumber = Integer.parseInt(roomNumberStr);
            double price = parsePrice.apply(priceText);
            int capacity = parseCapacity.apply(capacityText);
            
            // إنشاء كائن Room جديد
            hotelsystem.models.Room room = new hotelsystem.models.Room(
                roomNumber, type, price, status, floor, capacity, amenities
            );
            
            // === إضافة الغرفة إلى RoomManager ===
            roomManager.addRoom(room);
            
            // إضافة للجدول
            Object[] newRow = {
                String.valueOf(roomNumber),
                type,
                "$" + String.format("%.2f", price),
                status,
                floor,
                String.valueOf(capacity),
                amenities
            };
            
            tableModel.addRow(newRow);
            
            roomNumberField.setText("");
            priceField.setText("");
            capacityField.setText("");
            amenitiesField.setText("");
            
            updateStatistics(tableModel, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
            updateTableTitle(scrollPane, tableModel);
            
            showSuccess("Room " + roomNumber + " added successfully!");
            
        } catch (NumberFormatException ex) {
            showError("Room number must be a valid integer!");
        } catch (Exception ex) {
            showError("Error adding room: " + ex.getMessage());
        }
    });

    // Update Button Action
    updateBtn.addActionListener(e -> {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a room from the table!");
            return;
        }
        
        try {
            String newRoomNumberStr = roomNumberField.getText().trim();
            String oldRoomNumberStr = tableModel.getValueAt(selectedRow, 0).toString();
            String type = (String) roomTypeField.getSelectedItem();
            String priceText = priceField.getText().trim();
            String status = (String) statusField.getSelectedItem();
            String floor = (String) floorField.getSelectedItem();
            String capacityText = capacityField.getText().trim();
            String amenities = amenitiesField.getText().trim();
            
            if (newRoomNumberStr.isEmpty()) {
                showError("Please enter room number!");
                roomNumberField.requestFocus();
                return;
            }
            
            if (!isValidPrice.apply(priceText)) {
                showError("Please enter a valid price!");
                priceField.requestFocus();
                return;
            }
            
            if (!isValidCapacity.apply(capacityText)) {
                showError("Please enter a valid capacity!");
                capacityField.requestFocus();
                return;
            }
            
            int newRoomNumber = Integer.parseInt(newRoomNumberStr);
            int oldRoomNumber = Integer.parseInt(oldRoomNumberStr);
            double price = parsePrice.apply(priceText);
            int capacity = parseCapacity.apply(capacityText);
            
            // إنشاء كائن Room محدث
            hotelsystem.models.Room updatedRoom = new hotelsystem.models.Room(
                newRoomNumber, type, price, status, floor, capacity, amenities
            );
            
            // === تحديث الغرفة في RoomManager ===
            roomManager.updateRoom(updatedRoom, oldRoomNumber);
            
            // تحديث الجدول
            tableModel.setValueAt(String.valueOf(newRoomNumber), selectedRow, 0);
            tableModel.setValueAt(type, selectedRow, 1);
            tableModel.setValueAt("$" + String.format("%.2f", price), selectedRow, 2);
            tableModel.setValueAt(status, selectedRow, 3);
            tableModel.setValueAt(floor, selectedRow, 4);
            tableModel.setValueAt(String.valueOf(capacity), selectedRow, 5);
            tableModel.setValueAt(amenities, selectedRow, 6);
            
            updateStatistics(tableModel, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
            
            showSuccess("Room " + oldRoomNumber + " updated to " + newRoomNumber + " successfully!");
            
        } catch (NumberFormatException ex) {
            showError("Room number must be a valid integer!");
        } catch (Exception ex) {
            showError("Error updating room: " + ex.getMessage());
        }
    });

    // Delete Button Action
    deleteBtn.addActionListener(e -> {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a room to delete!");
            return;
        }
        
        try {
            String roomNumberStr = tableModel.getValueAt(selectedRow, 0).toString();
            int roomNumber = Integer.parseInt(roomNumberStr);
            String status = tableModel.getValueAt(selectedRow, 3).toString();
            
            String message;
            int messageType;
            
            if (status.equals("Occupied") || status.equals("Reserved")) {
                message = "Room " + roomNumber + " is currently " + status + "!\nDelete anyway?";
                messageType = JOptionPane.WARNING_MESSAGE;
            } else {
                message = "Delete room " + roomNumber + "?";
                messageType = JOptionPane.QUESTION_MESSAGE;
            }
            
            int confirm = JOptionPane.showConfirmDialog(panel, message, "Confirm Delete", 
                JOptionPane.YES_NO_OPTION, messageType);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            // === حذف الغرفة من RoomManager ===
            roomManager.deleteRoom(roomNumber);
            
            tableModel.removeRow(selectedRow);
            
            roomNumberField.setText("");
            priceField.setText("");
            capacityField.setText("");
            amenitiesField.setText("");
            
            updateStatistics(tableModel, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
            updateTableTitle(scrollPane, tableModel);
            
            showSuccess("Room " + roomNumber + " deleted successfully!");
            
        } catch (Exception ex) {
            showError("Error deleting room: " + ex.getMessage());
        }
    });

    // Search Button Action
    searchBtn.addActionListener(e -> {
        String searchNumberStr = roomNumberField.getText().trim();
        if (searchNumberStr.isEmpty()) {
            showError("Please enter a room number to search!");
            roomNumberField.requestFocus();
            return;
        }

        try {
            int searchNumber = Integer.parseInt(searchNumberStr);
            
            // البحث في RoomManager
            hotelsystem.models.Room foundRoom = roomManager.findRoomByNumber(searchNumber);
            
            if (foundRoom != null) {
                // البحث في الجدول لعرضه
                boolean found = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String roomNoStr = tableModel.getValueAt(i, 0).toString();
                    int roomNo = Integer.parseInt(roomNoStr);
                    if (roomNo == searchNumber) {
                        roomTable.setRowSelectionInterval(i, i);
                        roomTable.scrollRectToVisible(roomTable.getCellRect(i, 0, true));
                        
                        roomNumberField.setText(String.valueOf(foundRoom.getRoomNumber()));
                        roomTypeField.setSelectedItem(foundRoom.getType());
                        priceField.setText(String.format("%.2f", foundRoom.getPrice()));
                        statusField.setSelectedItem(foundRoom.getStatus());
                        floorField.setSelectedItem(foundRoom.getFloor());
                        capacityField.setText(String.valueOf(foundRoom.getCapacity()));
                        amenitiesField.setText(foundRoom.getAmenities());
                        
                        found = true;
                        showSuccess("Room " + searchNumber + " found!");
                        break;
                    }
                }
            } else {
                showError("Room " + searchNumber + " not found!");
            }
        } catch (NumberFormatException ex) {
            showError("Please enter a valid room number!");
        }
    });

    // Filter Button Action
    filterBtn.addActionListener(e -> {
        String selectedStatus = (String) statusFilter.getSelectedItem();
        String selectedType = (String) typeFilter.getSelectedItem();
        String selectedFloor = (String) floorFilter.getSelectedItem();
        
        applyFilter(tableModel, roomManager, selectedStatus, selectedType, selectedFloor);
        updateStatistics(tableModel, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
        updateTableTitle(scrollPane, tableModel);
    });

    // Clear Button Action
    clearBtn.addActionListener(e -> {
        roomNumberField.setText("");
        priceField.setText("");
        capacityField.setText("");
        amenitiesField.setText("");
        
        statusField.setSelectedIndex(0);
        roomTypeField.setSelectedIndex(0);
        floorField.setSelectedIndex(0);
        
        roomTable.clearSelection();
        
        statusFilter.setSelectedIndex(0);
        typeFilter.setSelectedIndex(0);
        floorFilter.setSelectedIndex(0);
        
        loadAllRooms(tableModel, roomManager, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
        
        showSuccess("All fields cleared!");
    });

    // Load Button Action
    loadBtn.addActionListener(e -> {
        roomManager.loadFromFile();
        loadAllRooms(tableModel, roomManager, totalRoomsLabel, availableRoomsLabel, occupiedRoomsLabel, revenueLabel);
        updateTableTitle(scrollPane, tableModel);
        
        statusFilter.setSelectedIndex(0);
        typeFilter.setSelectedIndex(0);
        floorFilter.setSelectedIndex(0);
        
        showSuccess("All rooms loaded successfully!");
    });

    return panel;
}
private void loadAllRooms(DefaultTableModel tableModel, hotelsystem.managers.RoomManager roomManager, 
        JLabel totalLabel, JLabel availableLabel, JLabel occupiedLabel, JLabel revenueLabel) {
    tableModel.setRowCount(0);
    
    try {
        roomManager.loadFromFile();
        
        // جلب الغرف مباشرة
        for (hotelsystem.models.Room room : roomManager.getAllRooms()) {
            Object[] row = {
                String.valueOf(room.getRoomNumber()),
                room.getType(),
                "$" + String.format("%.2f", room.getPrice()),
                room.getStatus(),
                room.getFloor(),
                String.valueOf(room.getCapacity()),
                room.getAmenities()
            };
            tableModel.addRow(row);
        }
        
        updateStatistics(tableModel, totalLabel, availableLabel, occupiedLabel, revenueLabel);
        
    } catch (Exception e) {
        e.printStackTrace();
        showError("Error: " + e.getMessage());
    }
}

private void applyFilter(DefaultTableModel tableModel, hotelsystem.managers.RoomManager roomManager, 
        String status, String type, String floor) {
    tableModel.setRowCount(0);
    
    try {
        // فلترة مباشرة بدون متغير
        for (hotelsystem.models.Room room : roomManager.filterRooms(type, status, floor)) {
            Object[] row = {
                String.valueOf(room.getRoomNumber()),
                room.getType(),
                "$" + String.format("%.2f", room.getPrice()),
                room.getStatus(),
                room.getFloor(),
                String.valueOf(room.getCapacity()),
                room.getAmenities()
            };
            tableModel.addRow(row);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        showError("Filter error: " + e.getMessage());
    }
}
private JButton createStyledButton(String text, Color bgColor) {
    JButton button = createButton(text, Color.WHITE);
    button.setBackground(bgColor);
    button.setOpaque(true);
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(120, 35));
    return button;
}

private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label1, JComponent field1, 
                       String label2, JComponent field2) {
    gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.weightx = 0.3;
    JLabel label1Comp = new JLabel(label1);
    label1Comp.setFont(new Font("Arial", Font.BOLD, 12));
    panel.add(label1Comp, gbc);
    
    gbc.gridx = 1; gbc.weightx = 0.7;
    panel.add(field1, gbc);
    
    gbc.gridx = 2; gbc.weightx = 0.3;
    JLabel label2Comp = new JLabel(label2);
    label2Comp.setFont(new Font("Arial", Font.BOLD, 12));
    panel.add(label2Comp, gbc);
    
    gbc.gridx = 3; gbc.weightx = 0.7;
    panel.add(field2, gbc);
}

private JPanel createStatisticsPanel() {
    JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
    panel.setBackground(Hotel.AppColors.LIGHT);
    panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
    
    panel.add(createStatLabel("Total Rooms", "0", Hotel.AppColors.PRIMARY));
    panel.add(createStatLabel("Available", "0", Hotel.AppColors.SUCCESS));
    panel.add(createStatLabel("Occupied", "0", Hotel.AppColors.DANGER));
    panel.add(createStatLabel("Daily Revenue", "$0", Hotel.AppColors.GOLD));
    
    return panel;
}

private JLabel getStatLabel(JPanel statsPanel, int index) {
    JLabel containerLabel = (JLabel) statsPanel.getComponent(index);
    JPanel statPanel = (JPanel) containerLabel.getComponent(0);
    return (JLabel) statPanel.getComponent(2);
}

// === دوال إضافية ===

private void updateStatistics(DefaultTableModel tableModel, JLabel totalLabel, JLabel availableLabel,
        JLabel occupiedLabel, JLabel revenueLabel) {
    int total = tableModel.getRowCount();
    int available = 0;
    int occupied = 0;
    double dailyRevenue = 0;

    for (int i = 0; i < total; i++) {
        String status = tableModel.getValueAt(i, 3).toString();
        String priceStr = tableModel.getValueAt(i, 2).toString();
        
        double price = 0;
        try {
            String cleanPrice = priceStr.replace("$", "").replace(",", "").trim();
            price = Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            price = 0;
        }

        if (status.equals("Available")) {
            available++;
        } else if (status.equals("Occupied") || status.equals("Reserved")) {
            occupied++;
            dailyRevenue += price;
        }
    }

    totalLabel.setText(String.valueOf(total));
    availableLabel.setText(String.valueOf(available));
    occupiedLabel.setText(String.valueOf(occupied));
    revenueLabel.setText("$" + String.format("%.2f", dailyRevenue));
}

private JLabel createStatLabel(String title, String value, Color color) {
    JPanel statPanel = new JPanel();
    statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
    statPanel.setBackground(Hotel.AppColors.WHITE);
    statPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(color, 2),
        BorderFactory.createEmptyBorder(10, 15, 10, 15)
    ));

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
    titleLabel.setForeground(Hotel.AppColors.TEXT_DARK);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel valueLabel = new JLabel(value);
    valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
    valueLabel.setForeground(color);
    valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    statPanel.add(titleLabel);
    statPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    statPanel.add(valueLabel);

    JLabel container = new JLabel();
    container.setLayout(new BorderLayout());
    container.add(statPanel, BorderLayout.CENTER);

    return container;
}

private void updateTableTitle(JScrollPane scrollPane, DefaultTableModel tableModel) {
    Border border = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHTER, 2),
        " Room Records (" + tableModel.getRowCount() + " rooms)",
        0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHTER
    );
    scrollPane.setBorder(border);
}
    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Hotel.AppColors.WHITE);
        panel.add(createHeader("Customer Management", Hotel.AppColors.PRIMARY_LIGHT), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Hotel.AppColors.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Hotel.AppColors.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            " Customer Information ",
            0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHT
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField custIdField = createTextField();
        JTextField nameField = createTextField();
        JTextField phoneField = createTextField();
        JTextField emailField = createTextField();

        // Form Layout
        addFormRow(formPanel, gbc, "Customer ID:", custIdField, 0);
        addFormRow(formPanel, gbc, "Full Name:", nameField, 1);
        addFormRow(formPanel, gbc, "Phone Number:", phoneField, 2);
        addFormRow(formPanel, gbc, "Email:", emailField, 3);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(Hotel.AppColors.WHITE);

        JButton viewAllBtn = createButton("Load All", Hotel.AppColors.PRIMARY_LIGHTER);
        JButton addBtn = createButton("Add Service", Color.WHITE); // نص أبيض
        addBtn.setBackground(Hotel.AppColors.SUCCESS); // خلفية سوداء
        addBtn.setOpaque(true);
        addBtn.setBorderPainted(false);

        JButton updateBtn = createButton("Update Service", Color.WHITE);
        updateBtn.setBackground(Hotel.AppColors.PRIMARY);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);

        JButton deleteBtn = createButton("Delete Service", Color.WHITE);
        deleteBtn.setBackground(Hotel.AppColors.DANGER);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);

        JButton searchBtn = createButton("Search", Color.white);
        searchBtn.setBackground(Hotel.AppColors.INFO);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);

        JButton clearBtn = createButton("Clear", Color.WHITE);
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setOpaque(true);
        clearBtn.setBorderPainted(false);

        JButton loadBtn = createButton("Load All", Color.WHITE);
        loadBtn.setBackground(new Color(96, 125, 139));
        loadBtn.setOpaque(true);
        loadBtn.setBorderPainted(false);
        addBtn.setPreferredSize(new Dimension(130, 35));
        updateBtn.setPreferredSize(new Dimension(130, 35));
        deleteBtn.setPreferredSize(new Dimension(130, 35));
        searchBtn.setPreferredSize(new Dimension(100, 35));
        viewAllBtn.setPreferredSize(new Dimension(100, 35));
        clearBtn.setPreferredSize(new Dimension(100, 35));

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(viewAllBtn);
        btnPanel.add(clearBtn);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        contentPanel.add(formPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Phone", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable customerTable = new JTable(tableModel);
        customerTable.setFont(new Font("Arial", Font.PLAIN, 13));
        customerTable.setRowHeight(25);
        customerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        customerTable.getTableHeader().setBackground(Hotel.AppColors.PRIMARY_LIGHT);
        customerTable.getTableHeader().setForeground(Hotel.AppColors.DARK);
        customerTable.getTableHeader().setReorderingAllowed(false);

        customerTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && customerTable.getSelectedRow() != -1) {
                int row = customerTable.getSelectedRow();
                custIdField.setText(customerTable.getValueAt(row, 0).toString());
                nameField.setText(customerTable.getValueAt(row, 1).toString());
                phoneField.setText(customerTable.getValueAt(row, 2).toString());
                emailField.setText(customerTable.getValueAt(row, 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            " Customer Records (" + tableModel.getRowCount() + " customers)",
            0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY_LIGHT
        ));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(contentPanel, BorderLayout.CENTER);

        loadCustomersToTable(tableModel);

        // Action Listeners
        addBtn.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(custIdField.getText().trim());
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    showError("Please fill all required fields!");
                    return;
                }

                Customer customer = new Customer(custId, name, phone, email);
                
                customerManager.add(customer);
                
                loadCustomersToTable(tableModel);
                
                
                clearCustomerFields(custIdField, nameField, phoneField, emailField);
                
                showSuccess("Customer added successfully!");

            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric value for Customer ID!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a customer to update!");
                return;
            }

            try {
                int oldCustId = (int) tableModel.getValueAt(selectedRow, 0);
                int newCustId = Integer.parseInt(custIdField.getText().trim());
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                Customer newCustomer = new Customer(newCustId, name, phone, email);
                
                customerManager.update(newCustomer, oldCustId);
                
                loadCustomersToTable(tableModel);
                
                showSuccess("Customer updated successfully!");

            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric value for Customer ID!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a customer to delete!");
                return;
            }

            int custId = (int) tableModel.getValueAt(selectedRow, 0);
            String name = tableModel.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(panel,
                "Delete customer " + name + " (ID: " + custId + ")?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    customerManager.delete(custId);
                    
                    // Refresh table
                    loadCustomersToTable(tableModel);
                    
                    clearCustomerFields(custIdField, nameField, phoneField, emailField);
                    
                    showSuccess("Customer deleted successfully!");
                    
                } catch (Exception ex) {
                    showError(ex.getMessage());
                }
            }
        });

        searchBtn.addActionListener(e -> {
            String searchValue = custIdField.getText().trim();
            if (searchValue.isEmpty()) {
                showError("Please enter a Customer ID to search!");
                return;
            }

            try {
                int custId = Integer.parseInt(searchValue);
                
                Customer customer = customerManager.findCustomerById(custId);
                
                if (customer != null) {
                    custIdField.setText(String.valueOf(customer.getId()));
                    nameField.setText(customer.getName());
                    phoneField.setText(customer.getPhone_number());
                    emailField.setText(customer.getEmail());
                    
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if ((int) tableModel.getValueAt(i, 0) == custId) {
                            customerTable.setRowSelectionInterval(i, i);
                            customerTable.scrollRectToVisible(customerTable.getCellRect(i, 0, true));
                            break;
                        }
                    }
                    
                    showSuccess("Customer found!");
                } else {
                    showError("Customer not found!");
                }

            } catch (NumberFormatException ex) {
                showError("Please enter a valid Customer ID!");
            }
        });

        viewAllBtn.addActionListener(e -> {
            customerManager.loadFromFile();
            loadCustomersToTable(tableModel);
            showSuccess("All customers loaded!");
        });

        clearBtn.addActionListener(e -> {
            clearCustomerFields(custIdField, nameField, phoneField, emailField);
            customerTable.clearSelection();
        });

        return panel;
    }

    private void loadCustomersToTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        customerManager.loadFromFile();
        
        java.util.List<Customer> customers = customerManager.getAllCustomers();
        for (Customer c : customers) {
            Object[] row = {
                c.getId(),
                c.getName(),
                c.getPhone_number(),
                c.getEmail()
            };
            tableModel.addRow(row);
        }
    }

    private JPanel createEmployeePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Hotel.AppColors.WHITE);
        panel.add(createHeader("Employee Management", Hotel.AppColors.DARK), BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Hotel.AppColors.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setBackground(Hotel.AppColors.WHITE);
        
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(Hotel.AppColors.WHITE);
        filterPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_DARK, 2),
            " Filter Employees ",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.PRIMARY_DARK
        ));
        filterPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel jobLabel = new JLabel("Job Title:");
        jobLabel.setFont(new Font("Arial", Font.BOLD, 12));
        String[] jobOptions = {"All", "Manager", "Receptionist", "Housekeeping", "Security", "Maintenance"};
        JComboBox<String> jobFilter = new JComboBox<>(jobOptions);
        jobFilter.setBackground(Hotel.AppColors.WHITE);
        jobFilter.setPreferredSize(new Dimension(120, 30));
        
        JButton filterBtn = createButton("Apply Filter", Hotel.AppColors.PRIMARY_DARK);
        filterBtn.setPreferredSize(new Dimension(120, 30));
        
        filterPanel.add(jobLabel);
        filterPanel.add(jobFilter);
        filterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        filterPanel.add(filterBtn);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Hotel.AppColors.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.DARK, 2),
            " Employee Information ",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.DARK
        ));
        formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Form Fields
        JTextField empIdField = createTextField();
        JTextField nameField = createTextField();
        JTextField phoneField = createTextField();
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        String[] jobTitles = {"Manager", "Receptionist", "Housekeeping", "Security", "Maintenance"};
        JComboBox<String> jobField = new JComboBox<>(jobTitles);
        jobField.setBackground(Hotel.AppColors.WHITE);
        jobField.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
        jobField.setPreferredSize(new Dimension(200, 30));
        
        // Form Layout
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(idLabel, gbc);
        
        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(empIdField, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.3;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 3; gbc.weightx = 0.7;
        formPanel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(phoneLabel, gbc);
        
        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(phoneField, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.3;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(passLabel, gbc);
        
        gbc.gridx = 3; gbc.weightx = 0.7;
        formPanel.add(passwordField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        JLabel jobLabelForm = new JLabel("Job Title:");
        jobLabelForm.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(jobLabelForm, gbc);
        
        gbc.gridx = 1; gbc.weightx = 0.7; gbc.gridwidth = 3;
        formPanel.add(jobField, gbc);
        
        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(Hotel.AppColors.WHITE);
     JButton addBtn = createButton("Add Employee", Color.WHITE);
addBtn.setBackground(Hotel.AppColors.SUCCESS);
addBtn.setOpaque(true);
addBtn.setBorderPainted(false);

        JButton updateBtn = createButton("Update Employee", Color.WHITE);
        updateBtn.setBackground(Hotel.AppColors.PRIMARY);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);

        JButton deleteBtn = createButton("Delete Employee", Color.WHITE);
        deleteBtn.setBackground(Hotel.AppColors.DANGER);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);

        JButton searchBtn = createButton("Search", Color.WHITE);
        searchBtn.setBackground(Hotel.AppColors.INFO);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);

        JButton clearBtn = createButton("Clear", Color.WHITE);
        clearBtn.setBackground(new Color(96, 125, 139));
        clearBtn.setOpaque(true);
        clearBtn.setBorderPainted(false);

        JButton loadBtn = createButton("Load All", Color.WHITE);
        loadBtn.setBackground(Hotel.AppColors.PRIMARY_LIGHTER);
        loadBtn.setOpaque(true);
        loadBtn.setBorderPainted(false);
        addBtn.setPreferredSize(new Dimension(130, 35));
        updateBtn.setPreferredSize(new Dimension(130, 35));
        deleteBtn.setPreferredSize(new Dimension(130, 35));
        searchBtn.setPreferredSize(new Dimension(100, 35));
        clearBtn.setPreferredSize(new Dimension(100, 35));
        loadBtn.setPreferredSize(new Dimension(100, 35));
        
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(loadBtn);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(btnPanel, gbc);
        
        topContainer.add(filterPanel);
        topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        topContainer.add(formPanel);
        
        // Statistics Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(Hotel.AppColors.LIGHT);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        JLabel totalEmpLabel = createStatLabel("Total Employees", "0", Hotel.AppColors.PRIMARY_DARK);
        JLabel managersLabel = createStatLabel("Managers", "0", Hotel.AppColors.PRIMARY);
        JLabel receptionistsLabel = createStatLabel("Receptionists", "0", Hotel.AppColors.INFO);
        JLabel othersLabel = createStatLabel("Other Staff", "0", Hotel.AppColors.GOLD);
        
        statsPanel.add(totalEmpLabel);
        statsPanel.add(managersLabel);
        statsPanel.add(receptionistsLabel);
        statsPanel.add(othersLabel);
        
        // Table
        String[] columns = {"ID", "Name", "Phone", "Job Title", "Password"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("Arial", Font.PLAIN, 12));
        employeeTable.setRowHeight(30);
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        employeeTable.getTableHeader().setBackground(Hotel.AppColors.PRIMARY_DARK);
        employeeTable.getTableHeader().setForeground(Hotel.AppColors.DARK);
        employeeTable.getTableHeader().setReorderingAllowed(false);
        
        // Selection Listener
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1) {
                int row = employeeTable.getSelectedRow();
                empIdField.setText(employeeTable.getValueAt(row, 0).toString());
                nameField.setText(employeeTable.getValueAt(row, 1).toString());
                phoneField.setText(employeeTable.getValueAt(row, 2).toString());
                jobField.setSelectedItem(employeeTable.getValueAt(row, 3).toString());
                passwordField.setText(employeeTable.getValueAt(row, 4).toString());
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_DARK, 2),
            " Employee Records (" + tableModel.getRowCount() + " employees)",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.PRIMARY_DARK
        ));
        scrollPane.setPreferredSize(new Dimension(1300, 300));
        
        contentPanel.add(topContainer, BorderLayout.NORTH);
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        contentPanel.add(scrollPane, BorderLayout.SOUTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        loadAllEmployees(tableModel, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
        
        filterBtn.addActionListener(e -> {
            String selectedJob = (String) jobFilter.getSelectedItem();
            applyEmployeeFilter(tableModel, selectedJob, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
        });
        
        addBtn.addActionListener(e -> {
            try {
                String idText = empIdField.getText().trim();
                if (idText.isEmpty()) {
                    showError("Please enter Employee ID!");
                    return;
                }
                
                int empId = Integer.parseInt(idText);
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String password = new String(passwordField.getPassword());
                String jobTitle = (String) jobField.getSelectedItem();
                
                if (name.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    showError("Please fill all fields!");
                    return;
                }
                
                // Create employee object
                Employee employee = new Employee(empId, name, phone, password, jobTitle);
                
                // Add using EmployeeManager
                employeeManager.add(employee);
                
                // Refresh table
                loadAllEmployees(tableModel, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
                updateEmployeeTableTitle(scrollPane, tableModel);
                
                // Clear fields
                clearEmployeeFields(empIdField, nameField, phoneField, passwordField);
                showSuccess("Employee added successfully!");
                
            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric value for Employee ID!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
        
        updateBtn.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select an employee to update!");
                return;
            }
            
            try {
                int oldEmpId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                int newEmpId = Integer.parseInt(empIdField.getText().trim());
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String password = new String(passwordField.getPassword());
                String jobTitle = (String) jobField.getSelectedItem();
                
                // Create updated employee object
                Employee updatedEmployee = new Employee(newEmpId, name, phone, password, jobTitle);
                
                // Update using EmployeeManager
                employeeManager.update(updatedEmployee, oldEmpId);
                
                // Refresh table
                loadAllEmployees(tableModel, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
                updateEmployeeTableTitle(scrollPane, tableModel);
                
                showSuccess("Employee updated successfully!");
                
            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric values!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
        
        deleteBtn.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select an employee to delete!");
                return;
            }
            
            int empId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String name = tableModel.getValueAt(selectedRow, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(
                panel,
                "Delete employee " + name + " (ID: " + empId + ")?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    employeeManager.delete(empId);
                    loadAllEmployees(tableModel, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
                    updateEmployeeTableTitle(scrollPane, tableModel);
                    clearEmployeeFields(empIdField, nameField, phoneField, passwordField);
                    showSuccess("Employee deleted successfully!");
                } catch (Exception ex) {
                    showError(ex.getMessage());
                }
            }
        });
        
        searchBtn.addActionListener(e -> {
            String searchText = empIdField.getText().trim();
            if (searchText.isEmpty()) {
                showError("Please enter an Employee ID to search!");
                return;
            }
            
            try {
                int empId = Integer.parseInt(searchText);
                Employee employee = employeeManager.findEmployeeById(empId);
                
                if (employee != null) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (Integer.parseInt(tableModel.getValueAt(i, 0).toString()) == empId) {
                            employeeTable.setRowSelectionInterval(i, i);
                            employeeTable.scrollRectToVisible(employeeTable.getCellRect(i, 0, true));
                            showSuccess("Employee found!");
                            return;
                        }
                    }
                }
                showError("Employee not found!");
                
            } catch (NumberFormatException ex) {
                showError("Please enter a valid Employee ID!");
            }
        });
        
        clearBtn.addActionListener(e -> {
            clearEmployeeFields(empIdField, nameField, phoneField, passwordField);
            jobField.setSelectedIndex(0);
            employeeTable.clearSelection();
        });
        
        loadBtn.addActionListener(e -> {
            loadAllEmployees(tableModel, totalEmpLabel, managersLabel, receptionistsLabel, othersLabel);
            updateEmployeeTableTitle(scrollPane, tableModel);
            showSuccess("All employees loaded!");
        });
        
        return panel;
    }

    // Employee Panel Helper Methods
    private void loadAllEmployees(DefaultTableModel tableModel, JLabel totalLabel, 
                                 JLabel managersLabel, JLabel receptionistsLabel, JLabel othersLabel) {
        tableModel.setRowCount(0);
        employeeManager.loadFromFile();
        java.util.List<Employee> employees = employeeManager.getAllEmployees();
        
        for (Employee emp : employees) {
            Object[] row = {
                emp.getId(),
                emp.getName(),
                emp.getPhone_number(),
                emp.getJob_title(),
                emp.getEmployee_password()
            };
            tableModel.addRow(row);
        }
        
        updateEmployeeStatistics(tableModel, totalLabel, managersLabel, receptionistsLabel, othersLabel);
    }

    private void applyEmployeeFilter(DefaultTableModel tableModel, String jobFilter, 
                                    JLabel totalLabel, JLabel managersLabel, 
                                    JLabel receptionistsLabel, JLabel othersLabel) {
        tableModel.setRowCount(0);
        java.util.List<Employee> allEmployees = employeeManager.getAllEmployees();
        
        for (Employee emp : allEmployees) {
            if (jobFilter.equals("All") || emp.getJob_title().equals(jobFilter)) {
                Object[] row = {
                    emp.getId(),
                    emp.getName(),
                    emp.getPhone_number(),
                    emp.getJob_title(),
                    emp.getEmployee_password()
                };
                tableModel.addRow(row);
            }
        }
        
        updateEmployeeStatistics(tableModel, totalLabel, managersLabel, receptionistsLabel, othersLabel);
    }

    private void updateEmployeeStatistics(DefaultTableModel tableModel, JLabel totalLabel,
                                         JLabel managersLabel, JLabel receptionistsLabel, 
                                         JLabel othersLabel) {
        int total = tableModel.getRowCount();
        int managers = 0;
        int receptionists = 0;
        int others = 0;
        
        for (int i = 0; i < total; i++) {
            String jobTitle = tableModel.getValueAt(i, 3).toString();
            if (jobTitle.equals("Manager")) {
                managers++;
            } else if (jobTitle.equals("Receptionist")) {
                receptionists++;
            } else {
                others++;
            }
        }
        
        totalLabel.setText(String.valueOf(total));
        managersLabel.setText(String.valueOf(managers));
        receptionistsLabel.setText(String.valueOf(receptionists));
        othersLabel.setText(String.valueOf(others));
    }

    private void clearEmployeeFields(JTextField empIdField, JTextField nameField, 
                                     JTextField phoneField, JPasswordField passwordField) {
        empIdField.setText("");
        nameField.setText("");
        phoneField.setText("");
        passwordField.setText("");
    }

    private void updateEmployeeTableTitle(JScrollPane scrollPane, DefaultTableModel tableModel) {
        Border border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_DARK, 2),
            " Employee Records (" + tableModel.getRowCount() + " employees)",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.PRIMARY_DARK
        );
        scrollPane.setBorder(border);
    }

    private JPanel createReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Hotel.AppColors.WHITE);
        panel.add(createHeader("Reservation Management", Hotel.AppColors.PRIMARY), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Hotel.AppColors.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Hotel.AppColors.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY, 2),
            " Reservation Information ",
            0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField resIdField = createTextField();
        JTextField custIdField = createTextField();
        JTextField custNameField = createTextField();
        JTextField roomField = createTextField();
        JTextField checkInField = createTextField();
        JTextField checkOutField = createTextField();
        JTextField priceField = createTextField();
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Confirmed", "Pending", "Checked-In", "Cancelled", "Completed"});
        
        statusCombo.setBackground(Hotel.AppColors.WHITE);
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        statusCombo.setBorder(BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2));
        checkInField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        // Form Layout
        addFormRow(formPanel, gbc, "Reservation ID:", resIdField, 0);
        addFormRow(formPanel, gbc, "Customer ID:", custIdField, 1);
        addFormRow(formPanel, gbc, "Customer Name:", custNameField, 2);
        addFormRow(formPanel, gbc, "Room Number:", roomField, 3);
        addFormRow(formPanel, gbc, "Check-in Date:", checkInField, 4);
        addFormRow(formPanel, gbc, "Check-out Date:", checkOutField, 5);
        addFormRow(formPanel, gbc, "Total Price:", priceField, 6);
        addFormRow(formPanel, gbc, "Status:", statusCombo, 7);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(Hotel.AppColors.WHITE);
        JButton addBtn = createButton("Add Employee", Color.WHITE);
        addBtn.setBackground(Hotel.AppColors.SUCCESS);
        addBtn.setOpaque(true);
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);

        JButton updateBtn = createButton("Update Employee", Color.WHITE);
        updateBtn.setBackground(Hotel.AppColors.PRIMARY);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);
        updateBtn.setFocusPainted(false);

        JButton deleteBtn = createButton("Delete Employee", Color.WHITE);
        deleteBtn.setBackground(Hotel.AppColors.DANGER);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusPainted(false);

// View button بعد Delete
        JButton viewBtn = createButton("View Employee", Color.WHITE);
        viewBtn.setBackground(new Color(32, 201, 151)); // لون أزرق/أخضر
        viewBtn.setOpaque(true);
        viewBtn.setBorderPainted(false);
        viewBtn.setFocusPainted(false);

        JButton searchBtn = createButton("Search", Color.WHITE);
        searchBtn.setBackground(Hotel.AppColors.INFO);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);
        searchBtn.setFocusPainted(false);

        JButton clearBtn = createButton("Clear", Color.WHITE);
        clearBtn.setBackground(new Color(96, 125, 139));
        clearBtn.setOpaque(true);
        clearBtn.setBorderPainted(false);
        clearBtn.setFocusPainted(false);

        JButton cancelBtn = createButton("Cancel", Color.WHITE);
        cancelBtn.setBackground(new Color(108, 117, 125)); // رمادي
        cancelBtn.setOpaque(true);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setFocusPainted(false);

        JButton loadBtn = createButton("Load All", Color.WHITE);
        loadBtn.setBackground(Hotel.AppColors.PRIMARY_LIGHTER);
        loadBtn.setOpaque(true);
        loadBtn.setBorderPainted(false);
        loadBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(150, 35));
        updateBtn.setPreferredSize(new Dimension(150, 35));
        deleteBtn.setPreferredSize(new Dimension(150, 35));
        cancelBtn.setPreferredSize(new Dimension(100, 35));
        searchBtn.setPreferredSize(new Dimension(100, 35));
        viewBtn.setPreferredSize(new Dimension(100, 35));
        clearBtn.setPreferredSize(new Dimension(100, 35));

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(cancelBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(viewBtn);
        btnPanel.add(clearBtn);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        contentPanel.add(formPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Res ID", "Cust ID", "Customer", "Room", "Check-In", "Check-Out", "Status", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable reservationTable = new JTable(tableModel);
        reservationTable.setFont(new Font("Arial", Font.PLAIN, 13));
        reservationTable.setRowHeight(25);
        reservationTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        reservationTable.getTableHeader().setBackground(Hotel.AppColors.PRIMARY);
        reservationTable.getTableHeader().setForeground(Hotel.AppColors.DARK);
        reservationTable.getTableHeader().setReorderingAllowed(false);

        // Selection Listener
        reservationTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && reservationTable.getSelectedRow() != -1) {
                int row = reservationTable.getSelectedRow();
                resIdField.setText(reservationTable.getValueAt(row, 0).toString());
                custIdField.setText(reservationTable.getValueAt(row, 1).toString());
                custNameField.setText(reservationTable.getValueAt(row, 2).toString());
                roomField.setText(reservationTable.getValueAt(row, 3).toString());
                checkInField.setText(reservationTable.getValueAt(row, 4).toString());
                checkOutField.setText(reservationTable.getValueAt(row, 5).toString());
                statusCombo.setSelectedItem(reservationTable.getValueAt(row, 6).toString());
                priceField.setText(reservationTable.getValueAt(row, 7).toString().replace("$", "").trim());
            }
        });

        JScrollPane scrollPane = new JScrollPane(reservationTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY, 2),
            " Reservation Records (" + tableModel.getRowCount() + " reservations)",
            0, 0, new Font("Arial", Font.BOLD, 14), Hotel.AppColors.PRIMARY
        ));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(contentPanel, BorderLayout.CENTER);

        // Load initial data
        loadReservationsFromFile(tableModel);

        addBtn.addActionListener(e -> {
            try {
                int resId = Integer.parseInt(resIdField.getText().trim());
                int custId = Integer.parseInt(custIdField.getText().trim());
                String custName = custNameField.getText().trim();
                String room = roomField.getText().trim();
                String checkIn = checkInField.getText().trim();
                String checkOut = checkOutField.getText().trim();
                String status = (String) statusCombo.getSelectedItem();
                double price = Double.parseDouble(priceField.getText().trim());

                if (custName.isEmpty() || room.isEmpty()) {
                    showError("Please fill all required fields!");
                    return;
                }

                if (reservationExists(resId, tableModel)) {
                    showError("Reservation ID already exists!");
                    return;
                }

                Object[] newRow = {resId, custId, custName, room, checkIn, checkOut, status, "$" + String.format("%.2f", price)};
                tableModel.addRow(newRow);

                saveReservationToFile(resId, custId, custName, room, checkIn, checkOut, status, price);

                clearReservationFields(resIdField, custIdField, custNameField, roomField, checkInField, checkOutField, priceField);
                statusCombo.setSelectedIndex(0);

                showSuccess("Reservation added successfully!");

            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric values!");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = reservationTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a reservation to update!");
                return;
            }

            try {
                int newResId = Integer.parseInt(resIdField.getText().trim());
                int oldResId = (int) tableModel.getValueAt(selectedRow, 0);

                if (newResId != oldResId && reservationExists(newResId, tableModel)) {
                    showError("Reservation ID already exists!");
                    return;
                }

                int custId = Integer.parseInt(custIdField.getText().trim());
                String custName = custNameField.getText().trim();
                String room = roomField.getText().trim();
                String checkIn = checkInField.getText().trim();
                String checkOut = checkOutField.getText().trim();
                String status = (String) statusCombo.getSelectedItem();
                double price = Double.parseDouble(priceField.getText().trim());
                tableModel.setValueAt(newResId, selectedRow, 0);
                tableModel.setValueAt(custId, selectedRow, 1);
                tableModel.setValueAt(custName, selectedRow, 2);
                tableModel.setValueAt(room, selectedRow, 3);
                tableModel.setValueAt(checkIn, selectedRow, 4);
                tableModel.setValueAt(checkOut, selectedRow, 5);
                tableModel.setValueAt(status, selectedRow, 6);
                tableModel.setValueAt("$" + String.format("%.2f", price), selectedRow, 7);
                updateReservationInFile(oldResId, newResId, custId, custName, room, checkIn, checkOut, status, price);

                showSuccess("Reservation updated successfully!");

            } catch (NumberFormatException ex) {
                showError("Please enter valid numeric values!");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = reservationTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a reservation to delete!");
                return;
            }

            int resId = (int) tableModel.getValueAt(selectedRow, 0);
            String custName = tableModel.getValueAt(selectedRow, 2).toString();

            int confirm = JOptionPane.showConfirmDialog(panel,
                "Delete reservation for " + custName + " (ID: " + resId + ")?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                deleteReservationFromFile(resId);

                clearReservationFields(resIdField, custIdField, custNameField, roomField, checkInField, checkOutField, priceField);
                statusCombo.setSelectedIndex(0);

                showSuccess("Reservation deleted successfully!");
            }
        });

        cancelBtn.addActionListener(e -> {
            int selectedRow = reservationTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a reservation to cancel!");
                return;
            }

            int resId = (int) tableModel.getValueAt(selectedRow, 0);
            
            int confirm = JOptionPane.showConfirmDialog(panel,
                "Cancel reservation #" + resId + "?",
                "Confirm Cancel", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.setValueAt("Cancelled", selectedRow, 6);
                updateReservationStatusInFile(resId, "Cancelled");
                showSuccess("Reservation cancelled!");
            }
        });

        searchBtn.addActionListener(e -> {
            String searchValue = resIdField.getText().trim();
            if (searchValue.isEmpty()) {
                showError("Please enter a Reservation ID to search!");
                return;
            }

            try {
                int resId = Integer.parseInt(searchValue);
                
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if ((int) tableModel.getValueAt(i, 0) == resId) {
                        reservationTable.setRowSelectionInterval(i, i);
                        reservationTable.scrollRectToVisible(reservationTable.getCellRect(i, 0, true));
                        showSuccess("Reservation found!");
                        return;
                    }
                }
                
                showError("Reservation not found!");

            } catch (NumberFormatException ex) {
                showError("Please enter a valid Reservation ID!");
            }
        });

         viewBtn.addActionListener(e -> {
            loadReservationsFromFile(tableModel);
            showSuccess("All reservations loaded from file!");
        });

        clearBtn.addActionListener(e -> {
            clearReservationFields(resIdField, custIdField, custNameField, roomField, checkInField, checkOutField, priceField);
            statusCombo.setSelectedIndex(0);
            reservationTable.clearSelection();
        });

        return panel;
    }

    private JPanel createServicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Hotel.AppColors.DARK);
        panel.add(createHeader("Service Management", Hotel.AppColors.PRIMARY_DARK), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Hotel.AppColors.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setBackground(Hotel.AppColors.WHITE);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(Hotel.AppColors.WHITE);
        filterPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_DARK, 2),
            " Filter Services ",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.DARK
        ));
        filterPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel statusLabel = new JLabel("Availability:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        String[] statusOptions = {"All", "Available", "Unavailable"};
        JComboBox<String> statusFilter = new JComboBox<>(statusOptions);
        statusFilter.setBackground(Hotel.AppColors.WHITE);
        statusFilter.setPreferredSize(new Dimension(120, 30));

        JButton filterBtn = createButton("Apply Filter", Hotel.AppColors.PRIMARY_LIGHT);
        filterBtn.setPreferredSize(new Dimension(120, 30));

        filterPanel.add(statusLabel);
        filterPanel.add(statusFilter);
        filterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        filterPanel.add(filterBtn);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Hotel.AppColors.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            " Service Information ",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.DARK
        ));
        formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JTextField nameField = createTextField();
        JTextField costField = createTextField();
        JTextField descriptionField = createTextField();
        JCheckBox availableCheckBox = new JCheckBox("Available");
        availableCheckBox.setFont(new Font("Arial", Font.BOLD, 12));
        availableCheckBox.setBackground(Hotel.AppColors.WHITE);
        availableCheckBox.setSelected(true);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel nameLabel = new JLabel("Service Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7; gbc.gridwidth = 3;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3; gbc.gridwidth = 1;
        JLabel costLabel = new JLabel("Cost ($):");
        costLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(costLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(costField, gbc);

        gbc.gridx = 2; gbc.weightx = 0.3;
        JLabel availableLabel = new JLabel("Status:");
        availableLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(availableLabel, gbc);

        gbc.gridx = 3; gbc.weightx = 0.7;
        formPanel.add(availableCheckBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.15;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.85;
        formPanel.add(descriptionField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(Hotel.AppColors.WHITE);
        JButton addBtn = createButton("Add Service", Color.WHITE);
        addBtn.setBackground(Hotel.AppColors.SUCCESS);
        addBtn.setOpaque(true);
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);

        JButton updateBtn = createButton("Update Service", Color.WHITE);
        updateBtn.setBackground(Hotel.AppColors.PRIMARY);
        updateBtn.setOpaque(true);
        updateBtn.setBorderPainted(false);
        updateBtn.setFocusPainted(false);

        JButton deleteBtn = createButton("Delete Service", Color.WHITE);
        deleteBtn.setBackground(Hotel.AppColors.DANGER);
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusPainted(false);

        JButton searchBtn = createButton("Search", Color.WHITE);
        searchBtn.setBackground(Hotel.AppColors.INFO);
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);
        searchBtn.setFocusPainted(false);

        JButton clearBtn = createButton("Clear", Color.WHITE);
        clearBtn.setBackground(new Color(96, 125, 139));
        clearBtn.setOpaque(true);
        clearBtn.setBorderPainted(false);
        clearBtn.setFocusPainted(false);

        JButton loadBtn = createButton("Load All", Color.WHITE);
        loadBtn.setBackground(Hotel.AppColors.PRIMARY_LIGHTER);
        loadBtn.setOpaque(true);
        loadBtn.setBorderPainted(false);
        loadBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(130, 35));
        updateBtn.setPreferredSize(new Dimension(130, 35));
        deleteBtn.setPreferredSize(new Dimension(130, 35));
        searchBtn.setPreferredSize(new Dimension(100, 35));
        clearBtn.setPreferredSize(new Dimension(100, 35));
        loadBtn.setPreferredSize(new Dimension(100, 35));

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(loadBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(btnPanel, gbc);

        topContainer.add(filterPanel);
        topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        topContainer.add(formPanel);

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(Hotel.AppColors.LIGHT);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel totalServicesLabel = createStatLabel("Total Services", "0", Hotel.AppColors.PURPLE);
        JLabel availableServicesLabel = createStatLabel("Available", "0", Hotel.AppColors.SUCCESS);
        JLabel unavailableServicesLabel = createStatLabel("Unavailable", "0", Hotel.AppColors.DANGER);
        JLabel avgCostLabel = createStatLabel("Avg Cost", "$0", Hotel.AppColors.GOLD);

        statsPanel.add(totalServicesLabel);
        statsPanel.add(availableServicesLabel);
        statsPanel.add(unavailableServicesLabel);
        statsPanel.add(avgCostLabel);

        String[] columns = {"Service Name", "Cost", "Description", "Available"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable serviceTable = new JTable(tableModel);
        serviceTable.setFont(new Font("Arial", Font.PLAIN, 12));
        serviceTable.setRowHeight(30);
        serviceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        serviceTable.getTableHeader().setBackground(Hotel.AppColors.PURPLE);
        serviceTable.getTableHeader().setForeground(Hotel.AppColors.DARK);
        serviceTable.getTableHeader().setReorderingAllowed(false);

        serviceTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && serviceTable.getSelectedRow() != -1) {
                int row = serviceTable.getSelectedRow();
                nameField.setText(serviceTable.getValueAt(row, 0).toString());
                costField.setText(serviceTable.getValueAt(row, 1).toString().replace("$", ""));
                descriptionField.setText(serviceTable.getValueAt(row, 2).toString());
                availableCheckBox.setSelected(serviceTable.getValueAt(row, 3).toString().equals("true"));
            }
        });

        JScrollPane scrollPane = new JScrollPane(serviceTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.DARK, 2),
            " Service Records (" + tableModel.getRowCount() + " services)",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.PURPLE
        ));
        scrollPane.setPreferredSize(new Dimension(1300, 300));

        contentPanel.add(topContainer, BorderLayout.NORTH);
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        contentPanel.add(scrollPane, BorderLayout.SOUTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        // Load initial data
        loadAllServices(tableModel, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);

        // Action Listeners
        filterBtn.addActionListener(e -> {
            String selectedStatus = (String) statusFilter.getSelectedItem();
            applyServiceFilter(tableModel, selectedStatus, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);
        });

        addBtn.addActionListener(e -> {
            try {
                String serviceName = nameField.getText().trim();
                if (serviceName.isEmpty()) {
                    showError("Please enter service name!");
                    return;
                }

                String costText = costField.getText().trim();
                if (costText.isEmpty()) {
                    showError("Please enter cost!");
                    return;
                }
                double cost = Double.parseDouble(costText);
                String description = descriptionField.getText().trim();
                boolean available = availableCheckBox.isSelected();

                // Check if service already exists
                if (serviceExists(serviceName, tableModel)) {
                    showError("Service already exists!");
                    return;
                }

                // Create service object
                Service service = new Service(serviceName, cost, description, available);
                
                serviceManager.addService(service);
                
                loadAllServices(tableModel, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);
                updateServiceTableTitle(scrollPane, tableModel);
                
                clearServiceFields(nameField, costField, descriptionField);
                availableCheckBox.setSelected(true);
                
                showSuccess("Service added successfully!");

            } catch (NumberFormatException ex) {
                showError("Service add successfully!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = serviceTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a service to update!");
                return;
            }

            try {
                String oldServiceName = tableModel.getValueAt(selectedRow, 0).toString();
                String newServiceName = nameField.getText().trim();
                double cost = Double.parseDouble(costField.getText().trim());
                String description = descriptionField.getText().trim();
                boolean available = availableCheckBox.isSelected();

                Service oldService = new Service(oldServiceName, 0, "", true);
                Service newService = new Service(newServiceName, cost, description, available);
                
                serviceManager.updateService(oldService, newService);
                
                loadAllServices(tableModel, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);
                updateServiceTableTitle(scrollPane, tableModel);
                
                showSuccess("Service updated successfully!");

            } catch (NumberFormatException ex) {
                showError("Service updated successfully!");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = serviceTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a service to delete!");
                return;
            }

            String serviceName = tableModel.getValueAt(selectedRow, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(panel,
                "Delete service '" + serviceName + "'?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    serviceManager.removeService(serviceName);
                    loadAllServices(tableModel, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);
                    updateServiceTableTitle(scrollPane, tableModel);
                    clearServiceFields(nameField, costField, descriptionField);
                    availableCheckBox.setSelected(true);
                    showSuccess("Service deleted successfully!");
                } catch (Exception ex) {
                    showError(ex.getMessage());
                }
            }
        });

        searchBtn.addActionListener(e -> {
            String searchText = nameField.getText().trim();
            if (searchText.isEmpty()) {
                showError("Please enter a service name to search!");
                return;
            }

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(searchText)) {
                    serviceTable.setRowSelectionInterval(i, i);
                    serviceTable.scrollRectToVisible(serviceTable.getCellRect(i, 0, true));
                    showSuccess("Service found!");
                    return;
                }
            }
            showError("Service not found!");
        });

        clearBtn.addActionListener(e -> {
            clearServiceFields(nameField, costField, descriptionField);
            availableCheckBox.setSelected(true);
            serviceTable.clearSelection();
        });

        loadBtn.addActionListener(e -> {
            loadAllServices(tableModel, totalServicesLabel, availableServicesLabel, unavailableServicesLabel, avgCostLabel);
            updateServiceTableTitle(scrollPane, tableModel);
            showSuccess("All services loaded!");
        });

        return panel;
    }

    // Service Panel Helper Methods
    private void loadAllServices(DefaultTableModel tableModel, JLabel totalLabel, JLabel availableLabel,
                                JLabel unavailableLabel, JLabel avgCostLabel) {
        tableModel.setRowCount(0);
        serviceManager.loadFromFile();
        java.util.List<Service> services = serviceManager.getAllServices();
        
        for (Service s : services) {
            Object[] row = {
                s.getName(),
                "$" + String.format("%.2f", s.getCost()),
                s.getDescription(),
                s.isAvailable()
            };
            tableModel.addRow(row);
        }
        
        updateServiceStatistics(tableModel, totalLabel, availableLabel, unavailableLabel, avgCostLabel);
    }

    private void applyServiceFilter(DefaultTableModel tableModel, String statusFilter,
                                   JLabel totalLabel, JLabel availableLabel,
                                   JLabel unavailableLabel, JLabel avgCostLabel) {
        tableModel.setRowCount(0);
        java.util.List<Service> allServices = serviceManager.getAllServices();
        
        for (Service s : allServices) {
            if (statusFilter.equals("All") || 
                (statusFilter.equals("Available") && s.isAvailable()) ||
                (statusFilter.equals("Unavailable") && !s.isAvailable())) {
                Object[] row = {
                    s.getName(),
                    "$" + String.format("%.2f", s.getCost()),
                    s.getDescription(),
                    s.isAvailable()
                };
                tableModel.addRow(row);
            }
        }
        
        updateServiceStatistics(tableModel, totalLabel, availableLabel, unavailableLabel, avgCostLabel);
    }

    private void updateServiceStatistics(DefaultTableModel tableModel, JLabel totalLabel,
                                        JLabel availableLabel, JLabel unavailableLabel,
                                        JLabel avgCostLabel) {
        int total = tableModel.getRowCount();
        int available = 0;
        int unavailable = 0;
        double totalCost = 0;

        for (int i = 0; i < total; i++) {
            boolean isAvailable = (boolean) tableModel.getValueAt(i, 3);
            double cost = Double.parseDouble(tableModel.getValueAt(i, 1).toString().replace("$", ""));
            
            if (isAvailable) {
                available++;
            } else {
                unavailable++;
            }
            totalCost += cost;
        }

        totalLabel.setText(String.valueOf(total));
        availableLabel.setText(String.valueOf(available));
        unavailableLabel.setText(String.valueOf(unavailable));
        double avgCost = total > 0 ? totalCost / total : 0;
        avgCostLabel.setText("$" + String.format("%.2f", avgCost));
    }

    private boolean serviceExists(String serviceName, DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(serviceName)) {
                return true;
            }
        }
        return false;
    }

    private void clearServiceFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void updateServiceTableTitle(JScrollPane scrollPane, DefaultTableModel tableModel) {
        Border border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PURPLE, 2),
            " Service Records (" + tableModel.getRowCount() + " services)",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            Hotel.AppColors.PURPLE
        );
        scrollPane.setBorder(border);
    }

    // Reservation Helper Methods
    private void loadReservationsFromFile(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    tableModel.addRow(new Object[]{
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]), 
                        parts[2], 
                        parts[3], 
                        parts[4], 
                        parts[5], 
                        parts[6], 
                        "$" + parts[7] 
                    });
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reservations file not found, starting with empty list.");
        } catch (IOException e) {
            showError("Error reading reservations file: " + e.getMessage());
        }
    }

    private void saveReservationToFile(int resId, int custId, String custName, String room,
                                      String checkIn, String checkOut, String status, double price) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt", true))) {
            writer.write(resId + "," + custId + "," + custName + "," + room + "," +
                         checkIn + "," + checkOut + "," + status + "," + String.format("%.2f", price));
            writer.newLine();
        } catch (IOException e) {
            showError("Error saving reservation: " + e.getMessage());
        }
    }

    private void updateReservationInFile(int oldId, int newId, int custId, String custName, String room,
                                        String checkIn, String checkOut, String status, double price) {
        File inputFile = new File("reservations.txt");
        File tempFile = new File("reservations_temp.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && Integer.parseInt(parts[0]) == oldId) {
                    writer.write(newId + "," + custId + "," + custName + "," + room + "," +
                                checkIn + "," + checkOut + "," + status + "," + String.format("%.2f", price));
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            
        } catch (IOException e) {
            showError("Error updating reservation: " + e.getMessage());
            return;
        }
        
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    private void updateReservationStatusInFile(int resId, String newStatus) {
        File inputFile = new File("reservations.txt");
        File tempFile = new File("reservations_temp.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8 && Integer.parseInt(parts[0]) == resId) {
                    parts[6] = newStatus;
                    writer.write(String.join(",", parts));
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            
        } catch (IOException e) {
            showError("Error updating reservation status: " + e.getMessage());
            return;
        }
        
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    private void deleteReservationFromFile(int resId) {
        File inputFile = new File("reservations.txt");
        File tempFile = new File("reservations_temp.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && Integer.parseInt(parts[0]) != resId) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
        } catch (IOException e) {
            showError("Error deleting reservation: " + e.getMessage());
            return;
        }
        
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    private boolean reservationExists(int resId, DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == resId) {
                return true;
            }
        }
        return false;
    }

    // Helper Methods
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Hotel.AppColors.PRIMARY_LIGHT, 2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return textField;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String label, JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setForeground(Hotel.AppColors.TEXT_DARK);
        panel.add(lbl, gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        panel.add(component, gbc);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void clearCustomerFields(JTextField custIdField, JTextField nameField,
                                    JTextField phoneField, JTextField emailField) {
        custIdField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private void clearReservationFields(JTextField resIdField, JTextField custIdField,
                                       JTextField custNameField, JTextField roomField,
                                       JTextField checkInField, JTextField checkOutField,
                                       JTextField priceField) {
        resIdField.setText("");
        custIdField.setText("");
        custNameField.setText("");
        roomField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
        priceField.setText("");
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
 

}
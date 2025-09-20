package coffeeshopmanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainGUI extends JFrame {
    private Order currentOrder;
    private JButton btnAddCoffee, btnAddTea, btnShowOrder, btnRemove;
    private JTextField tfQuantity;
    private DefaultListModel<String> listModel;
    private JList<String> orderList;
    private JTextArea receiptArea;

    public MainGUI() {
        super("Coffee Shop Manager");
        currentOrder = new Order();

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font textAreaFont = new Font("Consolas", Font.PLAIN, 14);

        btnAddCoffee = new JButton("Add Coffee (60 Baht)");
        btnAddCoffee.setFont(btnFont);
        btnAddCoffee.setBackground(new Color(121, 85, 72));
        btnAddCoffee.setForeground(Color.WHITE);

        btnAddTea = new JButton("Add Tea (40 Baht)");
        btnAddTea.setFont(btnFont);
        btnAddTea.setBackground(new Color(67, 160, 71));
        btnAddTea.setForeground(Color.WHITE);

        btnShowOrder = new JButton("Show Receipt");
        btnShowOrder.setFont(btnFont);
        btnShowOrder.setBackground(new Color(33, 150, 243));
        btnShowOrder.setForeground(Color.WHITE);

        btnRemove = new JButton("Remove Selected");
        btnRemove.setFont(btnFont);
        btnRemove.setBackground(new Color(244, 67, 54));
        btnRemove.setForeground(Color.WHITE);

        tfQuantity = new JTextField("1", 3);
        tfQuantity.setFont(labelFont);
        tfQuantity.setHorizontalAlignment(JTextField.CENTER);

        listModel = new DefaultListModel<>();
        orderList = new JList<>(listModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setFont(labelFont);
        orderList.setFixedCellHeight(30);

        receiptArea = new JTextArea(15, 30);
        receiptArea.setEditable(false);
        receiptArea.setFont(textAreaFont);
        receiptArea.setBackground(new Color(250, 244, 236));
        receiptArea.setBorder(BorderFactory.createLineBorder(new Color(121, 85, 72), 2));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(245, 240, 230));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(new JLabel("Quantity:") {{ setFont(labelFont); }});
        topPanel.add(tfQuantity);
        topPanel.add(btnAddCoffee);
        topPanel.add(btnAddTea);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        middlePanel.setBackground(new Color(245, 240, 230));
        middlePanel.add(new JScrollPane(orderList), BorderLayout.CENTER);
        middlePanel.add(btnRemove, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 240, 230));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(btnShowOrder);

        Container container = getContentPane();
        container.setBackground(new Color(245, 240, 230));
        container.setLayout(new BorderLayout(10, 10));
        container.add(topPanel, BorderLayout.NORTH);
        container.add(middlePanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);
        container.add(new JScrollPane(receiptArea), BorderLayout.EAST);

        btnAddCoffee.addActionListener(e -> addItemToOrder("Coffee", 60));
        btnAddTea.addActionListener(e -> addItemToOrder("Tea", 40));

        btnRemove.addActionListener(e -> {
            int selectedIndex = orderList.getSelectedIndex();
            if (selectedIndex != -1) {
                currentOrder.removeItemByIndex(selectedIndex);
                refreshOrderList();
                receiptArea.setText(currentOrder.getOrderSummary());
            } else {
                JOptionPane.showMessageDialog(this, "Please select an item to remove.");
            }
        });

        btnShowOrder.addActionListener(e -> receiptArea.setText(currentOrder.getOrderSummary()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addItemToOrder(String name, double price) {
        try {
            int qty = Integer.parseInt(tfQuantity.getText());
            if (qty <= 0) throw new NumberFormatException();

            MenuItem item = name.equals("Coffee") ? new Coffee(name, price) : new Tea(name, price);
            currentOrder.addItem(item, qty);
            tfQuantity.setText("1"); // reset quantity
            refreshOrderList();
            receiptArea.setText(currentOrder.getOrderSummary());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive integer for quantity.");
        }
    }

    private void refreshOrderList() {
        listModel.clear();
        for (OrderItem oi : currentOrder.getOrderItems()) {
            listModel.addElement(oi.getQuantity() + " x " + oi.getItem().getName() + 
                " = " + String.format("%.2f", oi.getTotalPrice()) + " Baht");
        }
    }
}

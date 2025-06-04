

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class BookingFrame extends JFrame {
    private JTextField txtName, txtPhone, txtEmail, txtSub, txtTax, txtTotal;
    private JTextField[] ticketFields = new JTextField[13];
    private JComboBox<String> cmbFrom, cmbTo;
    private JSpinner spnAdultQty, spnChildQty;
    private JRadioButton rdbStandard, rdbEconomy, rdbFirstClass;
    private JRadioButton rdbSingle, rdbReturn;
    private ButtonGroup grpClass, grpType;
    private final TicketManager tm = new TicketManager();

    public BookingFrame() {
        setTitle("Train Ticket Booking System");
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        addHeader();
        addBookingPanel();
        addTicketPanel();

        setVisible(true);
    }

    private void addHeader() {
        JLabel clockLabel = new JLabel();
        clockLabel.setBounds(700, 20, 200, 25);
        add(clockLabel);

        new javax.swing.Timer(1000, e -> {
            clockLabel.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }).start();

        JLabel title = new JLabel("Train Ticket Booking System");
        title.setBounds(80, 15, 400, 30);
        title.setFont(title.getFont().deriveFont(20f));
        add(title);
    }

    private void addBookingPanel() {
        JPanel panel = new JPanel(null);
        panel.setBounds(10, 70, 400, 470);
        panel.setBorder(BorderFactory.createTitledBorder("Booking Panel"));
        add(panel);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 20, 100, 25);
        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 200, 25);
        panel.add(txtName);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(10, 50, 100, 25);
        panel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(120, 50, 200, 25);
        panel.add(txtPhone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 80, 100, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 80, 200, 25);
        panel.add(txtEmail);

        JLabel lblClass = new JLabel("Class:");
        lblClass.setBounds(10, 110, 100, 25);
        panel.add(lblClass);

        rdbStandard = new JRadioButton("Standard");
        rdbStandard.setBounds(10, 135, 100, 25);
        panel.add(rdbStandard);

        rdbEconomy = new JRadioButton("Economy");
        rdbEconomy.setBounds(120, 135, 100, 25);
        panel.add(rdbEconomy);

        rdbFirstClass = new JRadioButton("First Class");
        rdbFirstClass.setBounds(230, 135, 100, 25);
        panel.add(rdbFirstClass);

        grpClass = new ButtonGroup();
        grpClass.add(rdbStandard);
        grpClass.add(rdbEconomy);
        grpClass.add(rdbFirstClass);

        JLabel lblType = new JLabel("Ticket Type:");
        lblType.setBounds(10, 165, 100, 25);
        panel.add(lblType);

        rdbSingle = new JRadioButton("Single");
        rdbSingle.setBounds(10, 190, 100, 25);
        panel.add(rdbSingle);

        rdbReturn = new JRadioButton("Return");
        rdbReturn.setBounds(120, 190, 100, 25);
        panel.add(rdbReturn);

        grpType = new ButtonGroup();
        grpType.add(rdbSingle);
        grpType.add(rdbReturn);

        JLabel lblAdult = new JLabel("Adult Qty:");
        lblAdult.setBounds(10, 220, 100, 25);
        panel.add(lblAdult);

        spnAdultQty = new JSpinner(new SpinnerNumberModel(1, 0, 10, 1));
        spnAdultQty.setBounds(120, 220, 50, 25);
        panel.add(spnAdultQty);

        JLabel lblChild = new JLabel("Child Qty:");
        lblChild.setBounds(10, 250, 100, 25);
        panel.add(lblChild);

        spnChildQty = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        spnChildQty.setBounds(120, 250, 50, 25);
        panel.add(spnChildQty);

        String[] stations = {
            "Dhaka kamalapur", "Joydevpur", "Tongi", "Mirzapur",
            "Tangail", "Jamuna Shetu,Sirajgong", "Natore", "Rajshahi"
        };

        JLabel lblFrom = new JLabel("From:");
        lblFrom.setBounds(10, 280, 100, 25);
        panel.add(lblFrom);

        cmbFrom = new JComboBox<>(stations);
        cmbFrom.setBounds(120, 280, 200, 25);
        panel.add(cmbFrom);

        JLabel lblTo = new JLabel("To:");
        lblTo.setBounds(10, 310, 100, 25);
        panel.add(lblTo);

        cmbTo = new JComboBox<>(stations);
        cmbTo.setBounds(120, 310, 200, 25);
        panel.add(cmbTo);

        txtSub = new JTextField(); txtSub.setBounds(120, 340, 100, 25);
        txtTax = new JTextField(); txtTax.setBounds(120, 370, 100, 25);
        txtTotal = new JTextField(); txtTotal.setBounds(120, 400, 100, 25);

        txtSub.setEditable(false);
        txtTax.setEditable(false);
        txtTotal.setEditable(false);

        panel.add(new JLabel("SubTotal:")).setBounds(10, 340, 100, 25);
        panel.add(txtSub);
        panel.add(new JLabel("Tax (10%):")).setBounds(10, 370, 100, 25);
        panel.add(txtTax);
        panel.add(new JLabel("Total:")).setBounds(10, 400, 100, 25);
        panel.add(txtTotal);

        JButton btnTotal = new JButton("Total");
        btnTotal.setBounds(10, 430, 80, 30);
        panel.add(btnTotal);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(100, 430, 80, 30);
        panel.add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(190, 430, 80, 30);
        panel.add(btnExit);

        btnTotal.addActionListener(e -> calculateTicket());
        btnReset.addActionListener(e -> resetForm());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void addTicketPanel() {
        JPanel ticketPanel = new JPanel(null);
        ticketPanel.setBounds(420, 70, 500, 470);
        ticketPanel.setBorder(BorderFactory.createTitledBorder("Ticket Info"));
        add(ticketPanel);

        String[] labels = {"Name", "Phone", "Email", "Class", "Ticket Type", "Adult", "Child", "From", "To", "Date", "Time", "Seat No.", "Total Price"};
        for (int i = 0; i < labels.length; i++) {
            ticketPanel.add(new JLabel(labels[i])).setBounds(10, i * 30 + 20, 100, 25);
            ticketFields[i] = new JTextField();
            ticketFields[i].setBounds(120, i * 30 + 20, 350, 25);
            ticketFields[i].setEditable(false);
            ticketPanel.add(ticketFields[i]);
        }
    }

    private void calculateTicket() {
        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all personal information.");
            return;
        }

        if (!rdbStandard.isSelected() && !rdbEconomy.isSelected() && !rdbFirstClass.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a ticket class.");
            return;
        }
        if (!rdbSingle.isSelected() && !rdbReturn.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a ticket type.");
            return;
        }

        int fromIndex = cmbFrom.getSelectedIndex();
        int toIndex = cmbTo.getSelectedIndex();
        if (fromIndex == toIndex) {
            JOptionPane.showMessageDialog(this, "'From' and 'To' stations cannot be the same.");
            return;
        }

        String ticketClass = rdbStandard.isSelected() ? "Standard" :
                             rdbEconomy.isSelected() ? "Economy" : "First Class";
        String ticketType = rdbSingle.isSelected() ? "Single" : "Return";

        int adultQty = (Integer) spnAdultQty.getValue();
        int childQty = (Integer) spnChildQty.getValue();

        double[] results = tm.calculateFare(ticketClass, ticketType, fromIndex, toIndex, adultQty, childQty);

        txtSub.setText(String.format("%.2f", results[0]));
        txtTax.setText(String.format("%.2f", results[1]));
        txtTotal.setText(String.format("%.2f", results[2]));

        String seatNo;
        try {
            seatNo = tm.assignSeat();
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

        String[] info = {
            txtName.getText(), txtPhone.getText(), txtEmail.getText(), ticketClass, ticketType,
            String.valueOf(adultQty), String.valueOf(childQty),
            (String) cmbFrom.getSelectedItem(), (String) cmbTo.getSelectedItem(),
            sdf.format(now), stf.format(now), "Seat " + seatNo, "Tk " + String.format("%.2f", results[2])
        };

        for (int i = 0; i < ticketFields.length; i++) {
            ticketFields[i].setText(info[i]);
        }

        JOptionPane.showMessageDialog(this, "Ticket successfully booked!");
    }

    private void resetForm() {
        txtName.setText(""); txtPhone.setText(""); txtEmail.setText("");
        txtSub.setText(""); txtTax.setText(""); txtTotal.setText("");
        grpClass.clearSelection(); grpType.clearSelection();
        spnAdultQty.setValue(1); spnChildQty.setValue(0);
        cmbFrom.setSelectedIndex(0); cmbTo.setSelectedIndex(0);
        for (JTextField field : ticketFields) field.setText("");
    }
}
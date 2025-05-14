package sis4;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentRegistration {
    private JFrame frame;
    private JTextField txtName, txtMobile, txtCourse;
    private JTable table;
    private DefaultTableModel model;
    private Connection con;
    
    public StudentRegistration() {
        initialize();
        connect();
        loadStudents();
    }
    
    private void initialize() {
        frame = new JFrame("Student Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("Student Registration");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(300, 10, 300, 30);
        panel.add(lblTitle);

        JLabel lblName = new JLabel("Student Name:");
        lblName.setBounds(20, 60, 100, 25);
        panel.add(lblName);
        txtName = new JTextField();
        txtName.setBounds(120, 60, 150, 25);
        panel.add(txtName);

        JLabel lblMobile = new JLabel("Mobile:");
        lblMobile.setBounds(20, 100, 100, 25);
        panel.add(lblMobile);
        txtMobile = new JTextField();
        txtMobile.setBounds(120, 100, 150, 25);
        panel.add(txtMobile);

        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(20, 140, 100, 25);
        panel.add(lblCourse);
        txtCourse = new JTextField();
        txtCourse.setBounds(120, 140, 150, 25);
        panel.add(txtCourse);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 180, 80, 30);
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(110, 180, 80, 30);
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(200, 180, 80, 30);

        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);

        frame.add(panel, BorderLayout.WEST);
        panel.setPreferredSize(new Dimension(300, 400));

        model = new DefaultTableModel(new String[]{"ID", "Name", "Mobile", "Course"}, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtName.setText(model.getValueAt(selectedRow, 1).toString());
                    txtMobile.setText(model.getValueAt(selectedRow, 2).toString());
                    txtCourse.setText(model.getValueAt(selectedRow, 3).toString());
                }
            }
        });
        
        frame.setVisible(true);
    }
    
    private void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "Sanman8070");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadStudents() {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("course")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void addStudent() {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO students (name, mobile, course) VALUES (?, ?, ?)");
            pst.setString(1, txtName.getText());
            pst.setString(2, txtMobile.getText());
            pst.setString(3, txtCourse.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Record Saved");
            clearFields();
            loadStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int id = (int) model.getValueAt(selectedRow, 0);
                PreparedStatement pst = con.prepareStatement("UPDATE students SET name=?, mobile=?, course=? WHERE id=?");
                pst.setString(1, txtName.getText());
                pst.setString(2, txtMobile.getText());
                pst.setString(3, txtCourse.getText());
                pst.setInt(4, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Record Updated");
                clearFields();
                loadStudents();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    PreparedStatement pst = con.prepareStatement("DELETE FROM students WHERE id=?");
                    pst.setInt(1, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Record Deleted");
                    clearFields();
                    loadStudents();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtMobile.setText("");
        txtCourse.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRegistration::new);
    }
}

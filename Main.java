import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    private JTextField tempField;
    private JComboBox<String> unitBox;
    private JButton convertButton;

    private JLabel result1;
    private JLabel result2;

    public Main() {

        setTitle("🌡 Temperature Converter");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 35));
        panel.setLayout(null);

        JLabel title = new JLabel("Temperature Converter");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(95, 25, 400, 35);

        JLabel tempLabel = new JLabel("Enter Temperature:");
        tempLabel.setForeground(Color.WHITE);
        tempLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tempLabel.setBounds(40, 100, 180, 30);

        tempField = new JTextField();
        tempField.setFont(new Font("Arial", Font.PLAIN, 18));
        tempField.setBounds(240, 100, 230, 35);

        JLabel unitLabel = new JLabel("Select Unit:");
        unitLabel.setForeground(Color.WHITE);
        unitLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        unitLabel.setBounds(40, 170, 180, 30);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitBox = new JComboBox<>(units);
        unitBox.setFont(new Font("Arial", Font.PLAIN, 16));
        unitBox.setBounds(240, 170, 230, 35);

        convertButton = new JButton("Convert");
        convertButton.setBounds(170, 240, 180, 45);
        convertButton.setFont(new Font("Arial", Font.BOLD, 18));
        convertButton.setBackground(new Color(0, 180, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.addActionListener(this);

        JLabel resultTitle = new JLabel("Converted Values");
        resultTitle.setForeground(Color.WHITE);
        resultTitle.setFont(new Font("Arial", Font.BOLD, 20));
        resultTitle.setBounds(160, 305, 250, 30);

        result1 = new JLabel("");
        result1.setForeground(new Color(0, 255, 150));
        result1.setFont(new Font("Arial", Font.BOLD, 18));
        result1.setBounds(60, 345, 420, 30);

        result2 = new JLabel("");
        result2.setForeground(new Color(0, 255, 150));
        result2.setFont(new Font("Arial", Font.BOLD, 18));
        result2.setBounds(60, 375, 420, 30);

        panel.add(title);
        panel.add(tempLabel);
        panel.add(tempField);
        panel.add(unitLabel);
        panel.add(unitBox);
        panel.add(convertButton);
        panel.add(resultTitle);
        panel.add(result1);
        panel.add(result2);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            double temp = Double.parseDouble(tempField.getText());
            String unit = (String) unitBox.getSelectedItem();

            // Kelvin validation
            if (unit.equals("Kelvin") && temp < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Kelvin cannot be negative!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
                result1.setText("");
                result2.setText("");
                return;
            }

            if (unit.equals("Celsius")) {

                double fahrenheit = (temp * 9 / 5) + 32;
                double kelvin = temp + 273.15;

                result1.setText("Fahrenheit : " +
                        String.format("%.2f", fahrenheit) + " °F");

                result2.setText("Kelvin : " +
                        String.format("%.2f", kelvin) + " K");
            }

            else if (unit.equals("Fahrenheit")) {

                double celsius = (temp - 32) * 5 / 9;
                double kelvin = celsius + 273.15;

                result1.setText("Celsius : " +
                        String.format("%.2f", celsius) + " °C");

                result2.setText("Kelvin : " +
                        String.format("%.2f", kelvin) + " K");
            }

            else {

                double celsius = temp - 273.15;
                double fahrenheit = (celsius * 9 / 5) + 32;

                result1.setText("Celsius : " +
                        String.format("%.2f", celsius) + " °C");

                result2.setText("Fahrenheit : " +
                        String.format("%.2f", fahrenheit) + " °F");
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid temperature value!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

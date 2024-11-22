import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommonUtilGUI extends JFrame {

    private JTextArea messageArea;
    private JTextField keyField;
    private JTextField lauField;
    private JButton generateButton;

    public CommonUtilGUI() {
        setTitle("LAU Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        messageArea = new JTextArea(10, 40);
        keyField = new JTextField(30);
        lauField = new JTextField(30);
        lauField.setEditable(false);
        generateButton = new JButton("Generate LAU");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("SWIFT Message:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JScrollPane(messageArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Key:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(keyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Generated LAU:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lauField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(generateButton, gbc);

        add(panel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText();
                String key = keyField.getText();
                String lau = hmacSHA256(message, key);
                lauField.setText(lau);

                String formattedMessage = message + "\r\n{S:{MDG:" + lau + "}}";
                
                try (FileWriter fileWriter = new FileWriter("C:\\Users\\user\\Downloads\\MTLAU.txt")) {
                    fileWriter.write(formattedMessage);
                    JOptionPane.showMessageDialog(null, "Formatted message with LAU has been saved to C:\\Users\\user\\Downloads\\MTLAU.txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error writing to file: " + ex.getMessage());
                }
            }
        });
    }

    public static String hmacSHA256(String message, String key) {
        try {
            byte[] rawHmac = getHmac(key, message.getBytes("UTF-8"));
            byte[] hexBytes = new Hex().encode(rawHmac);
            return new String(hexBytes, "UTF-8").toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getHmac(String key, byte[] messageBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        return mac.doFinal(messageBytes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CommonUtilGUI().setVisible(true);
            }
        });
    }
}

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

public class LAUF {

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
        try {
            // Read the content of the file
            List<String> lines = Files.readAllLines(Paths.get("Z:\\2_Qualifications\\100_Message Samples ITB\\SU5_Intellec\\NetlinkSC\\MTLAU_NetlinkSc.txt"));
            StringBuilder messageBuilder = new StringBuilder();
            for (String line : lines) {
                messageBuilder.append(line).append("\r\n");
            }
            String message = messageBuilder.toString().trim();
            
            // Extract and update field 20 content
            String messageField20 = "11112234"; // New field 20 content for LAU generation

            // Key for HMAC generation
            String key = "Abcdef0123456789Abcdef0123456789";
            
            // Generate the LAU based on field 20 only
            String lau = hmacSHA256(messageField20, key);
            System.out.println("Generated HMAC-SHA256 for field 20: " + lau);
            
            // Replace field 20 in the message
            String updatedMessage = message.replaceFirst(":20:.*", ":20:" + messageField20);
            
            // Format the full message with the LAU
            String formattedMessage = updatedMessage + "{S:{MDG:" + lau + "}}";
            
            // Save the message and LAU to a file
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\user\\Downloads\\MTLAU.txt")) {
                fileWriter.write(formattedMessage);
                System.out.println("Formatted message with LAU has been saved to C:\\Users\\user\\Downloads\\MTLAU.txt");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

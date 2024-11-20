import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import java.io.FileWriter;
import java.io.IOException;

public class CommonUtil {

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
        // SWIFT message parts
        String messageField20 = "1111ssssss"; // Field 20 content

        // Key for HMAC generation
        String key = "Abcdef0123456789Abcdef0123456789";
        
        // Generate the LAU based on field 20 only
        String lau = hmacSHA256(messageField20, key);
        System.out.println("Generated HMAC-SHA256 for field 20: " + lau);
        
        // Full message without field 20 for demonstration
        String message = String.join("\r\n",
            "{1:F01PTSQGBB0XFIT0183000001}{2:I199PTSQGBB0XFITU3}{3:{108:swiftuser6Lau}}{4:",
            ":20:" + messageField20, // Including field 20 value
            ":79:SAA AKURA JJJ Branch Filesystem look in incoming messages -}"
            
        );

        // Format the full message with the LAU
        String formattedMessage = message + "\r\n{S:{MDG:" + lau + "}}";
        
        // Save the message and LAU to a file
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\user\\Downloads\\MTLAU.txt")) {
            fileWriter.write(formattedMessage);
            System.out.println("Formatted message with LAU has been saved to C:\\Users\\user\\Downloads\\MTLAU.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

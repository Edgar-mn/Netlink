import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class LAU {
    
    public static String hmacSHA256(String sMessage, String key) {
        try {
            byte[] rawHmac = getHmac(key, sMessage.getBytes("UTF-8"));
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
        // Example usage
        String message = String.join( "{1:F01PTSQGBB0XAAA0183000001}{2:I199PTSQGBB0XFITU3}{3:{108:swiftuser6lau}}{4:",
            ":20:20112024 ",
                        ":79:SAA AKURA JJJ Branch Filesystem look in incoming messages -}");
        String key = "Abcdef0123456789Abcdef0123456789";
        String lau = hmacSHA256(message, key);
        System.out.println("Generated HMAC-SHA256: " + lau);
    }
}

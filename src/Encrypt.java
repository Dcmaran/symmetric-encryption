import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Encrypt {

    public static void main(String[] args) throws Exception {
        String textoOriginal = "CESAR School";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey chave = keyGenerator.generateKey();

        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[12];
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, chave, parameterSpec);

        byte[] textoCriptografado = cipher.doFinal(textoOriginal.getBytes());

        String ivString = Base64.getEncoder().encodeToString(iv);
        String textoCriptografadoString = Base64.getEncoder().encodeToString(textoCriptografado);

        System.out.println("IV: " + ivString);
        System.out.println("Texto Criptografado: " + textoCriptografadoString);
        System.out.println("Chave: " + Base64.getEncoder().encodeToString(chave.getEncoded()));
    }
}

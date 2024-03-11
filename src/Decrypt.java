import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Decrypt {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o texto criptografado:");
            String textoCriptografadoString = sc.nextLine();

            System.out.println("Digite o IV:");
            String ivString = sc.nextLine();

            System.out.println("Digite a chave secreta:");
            String chaveString = sc.nextLine();

            byte[] iv = Base64.getDecoder().decode(ivString);
            byte[] textoCriptografado = Base64.getDecoder().decode(textoCriptografadoString);
            byte[] chaveBytes = Base64.getDecoder().decode(chaveString);
            SecretKey chave = new SecretKeySpec(chaveBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.DECRYPT_MODE, chave, parameterSpec);

            byte[] textoDescriptografado = cipher.doFinal(textoCriptografado);

            String textoOriginal = new String(textoDescriptografado);

            System.out.println("Texto Original: " + textoOriginal);

        } catch (Exception e) {
            System.out.println("Erro ao descriptografar o texto: " + e.getMessage());
        }
    }
}

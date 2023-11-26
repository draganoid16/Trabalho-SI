import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserDataCollector {
    private Scanner sc = new Scanner(System.in);
    private String configName = "C:\\Users\\Joao\\Desktop\\TrabalhoSI\\Autenticacao\\config.cfg"; // Absolute path

    private Provider prov;
    private String name;
    private String civilId;
    private PublicKey publicKey;
    private KeyStore ks;

    public UserDataCollector() throws Exception {
        prov = Security.getProvider("SunPKCS11-CartaoCidadao");

        ks = KeyStore.getInstance("PKCS11", prov);

    }


    public void getDataFromCard() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        ks.load(null, null);

        Enumeration<String> als = ks.aliases();
        while (als.hasMoreElements()) {
            System.out.println(als.nextElement());
        }

        // CERTIFICADO de chave pública do Cartão de Cidadão
        Certificate certificado = ks.getCertificate("CITIZEN AUTHENTICATION CERTIFICATE");

        // CHAVE PUBLICA DO CERTIFICADO DE AUTENTICACAO
        PublicKey pkCA = certificado.getPublicKey();
        this.publicKey = pkCA;

        X509Certificate x509Certificado = (X509Certificate) certificado;
        String subjectDN = x509Certificado.getSubjectX500Principal().getName();
        String[] dnComponents = subjectDN.split(",");

        //extrair nome
        for (String component : dnComponents) {
            if (component.trim().startsWith("CN=")) {
                this.name = component.trim().substring(3);
            }
        }
        //extrair id
        String civilIdHex = "";
        for (String component : subjectDN.split(",")) {
            if (component.trim().startsWith("2.5.4.5=#")) {
                civilIdHex = component.trim().substring("2.5.4.5=#".length());
                break;
            }
        }
        StringBuilder civilId = new StringBuilder();
        for (int i = 0; i < civilIdHex.length(); i += 2) {
            String str = civilIdHex.substring(i, i + 2);
            civilId.append((char) Integer.parseInt(str, 16));
        }

        //extrair so os numeros
        String civilIdDigits = civilId.toString().replaceAll("[^\\d]", "");

        System.out.println("Subject DN: " + subjectDN);

        System.out.println(name);
        System.out.println(civilIdDigits);
        System.out.println(publicKey);
    }

    public String getEmail(){
        String email;
        System.out.println("Introduza o seu email: ");
        email = sc.next();
        return email;
    }

    public static void main(String[] args) throws Exception {
       // Provider[] provs = Security.getProviders();
       // for (int i = 0; i < provs.length; i++) {
         //   System.out.println(i + 1 + " - Nome do provider: " + provs[i].getName());
       // }
        UserDataCollector user = new UserDataCollector();
        user.getDataFromCard();
    }
}


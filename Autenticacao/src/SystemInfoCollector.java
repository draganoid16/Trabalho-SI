import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SystemInfoCollector {
    Runtime runInfo = Runtime.getRuntime();
    int availableProcessors = runInfo.availableProcessors();

    /*
    Informação que identifique o sistema: um identificador do sistema obtido do conjunto de hardware presente
tais como número e tipo de CPUs, placas de rede (endereços MAC), números de série do suporte de
armazenamento, ou mesmo identificadores da BIOS.
    tipo cpus
    numero cpus -
    endereco mac -
    numero serie de armazenamento
     */
    public String getBasicSystemInfo() {
        String arch = System.getProperty("os.arch");
        String name = System.getProperty("os.name");
        return "Architecture: " + arch + "\nOS Name: " + name;
    }

    public String testeOsDetails() {


        String osDetails;
        String osInfo = "os.arch";
        osDetails = System.getProperty(osInfo);
        return "\n" + osDetails + "\nVariavel: " + "\n" + availableProcessors;
    }

    public String getMacAddress() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();

                if (mac != null) {
                    try (Formatter formatter = new Formatter(sb)) {
                        for (int i = 0; i < mac.length; i++) {
                            formatter.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        }
                        sb.append(System.lineSeparator());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public String getNetworkCardHash() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface ni : Collections.list(networkInterfaces)) {
                if (!ni.isLoopback() && ni.isUp()) {
                    sb.append(ni.getDisplayName());
                    sb.append(ni.getHardwareAddress() != null ? Arrays.toString(ni.getHardwareAddress()) : "");
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return "Error collecting network info";
        }

        return hashString(sb.toString());
    }


    private String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Error hashing the string";
        }
    }


}

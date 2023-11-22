
public class Main {
    public static void main(String[] args) {
        LicenseValidator licenseValidator = new LicenseValidator("NomeDaAplicacao", "1.0");

        if (licenseValidator.isRegistered()) {
            System.out.println("Aplicação está registada");
        } else {
            System.out.println("Aplicação não está registada");
            if (licenseValidator.startRegistration()) {
                System.out.println("Processo de registo iniciado.");
            } else {
                System.out.println("Não foi possível iniciar o processo de registo");
            }
        }
        System.out.println("A mostrar informações da licença:");
        licenseValidator.showLicenseInfo();
    }
}
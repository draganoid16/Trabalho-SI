import java.util.Scanner;

public class Autentication {
    Scanner sc = new Scanner(System.in);

    public Autentication() {


    }

    public void init(String nomeDaApp, String versao) {
        try {
            // Instantiate the controller
            RegistrationController registrationController = new RegistrationController();

            // Start the registration process
            registrationController.initiateRegistrationProcess();


        } catch (Exception e) {
            // Handle any exceptions thrown during the registration process
            e.printStackTrace();
        }
    }

    public boolean isRegistered() {
        return false;
    }

    public boolean startRegistration() {
        System.out.println("A sua aplicacao não se encontra registada! Quer comecar o registo?");
        System.out.println("\n 1- Sim, 2- Não");
        int escolha = sc.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("registo");
                return true;

            default:
                return false;

        }
    }

        public void showLicenseInfo(){

        }

    }

}

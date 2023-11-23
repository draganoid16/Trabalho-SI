import java.util.Scanner;
import pt.gov.cartaodecidadao.*; // Esta é uma importação genérica para o SDK do Cartão de Cidadão

public class UserDataCollector {
    private Scanner sc = new Scanner(System.in);
    private PTEID_ReaderSet readerSet;
    private PTEID_EIDCard card;
    private PTEID_EId eid;

    public UserDataCollector() {
        // Inicialize o leitor do Cartão de Cidadão aqui
        // Certifique-se de que o SDK do Cartão de Cidadão está configurado corretamente
    }

    public UserData getUserInput() {
        UserData userData = new UserData();

        // Coletar e-mail do usuário
        System.out.println("Digite o seu e-mail:");
        userData.setEmail(sc.nextLine());

        // Coletar dados do Cartão de Cidadão
        try {
            readerSet = PTEID_ReaderSet.instance();
            card = readerSet.getReader().getEIDCard();
            eid = card.getID();

            userData.setName(eid.getGivenName() + " " + eid.getSurname());
            userData.setId(eid.getDocumentNumber());
            // Adicione aqui mais campos conforme necessário

        } catch (PTEID_Exception e) {
            e.printStackTrace();
            // Tratamento de erros relacionados à leitura do Cartão de Cidadão
        }

        return userData;
    }
}


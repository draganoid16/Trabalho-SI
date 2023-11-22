public class LicenseValidator {

    private String appName;
    private String version;


    public LicenseValidator(String nomeDaApp, String versao) {
        this.appName = nomeDaApp;
        this.version = versao;
    }

    public boolean isRegistered() {
        return true;
    }


    public boolean startRegistration() {
        return true;
    }


    public void showLicenseInfo() {
        System.out.println(appName + ", " + version);
    }


}
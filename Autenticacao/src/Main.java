public class Main {
    //main para teste de metodos

    public static void main(String[] args) {
        SystemInfoCollector testeOs = new SystemInfoCollector();
        //System.out.println(testeOs.testeOsDetails());
        //System.out.println(testeOs.getMacAddress());
        System.out.println(testeOs.getNetworkCardHash());

    }
}

//530f605bc98fe6659c50f38ca33a97629c8ec269a7176b1198ed509319e0a243

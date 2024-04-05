package works.nuka.modularkit;

public class Main {

    public static void main(String[] args) throws Exception {
        ModularSource mainSource = new ModularSource("09040865");
        ModuleTest testMod = new ModuleTest();
        mainSource.registerModule(testMod);
        mainSource.getModuleManager().runModule(testMod);
    }
}

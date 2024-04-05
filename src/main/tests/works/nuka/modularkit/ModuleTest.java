package works.nuka.modularkit;

public class ModuleTest extends ModularModule {

    public ModuleTest() throws Exception {
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", new AnotherModule());
    }

    @Override
    public void runEvent() {
        System.out.println("Hello World !");
    }
}

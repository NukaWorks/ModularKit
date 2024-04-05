package works.nuka.modularkit;

public class AnotherModule extends ModularModule {

    public AnotherModule() throws Exception {
        super("AnotherModule", "452457c5", "Kawalize", "1.2.4");
    }

    @Override
    public void runEvent() {
        System.out.println("Hello " + this.getModuleName() + " !");
    }
}

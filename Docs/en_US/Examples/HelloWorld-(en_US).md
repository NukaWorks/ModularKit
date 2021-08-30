# In the family of examples, I call the HelloWorld!    

First example : **HelloWorld** !

```java
package com.example;

import xyz.sunproject.modularkit.core.ModularModule;

public class ModuleTest extends ModularModule {

    public ModuleTest() throws Exception {
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0");
    }

    @Override
    public void runEvent() {
        System.out.println("Hello World !");
    }
}
```
This example is really simple, it doesn't use any module dependencies, if you want to add dependent modules, do it like this:
```java
public ModuleTest() throws Exception {
        AnotherModule anotherMod = new AnotherModule();
        AnotherModule anotherMod2 = new AnotherModule();
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", anotherMod, anotherMod2);
        // You can use many dependencies !
}
```

### To register and run a module : 
To launch a module, try this small example 😉:
```java
public class Main {
    public static void main() {
        // Register the source first ...
        ModularSource mainSource = new ModularSource("e3640e55");
        // And the Module ...
        ModuleTest testMod = new ModuleTest();
        // ... And then let's run it !
        mainSource.getModuleManager().findModuleByUuID("Dug40M90").runModule();
    }
}
```
For a module source and a module itself, you need to assign a unique UuID to it! Look at the examples below to generate one in a simple way.   
#### Unix/MacOS/Linux :
```bash
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```
#### UUIDGEN Online : 
You can use **https://www.uuidgenerator.net/** which works fine.
**You must copy only group 1 (*).    
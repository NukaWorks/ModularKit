<center><h1>ModularKit</h1></center>

<center><img alt="ModularKit Logo" src="https://repo.sunproject.xyz/SunProject.xyz/ModularKit/raw/branch/main/pub/www/src/assets/ModularKit-Logo.png" /></center>

### What is ModularKit ?
**ModularKit** is a minimal and powerful Java Framework for creating modular apps.    

### But why i using it on all my Java projects ?
With ModularKit, you can create modular apps with increased flexibility and stability, designed to create projects with no downtime worries and good maintainability.

### Easy to use ?
Yea, you can checkout documentation here : **LINK**


# Examples :    

### How to create a Modular hello-world ?    

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
This example is very simple, but this module don't use any ModDependencies.    
You can use :
```java
 public ModuleTest() throws Exception {
        AnotherModule anotherMod = new AnotherModule();
        AnotherModule anotherMod2 = new AnotherModule();
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", anotherMod, anotherMod2);
        // You can use many dependencies !
    }
```
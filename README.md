<center>
    <h1>ModularKit</h1>
    <img alt="ModularKit Logo" src="https://raw.githubusercontent.com/Sigmanificient/ModularKit/main/pub/www/svg/logo.svg" height="200" width="200"/>
</center>

## What is ModularKit ?

**ModularKit** is a minimal and powerful Java Framework for creating modular apps.

### But why i using it on all my Java projects ?

With ModularKit, you can create modular apps with increased flexibility and stability, designed to create projects with
no downtime worries and good maintainability.

### Easy to use ?

Yea, you can checkout documentation
here : **[Wiki on Sonikalize](https://repo.sunproject.xyz/NutDevs.org/ModularKit/wiki).**

## Examples

### How to create a Modular hello-world ?

First example : **HelloWorld** !

```java
package com.example;

import ModularModule;

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

### For registering and run a module :

For running a module, let's try this little example :

```java
public static void main() {
    // Register the module first
    ModularSource mainSource = new ModularSource("e3640e55"); 
    // Then let's run it !
    mainSource.getModuleManager().findModuleByUuID("Dug40M90").runModule();
}
```

For ModSource and Module you need to generate a new uuid (Group 1).

#### Unix/MacOS/Linux :

```bash
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```

#### UUIDGEN Online :

You can use **https://www.uuidgenerator.net/**

**You need to copy the group 1 (*)**

#### You can find the full documentation on the Wiki : **[Wiki on Sonikalize](https://repo.sunproject.xyz/NutDevs.org/ModularKit/wiki).**  

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7524371023014522906b1a8f0c5354d2)](https://app.codacy.com/gh/NutDevs-org/ModularKit?utm_source=github.com&utm_medium=referral&utm_content=NutDevs-org/ModularKit&utm_campaign=Badge_Grade_Settings)
[![Nightly Builder](https://github.com/NutDevs-org/ModularKit/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/NutDevs-org/ModularKit/actions/workflows/main.yml)
![Lines of code](https://img.shields.io/tokei/lines/github/NutDevs-org/ModularKit?label=Total%20Lines%20Of%20Codes%20%3A)
[![Discord](https://img.shields.io/discord/832638079255969794?label=Join%20our%20Discord%20%3A%20)](http://discord.nutdevs.org)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/NutDevs-org/KawiBot)
![GitHub Licence](https://img.shields.io/github/license/NutDevs-org/KawiBot)

**Thanks for  [@Sigmanificient](https://github.com/Sigmanificient) for the
Project-Page !**

<h1>ModularKit</h1>
<img alt="ModularKit Logo" src="https://raw.githubusercontent.com/Sigmanificient/ModularKit/main/pub/www/svg/logo.svg" height="200" width="200"/>

## What is ModularKit ?

**ModularKit** it's a lightweight and powerful Java Framework for creating
modular apps.

### But why you need to use it ?

With ModularKit, you can create modular apps with increased flexibility and
stability, designed to create projects with no downtime worries and good
maintainability.

### Easy to use ?

Of course ! You can check out documentation
here : **[Wiki on Github](https://github.com/NutDevs-org/ModularKit/wiki).**

## Examples

### How to create a Modular hello-world

First example : **HelloWorld** !

```java
package com.example;

import org.nutdevs.modularkit.core.ModularModule;

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

This example is very simple, you can add others ModuleDependencies so,    
You can use :

```java
public class ModuleTest {
    public ModuleTest() {
        AnotherModule anotherMod = new AnotherModule();
        AnotherModule anotherMod2 = new AnotherModule();
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", anotherMod, anotherMod2);
        // You can use Module dependencies !
    }
}
```

### For registering and run a module

For running a module, let's try this little example :

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

For ModSource and Module you need to generate a new uuid (Group 1).

#### Unix/MacOS/Linux

```shell
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```

#### UUIDGEN Online

You can use **https://www.uuidgenerator.net/**

**You need to copy the group 1 (*)**

#### You can find the full documentation on the Wiki : **[Wiki on Github](https://github.com/NutDevs-org/ModularKit/wiki)**
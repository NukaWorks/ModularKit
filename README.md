[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7524371023014522906b1a8f0c5354d2)](https://app.codacy.com/gh/NutDevs-org/ModularKit?utm_source=github.com&utm_medium=referral&utm_content=NutDevs-org/ModularKit&utm_campaign=Badge_Grade_Settings)
[![Nightly Builder](https://github.com/NutDevs-org/ModularKit/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/NutDevs-org/ModularKit/actions/workflows/main.yml)
![Maven metadata URL](https://img.shields.io/maven-metadata/v?label=mvn.nutdevs.org&metadataUrl=https%3A%2F%2Fmvn.nutdevs.org%2FNutDevs-org%2FModularKit%2Fnightly-builds%2Forg%2Fnutdevs%2FModularKit%2Fmaven-metadata.xml)
![Lines of code](https://img.shields.io/tokei/lines/github/NutDevs-org/ModularKit?label=Total%20Lines%20Of%20Codes%20%3A)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/NutDevs-org/KawiBot)
![GitHub Licence](https://img.shields.io/github/license/NutDevs-org/ModularKit)

<h1 align="center">ModularKit</h1>

<hr/>

## What is ModularKit ?

**ModularKit**, a lightweight but powerful Java micro-framework for creating
modular apps.

With ModularKit, you can create modular apps with increased flexibility and
stability, designed to create projects without server shutdown and a good
maintainability.

Checkout the documentation on the documentation folder: **[Docs/](https://github.com/NutDevs-org/ModularKit/tree/main/Docs/).**


## Getting started

First, you need to create a new project (maven recommended) and add **ModularKit** dependency : 

### For creating a modular-based app (Client side) : 

```xml
<dependency>
    <groupId>org.nutdevs</groupId>
    <artifactId>ModularKit</artifactId>
    <version>{check the mvn.nutdevs.org badge}</version>
</dependency>
```

### For external module (Module side) : 
#### Create manualy
```xml
<dependency>
    <groupId>org.nutdevs</groupId>
    <artifactId>ModularKit</artifactId>
    <version>{check the mvn.nutdevs.org badge}</version>
    <scope>provided</scope>
</dependency>
```
#### Or use the ModuleTemplate
Use this ModuleTemplate : https://github.com/NutDevs-org/ModuleTest

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

An another example with module-dependencies : you can add others dependant module by this way :

```java
package com.example;

import org.nutdevs.modularkit.core.ModularModule;

public class ModuleTest extends ModularModule {
    public ModuleTest() {
        AnotherModule anotherMod = new AnotherModule();
        AnotherModule anotherMod2 = new AnotherModule();
        
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", anotherMod, anotherMod2);
        // You can use Module dependencies !
    }
    
    @Override
    public void runEvent() {
        System.out.println("Hello World with module-dependencies !");
    }
}
```

### For registering and run a module

For running a module, let's try this little example :

```java
public class Main {
    public static void main() {
        // Register the source first
        ModularSource mainSource = new ModularSource("e3640e55");
        
        // and the Module ...
        ModuleTest testMod = new ModuleTest();
        
        // ... and then let's run it !
        mainSource.getModuleManager().runModule(testMod);
    }
}
```

For ModSource and Module you need to generate a new uuid (Group 1).

#### MacOS/Linux and others unix systems

```shell
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```

#### UUIDGEN Online

You can use **https://www.uuidgenerator.net/**

⚠ **You need to copy the group 1 of the uuid.**

#### You can find the full documentation on the directory : **[Docs/](https://github.com/NutDevs-org/ModularKit/tree/main/Docs/)**

# Many thanks to

**- [@Sigmanificient](https://github.com/Sigmanificient) for the
Project-Page !**  

# Social links

**Telegram Channel :** [ModularKit Dev Channel](https://t.me/ModularKit)

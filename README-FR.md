[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4eff78558cbc47c0b929dd1c45d12bda)](https://www.codacy.com/gh/NukaWorks/ModularKit/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NukaWorks/ModularKit&amp;utm_campaign=Badge_Grade)
[![Nightly Builder](https://github.com/NukaWorks/ModularKit/actions/workflows/prebuild-nightly.yml/badge.svg?branch=main)](https://github.com/NutDevs-org/ModularKit/actions/workflows/prebuild-nightly.yml)
[![CodeQL](https://github.com/NukaWorks/ModularKit/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/NukaWorks/ModularKit/actions/workflows/codeql-analysis.yml)
![Maven Central](https://img.shields.io/maven-central/v/works.nuka/ModularKit)
![Lines of code](https://img.shields.io/tokei/lines/github/NukaWorks/ModularKit?label=Total%20Lines%20Of%20Codes%20%3A)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/NukaWorks/ModularKit)
![GitHub Licence](https://img.shields.io/github/license/NutDevs-org/ModularKit)

[go to English readme](README.md)

# ModularKit

# fr
## Qu'est-ce que ModularKit ?

**ModularKit**, un micro-framework Java léger mais puissant pour la création de
d'applications modulaires.

Avec ModularKit, vous pouvez créer des applications modulaires avec une flexibilité et une stabilité accrues.
stabilité, conçues pour créer des projets sans arrêt du serveur et une bonne
maintenabilité.

Consultez la documentation dans le dossier de documentation : **[docs](https://github.com/NukaWorks/ModularKit/tree/main/docs/).** 

## Pour commencer

Tout d'abord, vous devez créer un nouveau projet (maven recommandé) et ajouter la dépendance **ModularKit** : 

### Pour créer une application modulaire (côté client) :

```xml
<dependency>
    <groupId>works.nuka</groupId>
    <artifactId>ModularKit</artifactId>
    <version>{ Check maven-central badge }</version>
</dependency>
```
### Pour le module externe (côté module) : 
#### Créer manuellement

```xml
<dependency>
    <groupId>works.nuka</groupId>
    <artifactId>ModularKit</artifactId>
    <version>{ Check maven-central badge }</version>
    <scope>provided</scope>
</dependency>
```
#### Ou utilisez le ModuleTemplate
Utilisez ce ModuleTemplate : https://github.com/NukaWorks/ModuleTest

## Exemples

### Comment créer un hello-world modulaire

Premier exemple : **HelloWorld** !

```java
package com.example;

import works.nuka.modularkit.ModularModule;

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
Un autre exemple avec les dépendances de modules : vous pouvez ajouter d'autres modules dépendants de cette manière :

```java
package com.example;

import works.nuka.modularkit.ModularModule;

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

### Pour enregistrer et exécuter un module
Pour exécuter un module, essayons ce petit exemple :

```java
import works.nuka.modularkit.ModularSource;
import ModuleTest;

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

Pour ModSource et Module, vous devez générer un nouvel uuid (Groupe 1).
#### MacOS/Linux et autres systèmes unix

```shell
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```

#### UUIDGEN en ligne

Vous pouvez utiliser **https://www.uuidgenerator.net/**

⚠ **Vous devez copier le groupe 1 de l'uuid.**

#### Vous pouvez trouver la documentation complète sur le répertoire : **[docs](https://github.com/NukaWorks/ModularKit/tree/main/docs/)**

# Liens sociaux

***[Voir sur Powerm1nt Profile](https://github.com/Powerm1nt#--lets-talk-with-me-on)***

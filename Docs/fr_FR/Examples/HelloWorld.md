# Dans la famille des exemples, je demande L'HelloWorld !


## Avant toute chose la première étape est d'importer ModularKit

Pour cela vous devez créer un nouveau projet maven (recommandé) et ajouter cette dépendence : 

### For creating a modular-based app : 

```xml
<dependency>
    <groupId>org.nutdevs</groupId>
    <artifactId>ModularKit</artifactId>
    <version>v1.4.11-nightly_e67ab56</version>
</dependency>
```

### For external module : 

```xml
<dependency>
    <groupId>org.nutdevs</groupId>
    <artifactId>ModularKit</artifactId>
    <version>v1.4.11-nightly_e67ab56</version>
    <scope>provided</scope>
</dependency>
```
A partir de là, vous pouvez commencer a jouer avec 😋    

Premier example : **HelloWorld** !

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
Cet exemple est vraiment simple, il n'utilise aucune dépendance de module, si vous voulez ajouter des modules dépendants, pocédez comme ceci :
```java
public ModuleTest() throws Exception {
        AnotherModule anotherMod = new AnotherModule();
        AnotherModule anotherMod2 = new AnotherModule();
        super("ModuleTest", "81f9ab59", "Sundev79", "1.0.0", anotherMod, anotherMod2);
        // You can use many dependencies !
}
```

### Pour enregistrer et lancer un module : 
Pour lancer un module, essayez ce petit exemple 😉:
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
Pour une source de module et un module en lui-même, vous devez lui attribuer un UuID unique ! Regardez les exemples en dessous pour en générer de manière simple.   
#### Unix/MacOS/Linux :
```bash
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```
#### UUIDGEN Online : 
Vous pouvez utiliser **https://www.uuidgenerator.net/** qui marche vraiment bien !

**Vous devez copier uniquement le groupe 1 (*).**    

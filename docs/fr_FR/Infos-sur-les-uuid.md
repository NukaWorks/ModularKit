# Pourquoi avons besoin d'uuid pour chaque ModSource/ModModule ?
Sujet courant, en effet vous devez utiliser le groupe 1 d'un **UUID** fraichement généré avec l'aide des outils suivants : 

``Extrait de la doc en FR de l'HelloWorld.``

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

# Mais Pourquoi et comment ?

L'uuid groupe 1 (ou un LittleUuID pour les intimes 😉) sert pour obtenir, rechercher un module dans une source, et ça va dans le même sans pour une **ModSource** (qui a elle aussi son propre uuid).
Par exemple on peut obtenir l'objet du Module grâce à cette fonctionalité vital !

```java
ModularModule obtainedModuleByUuID = modSource.getModuleManager().findModuleByUuiD("160a3820");
```

Et la tadaaa ! vous avez accès à tout le module ! 
# Why do we need uuid for each ModSource/ModModule?
Common topic, in fact you need to use group 1 of a freshly generated **UUID** with the help of the following tools: 

Extract from the HelloWorld doc.

For a module source and a module itself, you must assign a unique UuID to it! Look at the examples below to generate them in a simple way.   
#### Unix/MacOS/Linux:

```bash
~$ uuidgen
**e3640e55**-cbaf-42c2-b053-52a2cfa2e0a5
~$
```

#### UUIDGEN Online: 
You can use **https://www.uuidgenerator.net/** which works really well!

**You have to copy only the group 1 (*).**    

# But why and how ?

The uuid group 1 (or a LittleUuID for the intimates 😉 ) is used to get, search a module in a source, and it goes in the same without for a **ModSource** (which also has its own uuid).
For example we can get the Module object thanks to this vital functionality !

```java
ModularModule obtainedModuleByUuID = modSource.getModuleManager().findModuleByUuiD("160a3820");
```

And the tadaaa ! you have access to the whole module ! 


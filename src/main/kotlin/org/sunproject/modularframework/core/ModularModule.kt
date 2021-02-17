package org.sunproject.modularframework.core

abstract class ModularModule(_uuid : String, _name : String) {

    val uuid : String = _uuid
    lateinit var moduleName : String

    init {
        if (ModuleManager.instance!!.moduleMap.containsKey(uuid)) throw Exception("Module already instantiated !")
        if (!_name.isBlank()) moduleName = _name
        else moduleName = "Module_$uuid"
        println("New module loaded : $moduleName ! NOTE: the uuid of this module is : $uuid")

        ModuleManager.instance!!.moduleMap[uuid] = this
    }

    abstract fun enable()
    abstract fun disable() : Boolean
}
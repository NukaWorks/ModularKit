package org.sunproject.modularframework.core

abstract class ModularModule(_uuid : String, _name : String?) {

    val uuid : String = _uuid
    private var moduleName : String? = "Module_$uuid"

    init {
        if (ModuleManagement.moduleMap.containsKey(uuid)) throw Exception("Module already instantiated !")
        if (!_name.isNullOrBlank()) moduleName = _name
        println("New module loaded : $moduleName ! NOTE: the uuid of this module is : $uuid")

        ModuleManagement.moduleMap[uuid] = this
    }

    abstract fun enable()
    abstract fun disable()
}
package org.sunproject.modularframework.core

import kotlin.concurrent.thread

abstract class ModularModule(_uuid: String, _name: String) {

    val uuid: String = _uuid
    val threadName = Thread.currentThread().name
    lateinit var moduleName: String

    /**
     * @param threadName Thread name conventions : "Mod-${moduleName}#${ModuleManager.instance?.dynUuiD}_$uuid"
     */

    init {
        if (!_name.isBlank()) moduleName = _name
        else moduleName = threadName

        println(threadName)

        if (threadName != "Mod-${moduleName}#${ModuleManager.instance?.dynUuiD}_$uuid") throw Exception("You cannot execute the module outside a ModuleThread.")
        if (uuid.length > 8 || uuid.isBlank()) throw Exception("UUiD group 1 is invalid !")
        if (ModuleManager.instance!!.moduleMap.containsKey(uuid)) throw Exception("Module already instantiated !")

        println("New module loaded : $moduleName ! NOTE: the uuid of this module is : $uuid")

        ModuleManager.instance!!.moduleMap[uuid] = this // Store the module to the moduleMap
    }
}
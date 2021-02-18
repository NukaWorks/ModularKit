package org.sunproject.modularframework.core

import org.sunproject.modularframework.modules.ModuleTest
import java.util.*
import kotlin.collections.HashMap

open class ModuleManager private constructor() {

    var moduleMap = HashMap<String, ModularModule>()

    var dynUuiD = "no generated yet."

    companion object {
        var instance : ModuleManager? = null
            get() {
                if (field == null) field = ModuleManager()
                return field
            }
    }

    fun findModuleByUUiD(_uuid: String): ModularModule? {
        moduleMap.forEach { if (it.key == _uuid) return it.value }
        return null
    }

    fun refreshDynUuiD() {
        dynUuiD = UUID.randomUUID().toString().split('-')[1]
    }

    fun runModule(module: Class<ModularModule>) {
        val runModule = module.kotlin as ModularModule
        var runThread = Thread {
           module.javaClass.newInstance()
        }

        runThread.isDaemon = true
        runThread.name = "Mod-${runModule.moduleName}#${dynUuiD}_${runModule.uuid}"
    }

    /*
    fun removeModule(module: ModularModule) {
        if (moduleMap.containsValue(module)) module.disable() && moduleMap.remove(module.uuid, module)
    }

    fun runModule(module: ModularModule) {
        var runThread = Thread {module.enable()}
    }

    fun stopModule(module: ModularModule) {
        var stopThread = Thread {module.disable()}
    }*/

}
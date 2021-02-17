package org.sunproject.modularframework.core

open class ModuleManager private constructor() {

    var moduleMap = HashMap<String, ModularModule>()

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

    fun removeModule(module: ModularModule) {
        if (moduleMap.containsValue(module)) module.disable() && moduleMap.remove(module.uuid, module)
    }
}
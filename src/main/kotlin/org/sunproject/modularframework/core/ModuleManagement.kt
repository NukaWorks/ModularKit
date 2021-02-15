package org.sunproject.modularframework.core

object ModuleManagement {

    var moduleMap = HashMap<String, ModularModule>()

    fun findModuleByUUiD(_uuid: String): ModularModule? {
        moduleMap.forEach { if (it.key == _uuid) return it.value }
        return null
    }
}
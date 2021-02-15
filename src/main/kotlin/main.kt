import org.sunproject.modularframework.core.ModularFramework
import org.sunproject.modularframework.core.ModuleManagement
import org.sunproject.modularframework.modules.ModuleTest

fun main(args: Array<String>) {
    ModularFramework.ModularInstance

    ModuleTest()

    // Init the manager

    ModuleManagement.moduleMap.keys.forEach { println(it) }


    // println(ModuleManagement.findModuleByUUiD())
}
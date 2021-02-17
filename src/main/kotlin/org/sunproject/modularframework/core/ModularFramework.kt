package org.sunproject.modularframework.core

import java.io.File

/**
 * Main Class of {@link ModularFramework} project.
 */
open class ModularFramework private constructor() {

    companion object ModularInstance {
       protected var objects : ModularFramework? = null
            get() {
                if (field == null) objects = ModularFramework()
                return field
            }

       // Faisons un test pour charger les modules ...

        init {
            var mainSource = ModuleSource(File("temp/modules"))
            var manager = ModuleManager.instance
        }

    }
}
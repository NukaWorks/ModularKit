package org.sunproject.modularframework.modules

import org.sunproject.modularframework.core.ModularModule

/**
 * ModuleTest example
 * @author Sundev79 <sundev79.sunproject@gmail.com>
 * Tool for generate new UUiD : https://www.uuidgenerator.net/version4
 * You need to use the group 1 of the UUiD : XXXXXXXX-0000-0000-0000-000000000000
 */

class ModuleTest : ModularModule("4dba2e7f", "Test") {

    init {
        println("Module created !")
    }
}
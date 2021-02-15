package org.sunproject.modularframework.core

import java.io.File
import java.nio.file.Files

class ModuleSource(_path : File) {

    init {
        Files.walk(_path.toPath()).forEach { println(it) }
    }


}
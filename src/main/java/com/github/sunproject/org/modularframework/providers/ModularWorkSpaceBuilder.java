package com.github.sunproject.org.modularframework.providers;

import com.github.sunproject.org.modularframework.init.ModularInit;
import com.github.sunproject.org.modularframework.console.ModularLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Modular Workspace builder.
 * @since 1.0
 */

public class ModularWorkSpaceBuilder {

    private ModularLog console = ModularInit.getConsole();
    private HashMap<String, File> dirs = new HashMap<>();

    public enum dirsName {
        MODULES, TMP
    }

    public ModularWorkSpaceBuilder(File workDir) throws FileNotFoundException {
        if (!dirs.isEmpty()) dirs.clear();
        for (dirsName fDrNm : dirsName.values()) dirs.put(fDrNm.name(), new File(workDir, fDrNm.name()));

        if (!workDir.exists()) {
            console.log("Creating Workspace ...");
            if (!workDir.mkdir()) throw new FileNotFoundException();
        }

        for (Map.Entry<String, File> entry : dirs.entrySet()) {
            File child = entry.getValue();
            if (!child.exists()) {
                console.log("Creating " + child.getAbsolutePath() + " ...");
                if (!child.mkdir()) throw new FileNotFoundException();
            }
        }
    }

    public HashMap<String, File> getWorkspaceDirs() {
        return dirs;
    }
}
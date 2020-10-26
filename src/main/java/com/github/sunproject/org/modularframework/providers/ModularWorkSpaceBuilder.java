package com.github.sunproject.org.modularframework.providers;

import com.github.sunproject.org.modularframework.utils.OSUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class ModularWorkSpaceBuilder {
    public static void initDefaultWorkSpace() throws FileNotFoundException {
        // Create a directory for store config files
        File workDir = OSUtil.getWorkSpacePath();
        File[] dirs = {
                new File(workDir, "/tmp"),
                new File(workDir, "/modules")
        };

        if (!workDir.exists()) {
            System.out.println("Creating Workspace ...");
            workDir.mkdir();
        }

        for (File child : dirs) {
            if (!child.exists()) {
                System.out.println("Creating " + child.getAbsolutePath() + " ...");
                child.mkdir();
            }
        }
    }
}

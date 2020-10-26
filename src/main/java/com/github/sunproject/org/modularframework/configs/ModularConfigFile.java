package com.github.sunproject.org.modularframework.configs;


import com.github.sunproject.org.modularframework.utils.Logger;
import com.github.sunproject.org.modularframework.utils.OSUtil;

import java.io.*;
import java.util.Properties;

@SuppressWarnings("ResultOfMethodCallIgnored")

public class ModularConfigFile {

    public static String username;
    public static String ram;
    public static String gameWidth;
    public static String gameHeight;

    private static Properties prop;
    private static OutputStream output;
    private static InputStream input;
    private static String cfgFileName = "/config.cfg";

    public static void loadUserProps() throws Exception {
        File userProps = new File(OSUtil.getWorkSpacePath() + "/config.cfg");
        if (userProps.exists()) Logger.log("config file is found !");
        else {
            try {
                userProps.getParentFile().mkdirs();
                userProps.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Logger.log("Creating user Properties folder...");
            ModularConfigFile.initDefaultProps();
        }
        ModularConfigFile.loadProps();
    }

    private static void initDefaultProps() throws Exception {
        prop = new Properties();
        try {
            output = new FileOutputStream(OSUtil.getWorkSpacePath() + "/config.cfg");
            prop.setProperty("accountName", "Username");
            prop.setProperty("ramAllowed", "512M");
            prop.setProperty("gameWidth", "854");
            prop.setProperty("gameHeight", "480");
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void saveProps() throws Exception {
        prop = new Properties();
        try {
            output = new FileOutputStream(OSUtil.getWorkSpacePath() + cfgFileName);
//            Logger.log("Profile " + MineBootAccount.getUsername() + ", is successfully saved !");
//            prop.setProperty("accountName", MineBootAccount.getUsername());
//            prop.setProperty("ramAllowed", MineBootAccount.getRam());

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void loadProps() throws Exception {
        prop = new Properties();
        try {
            input = new FileInputStream(OSUtil.getWorkSpacePath() + cfgFileName);
            prop.load(input);
            Logger.log("Hi " + prop.getProperty("accountName") + " !");
            username = prop.getProperty("accountName");
//            MineBootAccount.setUsername(prop.getProperty("accountName"));
            Logger.log("Memory Allowed : " + prop.getProperty("ramAllowed"));
            ram = prop.getProperty("ramAllowed");
//            MineBootAccount.setRam(prop.getProperty("ramAllowed"));
            gameWidth = prop.getProperty("gameWidth");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @SuppressWarnings("unused")
    public static String getUsername() {
        return username;
    }

    @SuppressWarnings("unused")
    public static void setUsername(String username) {
        ModularConfigFile.username = username;
    }

    @SuppressWarnings("unused")
    public static String getRam() {
        return ram;
    }

    @SuppressWarnings("unused")
    public static void setRam(String ram) {
        ModularConfigFile.ram = ram;
    }
}

package com.github.sunproject.org.modularframework.providers;

import com.github.sunproject.org.modularframework.events.ModularEventHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * The Module File Watcher.
 */

public class ModularFileWatcher {
    private final Thread fileWatchThread;
    private Path dirPath;
    public ModularFileWatcher(Path dirPath, ModularEventHandler eventHandler) throws IOException {
        this.dirPath = dirPath;
        WatchService watchService = dirPath.getFileSystem().newWatchService();

        WatchKey watchKey = dirPath.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);

        fileWatchThread = new Thread(() -> {
            while (true) {
                watchKey.pollEvents().forEach(e -> {
                    eventHandler.onEvent();
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                watchKey.reset();

            }
        });

        fileWatchThread.setDaemon(true);
    }

    public void startFileWatcher() {
    	System.out.println("Starting-up FileWather on: " + dirPath + " ...");
        fileWatchThread.start();
    }

    public void stopFileWatcher() {
        System.out.println("Stopping fileWatcher ...");
        fileWatchThread.stop();
    }

}

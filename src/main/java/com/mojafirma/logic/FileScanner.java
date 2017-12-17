package com.mojafirma.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileScanner implements Runnable {


    public void run() {

        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get("C:/Users/Bartek/IdeaProjects/ExchangeRatesProject/Data Files");
            dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);

            System.out.println("Watch Service registered for dir: " + dir.getFileName());

            boolean ifFileCreate = false;

            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    File file = new File(dir + "/" + String.valueOf(fileName));
                    System.out.println(kind.name() + ": " + file.getAbsolutePath());

                    String fileExtension = "";

                    int i = String.valueOf(fileName).lastIndexOf('.');
                    if (i > 0) {
                        fileExtension = String.valueOf(fileName).substring(i+1);
                    }

                    if (kind == ENTRY_CREATE && fileExtension.equals("xml")) {
                        ifFileCreate = true;
                    }

                    if (kind == ENTRY_MODIFY && ifFileCreate == true && fileExtension.equals("xml")) {
                        FileReader fileReader = new FileReader();
                        fileReader.readFile(file);
                        ifFileCreate = false;
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

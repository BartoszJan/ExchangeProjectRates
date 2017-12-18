package com.mojafirma;

import com.mojafirma.gui.MainPanel;
import com.mojafirma.logic.FileScanner;

public class Main {
    public static void main(String[] args) {

        new Thread(new FileScanner()).start();

        MainPanel mainPanel = new MainPanel();
    }
}

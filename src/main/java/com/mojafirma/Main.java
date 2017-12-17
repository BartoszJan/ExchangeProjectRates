package com.mojafirma;

import com.mojafirma.gui.MainPanel;
import com.mojafirma.logic.FileScanner;
import com.mojafirma.model.dao.CurrencyDao;
import com.mojafirma.utility.HibernateUtility;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        new Thread(new FileScanner()).start();

        MainPanel mainPanel = new MainPanel();
    }
}

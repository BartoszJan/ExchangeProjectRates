package com.mojafirma.model.dao;


import com.mojafirma.model.CurrencyModelDB;
import com.mojafirma.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CurrencyDao {

    public void addCurrrency(CurrencyModelDB currency) {

        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(currency);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public CurrencyModelDB getCurrency(Integer currencyId) {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        CurrencyModelDB currency = null;
        try {
            tx = session.beginTransaction();
            currency = session.get(CurrencyModelDB.class, currencyId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return currency;
    }

    public List<String> getCurrencyList() {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        List<String> currencyList = null;
        try {
            tx = session.beginTransaction();
            currencyList = (List<String>) session.createQuery("SELECT DISTINCT name FROM CurrencyModelDB").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return currencyList;
    }

    public void deleteCurrency(CurrencyModelDB currency) {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(currency);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

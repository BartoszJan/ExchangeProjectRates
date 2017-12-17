package com.mojafirma.model.dao;

import com.mojafirma.model.RatesTableModelDB;
import com.mojafirma.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class RatesTableDao {

    public void addRatesTable(RatesTableModelDB ratesTable) {

        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(ratesTable);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public RatesTableModelDB getCurrency(Integer ratesTableId) {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        RatesTableModelDB ratesTable = null;
        try {
            tx = session.beginTransaction();
            ratesTable = session.get(RatesTableModelDB.class, ratesTableId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ratesTable;
    }

    public List<RatesTableModelDB> getRatesTableList() {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        List<RatesTableModelDB> ratesTableList = null;
        try {
            tx = session.beginTransaction();
            ratesTableList = session.createQuery("FROM RatesTableModelDB").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ratesTableList;
    }

    public List<LocalDate> getPublicationDateList() {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        List<LocalDate> datesList = null;
        try {
            tx = session.beginTransaction();
            datesList = session.createQuery("Select publicationDate FROM RatesTableModelDB").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return datesList;
    }

    public void deleteRatesTable(RatesTableModelDB ratesTable) {
        Session session = HibernateUtility.getHibernateSession().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ratesTable);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

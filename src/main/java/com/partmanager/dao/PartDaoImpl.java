package com.partmanager.dao;

import com.partmanager.utility.PartNameNotFoundException;
import com.partmanager.model.Part;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PartDaoImpl implements PartDao {
    private static final Logger logger= LoggerFactory.getLogger(PartDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
        logger.info("Part successfully updated. Part details: "+part);
    }

    @Override
    public void updatePart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        logger.info("Part successfully updated. Part details: "+ part);
    }

    @Override
    public void removePart(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, new Integer(id));

        if(part!=null){
            session.delete(part);
            logger.info("Part successfully removed. Part details: "+ part);
        }
    }

    @Override
    public Part getPartById(int id) {
        Session session =sessionFactory.getCurrentSession();
        Part part= (Part) session.load(Part.class, new Integer(id));
        logger.info("Part successfully loaded. Part details: "+ part);
        return part;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> getListParts() {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from Part";
        List<Part> partList = session.createQuery(sqlQuery).list();
        for(Part part:partList){
            logger.info("Part list: "+ part);
        }
        return partList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> findPartByName(String partName) {

        Session session=sessionFactory.getCurrentSession();
        List<Part> partList = findPartBySomething(session, "partName", partName);
        logger.info("Parts successfully find by Name.");

        return partList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> findPartBуNeed(int need) {
        Session session=sessionFactory.getCurrentSession();
        List <Part> listParts;
        switch (need){
            case 1: listParts = findPartBySomething(session, "partNeed", "1");
                logger.info("Parts successfully find by Part Need.");
                break;
            case 2: listParts = findPartBySomething(session, "partNeed", "0");
                logger.info("Parts successfully find by Part Option");
                break;
            default: listParts = getListParts();
        }

        return listParts;
    }

    @SuppressWarnings("unchecked")
    public List<Part> findPartBySomething(Session session, String nameSearch, String valueSearch){
        List<Part> listParts= new ArrayList<>();
        try {
            listParts=session.createQuery("from Part  where "+nameSearch+"= :"+nameSearch).setString(nameSearch,valueSearch).list();

            if(listParts.size()==0){
                throw new PartNameNotFoundException("Sorry....parts :"+valueSearch+": not found");
            }

        }
        catch (PartNameNotFoundException e){
            e.printStackTrace();
        }
        return listParts;
    }
}

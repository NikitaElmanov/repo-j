package org.arpit.java2blog.dao;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.StaticShape;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaticShapeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<StaticShape> getAllStaticShapes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<StaticShape> shapeList = session.createQuery("from StaticShape").list();
        return shapeList;
    }

    public StaticShape getStaticShape(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        StaticShape staticShape = (StaticShape) session.get(StaticShape.class, new Integer(id));
        return staticShape;
    }

    public StaticShape addStaticShape(StaticShape staticShape) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(staticShape);
        return staticShape;
    }

    public void updateStaticShape(StaticShape staticShape) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(staticShape);
    }

    public void deleteStaticShape(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        StaticShape p = (StaticShape) session.load(Country.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }

}

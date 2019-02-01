package org.arpit.java2blog.dao;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.Flag;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class FlagDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Flag> getAllFlags() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Flag> flagList = session.createQuery("from Flag").list();
        return flagList;
    }

    public List<String> getAllNames(){
        Session session = this.sessionFactory.getCurrentSession();
        List<String> flagList = session.createSQLQuery("select shape from flag").list();
        return flagList;
    }

    public Flag getFlag(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Flag flag = (Flag) session.get(Flag.class, id);
        return flag;
    }

    public Flag addFlag(Flag flag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(flag);
        return flag;
    }

    public void updateFlag(Flag flag) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(flag);
    }

    public void deleteFlag(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Flag p = (Flag) session.load(Flag.class, id);
        if (null != p) {
            session.delete(p);
        }
    }

    public void fillTableFlag() {
        Session session = sessionFactory.getCurrentSession();

        session.persist(new Flag("rectangle"));
        session.persist(new Flag("square"));
        session.persist(new Flag("circle"));
        session.persist(new Flag("triangle"));
    }

}

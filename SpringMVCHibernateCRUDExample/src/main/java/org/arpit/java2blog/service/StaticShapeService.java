package org.arpit.java2blog.service;

import org.arpit.java2blog.dao.FlagDao;
import org.arpit.java2blog.dao.StaticShapeDao;
import org.arpit.java2blog.model.Flag;
import org.arpit.java2blog.model.StaticShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaticShapeService {

    @Autowired
    StaticShapeDao staticShapeDao;

    @Transactional()
    public List<StaticShape> getAllStaticShapes() {
        return staticShapeDao.getAllStaticShapes();
    }

    @Transactional
    public StaticShape getStaticShape(int id) {
        return staticShapeDao.getStaticShape(id);
    }

    @Transactional
    public void addStaticShape(StaticShape staticShape) {
        staticShapeDao.addStaticShape(staticShape);
    }

    @Transactional
    public void updateStaticShape(StaticShape staticShape) {
        staticShapeDao.updateStaticShape(staticShape);
    }

    @Transactional
    public void deleteStaticShape(int id) {
        staticShapeDao.deleteStaticShape(id);
    }

}

package org.arpit.java2blog.service;

import org.arpit.java2blog.dao.CountryDAO;
import org.arpit.java2blog.dao.FlagDao;
import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.Flag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlagService {

    @Autowired
    FlagDao flagDao;

    @Transactional()
    public List<Flag> getAllFlags() {
        return flagDao.getAllFlags();
    }

    @Transactional
    public Flag getFlag(int id) {
        return flagDao.getFlag(id);
    }

    @Transactional
    public void addFlag(Flag flag) {
        flagDao.addFlag(flag);
    }

    @Transactional
    public void updateFlag(Flag flag) {
        flagDao.updateFlag(flag);
    }

    @Transactional
    public void deleteFlag(int id) {
        flagDao.deleteFlag(id);
    }

    @Transactional
    public List<String> getAllNames(){
        return flagDao.getAllNames();
    }

    @Transactional
    public void fillTableFlag() {
        flagDao.fillTableFlag();
    }
}

package com.oddle.app.repository.impl;

import com.oddle.app.model.WeatherLog;
import com.oddle.app.repository.util.AbstractGenericDao;
import com.oddle.app.repository.WeatherLogDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class WeatherLogDAOImpl extends AbstractGenericDao<WeatherLog> implements WeatherLogDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public WeatherLogDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

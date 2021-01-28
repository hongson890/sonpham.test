package com.oddle.app.test.repository;

import com.oddle.app.config.AppConfig;
import com.oddle.app.config.AppInitializer;
import com.oddle.app.config.HibernateConfiguration;
import com.oddle.app.model.OWMDetail;
import com.oddle.app.model.WeatherLog;
import com.oddle.app.repository.WeatherLogDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = {AppConfig.class, HibernateConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class WeatherLogDAOTest {

    @Autowired
    private WeatherLogDAO weatherLogDAO;


    @Test
    @Transactional
    @Rollback
    public void shouldAbleToInsertData() {

        OWMDetail detail = new OWMDetail();
        detail.setBase("base Example");

        WeatherLog weatherLog = new WeatherLog();
        weatherLog.setOWMDetail(detail);
        weatherLog.setUpdatedOn(new Date());
        weatherLogDAO.save(weatherLog);

        WeatherLog weatherLogRecord = weatherLogDAO.findById(weatherLog.getId());

        Assert.assertEquals(weatherLogRecord.getOWMDetail().getBase(), "base Example");
    }

    @Test
    @Transactional
    @Rollback
    public void shouldAbleToDeleteData() {

        OWMDetail detail = new OWMDetail();
        detail.setBase("base Example");

        WeatherLog weatherLog = new WeatherLog();
        weatherLog.setOWMDetail(detail);
        weatherLog.setUpdatedOn(new Date());
        weatherLogDAO.save(weatherLog);

        Assert.assertEquals(weatherLogDAO.findAll().size(), 1);


        WeatherLog weatherLogRecord = weatherLogDAO.findById(weatherLog.getId());
        weatherLogDAO.delete(weatherLogRecord);

        Assert.assertEquals(weatherLogDAO.findAll().size(), 0);
    }

}

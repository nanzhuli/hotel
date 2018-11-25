package com.hotel.service;

import com.hotel.model.employ;
import com.hotel.model.event;
import com.hotel.repository.employRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class employService {
    @Autowired
    employRepository employrepository;

    //假定所有的事务都只能是当天完成，所以只有当天的事务
    public employ eventMatch(int type, Timestamp t) {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date today = cal.getTime();
        Timestamp timestamp = new Timestamp((today).getTime());
        int worktime;
        if (timestamp.before(t)) {
            worktime = 2;
        } else {
            worktime = 1;
        }
        employ e = new employ();
        e.setEmployworktime(worktime);
        e.setEmployposition(type);
        e.setEmployno(0);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("type",
                ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("starttime",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("eventno");
        Example<employ> ex = Example.of(e, exampleMatcher);
        Optional<employ> worker = employrepository.findOne(ex);
        return worker.orElse(e);
    }

}

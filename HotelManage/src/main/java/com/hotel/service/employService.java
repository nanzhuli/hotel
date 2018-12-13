package com.hotel.service;

import com.hotel.model.Employ;
import com.hotel.repository.employRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class employService implements UserDetailsService {
    @Autowired
    employRepository employrepository;

    //登陆
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employ e = employrepository.findByUsername(s);
        if (e == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        String st = "server1";
//        System.out.println(BCrypt.hashpw(st, BCrypt.gensalt()));
        System.out.println("s:"+s);
        System.out.println("username:"+e.getUsername()+";password:"+e.getPassword());
        return e;
    }

    //假定所有的事务都只能是当天完成，所以只有当天的事务
    public Employ eventMatch(int type,Timestamp t) {
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
        Employ e = new Employ();
        e.setEmployworktime(worktime);
        e.setEmployposition(type);
        e.setEmployno(0);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("employworktime",
                ExampleMatcher.GenericPropertyMatchers.contains()).withMatcher("employposition",
                ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("employno","employname",
                "employsex","employage","employpaymentpermonth","employauthority","loginname","password");
        Example<Employ> ex = Example.of(e, exampleMatcher);
        Optional<Employ> worker = employrepository.findOne(ex);
        return worker.orElse(e);
    }

    public List<Employ> findAll() {
        return employrepository.findAll();
    }

    public Employ findByEmployno(int employno) {
        return employrepository.findById(employno).orElse(null);

    }

    public Employ save(Employ e) {
        return employrepository.save(e);
    }

    public void delete(Employ e) {
        employrepository.delete(e);
    }

}

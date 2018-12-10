package com.hotel.controller;

import com.hotel.model.employ;
import com.hotel.model.result;
import com.hotel.other.resultReturn;
import com.hotel.service.employService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class employController {
    @Autowired
    employService employservice;

    @RequestMapping("/employ/list")
    public result<employ> employList() {
        System.out.println(employservice.findAll());
        return resultReturn.success(employservice.findAll());
    }

    @RequestMapping("/employ/searchOne/{employno}")
    public result employSearchOne(@PathVariable("employno") int employno) {
        employ r = employservice.findByEmployno(employno);
        if(r == null) {
            return resultReturn.error(0,"it's not exist, you can't delete!");
        }
        else {
            return resultReturn.success(r);
        }
    }

    @RequestMapping("/employ/add")
    public result employAdd(@RequestParam("employno")int employno, @RequestParam("employname") String employname,
                            @RequestParam("employsex")int employsex, @RequestParam("employage") int employage,
                            @RequestParam("employposition")int employposition,
                            @RequestParam("employauthority") int employauthority,
                            @RequestParam("employpaymentpermonth")int employpaymentpermonth,
                            @RequestParam("employworktime") int employworktime) {
        employ e = employservice.findByEmployno(employno);
        if(e!=null)
            return resultReturn.error(0,"that employno arleady exist");
        else{
            e = saveEmploy(employno,employname,employsex,employage,employposition,employauthority,
                    employpaymentpermonth,  employworktime);
            return resultReturn.success(employservice.save(e));
        }
    }

    @RequestMapping("/employ/update/{employno}")
    public result employUpdate(@PathVariable("employno")int employno, @RequestParam("employname") String employname,
                               @RequestParam("employsex")int employsex, @RequestParam("employage") int employage,
                               @RequestParam("employposition")int employposition,
                               @RequestParam("employauthority") int employauthority,
                               @RequestParam("employpaymentpermonth")int employpaymentpermonth,
                               @RequestParam("employworktime") int employworktime) {
        employ e = employservice.findByEmployno(employno);
            if(e==null) {
            return resultReturn.error(0,"that employno did not exist");
        }
        else{
            e = saveEmploy(employno,employname,employsex,employage,employposition,employauthority,
                    employpaymentpermonth,  employworktime);
            return resultReturn.success(employservice.save(e));
        }

    }

    @RequestMapping("/employ/delete/{employno}")
    public result employDelete(@PathVariable("employno")int employno) {
        employ e = employservice.findByEmployno(employno);
        if (e==null)
            return resultReturn.error(0,"can't find this employno");
        employservice.delete(e);
        return resultReturn.success(e);
    }

    public employ saveEmploy(int employno, String employname, int employsex, int employage,
                           int employposition, int employauthority,
                           int employpaymentpermonth, int employworktime) {
        employ e = new employ();
        e.setEmployno(employno);
        e.setEmployposition(employposition);
        e.setEmployworktime(employworktime);
        e.setEmployage(employage);
        e.setEmploysex(employsex);
        e.setEmployname(employname);
        e.setEmploypaymentpermonth(employpaymentpermonth);
        e.setEmployauthority(employauthority);
        return e;
    }


}

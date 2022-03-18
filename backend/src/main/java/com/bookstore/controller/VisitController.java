package com.bookstore.controller;

import com.bookstore.statistic.GlobalStatistic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {
    @RequestMapping("/visit")
    public long visit() {
        return GlobalStatistic.addVisit();
    }
}

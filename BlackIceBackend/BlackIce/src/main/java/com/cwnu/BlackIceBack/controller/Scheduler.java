package com.cwnu.BlackIceBack.controller;

import com.cwnu.BlackIceBack.dto.BlackIceAreaDTO;
import com.cwnu.BlackIceBack.service.CalBlackIceByAreaService;
import com.cwnu.BlackIceBack.service.CalBlackIceByHighwayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Scheduler {

    /*
    @Autowired
    private CalBlackIceByAreaService calBlackIceByAreaService;

    @PostConstruct
    private void initArea() throws Exception {
        List<BlackIceAreaDTO> blackIceAreaDTOS;

        System.out.println("Initiating First Data");
        blackIceAreaDTOS = calBlackIceByAreaService.calInfo();
        System.out.print("current length: ");
        System.out.println(blackIceAreaDTOS.size());
        System.out.println("Done!");
    }

    @Scheduled(cron = "0 15 * * * *")
    private void refreshArea() throws Exception {
        List<BlackIceAreaDTO> blackIceAreaDTOS;

        System.out.println("Refresh Data");

        calBlackIceByAreaService.wipeInfo();

        blackIceAreaDTOS = calBlackIceByAreaService.calInfo();

        System.out.print("current length: ");
        System.out.println(blackIceAreaDTOS.size());
        System.out.println("Done!");
    }
     */

    @Autowired
    private CalBlackIceByHighwayService calBlackIceByHighwayService;

    @PostConstruct
    private void initHighway() throws Exception {
        List<BlackIceAreaDTO> blackIceAreaDTOS;

        System.out.println("Initiating First Data");

        blackIceAreaDTOS = calBlackIceByHighwayService.calInfo();
        System.out.print("current length: ");
        System.out.println(blackIceAreaDTOS.size());
        System.out.println("Done!");
    }

    @Scheduled(cron = "0 18 * * * *")
    private void refreshHighway() throws Exception {
        List<BlackIceAreaDTO> blackIceAreaDTOS;

        System.out.println("Refresh Data");

        calBlackIceByHighwayService.wipeInfo();

        blackIceAreaDTOS = calBlackIceByHighwayService.calInfo();
        System.out.print("current length: ");
        System.out.println(blackIceAreaDTOS.size());
        System.out.println("Done!");
    }
}

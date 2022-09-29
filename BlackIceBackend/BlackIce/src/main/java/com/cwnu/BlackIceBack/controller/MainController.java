package com.cwnu.BlackIceBack.controller;

import com.cwnu.BlackIceBack.dto.BlackIceAreaDTO;
import com.cwnu.BlackIceBack.dto.BlackIceDTO;
import com.cwnu.BlackIceBack.dto.LocationDTO;
import com.cwnu.BlackIceBack.dto.ResponseDTO;
import com.cwnu.BlackIceBack.service.CalBlackIceByAreaService;
import com.cwnu.BlackIceBack.service.CalBlackIceByHighwayService;
import com.cwnu.BlackIceBack.service.CalBlackIceByLatLngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("blackice")
public class MainController {

    @Autowired
    private CalBlackIceByLatLngService calBlackIceByLatLngService;

    /*
    @GetMapping("")
    public ResponseEntity<?> getBlackIce(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude) {
        try {
            System.out.println("Request Location Test Info");
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLatitude(latitude);
            locationDTO.setLongitude(longitude);
            BlackIceDTO blackIceDTO = calBlackIceByLatLngService.calInfo(locationDTO);
            blackIceDTO.setRainHour("2.0");
            ResponseDTO responseDTO = ResponseDTO.builder().data(blackIceDTO).error("success").build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO responseDTO = ResponseDTO.builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
    */

    @GetMapping("")
    public ResponseEntity<?> getBlackIce(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude) {
        try {
            System.out.println("Request Location Info");
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLatitude(latitude);
            locationDTO.setLongitude(longitude);
            BlackIceDTO blackIceDTO = calBlackIceByLatLngService.calInfo(locationDTO);
            ResponseDTO responseDTO = ResponseDTO.builder().data(blackIceDTO).error("success").build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO responseDTO = ResponseDTO.builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    /*

    @Autowired
    private CalBlackIceByAreaService calBlackIceByAreaService;

    @GetMapping("all")
    public ResponseEntity<?> getAllBlackIce() {
        try {
            System.out.println("Request All Info");
            List<BlackIceAreaDTO> blackIceAreaDTOS = calBlackIceByAreaService.getInfo();
            ResponseDTO responseDTO = ResponseDTO.builder().data(blackIceAreaDTOS).error("success").build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO responseDTO = ResponseDTO.builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

     */

    @Autowired
    private CalBlackIceByHighwayService calBlackIceByHighwayService;

    @GetMapping("highway")
    public ResponseEntity<?> getHighwayBlackIce() {
        try {
            System.out.println("Request Highway Info");
            List<BlackIceAreaDTO> blackIceAreaDTOS = calBlackIceByHighwayService.getInfo();
            ResponseDTO responseDTO = ResponseDTO.builder().data(blackIceAreaDTOS).error("success").build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO responseDTO = ResponseDTO.builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
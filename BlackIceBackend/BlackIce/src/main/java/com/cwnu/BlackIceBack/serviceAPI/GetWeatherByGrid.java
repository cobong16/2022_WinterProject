package com.cwnu.BlackIceBack.serviceAPI;

import com.cwnu.BlackIceBack.model.GridXYModel;
import com.cwnu.BlackIceBack.model.WeatherModel;
import com.cwnu.BlackIceBack.service.ConvertGPSService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@Service
@Slf4j
public class GetWeatherByGrid {

    @Retryable(value = {Exception.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public WeatherModel getInfo(GridXYModel gridXYModel) throws Exception {
        final String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        // TODO 공공데이터 API 키 기입
        final String apiKey = "공공데이터 API키";
        final String apiType = "JSON";

        // API의 제공시간은 매 정시 40분 마다 제공하므로 base_time 조정
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        String apiDate = dateFormat.format(calendar.getTime());
        String apiTime = timeFormat.format(calendar.getTime());

        String minute_pattern = "..[0-3].";
        String hour_pattern = "0{2}..";

        if (Pattern.matches(minute_pattern, apiTime) && Pattern.matches(hour_pattern, apiTime)) {
            // 자정이면서 00분~39분인경우
            calendar.add(Calendar.DATE, -1);
            apiDate = dateFormat.format(calendar.getTime());
            apiTime = "2300";
        } else if (Pattern.matches(minute_pattern, apiTime)) {
            // 자정이 아니면서 00분~39분인경우
            calendar.add(Calendar.HOUR, -1);
            apiTime = hourFormat.format(calendar.getTime()) + "00";
        } else {
            // 자정이 아니면서 40분~59분인경우
            apiTime = hourFormat.format(calendar.getTime()) + "00";
        }

        WeatherModel weatherModel = new WeatherModel();

        // url 합치기
        String urlBuilder = apiUrl + "?serviceKey=" + apiKey +
                "&dataType=" + apiType +
                "&base_date=" + apiDate +
                "&base_time=" + apiTime +
                "&nx=" + gridXYModel.getGridX() +
                "&ny=" + gridXYModel.getGridY();

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        // GET 결과
        String result = sb.toString();

        // 문자열 JSON으로 파싱하기
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject parseResponse = (JSONObject) jsonObject.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItem = (JSONArray) parseItems.get("item");

        JSONObject object;
        String category;

        for (Object objects : parseItem) {
            object = (JSONObject) objects;
            category = (String) object.get("category");

            // 카테고리에 해당하는 변수에 데이터 저장
            switch (category) {
                // 기온
                case "T1H" -> weatherModel.setT1H(object.get("obsrValue").toString());
                // 1시간 강수량
                case "RN1" -> weatherModel.setRN1(object.get("obsrValue").toString());
                // PTY 강수형태
                // 0 - 없음, 1 - 비, 2 - 비/눈, 3 - 눈
                // 4 - 소나기, 5 - 빗방울, 6 - 빗방울/눈날림, 7 - 눈날림
                case "PTY" -> weatherModel.setPTY(object.get("obsrValue").toString());
            }
        }

        weatherModel.setGridX(gridXYModel.getGridX());
        weatherModel.setGridY(gridXYModel.getGridY());

        return weatherModel;
    }

    @Recover
    public WeatherModel exceptionRecover(Exception e, GridXYModel gridXYModel) {
        WeatherModel weatherModel = new WeatherModel();

        weatherModel.setT1H("0");
        weatherModel.setRN1("0");
        weatherModel.setPTY("-1");
        weatherModel.setGridX(gridXYModel.getGridX());
        weatherModel.setGridY(gridXYModel.getGridY());

        System.out.println("Handling 1 Exception, API Request Error.");

        return weatherModel;
    }

}

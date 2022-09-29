package com.cwnu.BlackIceBack.serviceAPI;

import com.cwnu.BlackIceBack.model.LocationModel;
import com.cwnu.BlackIceBack.model.ThicknessModel;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Service
@Slf4j
public class GetGeopotentialThickness {

    // api - open.meteo.ECMWF
    @Retryable(value = {Exception.class}, maxAttempts = 2, backoff = @Backoff(delay = 2000))
    public ThicknessModel getInfo(LocationModel locationModel) throws Exception {

        final String apiURL = "https://api.open-meteo.com/v1/ecmwf?";
        final String variable = "&hourly=geopotential_height_1000hPa," +
                "geopotential_height_850hPa," +
                "geopotential_height_700hPa";

        double latitude = Double.parseDouble(locationModel.getLatitude());
        double longitude = Double.parseDouble(locationModel.getLongitude());
        String strLatitude = String.format("%.4f", latitude);
        String strLongitude = String.format("%.4f", longitude);

        String urlBuilder = apiURL + "latitude=" + strLatitude + "&longitude=" + strLongitude + variable;

        URL url = new URL(urlBuilder);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        BufferedReader reader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        connection.disconnect();

        String result = builder.toString();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject parseHourly = (JSONObject) jsonObject.get("hourly");

        JSONArray parse700hPa = (JSONArray) parseHourly.get("geopotential_height_700hPa");
        JSONArray parse850hPa = (JSONArray) parseHourly.get("geopotential_height_850hPa");
        JSONArray parse1000hPa = (JSONArray) parseHourly.get("geopotential_height_1000hPa");
        JSONArray parseTime = (JSONArray) parseHourly.get("time");

        // 현재 호출 시간 비교하여 다음 예측 시간만 저장하기
        // UTC +0로 비교하기
        String apiTime = this.getApiTime();
        ThicknessModel thicknessModel = new ThicknessModel();

        for (int i = 0; i < parseTime.size(); i++) {
            String hourlyTime = (String) parseTime.get(i);

            if (hourlyTime.compareTo(apiTime) == 0) {
                //thicknessModel.setTime(hourlyTime);
                thicknessModel.setGeopotential700hPa(parse700hPa.get(i).toString());
                thicknessModel.setGeopotential850hPa(parse850hPa.get(i).toString());
                thicknessModel.setGeopotential1000hPa(parse1000hPa.get(i).toString());

                break;
            }

        }

        return thicknessModel;
    }

    @Recover
    public ThicknessModel exceptionRecover(Exception e, LocationModel locationModel) {

        ThicknessModel thicknessModel = new ThicknessModel();

        thicknessModel.setGeopotential700hPa("-999");
        thicknessModel.setGeopotential850hPa("-999");
        thicknessModel.setGeopotential1000hPa("-999");

        System.out.println("Handling 1 Exception, API Request Error.");

        return thicknessModel;
    }

    private String getApiTime() {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        dateFormat.setTimeZone(timeZone);
        DateFormat hourFormat = new SimpleDateFormat("HH", Locale.UK);
        hourFormat.setTimeZone(timeZone);

        int time = Integer.parseInt(hourFormat.format(new Date()));
        int modular = time % 3;

        String apiTime;

        // 현재 시각이 3시간 단위로 나눠지는 경우
        if (modular == 0) {
            apiTime = dateFormat.format(new Date()) + "T" + hourFormat.format(new Date()) + ":00";
        } else {
            // 현재 시각이 22시와 23시인 경우 다음 날 00시로 변환해야 함
            if (time == 22 || time == 23) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'00:00", Locale.UK);
                dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
                calendar.add(Calendar.DATE, 1);
                apiTime = dateFormat2.format(calendar.getTime());
            } else {
                // 나머지 시각에서는 다음 가장 빠른 3시간 단위의 시각으로 변환
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:00", Locale.UK);
                dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
                calendar.add(Calendar.HOUR, 3 - modular);
                apiTime = dateFormat2.format(calendar.getTime());
            }
        }

        System.out.println("Geo API TIME IS: " + apiTime);
        return apiTime;
    }

}

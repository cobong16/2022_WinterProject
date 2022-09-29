package com.cwnu.BlackIceBack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONUtil {
    @SuppressWarnings("unchecked")
    public static JSONObject getJsonStringFromMap(Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }

        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public static JSONArray getJsonArrayFromList(List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }

        return jsonArray;
    }

    public static String getJsonStringFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = getJsonArrayFromList( list );
        return jsonArray.toJSONString();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObj )
    {
        Map<String, Object> map = null;

        try {

            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return map;
    }

    public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<>();

        if( jsonArray != null )
        {
            for (Object o : jsonArray) {
                Map<String, Object> map = JSONUtil.getMapFromJsonObject((JSONObject) o);
                list.add(map);
            }
        }

        return list;
    }
}

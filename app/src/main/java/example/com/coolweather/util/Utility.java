package example.com.coolweather.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import example.com.coolweather.db.City;
import example.com.coolweather.db.County;
import example.com.coolweather.db.Province;

/**
 * Created by 陈培湟 on 2019/1/1.
 */

public class Utility {
    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces=new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject =allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response,int provincedId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities=new JSONArray(response);
                for (int i=0;i<allCities.length();i++){
                    JSONObject provinceObject =allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(provinceObject.getString("name"));
                    city.setCityCode(provinceObject.getInt("id"));
                    city.setProvinceId(provincedId);
                    city.save();
                }
                return true;
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器返回的县数据
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties=new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countyObject =allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

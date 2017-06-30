/**
 * Created by xhtc on 2017/6/28.
 */


import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * 经纬度坐标计算方法
 */
public class GPSMethod {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 原纬度
     * @param lng1 原经度
     * @param lat2 目标纬度
     * @param lng2 目标经度
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;

        return distance;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为米）
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistanceInMeter(double lat1, double lng1, double lat2,
                                            double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    /**
     * 获取当前坐标一定距离以内的经纬度值,单位米
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map<String, Double> getAround(double lat, double lon, int raidus) {
        Map<String, Double> map = new HashMap<String, Double>();

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;

        map.put("minLat", minLat);
        map.put("maxLat", maxLat);
        map.put("minLng", minLng);
        map.put("maxLng", maxLng);

        return map;
    }

    /**
     * gcj02标准坐标系转换成bd09坐标系
     * 高德 -> 百度
     * @param gg_lat 源坐标纬度
     * @param gg_lon 源坐标经度
     * @return
     */
    public Pair<Double, Double> gcj02_to_bd09(Double gg_lat, Double gg_lon){
        double x = gg_lon, y = gg_lat;
        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) + 0.000003 * cos(x * Math.PI);
        double bd_lon = z * cos(theta) + 0.0065;
        double bd_lat = z * sin(theta) + 0.006;
        return Pair.of(geoFormat(bd_lat), geoFormat(bd_lon));
    }

    /**
     * bd09标准坐标系转换成gcj02坐标系
     * 百度 -> 高德
     * @param bd_lat 源坐标纬度
     * @param bd_lon 源坐标经度
     * @return
     */
    public Pair<Double, Double> bd09_to_gcj02(Double bd_lat, Double bd_lon){
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) - 0.000003 * cos(x * Math.PI);
        double gg_lon = z * cos(theta);
        double gg_lat = z * sin(theta);
        return Pair.of(geoFormat(gg_lat), geoFormat(gg_lon));
    }

    /**
     * 经纬度转换成小数点后八位
     *
     * @param d
     * @return
     */
    private double geoFormat(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(8, BigDecimal.ROUND_CEILING).doubleValue();
    }


    /**
     * 关键字：华星现代产业园F座 (经度,纬度)
     * 高德地图坐标：GCJ-02标准 120.121663,30.274788
     * 百度地图坐标：BD-09标准  120.127832,30.281228
     *
     * Point: x:经度 y:纬度
     * 百度api给出的结果： x是经度，y是纬度
     */
    public static void main(String[] args) {
        GPSMethod method = new GPSMethod();

        BigDecimal bdLng = BigDecimal.valueOf(120.127832);
        BigDecimal bdLat = BigDecimal.valueOf(30.281228);
        BigDecimal gdLng = BigDecimal.valueOf(120.121663);
        BigDecimal gdLat = BigDecimal.valueOf(30.274788);

        Pair<Double, Double> pair = method.gcj02_to_bd09(gdLat.doubleValue(), gdLng.doubleValue());
        System.out.println("经度:" + pair.getRight());
        System.out.println("纬度" + pair.getLeft());
        Double distance2 = getDistanceInMeter(pair.getLeft(), pair.getRight(), bdLat.doubleValue(), bdLng.doubleValue());
        System.out.println("方法转换高德数据后的坐标和百度直接定位的距离误差(M)：" + distance2);

    }

}

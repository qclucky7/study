package com.qingchen.study.redis;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;

/**
 * @ClassName RedisZSet
 * @description:
 * @author: WangChen
 * @create: 2020-04-17 13:38
 **/
@Service
public class RedisZSet {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void reids(){
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        SetOperations<String, Object> stringObjectSetOperations = redisTemplate.opsForSet();
        ZSetOperations<String, Object> stringObjectZSetOperations = redisTemplate.opsForZSet();
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        ListOperations<String, Object> stringObjectListOperations = redisTemplate.opsForList();
        //StreamOperations<String, Object, Object> stringObjectObjectStreamOperations = redisTemplate.opsForStream();
        ClusterOperations<String, Object> stringObjectClusterOperations = redisTemplate.opsForCluster();
        GeoOperations<String, Object> stringObjectGeoOperations = redisTemplate.opsForGeo();
        HyperLogLogOperations<String, Object> stringObjectHyperLogLogOperations = redisTemplate.opsForHyperLogLog();
        

    }


    public Boolean addZset(String key, Object value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }


    public void addZsetScore(String key, Object value, double score){
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Set<Object> reverseRank(String key){
        return this.reverseRank(key, 0, -1);
    }

    public Set<Object> reverseRank(String key, long offset, long rows){
        return redisTemplate.opsForZSet().reverseRange(key, offset, rows);
    }


    public Set<Object> reverseRankByScore(String key, double min, double max){
        return this.reverseRankByScore(key, min, max, 0, -1);
    }

    public Set<Object> reverseRankByScore(String key, double min, double max, long offset, long rows){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, rows);
    }

    public Long valueRank(String key, Object value){
        return redisTemplate.opsForZSet().rank(key, value);
    }

    public Long rangeCount(String key, double min, double max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    public Long zsetSize(String key){
        return redisTemplate.opsForZSet().zCard(key);
    }

    public void valueScore(String key, Object value){
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, -1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = Optional.ofNullable(typedTuples).orElse(Collections.emptySet())
                .iterator();

    }

    public void test(){

        Set<ZSetOperations.TypedTuple<Object>> objects = new HashSet<>();
        Set<ZSetOperations.TypedTuple<Object>> objects1 = new HashSet<>();

        DefaultTypedTuple<Object> objectDefaultTypedTuple = new DefaultTypedTuple<>("wangchen", 1.0);
        DefaultTypedTuple<Object> objectDefaultTypedTuple1 = new DefaultTypedTuple<>("wangchen1", 2.0);
        DefaultTypedTuple<Object> objectDefaultTypedTuple2 = new DefaultTypedTuple<>("wangchen2", 3.0);

        DefaultTypedTuple<Object> objectDefaultTypedTuple4 = new DefaultTypedTuple<>("wangchen", 2.0);
        objects.add(objectDefaultTypedTuple);
        objects.add(objectDefaultTypedTuple1);
        objects.add(objectDefaultTypedTuple2);

        objects1.add(objectDefaultTypedTuple4);

        redisTemplate.opsForZSet().add("mytest",objects);
        redisTemplate.opsForZSet().add("mytest1",objects1);
        List<String> objects2 = new ArrayList<>();
        objects2.add("mytest1");
        redisTemplate.opsForZSet().intersectAndStore("mytest",objects2,"mytest3", RedisZSetCommands.Aggregate.MAX);
        redisTemplate.opsForZSet().unionAndStore("mytest",objects2,"mytest4", RedisZSetCommands.Aggregate.SUM);
        Double score = redisTemplate.opsForZSet().score("mytest3", "wangchen");
        Long rank = redisTemplate.opsForZSet().rank("mytest3", "wangchen");
        System.out.println(score);
        System.out.println(rank);
    }


    public void testGeo(){

        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();
        Point point = new Point(117.2099409900, 39.1364388500);
        Point point1 = new Point(117.1630703600, 39.1590102700);
        Point point2 = new Point(117.2082565600, 39.1359229000);
//        RedisGeoCommands.GeoLocation geoLocation = new RedisGeoCommands.GeoLocation(
//                "tjStation",point
//        );
//        RedisGeoCommands.GeoLocation geoLocation1 = new RedisGeoCommands.GeoLocation(
//                "tjWestStation",point1
//        );
//        RedisGeoCommands.GeoLocation geoLocation2 = new RedisGeoCommands.GeoLocation(
//                "tjStationHouse",point2
//        );
//
//        List<RedisGeoCommands.GeoLocation> objects = new ArrayList<>();
//        objects.add(geoLocation);
//        objects.add(geoLocation1);
//        objects.add(geoLocation2);
//        geoOperations.add("tj", (Iterable) objects);

        geoOperations.add("tj", point,"tjStation");
        geoOperations.add("tj", point1,"tjWestStation");
        geoOperations.add("tj", point2,"tjStationHouse");

        Distance distance = geoOperations.distance("tj", "tjStation", "tjWestStation", Metrics.NEUTRAL);
        Distance distance1 = geoOperations.distance("tj", "tjStation", "tjStationHouse", Metrics.NEUTRAL);
        assert distance != null;
        assert distance1 != null;
        System.out.println("天津站到天津西站距离 = " + distance.getValue());
        System.out.println("天津站到天津西站综合楼 = " + distance1.getValue());

//        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults =
//                geoOperations.radius("tj", new Circle(point, new Distance(1000, Metrics.NEUTRAL)));
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        geoRadiusCommandArgs.sortDescending();
        geoRadiusCommandArgs.limit(10);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> radius = geoOperations.radius("tj", "tjStation", new Distance(1000, Metrics.NEUTRAL), geoRadiusCommandArgs);
//        Iterator<GeoResult<RedisGeoCommands.GeoLocation<Object>>> iterator = geoResults.iterator();
//        while (iterator.hasNext()){
//            GeoResult<RedisGeoCommands.GeoLocation<Object>> next = iterator.next();
//            System.out.println("对象 = " + next.getContent().toString());
//            Point point3 = next.getContent().getPoint();
//            System.out.println("point3 = " + point3);
//            System.out.println(next.getContent().getName().toString());
//            Distance distance2 = next.getDistance();
//            System.out.println("value = " + distance2.getValue());
//            System.out.println("getNormalizedValue = " + distance2.getNormalizedValue());
//            System.out.println("getUnit = " + distance2.getUnit());
//            System.out.println("in = " + distance2.in(Metrics.NEUTRAL));
//        }
        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults =
                geoOperations.radius("tj", "tjStation", new Distance(1000, Metrics.NEUTRAL));
        assert geoResults != null;
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> geoResult : geoResults) {
            System.out.println("对象 = " + geoResult.getContent().toString());
            Point point3 = geoResult.getContent().getPoint(); //这不取不到范围内对象的经纬度啊, 只能拿到存进去的对象?
            System.out.println("point3 = " + point3);
            System.out.println(geoResult.getContent().getName().toString()); //能拿到存进去的对象
            Distance distance2 = geoResult.getDistance();
            System.out.println("value = " + distance2.getValue());
            System.out.println("getNormalizedValue = " + distance2.getNormalizedValue());
            System.out.println("getUnit = " + distance2.getUnit());
            System.out.println("in = " + distance2.in(Metrics.NEUTRAL));
        }

//        List<Point> position = geoOperations.position("tj", "tjStation");
//        assert position != null;
//        System.out.println("pos = " + position.toString());
//
//        List<String> hash = geoOperations.hash("tj", "tjStation");
//        assert hash != null;
//        System.out.println(hash.toString());

    }
}


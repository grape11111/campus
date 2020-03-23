package com.gdut.imis.campus.utils;



import com.gdut.imis.campus.model.JobWithBLOBs;

import java.util.*;

public class RecommendUtil {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
    int[] tempArray = null;

    public double getSimilarity(String source,String target){
        for(Character sch:source.toCharArray()){
            if(vectorMap.containsKey(sch)){
                vectorMap.get(sch)[0]++;
            }
            //用将字符转化为向量
            else{
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(sch, tempArray);
            }
        }

        for(Character tch:target.toCharArray()){
            if(vectorMap.containsKey(tch)){
                vectorMap.get(tch)[1]++;
            }
            //用将字符转化为向量
            else{
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(tch, tempArray);
            }
        }
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }


    // 求平方和
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    // 点乘法
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }


    /**
     *计算推荐系数
     */
    public double getCoefficient(JobWithBLOBs jobWithBLOBs, String job, String province, String city, String district){
        double res=0;
        double jobSim=0;
        double addressSim=0;
        double typeSim=0;
        if(job!="" && job!=null){
            //意向职位相似度
            jobSim=this.getSimilarity(jobWithBLOBs.getName(),job);
            //System.out.println("jobSim"+jobSim);
        }
        //工作地点相似度
        if("".equals(province) || province==null){
            //地址不限制相似度为1
            addressSim=1;
        }else if(province.equals(jobWithBLOBs.getProvince())){
            if("".equals(city) || city==null){
                //同省不限城市
                addressSim=1;
            }else if(city.equals(jobWithBLOBs.getCity())){
                if("".equals(district) || district==null){
                    //同省同市不限区
                    addressSim=1;
                }else if(district.equals(jobWithBLOBs.getDistrict())){
                    //同省同市同区
                    addressSim=1;
                }else{
                    //同省同市不同区
                    addressSim=0.8;
                }
            }else{
                //同省不同市
                addressSim=0.5;
            }
        }else{
            //不同省不同市
            addressSim=0.2;
        }
        //System.out.println("add"+addressSim);
        //推荐度计算
        res=0.5*typeSim+0.3*jobSim+0.2*addressSim;
        return res;
    }

    /**
     *对map进行排序
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

}

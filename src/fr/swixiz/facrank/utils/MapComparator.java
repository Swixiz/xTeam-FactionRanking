package fr.swixiz.facrank.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Project: xTeam - FactionRanking
 * Date: 25/03/2017
 * Copyright 2017 - Agent_Aqua_
 */
public class MapComparator implements Comparator<String>{

    private Map<String, Integer> map;

    public MapComparator(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(String o1, String o2) {
        if(map.get(o1) >= map.get(o2)){
            return -1;
        }else{
            return 1;
        }
    }
}

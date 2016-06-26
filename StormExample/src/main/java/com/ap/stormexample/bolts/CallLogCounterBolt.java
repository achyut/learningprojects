/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.stormexample.bolts;

import java.util.HashMap;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 *
 * @author ananda
 */
public class CallLogCounterBolt implements IRichBolt{
    private OutputCollector collector;
    private Map<String,Integer> countermap;
    
    @Override
    public void prepare(Map map, TopologyContext tc, OutputCollector oc) {
        this.collector = oc;
        this.countermap = new HashMap<String,Integer>();
    }

    @Override
    public void execute(Tuple tuple) {
        String call = tuple.getString(0);
        Integer duration = tuple.getInteger(1);
        if(!this.countermap.containsKey(call)){
            countermap.put(call, duration);
        }
        else{
            Integer prevcall = countermap.get(call);
            countermap.put(call, prevcall+1);
        }
        collector.ack(tuple);
    }

    @Override
    public void cleanup() {
        for(Map.Entry<String,Integer> entry:countermap.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer ofd) {
        ofd.declare(new Fields("call"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
    
}

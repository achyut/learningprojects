/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.stormexample.spouts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 *
 * @author ananda
 */
public class FakeCallLogReaderSpout implements IRichSpout{

    private SpoutOutputCollector collector;
   
    private TopologyContext context;
    private Random randomGenerator = new Random();
    private Integer idx = 0;
    
    @Override
    public void open(Map map, TopologyContext tc, SpoutOutputCollector soc) {
        this.context = tc;
        this.collector = soc;
    }

    @Override
    public void close() {
      
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    @Override
    public void nextTuple() {
        if(this.idx<=1000){
            List<String> mobileNumbers = new ArrayList<String>();
            mobileNumbers.add("1234123401");
            mobileNumbers.add("1234123402");
            mobileNumbers.add("1234123403");
            mobileNumbers.add("1234123404");
            
            Integer localIdx = 0;
            while(localIdx++ < 100 && this.idx++ <1000){
                String fromMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
                String toMobileNumber = mobileNumbers.get(randomGenerator.nextInt(4));
                while(fromMobileNumber.equals(toMobileNumber)){
                    toMobileNumber  = mobileNumbers.get(randomGenerator.nextInt(4));
                }
                
                Integer duration = randomGenerator.nextInt(60);
                this.collector.emit(new Values(fromMobileNumber,toMobileNumber,duration));
            }
        }
        
    }

    @Override
    public void ack(Object o) {
        
    }

    @Override
    public void fail(Object o) {
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer ofd) {
        ofd.declare(new Fields("from","to","duration"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
    
}

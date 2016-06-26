/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.stormexample;

import com.ap.stormexample.bolts.CallLogCounterBolt;
import com.ap.stormexample.bolts.CallLogCreatorBolt;
import com.ap.stormexample.spouts.FakeCallLogReaderSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 *
 * @author ananda
 */
public class TopologyMain {
    
    public static void main(String args[]) throws Exception{
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("call-log-reader-spout",new FakeCallLogReaderSpout());
        builder.setBolt("call-log-creator-bolt",new CallLogCreatorBolt()).shuffleGrouping("call-log-reader-spout");
        builder.setBolt("call-log-counter-bolt",new CallLogCounterBolt()).fieldsGrouping("call-log-creator-bolt",new Fields("call"));

        Config config = new Config();
        config.setDebug(false);
        
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LogAnalyserStorm", config,builder.createTopology());
        Thread.sleep(30000);
        
        cluster.shutdown();
        
    }
}

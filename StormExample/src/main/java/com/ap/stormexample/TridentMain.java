/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.stormexample;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.shade.com.google.common.collect.ImmutableList;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.testing.FeederBatchSpout;
import org.apache.storm.tuple.Values;

/**
 *
 * @author ananda
 */
public class TridentMain {
    
    public static void main(String args[]){
        TridentTopology topology = new TridentTopology();
        FeederBatchSpout testSpout = new FeederBatchSpout(ImmutableList.of("fromMobileNumber","toMobileNumber","duration"));
        topology.newStream("fixed-batch-spout",testSpout);
        testSpout.feed(ImmutableList.of(new Values("1234123401","1234123402",20)));
        
        Config conf = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("trident", conf,topology.build());
        
        
    }
}

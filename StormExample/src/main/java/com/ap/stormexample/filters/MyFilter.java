/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.stormexample.filters;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 *
 * @author ananda
 */
public class MyFilter extends BaseFilter{

    @Override
    public boolean isKeep(TridentTuple tt) {
        return tt.getInteger(1) % 2 == 0;
    }

}

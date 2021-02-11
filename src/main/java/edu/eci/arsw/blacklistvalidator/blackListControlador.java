/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

/**
 *
 * @author Acer
 */
public class blackListControlador {
    
    private int ocurrencesCount;
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    
    public blackListControlador(){
        ocurrencesCount = 0;
    }
    
    public synchronized boolean canIncrementOcurrencesCount(){
        boolean valid = false;
        if(ocurrencesCount<BLACK_LIST_ALARM_COUNT){
            ocurrencesCount++;
            valid = true;
        }
        return valid;
    }
    public synchronized boolean validar(){
        boolean valid = false;
        if(ocurrencesCount==BLACK_LIST_ALARM_COUNT){
            valid = true;
        }
        return valid;
    }
}

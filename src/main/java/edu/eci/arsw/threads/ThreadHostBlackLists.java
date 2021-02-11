/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

import edu.eci.arsw.blacklistvalidator.blackListControlador;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

/**
 *
 * @author Acer
 */
public class ThreadHostBlackLists extends Thread {
    private int a;
    private int b;
    private HostBlacklistsDataSourceFacade skds;
    private int checkedListsCount = 0;
    private int ocurrencesCount = 0;
    private String ipaddress;
    private final int BLACK_LIST_ALARM_COUNT = 5;
    private blackListControlador controlador;

    public ThreadHostBlackLists(int a, int b,HostBlacklistsDataSourceFacade skds,String ipaddress,blackListControlador controlador) {
        this.a = a;
        this.b = b;
        this.skds = skds;
        this.ipaddress = ipaddress;
        this.controlador = controlador;
    }
    public void run(){  
        for (int i=a;i<b+1 && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if(controlador.validar()){
                break;
            }
            if (skds.isInBlackListServer(i, ipaddress)){
                if(controlador.canIncrementOcurrencesCount()){
                    ocurrencesCount++;
                }
                else{
                //System.out.println("Miguel");
                    break;
                }
            }
            
        }
    }
    public synchronized int  getOcurrencesCount() {
        return ocurrencesCount;
    }

    public void setOcurrencesCount(int ocurrencesCount) {
        this.ocurrencesCount = ocurrencesCount;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }
    
    
}

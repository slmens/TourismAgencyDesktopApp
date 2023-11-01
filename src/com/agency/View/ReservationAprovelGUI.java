package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Reservation;

import javax.swing.*;

public class ReservationAprovelGUI extends JFrame {
    private JPanel wrapper;

    // RESERVASYON MODELİNDE KİMLİK EKLEME OLAYINI YAP
    // GEREKLİ KONTROLLERİ YAP TC GİRİLMİŞ Mİ FALAN DİYE
    // OKAY VERİRSE REZERVASYONU KONTROL ET DAHA ÖNCE BU USER ID İLE BU OTEL VE ODA IDSİNDE REZERVASYON VAR MI
    // OKAY VERİRSE ODANIN STOĞUNU BİR DÜŞÜR
    // MAKE RESERVATION TUŞUNA BASINCA RESERVATION KISMI KAPANSIN

    public ReservationAprovelGUI(){
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
    }
}

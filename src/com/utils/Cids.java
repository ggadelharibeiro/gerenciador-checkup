package com.utils;

public class Cids {

    public static String checarCID(String patologia){
     String cid = "";

     //Fibromialgia
     if(patologia == "Fibromialgia") { cid = "M79.7"; }

     //Ansiedade
     else if(patologia == "Ansiedade" || patologia == "TAG") { cid = "F41.1"; }

     //Depressao
     else if(patologia == "Depressao" || patologia == "Depressão") { cid = "F32"; }

     //Dor cronica
     else if(patologia == "Dor crônica" || patologia == "Dor cronica" || patologia == "Dor muscular" || patologia == "Dores musculares" || patologia == "Dor") { cid = "R52"; }

     //Alzheimer
     else if(patologia == "Alzheimer") { cid = "G30"; }

     //Parkinson
     else if(patologia == "Parkinson" || patologia == "Mal de Parkinson" || patologia == "Doença de Parkinson") { cid = "G20"; }

     //Autismo
     else if(patologia == "Autismo" || patologia == "TEA" || patologia == "Transtorno do espectro autista"){ cid = "F84 / F84.0 (Criança)"; }

     //TPB - Borderline
     else if(patologia == "TPB" || patologia == "Transtorno de personalidade Borderline" || patologia == "Borderline") { cid = "F60.3"; }

     return cid;
    }


}

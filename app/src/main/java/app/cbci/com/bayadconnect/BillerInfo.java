package app.cbci.com.bayadconnect;

import android.content.Intent;
import android.view.View;

/**
 * Created by USER on 9/7/2017.
 */
public final class BillerInfo {

    String sServiceCode,sBillerCode,sBillerName;
    int iBillerImage;


    public String getBillerName(String sServiceCode){

        switch (sServiceCode){

            case "MECOA":
                sBillerName = "Meralco";
                break;

            case "MECOP":
                sBillerName = "Meralco Kuryente Load";
                break;

            case "VIECO":
                sBillerName = "Visayan Electric Company";
                break;

            case "PLECO":
                sBillerName = "Palawan Electric Cooperative";
                break;

            case "PELC2":
                sBillerName = "Pampanga II Electric Cooperative";
                break;

            case "MWSIN":
                sBillerName = "Maynilad Water Services Inc";
                break;

            case "MWCOM":
                sBillerName = "Manila Water Company";
                break;

            case "GLOBE":
                sBillerName = "Globe";
                break;

            case "INNOV":
                sBillerName = "Innove";
                break;

            case "SMART":
                sBillerName = "Smart / Sun Cellular";
                break;

            case "PLDT6":
                sBillerName = "PLDT";
                break;

            case "SKY01":
                sBillerName = "Sky Cable";
                break;

            case "MPAY1":
                sBillerName = "NBI | PRC | DFA | Marina";
                break;

            case "PILTS":
                sBillerName = "NSO (Pilipinas Teleserv)";
                break;

            case "PHLTH":
                sBillerName = "PhilHealth";
                break;

            case "HDMF1":
                sBillerName = "Pag-ibig";
                break;

            case "CGNAL":
                sBillerName = "Cignal Mediascape";
                break;

            case "CLNK1":
                sBillerName = "Cable Link";
                break;

            case "BNKRD":
                sBillerName = "Bankard";
                break;

            case "MBCCC":
                sBillerName = "Metrobank Card Corporation";
                break;

            case "APEC1":
                sBillerName = "Albay Power and Energy Corp";
                break;

            case "DVOLT":
                sBillerName = "Davao Light";
                break;

            case "BAYAN":
                sBillerName = "Bayantel";
                break;

            case "BTCO2":
                sBillerName = "Batangas II Electric Cooperative";
                break;

            case "LGNWC":
                sBillerName = "Laguna Water";
                break;

            case "PWCOR":
                sBillerName = "Prime Water and Affiliates";
                break;

            case "SJDWD":
                sBillerName = "San Jose Del Monte Water District";
                break;

            case "BTCO1":
                sBillerName = "Batangas I Electric Cooperative";
                break;

            case "SSSC1":
                sBillerName = "SSS Contribution";
                break;

            case "HCPHL":
                sBillerName = "Home Credit";
                break;

            case "INSTA":
                sBillerName = "Instasurance";
                break;

            case "DRGPY":
                sBillerName = "Dragon Pay";
                break;

            case "AEON1":
                sBillerName =  "AEON Credit Services";
                break;

            case "CAINC":
                sBillerName =  "Cebu Pacific";
                break;

            case "ABPWR":
                sBillerName =  "Aboitiz Power";
                break;

            case "ABSMO":
                sBillerName = "ABS-CBN Mobile";
                break;

            case "ASVCA":
                sBillerName = "Asian Vision Cable";
                break;

            case "BNECO":
                sBillerName = "Benguet Electric Company";
                break;

            case "BPWWI":
                sBillerName = "BP Water Works Inc";
                break;

            case "GHWSI":
                sBillerName = "Good Hands";
                break;

            case "HWMCS":
                sBillerName = "Happy Well";
                break;

            case "INEC1":
                sBillerName = "Ilocos Norte Electric Cooperative";
                break;

            case "ILECO":
                sBillerName = "Iloilo I Electric Cooperative";
                break;

            case "LEYC2":
                sBillerName = "Leyte II Electric Cooperative Inc";
                break;

            case "NOCEC":
                sBillerName = "Negros Occidental Electric Cooperative";
                break;

            case "LUELC":
                sBillerName = "La Union Electric Cooperative";
                break;

            case "MWDIS":
                sBillerName = "Meycauayan Water District";
                break;

            case "RFID1":
                sBillerName = "Radio Frequency ID";
                break;

            case "ECNSS":
                sBillerName = "PSA Serbilis";
                break;

            case "AECOR":
                sBillerName = "Angeles Electric Corporation";
                break;

            case "ANTEC":
                sBillerName = "Antique Electric Cooperative";
                break;

            case "CELCO":
                sBillerName = "Cagayan I Electric Cooperative";
                break;

            case "CEPCO":
                sBillerName = "Cagayan Electric Power and Light Co. Inc";
                break;

            case "ISCL1":
                sBillerName = "Isabela I Electric Cooperative";
                break;

            case "ISCL2":
                sBillerName = "Isabela II Electric Cooperative";
                break;

            case "MAREC":
                sBillerName = "Marinduque Electric Cooperative, Inc";
                break;

            case "NONEC":
                sBillerName = "Northern Negros Electric Cooperative";
                break;

            case "UBNK3":
                sBillerName = "Nueva Ecija II Electric Cooperative";
                break;

            case "NVLCO":
                sBillerName = "Nueva Vizcaya Electric Cooperative";
                break;

            case "PNLCO":
                sBillerName = "Peninsula Electric Cooperative";
                break;

            case "SJEC1":
                sBillerName = "San Jose Electric Cooperative";
                break;

            case "SORE2":
                sBillerName = "Sorsogon II Electric Cooperative";
                break;

            case "BCWD1":
                sBillerName = "Bacolod City Water District";
                break;

            case "BLIWD":
                sBillerName = "Baliwag Water District Online";
                break;

            case "CDOWD":
                sBillerName = "Cagayan de Oro Water";
                break;

            case "CWCOR":
                sBillerName = "Calapan Waterworks Corporation";
                break;

            case "CRMWD":
                sBillerName = "Carmona Water District";
                break;

            case "CARWD":
                sBillerName = "Car Car Water District";
                break;

            case "DWD01":
                sBillerName = "Dasmarinas City Water District";
                break;

            case "MABWD":
                sBillerName = "Mabalacat Water District";
                break;

            case "MALWD":
                sBillerName = "Malolos Water District";
                break;

            case "SILWD":
                sBillerName = "Silang Water District";
                break;

            case "TCWD1":
                sBillerName = "Tagaytay City Water District";
                break;

            case "TWDIS":
                sBillerName = "Tagum Water District";
                break;

            case "LARC1":
                sBillerName = "Laguna Water District Aquatech Inc";
                break;

            case "LCWD1":
                sBillerName = "Legazpi City Water District";
                break;

            case "MMDA1":
                sBillerName = "MMDA";
                break;

            default:
                sBillerName = "Biller Name Unknown";
                break;
        }

        return sBillerName;
    }

    public String getServiceCode(String sBiller){

        switch (sBiller){

            case "Meralco":
                sServiceCode = "MECOA";
                break;

            case "Meralco Kuryente Load":
                sServiceCode = "MECOP";
                break;

            case "Visayan Electric Company":
                sServiceCode = "VIECO";
                break;

            case "Palawan Electric Cooperative":
                sServiceCode = "PLECO";
                break;

            case "Pampanga II Electric Cooperative":
                sServiceCode = "PELC2";
                break;

            case "Maynilad Water Services Inc":
                sServiceCode = "MWSIN";
                break;

            case "Manila Water Company":
                sServiceCode = "MWCOM";
                break;

            case "Globe":
                sServiceCode = "GLOBE";
                break;

            case "Innove":
                sServiceCode = "INNOV";
                break;

            case "Smart / Sun Cellular":
                sServiceCode = "SMART";
                break;

            case "PLDT":
                sServiceCode = "PLDT6";
                break;

            case "Sky Cable":
                sServiceCode = "SKY01";
                break;

            case "NBI | PRC | DFA | Marina":
                sServiceCode = "MPAY1";
                break;

            case "NSO (Pilipinas Teleserv)":
                sServiceCode = "PILTS";
                break;

            case "PhilHealth":
                sServiceCode = "PHLTH";
                break;

            case "Pag-ibig":
                sServiceCode = "HDMF1";
                break;

            case "Cignal Mediascape":
                sServiceCode = "CGNAL";
                break;

            case "Cable Link":
                sServiceCode = "CLNK1";
                break;

            case "Bankard":
                sServiceCode = "BNKRD";
                break;

            case "Metrobank Card Corporation":
                sServiceCode = "MBCCC";
                break;

            case "Albay Power and Energy Corp":
                sServiceCode = "APEC1";
                break;

            case "Davao Light":
                sServiceCode = "DVOLT";
                break;

            case "Bayantel":
                sServiceCode = "BAYAN";
                break;

            case "Batangas II Electric Cooperative":
                sServiceCode = "BTCO2";
                break;

            case "Laguna Water":
                sServiceCode = "LGNWC";
                break;

            case "Prime Water and Affiliates":
                sServiceCode = "PWCOR";
                break;

            case "San Jose Del Monte Water District":
                sServiceCode = "SJDWD";
                break;

            case "Batangas I Electric Cooperative":
                sServiceCode = "BTCO1";
                break;

            case "SSS Contribution":
                sServiceCode = "SSSC1";
                break;

            case "SSS OFW":
                sServiceCode = "SSSC1";
                break;

            case "SSS Household and Employer":
                sServiceCode = "SSSC1";
                break;

            case "Home Credit":
                sServiceCode = "HCPHL";
                break;

            case "Instasurance":
                sServiceCode = "INSTA";
                break;

            case "Dragon Pay":
                sServiceCode = "DRGPY";
                break;

            case "AEON Credit Services":
                sServiceCode = "AEON1";
                break;

            case "Cebu Pacific":
                sServiceCode = "CAINC";
                break;

            case "Aboitiz Power":
                sServiceCode =  "ABPWR";
                break;

            case "ABS-CBN Mobile":
                sServiceCode = "ABSMO";
                break;

            case "Asian Vision Cable":
                sServiceCode = "ASVCA";
                break;

            case "Benguet Electric Company":
                sServiceCode = "BNECO";
                break;

            case "BP Water Works Inc":
                sServiceCode = "BPWWI";
                break;

            case "Good Hands":
                sServiceCode = "GHWSI";
                break;

            case "Happy Well":
                sServiceCode = "HWMCS";
                break;

            case "Ilocos Norte Electric Cooperative":
                sServiceCode = "INEC1";
                break;

            case "Iloilo I Electric Cooperative":
                sServiceCode = "ILECO";
                break;

            case "Leyte II Electric Cooperative Inc":
                sServiceCode = "LEYC2";
                break;

            case "Negros Occidental Electric Cooperative":
                sServiceCode = "NOCEC";
                break;

            case "La Union Electric Cooperative":
                sServiceCode = "LUELC";
                break;

            case "Meycauayan Water District":
                sServiceCode = "MWDIS";
                break;

            case "Radio Frequency ID":
                sServiceCode = "RFID1";
                break;

            case "PSA Serbilis":
                sServiceCode = "ECNSS";
                break;

            case "Angeles Electric Corporation":
                sServiceCode = "AECOR";
                break;

            case "Antique Electric Cooperative":
                sServiceCode = "ANTEC";
                break;

            case "Cagayan I Electric Cooperative":
                sServiceCode = "CELCO";
                break;

            case "Cagayan Electric Power and Light Co. Inc":
                sServiceCode = "CEPCO";
                break;

            case "Isabela I Electric Cooperative":
                sServiceCode = "ISLC1";
                break;

            case "Marinduque Electric Cooperative, Inc":
                sServiceCode = "MAREC";
                break;

            case "Isabela II Electric Cooperative":
                sServiceCode = "ISLC2";
                break;

            case "Northern Negros Electric Cooperative":
                sServiceCode = "NONEC";
                break;

            case "Nueva Ecija II Electric Cooperative":
                sServiceCode = "UBNK3";
                break;

            case "Nueva Vizcaya Electric Cooperative":
                sServiceCode = "NVLCO";
                break;

            case "Peninsula Electric Cooperative":
                sServiceCode = "PNLCO";
                break;

            case "San Jose Electric Cooperative":
                sServiceCode = "SJEC1";
                break;

            case "Sorsogon II Electric Cooperative":
                sServiceCode = "SORE2";
                break;

            case "Bacolod City Water District":
                sServiceCode = "BCWD1";
                break;

            case "Baliwag Water District Online":
                sServiceCode = "BLIWD";
                break;

            case "Cagayan de Oro Water":
                sServiceCode = "CDOWD";
                break;

            case "Calapan Waterworks Corporation":
                sServiceCode = "CWCOR";
                break;

            case "Carmona Water District":
                sServiceCode = "CRMWD";
                break;

            case "Car Car Water District":
                sServiceCode = "CARWD";
                break;

            case "Dasmarinas City Water District":
                sServiceCode = "DWD01";
                break;

            case "Mabalacat Water District":
                sServiceCode = "MABWD";
                break;

            case "Malolos Water District":
                sServiceCode = "MALWD";
                break;

            case "Silang Water District":
                sServiceCode = "SILWD";
                break;

            case "Tagaytay City Water District":
                sServiceCode = "TCWD1";
                break;

            case "Tagum Water District":
                sServiceCode = "TWDIS";
                break;

            case "Laguna Water District Aquatech Inc":
                sServiceCode = "LARC1";
                break;

            case "Legazpi City Water District":
                sServiceCode = "LCWD1";
                break;

            case "MMDA":
                sServiceCode = "MMDA1";
                break;


            default:
                sServiceCode = "SERCode";
                break;
        }

        return sServiceCode;
    }

    public String getBillerCode(String sBiller){

        switch (sBiller){

            case "Meralco":
                sBillerCode = "00001";
                break;

            case "Meralco Kuryente Load":
                sBillerCode = "00001";
                break;

            case "Visayan Electric Company":
                sBillerCode = "00132";
                break;

            case "Palawan Electric Cooperative":
                sBillerCode = "00265";
                break;

            case "Pampanga II Electric Cooperative":
                sBillerCode = "00191";
                break;

            case "Maynilad Water Services Inc":
                sBillerCode = "00022";
                break;

            case "Manila Water Company":
                sBillerCode = "00015";
                break;

            case "Globe":
                sBillerCode = "00009";
                break;

            case "Innove":
                sBillerCode = "00046";
                break;

            case "Smart / Sun Cellular":
                sBillerCode = "00005";
                break;

            case "PLDT":
                sBillerCode = "00214";
                break;

            case "Sky Cable":
                sBillerCode = "00003";
                break;

            case "NBI | PRC | DFA | Marina":
                sBillerCode = "00239";
                break;

            case "PRC":
                sBillerCode = "00239";
                break;

            case "NSO (Pilipinas Teleserv)":
                sBillerCode = "00068";
                break;

            case "PhilHealth":
                sBillerCode = "00112";
                break;

            case "Pag-ibig":
                sBillerCode = "00136";
                break;

            case "Cignal Mediascape":
                sBillerCode = "00158";
                break;

            case "Cable Link":
                sBillerCode = "00030";
                break;

            case "Bankard":
                sBillerCode = "00040";
                break;

            case "Metrobank Card Corporation":
                sBillerCode = "00225";
                break;

            case "Albay Power and Energy Corp":
                sBillerCode = "00167";
                break;

            case "Davao Light":
                sBillerCode = "00114";
                break;

            case "Bayantel":
                sBillerCode = "00002";
                break;

            case "Batangas II Electric Cooperative":
                sBillerCode = "00129";
                break;

            case "Laguna Water":
                sBillerCode = "00128";
                break;

            case "Prime Water and Affiliates":
                sBillerCode = "00082";
                break;

            case "San Jose Del Monte Water District":
                sBillerCode = "00133";
                break;

            case "Batangas I Electric Cooperative":
                sBillerCode = "00074";
                break;

            case "SSS Contribution":
                sBillerCode = "00189";
                break;

            case "SSS OFW":
                sBillerCode = "00189";
                break;

            case "SSS Household and Employer":
                sBillerCode = "00189";
                break;

            case "Home Credit":
                sBillerCode = "00163";
                break;

            case "Instasurance":
                sBillerCode = "00227";
                break;

            case "Dragon Pay":
                sBillerCode = "00176";
                break;

            case "AEON Credit Services":
                sBillerCode = "00157";
                break;

            case "Cebu Pacific":
                sBillerCode = "00099";
                break;

            case "Aboitiz Power":
                sBillerCode =  "00132";
                break;

            case "ABS-CBN Mobile":
                sBillerCode =  "00186";
                break;

            case "Asian Vision Cable":
                sBillerCode = "00089";
                break;

            case "Benguet Electric Company":
                sBillerCode = "00135";
                break;

            case "BP Water Works Inc":
                sBillerCode = "00161";
                break;

            case "Good Hands":
                sBillerCode = "00160";
                break;

            case "Happy Well":
                sBillerCode = "00162";
                break;

            case "Ilocos Norte Electric Cooperative":
                sBillerCode = "00188";
                break;

            case "Iloilo I Electric Cooperative":
                sBillerCode = "00139";
                break;

            case "Leyte II Electric Cooperative Inc":
                sBillerCode = "00199";
                break;

            case "Negros Occidental Electric Cooperative":
                sBillerCode = "00233";
                break;

            case "La Union Electric Cooperative":
                sBillerCode = "00317";
                break;

            case "Meycauayan Water District":
                sBillerCode = "00088";
                break;

            case "Radio Frequency ID":
                sBillerCode = "00230";
                break;

            case "PSA Serbilis":
                sBillerCode = "00257";
                break;

            case "Angeles Electric Corporation":
                sBillerCode = "00288";
                break;

            case "Antique Electric Cooperative":
                sBillerCode = "00234";
                break;

            case "Cagayan I Electric Cooperative":
                sBillerCode = "00249";
                break;

            case "Cagayan Electric Power and Light Co. Inc":
                sBillerCode = "00251";
                break;

            case "Isabela I Electric Cooperative":
                sBillerCode = "00263";
                break;

            case "Marinduque Electric Cooperative, Inc":
                sBillerCode = "00314";
                break;

            case "Isabela II Electric Cooperative":
                sBillerCode = "00285";
                break;

            case "Northern Negros Electric Cooperative":
                sBillerCode = "00236";
                break;

            case "Nueva Ecija II Electric Cooperative":
                sBillerCode = "00208";
                break;

            case "Nueva Vizcaya Electric Cooperative":
                sBillerCode = "00256";
                break;

            case "Peninsula Electric Cooperative":
                sBillerCode = "00262";
                break;

            case "San Jose Electric Cooperative":
                sBillerCode = "00261";
                break;

            case "Sorsogon II Electric Cooperative":
                sBillerCode = "00270";
                break;

            case "Bacolod City Water District":
                sBillerCode = "00258";
                break;

            case "Baliwag Water District Online":
                sBillerCode = "00185";
                break;

            case "Cagayan de Oro Water":
                sBillerCode = "00212";
                break;

            case "Calapan Waterworks Corporation":
                sBillerCode = "00283";
                break;

            case "Carmona Water District":
                sBillerCode = "00305";
                break;

            case "Car Car Water District":
                sBillerCode = "00303";
                break;

            case "Dasmarinas City Water District":
                sBillerCode = "00268";
                break;

            case "Mabalacat Water District":
                sBillerCode = "00246";
                break;

            case "Malolos Water District":
                sBillerCode = "00224";
                break;

            case "Silang Water District":
                sBillerCode = "00171";
                break;

            case "Tagaytay City Water District":
                sBillerCode = "00216";
                break;

            case "Tagum Water District":
                sBillerCode = "00226";
                break;

            case "Laguna Water District Aquatech Inc":
                sBillerCode = "00244";
                break;

            case "Legazpi City Water District":
                sBillerCode = "00247";
                break;

            case "MMDA":
                sBillerCode = "00222";
                break;


            default:
                sBillerCode = "BILLcode";
                break;
        }

        return sBillerCode;
    }

    public int getBillerImage(String sBiller){

        switch (sBiller){

            case "Meralco":
                iBillerImage = R.drawable.img_mecoa;
                break;

            case "Meralco Kuryente Load":
                iBillerImage = R.drawable.img_kload;
                break;

            case "Visayan Electric Company":
                iBillerImage = R.drawable.img_vieco;
                break;

            case "Palawan Electric Cooperative":
                iBillerImage = R.drawable.img_palec;
                break;

            case "Pampanga II Electric Cooperative":
                iBillerImage = R.drawable.img_pelc2;
                break;

            case "Maynilad Water Services Inc":
                iBillerImage = R.drawable.img_mwsin;
                break;

            case "Manila Water Company":
                iBillerImage = R.drawable.img_mwcom;
                break;

            case "Globe":
                iBillerImage = R.drawable.img_globe;
                break;

            case "Innove":
                iBillerImage = R.drawable.img_innov;
                break;

            case "Smart / Sun Cellular":
                iBillerImage = R.drawable.img_smart;
                break;

            case "SUN":
                iBillerImage = R.drawable.img_suncl;
                break;

            case "PLDT":
                iBillerImage = R.drawable.img_pldt;
                break;

            case "Sky Cable":
                iBillerImage = R.drawable.img_sky01;
                break;

            case "NBI (National Bureau of Investigation)":
                iBillerImage = R.drawable.img_nbi;
                break;

            case "DFA (Department of Foreign Affairs)":
                iBillerImage = R.drawable.img_dfa;
                break;

            case "PRC (Professional Regulation Commission)":
                iBillerImage = R.drawable.img_prc;
                break;

            case "NSO (Pilipinas Teleserv)":
                iBillerImage = R.drawable.img_pilts;
                break;

            case "Phil Health":
                iBillerImage = R.drawable.img_phlth;
                break;

            case "Pag-ibig":
                iBillerImage = R.drawable.img_hdmf1;
                break;

            case "Cignal Mediascape":
                iBillerImage = R.drawable.img_cgnal;
                break;

            case "Cable Link":
                iBillerImage = R.drawable.img_clink;
                break;

            case "Bankard":
                iBillerImage = R.drawable.img_default;
                break;

            case "Metrobank Card Corporation":
                iBillerImage = R.drawable.img_default;
                break;

            case "Albay Power and Energy Corp":
                iBillerImage = R.drawable.img_apec1;
                break;

            case "Davao Light":
                iBillerImage = R.drawable.img_dvolt;
                break;

            case "Bayantel":
                iBillerImage = R.drawable.img_bayan;
                break;

            case "Batangas II Electric Cooperative":
                iBillerImage = R.drawable.img_btco2;
                break;

            case "Laguna Water":
                iBillerImage = R.drawable.img_lgnwc;
                break;

            case "Prime Water and Affiliates":
                iBillerImage = R.drawable.img_pwcor;
                break;

            case "San Jose Del Monte Water District":
                iBillerImage = R.drawable.img_sjdwd;
                break;

            case "Batangas I Electric Cooperative":
                iBillerImage = R.drawable.img_btco1;
                break;

            case "SSS Contribution":
                iBillerImage = R.drawable.img_sss;
                break;

            case "SSS OFW":
                iBillerImage = R.drawable.img_sss;
                break;

            case "SSS Household and Employer":
                iBillerImage = R.drawable.img_sss;
                break;

            case "Home Credit":
                iBillerImage = R.drawable.img_hcphl;
                break;

            case "Dragon Pay":
                iBillerImage = R.drawable.img_drgpy;
                break;

            case "AEON Credit Services":
                iBillerImage = R.drawable.img_aeon1;
                break;

            case "Cebu Pacific":
                iBillerImage = R.drawable.img_cainc;
                break;

            case "Aboitiz Power":
                iBillerImage = R.drawable.img_abpwr;
                break;

            case "ABS-CBN Mobile":
                iBillerImage = R.drawable.img_absmo;
                break;

            case "Asian Vision Cable":
                iBillerImage = R.drawable.img_asvca;
                break;

            case "Benguet Electric Company":
                iBillerImage = R.drawable.img_bneco;
                break;

            case "BP Water Works Inc":
                iBillerImage = R.drawable.img_bpwwi;
                break;

            case "Good Hands":
                iBillerImage = R.drawable.img_ghwsi;
                break;

            case "Happy Well":
                iBillerImage = R.drawable.img_hwmcs;
                break;

            case "Ilocos Norte Electric Cooperative":
                iBillerImage = R.drawable.img_inec1;
                break;

            case "Iloilo I Electric Cooperative":
                iBillerImage = R.drawable.img_ileco;
                break;

            case "Leyte II Electric Cooperative Inc":
                iBillerImage = R.drawable.img_leyc2;
                break;

            case "Negros Occidental Electric Cooperative":
                iBillerImage = R.drawable.img_leyc2;
                break;

            case "La Union Electric Cooperative":
                iBillerImage =  R.drawable.img_leyc2;
                break;

            case "Meycauayan Water District":
                iBillerImage = R.drawable.img_mwdis;
                break;

            case "SMART":
                iBillerImage = R.drawable.img_smart;
                break;

            case "GLOBE":
                iBillerImage = R.drawable.img_globe;
                break;

            case "PSA Serbilis":
                iBillerImage = R.drawable.img_ecnss;
                break;

            case "Angeles Electric Corporation":
                iBillerImage = R.drawable.img_default;
                break;

            case "Antique Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Cagayan I Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Cagayan Electric Power and Light Co. Inc":
                iBillerImage = R.drawable.img_default;
                break;

            case "Isabela I Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Marinduque Electric Cooperative, Inc":
                iBillerImage = R.drawable.img_default;
                break;

            case "Isabela II Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Northern Negros Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Nueva Ecija II Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Nueva Vizcaya Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Peninsula Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "San Jose Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Sorsogon II Electric Cooperative":
                iBillerImage = R.drawable.img_default;
                break;

            case "Bacolod City Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Baliwag Water District Online":
                iBillerImage = R.drawable.img_default;
                break;

            case "Cagayan de Oro Water":
                iBillerImage = R.drawable.img_default;
                break;

            case "Calapan Waterworks Corporation":
                iBillerImage = R.drawable.img_default;
                break;

            case "Carmona Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Car Car Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Dasmarinas City Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Mabalacat Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Malolos Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Silang Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Tagaytay City Water District":
               iBillerImage = R.drawable.img_default;
                break;

            case "Tagum Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "Laguna Water District Aquatech Inc":
                iBillerImage = R.drawable.img_default;
                break;

            case "Legazpi City Water District":
                iBillerImage = R.drawable.img_default;
                break;

            case "MMDA":
                iBillerImage = R.drawable.img_default;
                break;

            default:
                iBillerImage = R.drawable.img_default;
                break;


        }

        return iBillerImage;
    }




}

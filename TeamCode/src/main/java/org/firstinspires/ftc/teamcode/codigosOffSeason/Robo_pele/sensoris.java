package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class sensoris  {
    private NormalizedColorSensor ronaldo;

    public enum DetectedColor{
        RED,

        BLUE,

        GREEN,

        verbol, //verde bola

        robol, //roxo bola

        UNKNOWN
    }

    public DetectedColor  getCOR(Telemetry tl){
        NormalizedRGBA normie = ronaldo.getNormalizedColors();

        float normRed,normGreen,normBlue;

        normRed  = normie.red / normie.alpha;
        normGreen = normie.green / normie.alpha;
        normBlue = normie.blue / normie.alpha;

        tl.addData("vermio", normRed);
        tl.addData("azuli", normBlue);
        tl.addData("vdi",normGreen);

        /*
        red,green,blue
        RED =
        BLUE =
        YELLOW =
        roxo = {r = 1700, B = 3020, G = 2000}
         */
        return DetectedColor.UNKNOWN;
    }
    public void init(HardwareMap hwMap){
        ronaldo = hwMap.get(NormalizedColorSensor.class,"ronaldo");
        ronaldo.setGain(9);
    }
}

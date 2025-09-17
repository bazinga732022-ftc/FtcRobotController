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

        YELLOW,

        UNKNOWN
    }

    public DetectedColor  getCOR(Telemetry tl){
        NormalizedRGBA opa = ronaldo.getNormalizedColors();

        float normRed,normGreen,normBlue;

        normRed  = opa.red / opa.alpha;
        normGreen = opa.green / opa.alpha;
        normBlue = opa.blue / opa.alpha;

        tl.addData("vermio", normRed);
        tl.addData("azuli", normBlue);
        tl.addData("vdi",normGreen);

        /*
        red,green,blue
        RED =
        BLUE =
        YELLOW =
         */
        return DetectedColor.UNKNOWN;
    }
    public void init(HardwareMap hwMap){
        ronaldo = hwMap.get(NormalizedColorSensor.class,"ronaldo");
        ronaldo.setGain(9);
    }
}

package org.firstinspires.ftc.teamcode.DECODE.TesteSensor;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele.sensoris;

public class SensorDeCor {
     NormalizedColorSensor cor1;
     DcMotor zero;
    public void init(HardwareMap hw){
        zero = hw.get(DcMotor.class, "zero");
        cor1 = hw.get(NormalizedColorSensor.class,"cor1");
        cor1.setGain(19);
    }
    public enum DetectadColor{
        RED,

        BLUE,

        YELLOW,

        UNKNOWN
    }

  public DetectadColor getDetectadColor(Telemetry tl) {
      NormalizedRGBA colors = cor1.getNormalizedColors();
      float normRed, normBlue, normGreen;
      normRed = colors.red / colors.alpha;
      normBlue = colors.blue / colors.alpha;
      normGreen = colors.green / colors.alpha;

      tl.addData("vermelho", normRed);
      tl.addData("azul", normBlue);
      tl.addData("verde", normGreen);

        /*red,green,blue;
        RED = Verm = < 0.6 , Az < 0.14, Verd < 0.22
        YELLOW = Verm < 0.800, Az < 0.2, Verd < 1.2
        BLUE = Verm < 0.12, Az < 0.45, Verd < 0.21 =
         */
      // TODO!!: calibração das cores: calibre conforme precisar
      if (normRed < 0.3 && normBlue < 0.14 && normGreen < 0.22) {
          return DetectadColor.RED;
      } else if (normRed < .12 && normBlue < .45 && normGreen < 0.21) {
          return DetectadColor.BLUE;
      } else if (normRed < .8 && normBlue < .2 && normGreen < 1.2) {
          return DetectadColor.YELLOW;
      }
      else {
          return DetectadColor.UNKNOWN;
      }
    }
}
// Dudu e JoãoP
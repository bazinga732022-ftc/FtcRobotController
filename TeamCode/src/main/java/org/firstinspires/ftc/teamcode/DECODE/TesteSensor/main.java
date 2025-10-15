package org.firstinspires.ftc.teamcode.DECODE.TesteSensor;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.hardware.UserNameable;
@Disabled
@TeleOp (name =  "mainSensores")
public class main extends OpMode {
    DcMotor zero;
    SensorDeCor Scor = new SensorDeCor();
    SensorDeCor.DetectadColor detectadColor;


    @Override
    public void init(){
        zero = hardwareMap.get(DcMotor.class,"zero");
        Scor.init(hardwareMap);

    }
    @Override
    public void loop() {
        detectadColor = Scor.getDetectadColor(telemetry);
        telemetry.addData("cor", detectadColor);

        if (detectadColor == SensorDeCor.DetectadColor.RED) {
            zero.setPower(.4);
        } else if (detectadColor == SensorDeCor.DetectadColor.BLUE) {
            zero.setPower(-.4);
        }
        else if (detectadColor == SensorDeCor.DetectadColor.YELLOW){
            zero.setPower(1);
        } else {
            zero.setPower(0);
        }


    }
}

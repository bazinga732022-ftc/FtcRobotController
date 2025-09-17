/*
package org.firstinspires.ftc.teamcode.DECODE.protoLanc;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp
public class MainProt extends LinearOpMode {
    private DcMotor Out1, Out2;
    FuncsAct Fc = new FuncsAct();
    @Override
    public void runOpMode(){
        Out1= hardwareMap.get(DcMotor.class, "Out1");
        Out2= hardwareMap.get(DcMotor.class, "Out2");
        //Fc.init();
        waitForStart();
        while(opModeIsActive()){
            disparar();
        }
    }

    public void disparar() {
        boolean Bb = gamepad1.b;
        while (Bb) {
            Out1.setPower(1);
            Out2.setPower(1);
        }
    }
}

 */
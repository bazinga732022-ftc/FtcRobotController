package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class intake extends LinearOpMode {

    private CRServo intAke;
    public void runOpMode(){
    intAke = hardwareMap.get(CRServo.class, "intAke");

    waitForStart();
        while(opModeIsActive()){
            roDintake();
        }

    }
    public void roDintake(){
        double let = gamepad1.left_trigger;
        double ldt = gamepad1.right_trigger;
        double potencia = let - ldt;

        intAke.setPower(potencia);
    }
}

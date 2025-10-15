package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@Autonomous
public class calibracaoDaOdomo extends LinearOpMode {

    private DcMotor leftBack,leftFront,rightBack,rightFront;


    public void runOpMode(){
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while (opModeIsActive()){
            telemetria();
            mov(0.1);
        }
    }
    public void telemetria(){
        telemetry.addData("direita frontal:", rightFront.getCurrentPosition());
        telemetry.addData("Esquerda frontal:", leftFront.getCurrentPosition());
        telemetry.update();
    }
    public void mov(double potencia){
        rightFront.setTargetPosition(-1000);
        leftFront.setTargetPosition(2161);
        rightFront.setPower(potencia);
        leftFront.setPower(potencia);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(rightFront.isBusy()){
            rightBack.setPower(potencia);
        }
        if(leftFront.isBusy()){
            leftBack.setPower(potencia);
        }
    }
}

package org.firstinspires.ftc.teamcode.DECODE.Shooter;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp
public class PrendeBola extends LinearOpMode {
    public DcMotor m1, m2;
    @Override
    public void runOpMode(){
        m1 = hardwareMap.get(DcMotor.class, "DirF");
        m2 = hardwareMap.get(DcMotor.class, "EsqF");
        waitForStart();
        while(opModeIsActive()){
            prendeBola(m1, m2, 1);
        }
    }
    public void prendeBola (DcMotor mot1, DcMotor mot2, double vel){
        final boolean B = gamepad1.b;
        if (B){
            mot1.setPower(vel);
            mot2.setPower(vel);
        }
    }
}

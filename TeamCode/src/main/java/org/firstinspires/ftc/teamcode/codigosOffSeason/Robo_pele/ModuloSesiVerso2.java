package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ModuloSesiVerso2 extends LinearOpMode {
    funcsMov mov = new funcsMov();


    @Override
    public void runOpMode(){
        mov.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            funcionapeloamordedeus();
            garra();

        }
    }


    public void garra(){
        boolean Bx = gamepad1.x;
        boolean Ba = gamepad1.circle;

        if (Ba){
            mov.fechaGarra();
        }
        if (Bx){
            mov.abreGarra();
        }
    }
    public void funcionapeloamordedeus(){
        double fy = gamepad1.left_stick_y;
        double vira = gamepad1.right_stick_x;
        mov.andar(0.5,fy,vira);
    }
}

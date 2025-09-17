package org.firstinspires.ftc.teamcode.DECODE.Limelight;


import static java.lang.Math.abs;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.DECODE.protoLanc.PIDcontrolador;


import java.util.List;

@TeleOp
public class MainLimelight extends LinearOpMode {
    public Limelight3A limelight;
    public Servo S1;
    DcMotor zero;
    PIDcontrolador pid;

    @Override
    public void runOpMode() {
        S1 = hardwareMap.get(Servo.class, "S1");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        zero = hardwareMap.get(DcMotor.class,"zero");
        zero.setDirection(DcMotorSimple.Direction.REVERSE);
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
        pid = new PIDcontrolador(0.0125,0.00002, 0.0175);
        //kp = 0.0135
        //ki = 0.00002
        //kd = 0.015
    waitForStart();
        while (opModeIsActive()) {
            pegaTag();
            funcionaDesgraca();
            telemetry.update();
        }
    }

    public void pegaTag() {
        LLResult resultado = limelight.getLatestResult();
        if (resultado != null && resultado.isValid()) {
            double tx = resultado.getTx();
            double ty = resultado.getTy();
            double ta = resultado.getTa();
            List<LLResultTypes.FiducialResult> id = resultado.getFiducialResults();
            telemetry.addData("tx", tx);
            telemetry.addData("ty", ty);
            telemetry.addData("ta", ta);
            telemetry.addData("Id", id);
        } else {
            telemetry.addData("nao tem alvo", "");
        }
        List<LLResultTypes.FiducialResult> fiducials = resultado.getFiducialResults();
        for (LLResultTypes.FiducialResult fiducial : fiducials) {
            int id = fiducial.getFiducialId(); // O número de ID do fiducial
            telemetry.addData("id da tag", id);// toma  no seu butao ze nó naquele naipao memo di rocha

            if (id == 20) {
                S1.setPosition(.1);
            } else if (id == 21) {
                S1.setPosition(1);
            }
        }
    }

    public void funcionaDesgraca() {
        LLResult resultado = limelight.getLatestResult();
        double txCam = resultado.getTx();
        if (resultado != null && resultado.isValid()) {
            telemetry.addData("tx", txCam);
        }

        double alvo = 0;
      double saida =  pid.calculo(txCam,alvo);

        zero.setPower(saida);

    }
}
// dudu e joãoP
package org.firstinspires.ftc.teamcode.DECODE.Limelight;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TeleOp
public class LimeCalc extends LinearOpMode {
    public Limelight3A limelight;
    public IMU imu;
    public String Estado = "nulo";
    public DcMotor Out, In;
    public double pot;
    public double dis;
    @Override

 public void runOpMode(){
        In = hardwareMap.get(DcMotor.class, "In");
    Out = hardwareMap.get(DcMotor.class, "Outake");
    limelight = hardwareMap.get(Limelight3A.class, "limelight");
    imu = hardwareMap.get(IMU.class, "imu");
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot
            (RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

    limelight.setPollRateHz(100);
    limelight.pipelineSwitch(0);
    limelight.start();
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("estado", Estado);
            telemetry.update();
            pegaTag();
            bananao();
        }
    }

    public void pegaTag() {
        int id = 0;
        LLResult resultado = limelight.getLatestResult();
        if (resultado != null && resultado.isValid()) {
            double tx = resultado.getTx();
            double ty = resultado.getTy();
            double ta = resultado.getTa();;

            //List<LLResultTypes.FiducialResult> id = resultado.getFiducialResults();
            telemetry.addData("tx", tx);
            telemetry.addData("ty", ty);
            telemetry.addData("ta", ta);
            telemetry.addData("Id", id);
            double angCam = 22;
            double Hgoal = 0.75;
            double hCam = 0.145;
            double AngTotal = angCam + ty;
           /* double banana = (0.29 * 0.807) / ta;
            pot = banana;
            */
            double Dis = (Hgoal - hCam) / Math.tan(Math.toRadians(AngTotal));
            dis = Dis;

            telemetry.addData("dis",Dis);
        } else {
            telemetry.addData("nao tem alvo", "");
        }
        List<LLResultTypes.FiducialResult> fiducials = resultado.getFiducialResults();
        for (LLResultTypes.FiducialResult fiducial : fiducials) {
            id = fiducial.getFiducialId();
            telemetry.addData("id da tag", id);
        }
        if(id == 23){
            Estado = "PPG";
        }if(id == 22){

            Estado = "PGP";
        }if(id == 21){
            Estado = "GPP";
        }

    }
  public void bananao(){
        if (gamepad1.a){
            if(dis >= 1.38 && dis <=1.58){
                pot = 0.67;
                Out.setPower(pot);
            }else {
                pot = 0;
            }

        }
      

      telemetry.addData("potencia", pot);


    }
}

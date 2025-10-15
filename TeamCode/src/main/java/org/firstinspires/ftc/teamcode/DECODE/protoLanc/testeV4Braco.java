package org.firstinspires.ftc.teamcode.DECODE.protoLanc;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@Disabled
@TeleOp
public class testeV4Braco extends LinearOpMode {

    IMU imu;
    private DcMotor leftBack,leftFront,braco,
            rightBack,rightFront,Out1,Out2;
    @Override
    public void runOpMode(){
        leftFront = hardwareMap.get(DcMotor.class,"esqF");
        leftBack = hardwareMap.get(DcMotor.class,"esqT");
        Out1 = hardwareMap.get(DcMotor.class,"Out1");
        Out2 = hardwareMap.get(DcMotor.class,"Out2");
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront = hardwareMap.get(DcMotor.class, "dirF");
        rightBack = hardwareMap.get(DcMotor.class,"dirT");
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);
        waitForStart();
        while(opModeIsActive()){
            mecanum();
            dispara();
        }
    }

    public void mecanum(){
        double y =-gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        y = Math.pow(y,3);
        x = Math.pow(x, 3);
        rx = Math.pow(rx,3);

        double AnguloRobo = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        double rotX = x * Math.cos(-AnguloRobo) - y * Math.sin(-AnguloRobo);
        double rotY = x * Math.sin(-AnguloRobo) + y * Math.cos(-AnguloRobo);

        rotX = rotX * 1.1;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double leftFrontP = (y + x + rx ) / denominator;
        double leftBackP = (y - x + rx ) / denominator;
        double rightFrontP = (y - x - rx ) / denominator;
        double rightBackP = (y + x - rx ) / denominator;

        leftFront.setPower(leftFrontP);
        leftBack.setPower(leftBackP );
        rightBack.setPower(rightBackP);
        rightFront.setPower(rightFrontP);
    }
  /*  public void pivo(){
        double atual = braco.getCurrentPosition();
        double desejo = (gamepad1.right_trigger - gamepad1.left_trigger) + atual;
        double saida = pid.calculo(desejo, atual);

        braco.setPower(saida);
    }
   */


    private void dispara(){
        boolean B = gamepad1.b;
        if(B){
            Out1.setPower(1);
            Out2.setPower(1);
        }else {
            Out1.setPower(0);
            Out2.setPower(0);
        }
    }
}

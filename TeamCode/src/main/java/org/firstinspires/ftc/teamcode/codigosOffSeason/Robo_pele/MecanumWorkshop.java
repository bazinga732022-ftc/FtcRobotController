package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp
public class MecanumWorkshop extends OpMode {
    private DcMotor leftBack,leftFront,
            rightBack,rightFront, in, Out, bout;
    private IMU imu;
    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
        in = hardwareMap.get(DcMotor.class, "in");
        Out = hardwareMap.get(DcMotor.class, "Out");
        bout = hardwareMap.get(DcMotor.class, "bout");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        imu = hardwareMap.get(IMU.class,"imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));

        imu.initialize(parameters);

    }

    @Override
    public void loop() {
        Meca();
        Osintake();
    }

    public void Meca(){
        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

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
    public void Osintake(){
        if(gamepad1.a){
            in.setPower(-1);
        }else{
            in.setPower(-.3);
        }

            Out.setPower(gamepad1.left_trigger);
            telemetry.addData("sla", gamepad1.left_trigger);
            bout.setPower(-1);

    }
}

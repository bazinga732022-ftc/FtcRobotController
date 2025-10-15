package org.firstinspires.ftc.teamcode.DECODE.Mov;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.abs;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
/*
0 = leftFront
1 = leftBack
2 = rightFront
3 = rightBack
 */
public class MecanumSubClass {
     DcMotor leftFront,leftBack,rightFront,rightBack;
     IMU imu;
     PIDController pid;
    double anguloAlvo = 0;
    Gamepad gamepad;

    public void init(HardwareMap hw,Gamepad gb){
        leftFront = hw.get(DcMotorEx.class, "leftFront");
        leftBack = hw.get(DcMotorEx.class, "leftBack");
        rightFront = hw.get(DcMotorEx.class, "rightFront");
        rightBack = hw.get(DcMotorEx.class, "rightBack");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        imu = hw.get(IMU.class,"imu"); IMU.Parameters parameters =
                new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);
        imu.resetYaw();
        this.gamepad = gb;

    }

    public void PID(double P,double I, double D){
        pid = new PIDController(P, I, D);
    }

    public void Mecanum(double vel){
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double X = gamepad.left_stick_x;
        double Y = -gamepad.left_stick_y;
        double rx = Math.pow(gamepad.right_stick_x, 3);

        if (Math.abs(rx) > 0.05) {
            anguloAlvo = botHeading;
        } else {
            double erro = calcularErroAngular(anguloAlvo, botHeading);
            double correcao = pid.calculate(erro);
            rx = correcao;

        }

        double denominator = Math.max(abs(Y) + abs(X) + abs(rx), 1);

        double frontLeftPower = (Y + X + rx) / denominator;
        double backLeftPower = (Y - X + rx) / denominator;
        double frontRightPower = (Y - X - rx) / denominator;
        double backRightPower = (Y + X - rx) / denominator;

        leftFront.setPower(frontLeftPower * vel);
        leftBack.setPower(backLeftPower * vel);
        rightFront.setPower(frontRightPower * vel);
        rightBack.setPower(backRightPower * vel);


    }
    private double calcularErroAngular(double alvo, double leitura) {
        double erro = alvo - leitura;
        while (erro > 180) erro -= 360;
        while (erro <= -180) erro += 360;
        return erro;
    }
}

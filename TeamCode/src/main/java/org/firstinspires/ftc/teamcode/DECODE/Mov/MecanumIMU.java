package org.firstinspires.ftc.teamcode.DECODE.Mov;

import static java.lang.Math.abs;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class MecanumIMU extends OpMode {
    private DcMotor leftFront, leftBack, rightFront, rightBack, Out, In, bout;
    private IMU imu;
    PIDController pid;
    double anguloAlvo = 0;
    boolean ultimobuta = false;
    boolean ultimobuta1 = false;
    boolean ultimobuta2 = false;
    boolean ultimobuta3 = false;
    boolean abrido = false;
    boolean abrido1 = false;
    boolean abrido2 = false;
    boolean abrido3 = false;
    double testes = 0;


    @Override
    public void init() {

        pid = new PIDController(0.017, 0, 0.001);
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        Out = hardwareMap.get(DcMotor.class, "Out");
        In = hardwareMap.get(DcMotor.class, "In");
        bout = hardwareMap.get(DcMotor.class, "bout");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        Out.setDirection(DcMotorSimple.Direction.REVERSE);
        In.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);
        imu.resetYaw();
    }

    @Override
    public void loop() {
        intake();
        Mecanum();
       // OOut();
        Out.setPower(.8);
        telemetry.addData("AngAlvo", anguloAlvo);
        telemetry.update();
    }

    public void Mecanum() {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double X = gamepad1.left_stick_x;
        double Y = -gamepad1.left_stick_y;
        double rx = Math.pow(gamepad1.right_stick_x, 3);

        if (Math.abs(rx) > 0.05) {
            anguloAlvo = botHeading;
        } else {
            double erro = calcularErroAngular(anguloAlvo, botHeading);
            double correcao = pid.calculate(erro);
            rx = correcao;
            telemetry.addData("erro", erro);
            telemetry.addData("Heading", botHeading);
        }

        double denominator = Math.max(abs(Y) + abs(X) + abs(rx), 1);

        double frontLeftPower = (Y + X + rx) / denominator;
        double backLeftPower = (Y - X + rx) / denominator;
        double frontRightPower = (Y - X - rx) / denominator;
        double backRightPower = (Y + X - rx) / denominator;

        leftFront.setPower(frontLeftPower);
        leftBack.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);


    }

    private double calcularErroAngular(double alvo, double leitura) {
        double erro = alvo - leitura;
        while (erro > 180) erro -= 360;
        while (erro <= -180) erro += 360;
        return erro;
    }

    public void OOut() {

        telemetry.addData("power", testes);

        if (gamepad1.triangle && !ultimobuta) {
            abrido = !abrido;
            if (abrido) {
                testes = testes + 0.1;
            } else {
                testes = testes + 0.1;
            }
        }
        if (gamepad1.square && !ultimobuta1) {
            abrido1 = !abrido1;
            if (abrido1) {
                testes = testes - 0.1;
            } else {
                testes = testes - 0.1;
            }
        }
        if (gamepad1.cross && !ultimobuta2) {
            abrido2 = !abrido2;
            if (abrido2) {
                testes = testes - 0.01;
            } else {
                testes = testes - 0.01;
            }
        }
        if (gamepad1.circle && !ultimobuta3) {
            abrido3 = !abrido3;
            if (abrido3) {
                testes = testes + 0.01;
            } else {
                testes = testes + 0.01;
            }
        }
        if (testes >= 1) {
            testes = 1;
        }

        Out.setPower(testes);
        ultimobuta = gamepad1.triangle;
        ultimobuta1 = gamepad1.square;
        ultimobuta2 = gamepad1.cross;
        ultimobuta3 = gamepad1.circle;
    }
    public void intake(){
        if(gamepad1.dpad_up){
            In.setPower(1);
            bout.setPower(1);
        }else{
            In.setPower(.3);
        }
    }
}


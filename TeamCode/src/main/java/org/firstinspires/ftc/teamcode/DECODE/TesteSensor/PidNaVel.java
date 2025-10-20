package org.firstinspires.ftc.teamcode.DECODE.TesteSensor;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class PidNaVel extends LinearOpMode {
    public DcMotor teste;
    public int CPR = 28;
    public double ticksAntigos, kp, ki, kd, kf, RPM, DesejoRPM;
    @Override
    public void runOpMode() {
        DesejoRPM = 330;
        PIDFController pidf = new PIDFController(kp, ki, kd, kf);
        teste = hardwareMap.get(DcMotor.class, "testes");
        teste.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        teste.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while(opModeIsActive()){
            double erro = pidf.calculate(RPM, DesejoRPM);
            calculaRPM();
            telemetry.update();
            teste.setPower(.6 + erro);
        }
    }
    public void calculaRPM(){
        double ticksAtuais = teste.getCurrentPosition();
        RPM = (ticksAtuais - ticksAntigos) / CPR * 60;
        telemetry.addData("RPM", RPM);

        ticksAntigos = ticksAtuais;
        sleep(100);
    }
}

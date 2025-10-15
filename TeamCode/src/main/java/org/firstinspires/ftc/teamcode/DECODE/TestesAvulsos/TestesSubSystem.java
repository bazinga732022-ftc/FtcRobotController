package org.firstinspires.ftc.teamcode.DECODE.TestesAvulsos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.DECODE.Mov.MecanumSubClass;
import org.firstinspires.ftc.teamcode.DECODE.Shooter.TesteVelShooter;

@TeleOp
public class TestesSubSystem extends OpMode {
    MecanumSubClass meca = new MecanumSubClass();
    TesteVelShooter vel = new TesteVelShooter();

    @Override
    public void init() {
        vel.init(hardwareMap,gamepad1);
        meca.init(hardwareMap,gamepad1);
        meca.PID(0.017,0,0.001);
    }

    @Override
    public void loop() {
    meca.Mecanum(1);
    vel.outakeVel();
    vel.inS();
    vel.tele(telemetry);
    }
}

package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class sensohDeCohTesteh extends OpMode {
    sensoris senso = new sensoris();
    @Override
    public void init(){
        senso.init(hardwareMap);
    }

    @Override
    public void loop() {
        senso.getCOR(telemetry);
    }
}

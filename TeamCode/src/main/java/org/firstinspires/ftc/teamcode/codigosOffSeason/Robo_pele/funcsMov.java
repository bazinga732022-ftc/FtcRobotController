package org.firstinspires.ftc.teamcode.codigosOffSeason.Robo_pele;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class funcsMov {

    DcMotor[] motors = new DcMotor[2];
    public Servo s1;

    public void init(HardwareMap hw) {
        motors[0] = hw.get(DcMotor.class, "Dir");
        motors[1] = hw.get(DcMotor.class, "Esq");
        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        s1 = hw.get(Servo.class, "S1");
    }

    public void abreGarra(){
        s1.setPosition(.75);
    }
    public void fechaGarra(){
        s1.setPosition(1);
    }

    public void andar( double redutorVel,double fy,double vira ){



        double Esq = fy - vira;
        double dir = fy + vira;

        motors[0].setPower(Esq * redutorVel);
        motors[1].setPower(dir * redutorVel);
    }
}

package org.firstinspires.ftc.teamcode.DECODE.Shooter;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.Telemetry;


public class TesteVelShooter {
    public DcMotor Outake, In, bout;
    boolean ultimobuta = false;
    boolean abrido = false;
    boolean ultimobuta1 = false;
    boolean abrido1 = false;
    boolean ultimobuta2 = false;
    boolean abrido2 = false;
    boolean ultimobuta3= false;
    boolean abrido3 = false;
    boolean Ultimobuta3 = false;
    double testes = 0 ;
    double pot = 1;
    Gamepad gamepad1;


    public void init(HardwareMap hw,Gamepad gb) {
        Outake = hw.get(DcMotor.class,"Out");
        bout = hw.get(DcMotor.class,"bout");
       // Outake.setDirection(DcMotorSimple.Direction.REVERSE);
        Outake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       In = hw.get(DcMotor.class, "In");
        this.gamepad1 = gb;
    }


  public void tele (Telemetry tl){
        tl.addData("pot",testes);
        tl.update();
  }
        public void outakeVel(){
                if (gamepad1.triangle && !ultimobuta){
                    abrido = !abrido;
                    if (abrido){
                      testes =testes + 0.1;
                    }else {
                        testes =  testes+ 0.1;
                    }
                }
            if (testes >= 1) {
                testes = 1;
            }
                ultimobuta = gamepad1.triangle;


            if (gamepad1.square && !ultimobuta1){
                abrido1 = !abrido1;
                if (abrido1){
                    testes =testes - 0.1;
                }else {
                    testes =  testes - 0.1;
                }
            }
            if (testes <= 0) {
                testes = 0;
            }
            ultimobuta1 = gamepad1.square;
            Outake.setPower(testes);
            if (gamepad1.b && !ultimobuta2){
                abrido2 = !abrido2;
                if (abrido2){
                    testes =testes + 0.01;
                }else {
                    testes =  testes + 0.01;
                }
            }
            ultimobuta2 = gamepad1.b;
            if (gamepad1.cross && !ultimobuta3){
                abrido3 = !abrido3;
                if (abrido3){
                    testes =testes - 0.01;
                }else {
                    testes =  testes - 0.01;
                }
            }
            ultimobuta3 = gamepad1.cross;
            Outake.setPower(testes);
        }
        public void inS(){
            if(gamepad1.left_bumper){
                In.setPower(0.6);
            }
            else{
                In.setPower(0);
            }
            if(gamepad1.right_bumper){
                   bout.setPower(.8);
            }else
                bout.setPower(0);
        }
    }


package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmPipeline {
    //my motors
    private DcMotor liftL;
    private DcMotor liftR;
    private DcMotor arm;

    private Servo wrist;

    private OpMode opmode;

    String startingPoint = "home";

    String state = "raise";

    //costructor
    public ArmPipeline(OpMode opmode){
        //init the opmode
        this.opmode = opmode;
        //init the motors
        liftL = opmode.hardwareMap.get(DcMotor.class, "LiftL");
        liftR = opmode.hardwareMap.get(DcMotor.class, "LiftR");
        arm = opmode.hardwareMap.get(DcMotor.class, "Arm");
        //init the wrist
        wrist = opmode.hardwareMap.get(Servo.class, "Wrist");
    }

    public void moveLift(){
        //test if the lift is moving
        if(!isMoving()){
            if(state == "raise"){
                //test the starting point of the lift
                switch(startingPoint){
                    case "home":

                        break;
                    case "safety":
                        break;
                    case "top":
                        break;
                    case "back board":
                        break;
                }
            }else if(state == "lower"){
                //test the starting point of the lift
                switch(startingPoint){
                    case "home":

                        break;
                    case "safety":
                        break;
                    case "top":
                        break;
                    case "back board":
                        break;
                }
            }
        }
    }

    //is busy method
    private boolean isMoving(){
        return liftL.isBusy() || liftR.isBusy() || arm.isBusy();
    }
}

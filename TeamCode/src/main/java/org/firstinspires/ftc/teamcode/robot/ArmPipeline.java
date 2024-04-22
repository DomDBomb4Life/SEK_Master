package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmPipeline {
    //my motors
    private DcMotor liftL, liftR,arm;

    private Servo wrist;

    private OpMode opmode;

    String startingPoint = "home";
    private final double wristBackdropPos = 0.2699999;
    private final double wristHome = 0.53;

    String state = "raise";

    int[] liftPositions= new int[4];


    //costructor
    public ArmPipeline(OpMode opmode){
        //init the opmode
        this.opmode = opmode;
        //init the motors
        liftL = opmode.hardwareMap.get(DcMotor.class, "LiftL");
        liftR = opmode.hardwareMap.get(DcMotor.class, "LiftR");
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);
        arm = opmode.hardwareMap.get(DcMotor.class, "Arm");
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //init the wrist
        wrist = opmode.hardwareMap.get(Servo.class, "Wrist");
        //init the lift heights
        liftPositions[0] = 0;
        liftPositions[1] = 50;
        liftPositions[2] = -500;
        liftPositions[3] = 5500;

    }

    public void moveLift(){
        //test if the lift is moving
        if(!isMoving()){
            if(state == "raise"){
                //test the starting point of the lift
                switch(startingPoint){
                    case "home":
                        arm.setTargetPosition(liftPositions[1]);
                        startingPoint = "safety";
                        break;
                    case "safety":
                        liftR.setTargetPosition(liftPositions[3]);
                        liftL.setTargetPosition(liftPositions[3]);
                        startingPoint = "top";
                        break;
                    case "top":
                        arm.setTargetPosition(liftPositions[2]);
                        wrist.setPosition(wristBackdropPos);
                        break;
                    case "back board":
                        state = "lower";
                        break;
                }
            }else if(state == "lower"){
                //test the starting point of the lift
                switch(startingPoint){
                    case "home":
                        state = "raise";
                        break;
                    case "safety":
                        arm.setTargetPosition(liftPositions[0]);
                        startingPoint = "home";
                        break;
                    case "top":
                        liftR.setTargetPosition(liftPositions[0]);
                        liftL.setTargetPosition(liftPositions[0]);
                        startingPoint = "safety";
                        break;
                    case "back board":
                        arm.setTargetPosition(liftPositions[1]);
                        wrist.setPosition(wristHome);
                        startingPoint = "top";
                        break;
                }
            }
            liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    //is busy method
    private boolean isMoving(){
        return liftL.isBusy() || liftR.isBusy() || arm.isBusy();
    }
}

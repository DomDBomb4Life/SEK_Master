package org.firstinspires.ftc.teamcode.robot;

import org.firstinspires.ftc.teamcode.robot.ArmPipeline;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmPipeline {
    //my motors
    private DcMotor liftL, liftR, arm;
    private Servo wrist;
    private OpMode opmode;
    private final double wristBackdropPos = 0.2699999;
    private final double wristHome = 0.53;

    private enum StartingPoint {
        HOME,
        SAFETY,
        TOP,
        BACK_BOARD
    }

    private enum State {
        
        RAISE,
        LOWER
    }

    private StartingPoint startingPoint = StartingPoint.HOME;
    private State state = State.RAISE;

    private final int maxLift = 5500;

    int[] armPositions= new int[4];


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
        //init the arm heights
        armPositions[0] = 0;
        armPositions[1] = 50;
        armPositions[2] = -500;

    }

    public void moveLift(){
        //test if the lift is moving
        if(!isMoving()){
            if(state == State.RAISE){
                //test the starting point of the lift
                switch(startingPoint){
                    case HOME:
                        if(opmode.gamepad2.a) {
                            arm.setTargetPosition(armPositions[1]);
                            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            startingPoint = StartingPoint.SAFETY;
                        }
                        break;
                    case SAFETY:
                        if(!arm.isBusy()) {
                            liftR.setTargetPosition(armPositions[3]);
                            liftL.setTargetPosition(armPositions[3]);
                            liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            startingPoint = StartingPoint.TOP;
                            liftMoving = true;
                        }
                        break;
                    case TOP:
                        if(!arm.isBusy()) {
                            arm.setTargetPosition(armPositions[2]);
                            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            wrist.setPosition(wristBackdropPos);
                            liftMoving = true;
                        }
                        break;
                    case BACK_BOARD:
                        state = State.LOWER;
                        break;
                }
            }else if(state == State.LOWER){
                //test the starting point of the lift
                switch(startingPoint){
                    case HOME:
                        state = State.RAISE;
                        break;
                    case SAFETY:
                        if(!arm.isBusy()) {
                            arm.setTargetPosition(armPositions[0]);
                            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            startingPoint = StartingPoint.HOME;
                            liftMoving = true;
                        }
                        break;
                    case TOP:
                        if(!liftL.isBusy() && !liftR.isBusy()) {
                            liftR.setTargetPosition(armPositions[0]);
                            liftL.setTargetPosition(armPositions[0]);
                            liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            startingPoint = StartingPoint.SAFETY;
                            liftMoving = true;
                        }
                        break;
                    case BACK_BOARD:
                        if(opmode.gamepad2.a && !liftMoving) {
                            arm.setTargetPosition(armPositions[1]);
                            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            wrist.setPosition(wristHome);
                            startingPoint = StartingPoint.TOP;
                            liftMoving = true;
                        }
                        break;
                }
            }
        }
    }

    private boolean liftMoving = false;

    //is busy method
    private boolean isMoving(){
        return liftL.isBusy() || liftR.isBusy() || arm.isBusy();
    }
}

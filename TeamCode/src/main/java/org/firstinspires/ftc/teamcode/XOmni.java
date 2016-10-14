
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogOutput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;


@TeleOp(name="XOmni", group="121")

public class XOmni extends OpMode{

    public double abs(float x){
        if(x<0){
            x *= (-1);
        }else{/*do nothing*/}
        return x;
    }

    public float clip(float v){
        if(v > 1){
            v = 1;
        }else if( v < -1){
            v = -1;
        }
        return v;
    }

    ColorSensor colorSensor;
    OpticalDistanceSensor odsSensor;
    AnalogInput rangeSensor;


    public DcMotor FrontRight;
    public DcMotor  FrontLeft;
    public DcMotor  BackRight;
    public DcMotor  BackLeft;

    float FR;
    float FL;
    float BR;
    float BL;

    float hsvValues[] = {0F,0F,0F};
    final float values[] = hsvValues;
    boolean bLedOn = true;


    @Override
    public void init() {
        FrontRight = hardwareMap.dcMotor.get("FrontRight");
        FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
        BackRight = hardwareMap.dcMotor.get("BackRight");
        BackLeft = hardwareMap.dcMotor.get("BackLeft");

        colorSensor = hardwareMap.colorSensor.get("color sensor");
        colorSensor.enableLed(bLedOn);
        odsSensor = hardwareMap.opticalDistanceSensor.get("ods");
        rangeSensor = hardwareMap.analogInput.get("range");
    }

    @Override
    public void init_loop() {
        /*
         * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
         */
    }

    @Override
    public void start() {
        /*
         * Code to run ONCE when the driver hits PLAY
         */

    }


    @Override
    public void loop() {
        if(gamepad1.start) {
            colorSensor.enableLed(bLedOn);
        }
        if(gamepad1.back){
           colorSensor.enableLed(!bLedOn);
        }

        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        if ((abs(gamepad1.left_stick_y) >= 0.09) || (abs(gamepad1.left_stick_x) >= 0.09)) {
            FL= (-gamepad1.left_stick_y + gamepad1.left_stick_x);
            BL= (-gamepad1.left_stick_y - gamepad1.left_stick_x);
            FR= (gamepad1.left_stick_y + gamepad1.left_stick_x);
            BR= (gamepad1.left_stick_y - gamepad1.left_stick_x);

            if (gamepad1.left_stick_button) {
                FR /= 2;
                FL /= 2;
                BR /= 2;
                BL /= 2;
            }
        }else if((abs(gamepad1.right_stick_x) >= 0.09)
        && !((abs(gamepad1.left_stick_y) >= 0.1)
        || (abs(gamepad1.left_stick_x) >= 0.1))){

            FR = (gamepad1.right_stick_x);
            FL = (gamepad1.right_stick_x);
            BR = (gamepad1.right_stick_x);
            BL = (gamepad1.right_stick_x);
        }else{
            FR=0; FL=0; BR=0; BL=0;
        }
            FrontRight.setPower(clip(FR));
            FrontLeft.setPower(clip(FL));
            BackRight.setPower(clip(BR));
            BackLeft.setPower(clip(BL));


        telemetry.addData("LED", bLedOn ? "On" : "Off");
        telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        //telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("Raw",    odsSensor.getRawLightDetected());
        telemetry.addData("Normal", odsSensor.getLightDetected());



        telemetry.update();
    }


    @Override
    public void stop() {
        /*
         * Code to run ONCE after the driver hits STOP
         */
    }

}

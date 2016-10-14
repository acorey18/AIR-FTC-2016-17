/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="mechanum", group="121")

public class mechanum extends OpMode{

    public double abs(float x){
        if(x<0){
            x *= (-1);
        }else{/*do nothing*/}
        return x;
    }

    DcMotor FR,FL,BR,BL;


    @Override
    public void init() {
        /*
         * Code to run ONCE when the driver hits INIT
         */
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");

        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
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
        /*
         * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
         */
        if((abs(gamepad1.left_stick_x) >= 0.2) || (abs(gamepad1.right_stick_x) >= 0.2)){
            if(abs(gamepad1.right_stick_x) >= 0.2){
                FL.setPower(-gamepad1.right_stick_x);
                BL.setPower(gamepad1.right_stick_x);
                FR.setPower(gamepad1.right_stick_x);
                BR.setPower(-gamepad1.right_stick_x);
            }else if(abs(gamepad1.left_stick_x) >= 0.2){
                FL.setPower(-gamepad1.left_stick_x);
                BL.setPower(gamepad1.left_stick_x);
                FR.setPower(gamepad1.left_stick_x);
                BR.setPower(-gamepad1.left_stick_x);
            }
        } else {
            if (abs(gamepad1.left_stick_y) >= 0.09) {
                FL.setPower(gamepad1.left_stick_y);
                BL.setPower(gamepad1.left_stick_y);
            } else {
                FL.setPower(0);
                BL.setPower(0);
            }
            if (abs(gamepad1.right_stick_y) >= 0.09) {
                FR.setPower(gamepad1.right_stick_y);
                BR.setPower(gamepad1.right_stick_y);
            } else {
                FR.setPower(0);
                BR.setPower(0);
            }
        }


    }


    @Override
    public void stop() {
        /*
         * Code to run ONCE after the driver hits STOP
         */
    }

}

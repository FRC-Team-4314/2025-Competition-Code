// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode; 
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Robot extends TimedRobot {
    private final XboxController controller = new XboxController(0);
    
    private SparkMax spark = new SparkMax(23, MotorType.kBrushless);
    private SparkClosedLoopController sparkController;
    
    public Robot() {        
        //Documentation: https://docs.revrobotics.com/revlib/spark/configuring-a-spark
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40);
        config.idleMode(IdleMode.kBrake);

        //Encoder. Only enable if the encoder is actually plugged in
        //config.absoluteEncoder.setSparkMaxDataPortConfig();
        
        //Documentaion: https://docs.revrobotics.com/revlib/spark/closed-loop
        //PID Values
        double p = 0.1, i = 0, d = 0;
        //config.closedLoop.pid(p, i, d);

        spark.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
        sparkController = spark.getClosedLoopController();
    }

    @Override
    public void robotPeriodic() {}
        
    @Override
    public void autonomousInit() {}
    
    @Override
    public void autonomousPeriodic() {}
    
    @Override
    public void teleopInit() {}
    
    @Override
    public void teleopPeriodic() {
         double speed = 0;
        if (Math.abs(controller.getLeftX()) > 0.1)
            speed = controller.getLeftX();
        spark.set(speed);
        
        //More PID logic
        //sparkController.setReference(2, ControlType.kPosition);
    }
    
    @Override
    public void disabledInit() {}
    
    @Override
    public void disabledPeriodic() {}
    
    @Override
    public void testInit() {}
    
    @Override
    public void testPeriodic() {}
    
    @Override
    public void simulationInit() {}
    
    @Override
    public void simulationPeriodic() {}
}

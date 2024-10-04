// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.TestingBench;
import frc.robot.subsystems.TestingBench.States;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final TestingBench m_TestingBench = new TestingBench();
  private final XboxController m_Controller = new XboxController(0);

  public RobotContainer() {
    m_TestingBench.updateState(States.STOPPED, 0);
    configureBindings();
  }

  private void configureBindings() {
    Trigger rightBumper = new Trigger(m_Controller::getRightBumper);
    Trigger leftBumper = new Trigger(m_Controller::getLeftBumper);

    rightBumper.onTrue(new InstantCommand(() -> m_TestingBench.updateState(States.RUNNING, 1)));
    rightBumper.onFalse(new InstantCommand(() -> m_TestingBench.updateState(States.STOPPED, 0)));
    leftBumper.onTrue(new InstantCommand(() -> m_TestingBench.updateState(States.RUNNING, -1)));
    leftBumper.onFalse(new InstantCommand(() -> m_TestingBench.updateState(States.STOPPED, 0)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No command configured");
  }
}

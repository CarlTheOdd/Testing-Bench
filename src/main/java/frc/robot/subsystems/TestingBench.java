package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestingBench extends SubsystemBase {
    private CANSparkMax brushMotor, brushlessMotor;
    private static TestingBench m_TestingBench;
    
    public TestingBench(){
        brushMotor = new CANSparkMax(1, MotorType.kBrushed);
        brushlessMotor = new CANSparkMax(2, MotorType.kBrushless);
    }

    public static TestingBench getInstance(){
        if(m_TestingBench == null){
            m_TestingBench = new TestingBench();
        }

        return m_TestingBench;
    }

    @Override
    public void periodic(){
        
    }

    public void stop(){
        brushMotor.stopMotor();
        brushlessMotor.stopMotor();
    }

    public void setMotorSpeed(double speed){
        brushMotor.set(speed);
        brushlessMotor.set(speed);
    }

    public void updateState(States state, double desiredSpeed){
        switch(state){
            case STOPPED:
                stop();
                break;
            case RUNNING:
                setMotorSpeed(desiredSpeed);
                break;
            
            default:
                break;
        }
    }

    public enum States {
        STOPPED,
        RUNNING;
    }
}
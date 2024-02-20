package com.comsystem.homework;

import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import com.comsystem.homework.robot.RobotOperations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RobotOperationTest {
    private final RobotOperations robotOperations = new RobotOperations();

    @Test
    public void testExcavateStonesForDays() {
        int days = 13;
        RobotPlan robotPlan = robotOperations.excavateStonesForDays(days);

        assertEquals(days, robotPlan.numberOfDays(), "Number of days should match");

        List<RobotAction> actions = robotPlan.robotActions();
        int expectedNumberOfStones = 0;
        for (int i = 1; i <= days; i++) {
            if (i % 2 != 0) {
                expectedNumberOfStones++;
            }
        }

        assertEquals(expectedNumberOfStones, robotPlan.numberOfStones(), "Number of stones should match");

        assertEquals(days, actions.size(), "Number of actions should match the number of days");

        for (int i = 0; i < days; i++) {
            RobotAction expectedAction = (i % 2 == 0) ? RobotAction.DIG : RobotAction.CLONE;
            assertEquals(expectedAction, actions.get(i), "Action at index " + i + " should match");
        }
    }


    @Test
    public void testDaysRequiredToCollectStones() {
        int numberOfStones = 7;
        RobotPlan robotPlan = robotOperations.daysRequiredToCollectStones(numberOfStones);

        int expectedDays = numberOfStones * 2;
        assertEquals(expectedDays, robotPlan.numberOfDays(), "Number of days should match");

        assertEquals(numberOfStones, robotPlan.numberOfStones(), "Number of stones should match");

        List<RobotAction> actions = robotPlan.robotActions();
        assertEquals(expectedDays, actions.size(), "Number of actions should match the number of days");

        for (int i = 0; i < expectedDays; i++) {
            RobotAction expectedAction = (i % 2 == 0) ? RobotAction.DIG : RobotAction.CLONE;
            assertEquals(expectedAction, actions.get(i), "Action at index " + i + " should match");
        }
    }
}

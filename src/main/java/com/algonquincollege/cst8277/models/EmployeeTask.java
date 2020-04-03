/***************************************************************************f******************u************zz*******y**
 * File: EmployeeTask.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * @date 2020 02
 *
 */
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeTask {
    
    protected String description;
    protected LocalDateTime taskStart;
    protected LocalDateTime taskEndDate;
    protected boolean taskDone;

    /***
     * Default constructor
     */
    public EmployeeTask() {
    }

    /**
     * @return the description
     */
    @Column(name = "TASK_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the taskStart
     */
    @Column(name = "TASK_START")
    public LocalDateTime getTaskStart() {
        return taskStart;
    }

    /**
     * @param taskStart the taskStart to set
     */
    public void setTaskStart(LocalDateTime startDate) {
        this.taskStart = startDate;
    }

    /**
     * @return the taskEndDate
     */
    @Column(name = "TASK_END_DATE")
    public LocalDateTime getTaskEndDate() {
        return taskEndDate;
    }

    /**
     * @param taskEndDate the taskEndDate to set
     */
    public void setTaskEndDate(LocalDateTime endDate) {
        this.taskEndDate = LocalDateTime.now();
    }

    /**
     * @return the taskDone
     */
    @Column(name = "TASK_DONE")
    public boolean isTaskDone() {
        return taskDone;
    }

    /**
     * @param taskDone the taskDone to set
     */
    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

}
package isp.lab7.safehome;

import java.time.LocalDateTime;

public class AccessLog {
    private String tenantName;
    private LocalDateTime dateTime;
    private String operation;
    private DoorStatus doorStatus;
    private String errorMessage;
    public AccessLog(String tenantName, LocalDateTime dateTime, String operation, DoorStatus doorStatus, String errorMessage) {
        this.tenantName     = tenantName;
        this.dateTime       = dateTime;
        this.operation      = operation;
        this.doorStatus     = doorStatus;
        this.errorMessage   = errorMessage;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getOperation() {
        return operation;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}

package lab5.src.com.example.business;
import lab5.src.com.example.*;
public class EmployeeStockPlan {
    private final int employeeShares = 10;
    private final int managerShares = 100;
    private final int directorShares = 1000;
    public int grantStock(Employee emp) {
        if(emp instanceof Director) {
            return directorShares;
        } else if(emp instanceof Manager) {
            return managerShares;
        } else {
            return employeeShares;
        }
    }
}

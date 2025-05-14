package lab4.com.example.detail1;

public class EmployeeTest {
    public static void main(String[] args){
        Engineer eng = new Engineer(101, "Jane Smith", "012-34-5678", 120345.27);
        Manager man = new Manager(207, "Barbara Johnson", "054-12-2367", 109501.36, "US Marketing");
        Admin adm = new Admin(304, "Bill Monroe", "108-23-6509", 75002.34);
        Director dir = new Director(12, "Susan Wheeler", "099-45-2340", 120567.36, "Global Marketing", 1000000.00);

        printEmployee(eng);
        printEmployee(man);
        printEmployee(adm);
        printEmployee(dir);

        man.setName("Barbara Johnson-Smythe");
        man.raiseSalary(10000.00);
        printEmployee(man);
    }

    public static void printEmployee(Employee emp){
        System.out.println();
        System.out.println("Id: " + emp.getEmpId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Soc Sec #: " + emp.getSsn());
        System.out.println("Salary: " + emp.getSalary());
    }
}
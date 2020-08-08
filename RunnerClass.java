import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class EmployeeDetails implements Serializable{
    private int empId;
    private String empName;
    private int empAge;
    private double empSalary;
    private String empAddress;

    public EmployeeDetails(int id, String name, int age, double salary, String address){
        empId = id;
        empName = name;
        empAge = age;
        empSalary = salary;
        empAddress = address;
    }

    public int getEmpId(){return empId;}
    public int getEmpAge(){return empAge;}
    public double getEmpSalary(){return empSalary;}
    public String getEmpName(){return empName;}
    public String getEmpAddress(){return empAddress;}
}

public class RunnerClass {

    private ArrayList<EmployeeDetails> employeeList = new ArrayList<EmployeeDetails>();

    public RunnerClass(){
        try
        {
            FileInputStream fis = new FileInputStream("C:\\Users\\AR415\\Desktop\\Programming\\Projects\\Employee Details - Java\\JavaProject\\Deatils.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            employeeList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }


    public void Display(){
        System.out.println("Displaying...\n");
        for(int i = 0; i < employeeList.size(); i++){
            System.out.print(employeeList.get(i).getEmpId() + "\t");
            System.out.print(employeeList.get(i).getEmpName() + "\t");
            System.out.print(employeeList.get(i).getEmpAge() + "\t");
            System.out.print(employeeList.get(i).getEmpSalary() + "\t");
            System.out.print(employeeList.get(i).getEmpAddress() + "\t");
            System.out.println();
        }
        if(employeeList.size() == 0){
            System.out.println("No Records Avaliable!");
        }
        System.out.println();
    }

    public void Insert(int id, String name, int age, double salary, String address){
        System.out.println("Inserting...\n");
        employeeList.add(new EmployeeDetails(id, name, age, salary, address));

        try
        {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\AR415\\Desktop\\Programming\\Projects\\Employee Details - Java\\JavaProject\\Deatils.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employeeList);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void Enquiry(int id) {
        boolean flag = false;
        int searchId = id;

        for (int i = 0; i < employeeList.size() && flag == false; i++) {
            if (employeeList.get(i).getEmpId() == id) {
                System.out.print(employeeList.get(i).getEmpId() + "\t");
                System.out.print(employeeList.get(i).getEmpName() + "\t");
                System.out.print(employeeList.get(i).getEmpAge() + "\t");
                System.out.print(employeeList.get(i).getEmpSalary() + "\t");
                System.out.print(employeeList.get(i).getEmpAddress() + "\t");
                System.out.println();
                flag = true;
                break;
            }
        }
        if(flag == false){
            System.out.println("Record not Found!");
        }
        System.out.println();
    }

    public void Delete(int id){
        System.out.println("Deleting...\n");
        boolean flag = false;
        int searchId = id;

        for (int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getEmpId() == id){
                employeeList.remove(i);
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("Record not Found!");
        }

        try
        {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\AR415\\Desktop\\Programming\\Projects\\Employee Details - Java\\JavaProject\\Deatils.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employeeList);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static void main(String ...args) throws IOException {
        RunnerClass test = new RunnerClass();
        Scanner sc = new Scanner(System.in);
        int choice = 5;

        int mainEmpId;
        String mainEmpName;
        int mainEmpAge;
        double mainEmpSalary;
        String mainEmpAddress;

        // System.out.println("1. Display \n2. Insert \n3. Enquiry \n4. Delete \n0. Exit \nInput your choice: ");
        // choice = sc.nextInt();

        while(choice != 0){
            System.out.println();
            System.out.println("1. Display \n2. Insert \n3. Enquiry \n4. Delete \n0. Exit \nInput your choice: ");
            choice = sc.nextInt();

            if(choice == 2){
                System.out.println("\nInput your Employee ID: ");
                mainEmpId = sc.nextInt();

                System.out.println("Input your Name: ");
                mainEmpName = sc.next();

                System.out.println("Input your Age: ");
                mainEmpAge = sc.nextInt();

                System.out.println("Input your Salary: ");
                mainEmpSalary = sc.nextFloat();

                System.out.println("Input your Address: ");
                mainEmpAddress = sc.next();

                test.Insert(mainEmpId, mainEmpName, mainEmpAge, mainEmpSalary, mainEmpAddress);
            }
            else if(choice == 1){
                test.Display();
            }
            else if(choice == 3){
                System.out.println("Input Employee ID: ");
                mainEmpId = sc.nextInt();
                test.Enquiry(mainEmpId);
            }
            else if(choice == 4){
                System.out.println("Input Employee ID: ");
                mainEmpId = sc.nextInt();
                test.Delete(mainEmpId);
            }
            else if(choice == 0){
                System.out.println("Exiting...");
            }
            else{
                System.out.println("Not Valid Input.");
            }
        }
    }
}

package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int numOfItems = 0;
        Matrix matrix =null;
        List<String> labels = new ArrayList<>();
        Matrix checkbox=null;


        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome to Arranging Elemnts of a Hierarchy in Graphic Form V1.0.0");

        while (true) {
                //Initialize
                System.out.println("Please enter how many items are there to be arranged.");
                String msg = scanner.nextLine();
                try {
                    int i = Integer.valueOf(msg);
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter a valid number!");
                    break;
                }
                numOfItems = new Integer(Integer.valueOf(msg));
                matrix = new Matrix(numOfItems,numOfItems);
                checkbox = new Matrix(numOfItems,numOfItems);



                //Q1
                for(int i=0;i<numOfItems;i++){
                    System.out.println("Please enter the "+(i+1)+" item ");
                    labels.add(new String(scanner.nextLine()));
                }



                //Q2
                for(int i=0;i<numOfItems;i++){
                    for(int j=0;j<numOfItems;j++){
                        if(checkbox.getByIndex(i,j)==0&&i!=j){
                            System.out.println("Is \"" +labels.get(i)+ "\" subordinate to \"" +labels.get(j)+ "\"? (Enter \"Y\" or \"N\")");
                            System.out.println("ie.Is \"" +labels.get(j)+ "\" at a higher level of \"" +labels.get(i)+ "\"? (Enter \"Y\" or \"N\")");
                            String answer = scanner.nextLine();
                            if(answer.equals("Y")){
                                matrix.set(i,j,1);
                                checkbox.set(i,j,1);
                                checkbox.set(j,i,1);
                            }
                            else if(answer.equals("N")){
                            }
                            else {
                                System.out.println("The answer was not valid! Please try again!");
                                j--;
                            }
                        }
                    }
                }
            System.out.println("Checking the validity of the answers, please wait...");
           // System.out.println(matrix.printMatrix());
            Boolean pass = true;
            for(int i=0;i<numOfItems;i++){
                for(int j=0;j<numOfItems;j++){
                    if(matrix.getByIndex(i,j)==1){
                        if(!checkValidity(i,j,matrix)){
                            pass = false;
                        }
                    }

                }
            }
            if(!pass){
                System.out.println("The answers were not valid, unable to proceed!");
                break;
            }
            System.out.println("The answers were valid, please wait...");

            //Generate result

            AEHGF aehgf= new AEHGF(matrix,labels);
            String filename = aehgf.doAEHGF();
            System.out.println("Result successfully produced in file: "+filename);









        }
    }

    private static Boolean checkValidity(int r, int c, Matrix m) {
        for(int i=0;i<m.rowSize();i++){
            for(int j=0;j<m.columnSize();j++){
                if(m.getByIndex(i,j)==1&&i==c){
                    if(m.getByIndex(r,j)!=1){
                        return false;
                    }
                }
            }
        }
    return true;
    }
}

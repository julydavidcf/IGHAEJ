import Main.AEHGF;
import Main.Matrix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JTextField welcomeToArrangingElemntsTextField;
    private JButton startButton;
    private JPanel Main;

    public GUI() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
                int numOfItems = 0;
                Matrix matrix =null;
                List<String> labels = new ArrayList<>();
                Matrix checkbox=null;

                String NOI = JOptionPane.showInputDialog(null,"Please enter how many items are there to be arranged.");
                try {
                    int i = Integer.valueOf(numOfItems);
                } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(null,"Invalid Number!");
                    System.exit(0);

                }
                numOfItems = new Integer(Integer.valueOf(NOI));
                matrix = new Matrix(numOfItems,numOfItems);
                checkbox = new Matrix(numOfItems,numOfItems);

                //Q1
                for(int i=0;i<numOfItems;i++){
                    String item = JOptionPane.showInputDialog(null,"Please enter the "+(i+1)+" item");
                    labels.add(new String(item));
                }

                //Q2
                for(int i=0;i<numOfItems;i++){
                    for(int j=0;j<numOfItems;j++){
                        if(checkbox.getByIndex(i,j)==0&&i!=j){
                            //System.out.println("Is \"" +labels.get(i)+ "\" subordinate to \"" +labels.get(j)+ "\"? (Enter \"Y\" or \"N\")");
                            //System.out.println("ie.Is \"" +labels.get(j)+ "\" at a higher level of \"" +labels.get(i)+ "\"? (Enter \"Y\" or \"N\")");
                            int result = JOptionPane.showConfirmDialog(null,"Is \"" +labels.get(i)+ "\" subordinate to \"" +labels.get(j)+ "\"? (Enter \"Y\" or \"N\")\n"+"ie.Is \"" +labels.get(j)+ "\" at a higher level of \"" +labels.get(i)+ "\"? (Enter \"Y\" or \"N\")","Confirm",JOptionPane.YES_NO_OPTION);
                            //String answer = scanner.nextLine();
                            if(result==0){
                                matrix.set(i,j,1);
                                checkbox.set(i,j,1);
                                checkbox.set(j,i,1);
                            }
                            else {
                            }
                        }
                    }
                }


               // System.out.println("Checking the validity of the answers, please wait...");
                JOptionPane.showMessageDialog(null,"Checking the validity of the answers, please wait...");
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
                    JOptionPane.showMessageDialog(null,"The matrix is invalid!");
                    System.exit(0);
                }
                //System.out.println("The answers were valid, please wait...");
                JOptionPane.showMessageDialog(null,"The answers were valid, please wait...");

                //Generate result

                AEHGF aehgf= new AEHGF(matrix,labels);
                String filename = aehgf.doAEHGF();
               // System.out.println("Result successfully produced in file: "+filename);
                JOptionPane.showMessageDialog(null,"Result successfully produced in file: "+filename);



            }
        });
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("The Implementation of Graphical Hierarchy Arrangement of Elements in Java");
        frame.setContentPane(new GUI().Main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.pack();
        frame.setSize(1000, 200);
        frame.setVisible(true);
    }
}

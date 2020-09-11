package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Matrix {

    private Vector<Vector<Integer>> matrix;
    private int rowSize ;
    private int columnSize ;
    private List<Integer> rowLable;
    private List<Integer> columnLable;

    public Matrix(int row, int column) {
        matrix = new Vector<>();
        this.rowSize = row;
        this.columnSize = column;
        rowLable = new ArrayList<>();
        columnLable= new ArrayList<>();
        formMatrix(row,column);
    }

    public Matrix(Vector<Vector<Integer>> matrix) {
        this.matrix = matrix;
        this.rowSize = matrix.size();
        this.columnSize = matrix.get(0).size();
        rowLable = new ArrayList<>();
        columnLable = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            Vector<Integer> v = new Vector<>();
            rowLable.add(i);
            for (int j = 0; j < columnSize; j++) {
                columnLable.add(j);

            }
        }
    }

    public Matrix(Vector<Vector<Integer>> matrix,List<Integer> rowLable, List<Integer> columnLable){
        this.matrix = matrix;
        this.rowSize = matrix.size();
        this.columnSize = matrix.get(0).size();
        this.rowLable = rowLable;
        this.columnLable = columnLable;
    }




    private void formMatrix(int row,int column){
        for(int i =0;i<row; i++){
            Vector<Integer> v = new Vector<>();
            rowLable.add(i);
            for(int j=0; j<column;j++){
                v.add(0);

            }
            v.setSize(column);
            matrix.add(v);
        }
        for(int j=0; j<column;j++){
            columnLable.add(j);

        }
        matrix.setSize(row);
    }

    public int rowSize(){
        return rowSize;
    }

    public int columnSize(){
        return columnSize;
    }

    public Vector<Integer> getRowByIndex(int index){
        Vector<Integer> v = new Vector<>();
        if (index<rowSize){
         v = matrix.get(index);
        }

        return  v;
    }
    public Vector<Integer> getRowByLabel(int label){
        int index = rowLable.indexOf(label);
        Vector<Integer> v = new Vector<>();
        if (index<rowSize){
            v = matrix.get(index);
        }

        return  v;
    }

    public Vector<Integer> getColumnByIndex(int index){
        Vector<Integer> v = new Vector<>();
        if (index<columnSize){
            for(int i=0; i<rowSize;i++){
                v.add(matrix.get(i).get(index));
            }
        }

        return  v;
    }
    public Vector<Integer> getColumnByLabel(int label){
        int index = columnLable.indexOf(label);
        Vector<Integer> v = new Vector<>();
        if (index<columnSize){
            for(int i=0; i<matrix.get(index).size();i++){
                v.add(matrix.get(i).get(index));
            }
        }

        return  v;
    }

    public int getByIndex(int row,int column){
        return matrix.get(row).get(column);
    }

    public int getByLable(int row,int column){
        return matrix.get(rowLable.indexOf(row)).get(columnLable.indexOf(column));
    }

    public void set(int row, int column, int value){
        if(row<rowSize&&column<columnSize) {
            matrix.get(row).set(column, value);
        }
    }

    public void removeBothRowColumnByIndex(int index){
        removeRowByIndex(index);
        removeColumnByIndex(index);
    }

    public void removeRowByIndex(int index){
        matrix.remove(index);
        rowLable.remove(index);
        rowSize--;
    }

    public void removeColumnByIndex(int index){
        for (int i=0; i<rowSize;i++){
            matrix.get(i).remove(index);
        }
        columnLable.remove(index);
        columnSize--;
    }



    public String printMatrix(){
        String message = "    "+columnLable+"\n";
        for (int j=0;j<columnSize;j++){
            message = message+"------";
        }
        message = message+"\n";
        for(int i=0;i<rowSize;i++){
            String a = rowLable.get(i)+"| [ ";
            for (int j=0;j<columnSize;j++){
                a = a+ matrix.get(i).get(j) + ", ";
            }
            a = a +"]\n";
            message=message+a;
        }
        return message;
    }

    public String printInMatlab(){
        String result= "A = [";

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<columnSize;j++){
                result = result+getByIndex(i,j)+" ";
            }
            result = result +"; ";
        }
        result = result+"];\n";
        return result;
    }



    public void replaceColumn(int index, Vector<Integer> vector){
        for(int i=0;i<matrix.size();i++){
            set(i,index,vector.get(i));
        }
    }


    public Matrix clone(){
        Matrix result = new Matrix(rowSize,columnSize);
            for(int j=0;j<columnSize;j++){
                result.replaceColumn(j,getColumnByIndex(j));
            }

        result.setColumnSize(new Integer(columnSize));
        result.setRowSize(new Integer(rowSize));
        result.setColumnLable(new ArrayList<>(columnLable));
        result.setRowLable(new ArrayList<>(rowLable));


            return result;

    }

    public Vector<Vector<Integer>> getMatrix() {
        return matrix;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public List<Integer> getRowLable() {
        return rowLable;
    }

    public List<Integer> getColumnLable() {
        return columnLable;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setRowLable(List<Integer> rowLable) {
        this.rowLable = rowLable;
    }

    public void setColumnLable(List<Integer> columnLable) {
        this.columnLable = columnLable;
    }
}

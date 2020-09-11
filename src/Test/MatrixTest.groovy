package Test

import Main.Matrix
import junit.framework.TestCase
import org.junit.Test

class MatrixTest extends groovy.util.GroovyTestCase {

    @Test
    void testInitialize() {
        Matrix M3 = new Matrix(3,3)
        TestCase.assertEquals(3, M3.rowSize())
        TestCase.assertEquals(3,M3.columnSize())
        TestCase.assertEquals(3,M3.columnSize())
        TestCase.assertEquals(3,M3.columnSize())
        TestCase.assertEquals(3,M3.getColumnSize())
        TestCase.assertEquals(3,M3.getRowSize())
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        List<Integer> l1 = new ArrayList<>();
        l1.add(0);
        l1.add(1);
        l1.add(2);
        println (M3.printMatrix());

        List<Integer>  row = new ArrayList<>()
        List<Integer> column = new ArrayList<>()
        row.add(1)
        row.add(3)
        row.add(5)
        column.add(2)
        column.add(4)
        column.add(6)
        Matrix mm33 = new Matrix(M3.getMatrix(),row,column)
        println mm33.printMatrix()

        assertEquals(l,M3.getColumnLable())
        assertEquals(l1,M3.getRowLable())



    }
    void testGet(){
        Matrix M3 = new Matrix(3,3);
        Vector<Integer> i = new Vector<>();
        Vector<Integer> a = new Vector<>();
        i.add(0);
        i.add(0);
        i.add(0);
        i.setSize(3);
        assertEquals(i,M3.getColumnByIndex(2));
        assertEquals(i,M3.getRowByIndex(0));
        assertEquals(a, M3.getRowByIndex(5))
        assertEquals(a, M3.getColumnByIndex(3))
        assertEquals(i,M3.getColumnByLabel(2));
        assertEquals(i,M3.getRowByLabel(0));
    }

    void testSet(){
        Matrix M3 = new Matrix(3,3);
        M3.set(0,0,1);
        M3.set(1,1,1);
        M3.set(2,2,1);
        M3.set(0,3,1);
        Vector<Integer> i = new Vector<>();
        Vector<Integer> a = new Vector<>();
        Vector<Integer> b = new Vector<>();
        i.add(1);
        i.add(0);
        i.add(0);
        i.setSize(3);
        a.add(0);
        a.add(1);
        a.add(0);
        a.setSize(3);
        b.add(0);
        b.add(0);
        b.add(1);
        b.setSize(3);
        assertEquals(i,M3.getColumnByIndex(0));
        assertEquals(a,M3.getRowByLabel(1));
        assertEquals(b, M3.getRowByLabel(2))

    }
    void testRemove(){
        Matrix m4 = new Matrix(4,4)
        m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(3,0,1);
        m4.set(3,2,1);
        List<Integer> l = new ArrayList<>();
        println("before:\n" + m4.printMatrix())
        for(int i=0;i<=3;i++){
            l.add(i);
        }
        m4.removeBothRowColumnByIndex(2);
        Vector<Integer> a = new Vector<>();
        Vector<Integer> b = new Vector<>();
        Vector<Integer> c = new Vector<>();
        println("after:\n" + m4.printMatrix())

        a.add(0);
        a.add(0);
        a.add(0);
        a.setSize(3);
        b.add(1);
        b.add(0);
        b.add(0);
        b.setSize(3);
        c.add(1);
        c.add(0);
        c.add(0);
        c.setSize(3);



        TestCase.assertEquals(3,m4.getRowSize())
        TestCase.assertEquals(3,m4.getColumnSize())
        assertEquals(a,m4.getRowByIndex(0))
        assertEquals(b,m4.getRowByIndex(1))
        assertEquals(c,m4.getRowByIndex(2))


    }

    void testReplace(){
        Matrix M3 = new Matrix(3);
        M3.set(0,0,1);
        M3.set(1,1,1);
        M3.set(2,2,1);
        M3.set(0,3,1);
        println ("before: "+M3.printMatrix())
        Vector<Integer> a = new Vector<>();
        a.add(0);
        a.add(0);
        a.add(0);
        a.setSize(3);
        M3.replaceColumn(1,a)
        println ("after: "+M3.printMatrix())

        assertEquals(1,1)


    }

    void testClone(){
        Matrix M3 = new Matrix(3,3);
        M3.set(0,0,1);
        M3.set(1,1,1);
        M3.set(2,2,1);
        M3.set(0,3,1);
        Matrix M33 = M3.clone();
        println("original: \n"+M3.printMatrix())

        println("copied: \n"+M33.printMatrix())

        println("now apply change\n")
        M33.set(2,0,1)
        println("original: \n"+M3.printMatrix())

        println("copied: \n"+M33.printMatrix())

        println (M3.printInMatlab());

//        Main.Matrix M3 = new Main.Matrix(1,2);
//        M3.set(0,0,1);
//        M3.set(0,1,1);
//        Main.Matrix M33 = M3.clone();
//        println("original: \n"+M3.printMatrix())
//
//        println("copied: \n"+M33.printMatrix())
//
//        println("now apply change\n")
//        M33.set(2,0,1)
//        println("original: \n"+M3.printMatrix())
//
//        println("copied: \n"+M33.printMatrix())





    }

}

package Test

import Main.AEHGF
import Main.Matrix
import org.junit.Test

class AEHGFTest extends GroovyTestCase {
    @Test
    void testFindTopLevel() {
        Matrix m4 = new Matrix(4,4)
        m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(3,0,1);
        m4.set(3,2,1);
        println m4.printMatrix()
        AEHGF aehgf = new AEHGF(m4);
        List<Integer> l = new ArrayList<>();
        aehgf.findTopLevel();
        List<Integer> a =aehgf.getTopLevel()
        l.add(0);
        //l.add(1);
        //l.add(3);

        assertEquals(l,a)

    }
    void testFindBtmLevel() {
        Matrix m4 = new Matrix(4,4)
        m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(3,0,1);
        m4.set(3,2,1);
        println m4.printMatrix()
        AEHGF aehgf = new AEHGF(m4);
        List<Integer> l = new ArrayList<>();
        aehgf.findBtmLevel();
        List<Integer> a =aehgf.getBtmLevel()
        l.add(1);
        l.add(3)


        assertEquals(l,a)

    }

    void testStepOne() {
        Matrix m4 = new Matrix(4,4)
        m4.set(1,0,1);
        m4.set(3,0,1);
        m4.set(3,3,1);
        println m4.printMatrix()
        AEHGF aehgf = new AEHGF(m4);
        println("before: "+aehgf.getRowElements());
        println(aehgf.getSeperatedMatrix())
        List<Integer> l = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        println(aehgf.getMatrix().printMatrix())
        aehgf.stepOne();
        List<Integer> b =aehgf.getBtmLevel()
        List<Integer> t =aehgf.getTopLevel()
        List<Integer> v =aehgf.getByItself()
        l.add(0);
        l1.add(1)
       // l1.add(3)
        l2.add(2)
        //l2.add(3)
        println("after: "+aehgf.getRowElements());
        println(aehgf.getSeperatedMatrix())
        println(aehgf.getMatrix().printMatrix())

        assertEquals(l,t)
        assertEquals(l1,b)
        assertEquals(l2,v)

    }
    void testStepTwo(){
        Matrix m4 = new Matrix(5,5)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(2,1,1);
        m4.set(3,0,1);
        m4.set(3,1,1);
        m4.set(3,2,1);
        println m4.printMatrix()
        AEHGF aehgf = new AEHGF(m4);
        aehgf.stepOne()
        println ("matrix: "+aehgf.getMatrix().printMatrix())
        aehgf.stepTwo()
       // println (aehgf.getTopBtmRelation())
        List<Integer> l = new ArrayList<>();
        l.add(0)
        l.add(1)
//        List<Integer> l1 = new ArrayList<>();
//        l1.add(2)
        assertEquals(l,aehgf.getTopBtmRelation().get(3))
        //assertEquals(l1,aehgf.getTopBtmRelation().get(3))

    }

    void testStepThree(){
        Matrix m4 = new Matrix(4,4)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        //m4.set(2,1,1);
       // m4.set(3,0,1);
        m4.set(3,1,1);
        //m4.set(3,2,1);
        AEHGF aehgf = new AEHGF(m4);
        aehgf.stepOne()
        println ("matrix: "+aehgf.getMatrix().printMatrix())
        aehgf.stepTwo()
        aehgf.stepThree()
        println(aehgf.getSeperatedMatrix())
        //println(aehgf.getMatrix().printMatrix())
        assertEquals(0,0)


    }

    void testFilterSubsetEachOther(){
        Matrix m4 = new Matrix(4,4)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(2,1,1);
        m4.set(3,0,1);
        m4.set(3,1,1);
        m4.set(3,2,1);
        AEHGF aehgf = new AEHGF(m4);
        List<List<Integer>>  a = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>()
        List<Integer> l2 = new ArrayList<>()
        List<Integer> l3 = new ArrayList<>()
        List<Integer> l4 = new ArrayList<>()
        List<Integer> l5 = new ArrayList<>()
        List<Integer> l6 = new ArrayList<>()
        l1.add(0)
        l2.add(1)
        l3.add(3)
        l3.add(5)
        l4.add(1)
        l4.add(2)
        l5.add(6)
        l6.add(5)
        a.add(l1)
        a.add(l2)
        a.add(l3)
        a.add(l4)
        a.add(l5)
        a.add(l6)
        println ("Before: "+ a)
        aehgf.filterSubsetEachOther(a)
        println ("After: "+ a)


        assertEquals(1,1)

    }


    void testMergeOr(){
        Matrix m4 = new Matrix(4,4)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(2,1,1);
        m4.set(3,0,1);
        m4.set(3,1,1);
        m4.set(3,2,1);
        AEHGF aehgf = new AEHGF(m4);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1.add(0);
        l1.add(0);
        l1.add(1);
        l1.add(1);

        l2.add(0);
        l2.add(1);
        l2.add(0);
        l2.add(1);

        List<Integer> l3 = aehgf.mergeWithOr(l1,l2)

        List<Integer> l4 = new ArrayList<>();
        l4.add(0)
        l4.add(1);
        l4.add(1);
        l4.add(1);

        assertEquals(l4,l3)

    }

    void testMergeAnd(){
        Matrix m4 = new Matrix(4,4)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(2,1,1);
        m4.set(3,0,1);
        m4.set(3,1,1);
        m4.set(3,2,1);
        AEHGF aehgf = new AEHGF(m4);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1.add(0);
        l1.add(0);
        l1.add(1);
        l1.add(1);

        l2.add(0);
        l2.add(1);
        l2.add(0);
        l2.add(1);

        List<Integer> l3 = aehgf.mergeWithAnd(l1,l2)

        List<Integer> l4 = new ArrayList<>();
        l4.add(0)
        l4.add(0);
        l4.add(0);
        l4.add(1);

        assertEquals(l4,l3)

    }

    void testOrForList(){
        Matrix m4 = new Matrix(4,4)
        //m4.set(1,0,1);
        m4.set(2,0,1);
        m4.set(2,1,1);
        m4.set(3,0,1);
        m4.set(3,1,1);
        m4.set(3,2,1);
        AEHGF aehgf = new AEHGF(m4);


        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();
        List<Integer> l4 = new ArrayList<>();


        l1.add(0);
        l1.add(0);
        l1.add(0);
        l1.add(1);

        l2.add(0);
        l2.add(1);
        l2.add(0);
        l2.add(0);

        l3.add(1);
        l3.add(0);
        l3.add(0);
        l3.add(0);

        l4.add(0);
        l4.add(0);
        l4.add(0);
        l4.add(1);

        List<List<Integer>> l7 = new ArrayList<>();

        l7.add(l1);
        l7.add(l2);
        l7.add(l3);
        l7.add(l4);



        List<Integer> l5 = aehgf.orForListList(l7)

        List<Integer> l6 = new ArrayList<>();
        l6.add(1)
        l6.add(1);
        l6.add(0);
        l6.add(1);

        assertEquals(l6,l5)

    }
    void testLargeMatrix(){
        Matrix m = new Matrix(14,14)
        m.set(0,1,1);
        m.set(0,2,1);
        m.set(1,2,1);
        m.set(3,1,1);
        m.set(3,2,1);
        m.set(3,4,1);
        m.set(4,2,1);
        m.set(5,2,1);
        m.set(5,11,1);
        m.set(5,12,1);
        m.set(7,6,1);
        m.set(8,2,1);
        m.set(8,11,1);
        m.set(8,12,1);
        m.set(9,6,1);
        m.set(10,0,1);
        m.set(10,1,1);
        m.set(10,2,1);
        m.set(10,3,1);
        m.set(10,4,1);
        m.set(11,2,1);
        m.set(11,12,1);
        AEHGF aehgf = new AEHGF(m);

        println m.printMatrix()
        aehgf.stepOne()
        aehgf.stepTwo()
        aehgf.stepThree()
        aehgf.levelMethod()
        println("Alone:"+aehgf.getAloneNode())
        println("Top-Level: "+aehgf.getTopLevel())
        println("Bot-Level: "+aehgf.getBtmLevel())
        println("Top-Bot Relation:"+aehgf.getTopBtmRelation())
        println("Total Result: "+aehgf.getLevelOfWholeGraph())
       // println("aaaa"+aehgf.getLevelOfWholeGraph().get(1).get(0).third.printMatrix())
        println(aehgf.getLevelOfWholeGraph().size())
        for(int i=0;i<aehgf.getLevelOfWholeGraph().size();i++){
            for(int j=0;j<aehgf.getLevelOfWholeGraph().get(i).size();j++){
                println(aehgf.getLevelOfWholeGraph().get(i).get(j).first)
                println(aehgf.getLevelOfWholeGraph().get(i).get(j).second)
                println(aehgf.getLevelOfWholeGraph().get(i).get(j).third.printMatrix())
                aehgf.replotMatrix(aehgf.getLevelOfWholeGraph().get(i).get(j).first,aehgf.getLevelOfWholeGraph().get(i).get(j).second,aehgf.getLevelOfWholeGraph().get(i).get(j).third)
            }

        }
        aehgf.buildNewMatrix();


//        aehgf.doAEHGF();
//        println(aehgf.getTopLevel())


    }


    void testSmall(){
        Matrix m = new Matrix(4,4)
        m.set(1,0,1);
        m.set(2,0,1);
        m.set(3,0,1);
        m.set(3,2,1);
        AEHGF aehgf = new AEHGF(m);

        println m.printMatrix()
//        aehgf.stepOne()
//        aehgf.stepTwo()
//        aehgf.stepThree()
//        aehgf.levelMethod();
//
//        //println("Alone:"+aehgf.getAloneNode())
//        //println("Total Result: "+aehgf.getLevelOfWholeGraph())
//
//        aehgf.buildNewMatrix();

        aehgf.doAEHGF();



    }





    void testifOnlyConnectedTo(){
        Matrix m = new Matrix(4,4)
        m.set(1,0,1);
        m.set(2,0,1);
        m.set(3,0,1);
        m.set(3,2,1);
        AEHGF aehgf = new AEHGF(m);

        Vector<Integer> list = new Vector<>();
        List<Integer> label = new ArrayList<>();
        List<Integer> column = new ArrayList<>();
        List<Integer> column1 = new ArrayList<>();

        list.add(0)
        list.add(1)
        list.add(1)
        list.add(0)

        label.add(0)
        label.add(1)
        label.add(2)
        label.add(3)

        column.add(1)

        column1.add(1)
        column1.add(2)

        assertFalse(aehgf.ifOnlyConnectedTo(list,label,column))
        assertTrue((aehgf.ifOnlyConnectedTo(list,label,column1)))







    }



}

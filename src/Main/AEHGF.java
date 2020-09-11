package Main;

import org.jgrapht.alg.util.Pair;
import org.jgrapht.alg.util.Triple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AEHGF {

    private Matrix matrix;
    private int originalSize;
    private List<Integer> topLevel;
    private List<Integer> btmLevel;
    private List<Integer> byItself;
    private List<Integer> rowElements;
    private List<Integer> columnElements;
    private HashMap<List<Integer>,Matrix> seperatedMatrix;
    private HashMap<Integer,List<Integer>> topBtmRelation;
    private List<List<Integer>> filterdTopFromRelation;
    private List<List<Triple<List<Integer>,List<Integer>,Matrix>>> levelOfWholeGraph;
    private List<Integer> aloneNode;
    private Matrix finalResult;
    private  List<Pair> points;
    private List<String> labelNames;


    public AEHGF(Matrix matrix) {
        this.matrix = matrix;
        topLevel = new ArrayList<>();
        btmLevel = new ArrayList<>();
        byItself = new ArrayList<>();
        rowElements = matrix.getRowLable();
        columnElements = matrix.getColumnLable();
        seperatedMatrix = new HashMap<>();
        topBtmRelation = new HashMap<>();
        filterdTopFromRelation = new ArrayList<>();
        levelOfWholeGraph = new ArrayList<>();
        aloneNode = new ArrayList<>();
        originalSize = matrix.rowSize();
        finalResult = new Matrix(1,1);
         points = new ArrayList<>();
         labelNames = new ArrayList<>();


    }

    public AEHGF(Matrix matrix,List<String> names) {
        this.matrix = matrix;
        topLevel = new ArrayList<>();
        btmLevel = new ArrayList<>();
        byItself = new ArrayList<>();
        rowElements = matrix.getRowLable();
        columnElements = matrix.getColumnLable();
        seperatedMatrix = new HashMap<>();
        topBtmRelation = new HashMap<>();
        filterdTopFromRelation = new ArrayList<>();
        levelOfWholeGraph = new ArrayList<>();
        aloneNode = new ArrayList<>();
        originalSize = matrix.rowSize();
        finalResult = new Matrix(1,1);
         points = new ArrayList<>();
         labelNames = new ArrayList<>(names);


    }

    public String doAEHGF(){

        stepOne();
        stepTwo();
        stepThree();
        levelMethod();
        buildNewMatrix();
        String filename=writeToMatlab();
        return filename;
    }



    public void stepOne() {
        findTopLevel();
        findBtmLevel();
        findByItSelf();;
        for(int i=0;i<byItself.size();i++){
            List<Integer> l = new ArrayList<>();
            l.add(byItself.get(i));
            aloneNode.addAll(l);
            matrix.removeBothRowColumnByIndex(byItself.get(i));
            columnElements.remove(new Integer(byItself.get(i)));
            rowElements.remove(new Integer(byItself.get(i)));


        }



    }

    public void stepTwo(){
        for(int i=0;i<btmLevel.size();i++){
            List<Integer> l = matrix.getRowByLabel(btmLevel.get(i));
            topBtmRelation.put(btmLevel.get(i),new ArrayList<>());
            for(int j=0;j<l.size();j++){

                if(l.get(j)==1&&topLevel.contains(columnElements.get(j))){
                    topBtmRelation.get(btmLevel.get(i)).add(columnElements.get(j));
                }
            }
        }
        Iterator it = getTopBtmRelation().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry element = (Map.Entry)it.next();
            filterdTopFromRelation.add((List<Integer>) element.getValue());
        }



        //System.output.println("Before filter: "+filterdTopFromRelation);

        filterSubsetEachOther(filterdTopFromRelation);
       // System.output.println("After filter: "+filterdTopFromRelation);

    }

    public void stepThree(){
        for(int i=0;i<filterdTopFromRelation.size();i++){
            List<Integer> l = filterdTopFromRelation.get(i);
            List<List<Integer>> ll= new ArrayList<>();
            Matrix mtx = matrix.clone();
            for(int j=0;j<l.size();j++){
                ll.add(mtx.getColumnByLabel(l.get(j)));
            }
            List<Integer> listAfterOr = orForListList(ll);
          //  System.output.println(listAfterOr);

            Matrix m = matrix.clone();
            listAndMatrix(listAfterOr,m);
        //    System.output.println(m.printMatrix());

            for(int a=0;a<m.rowSize();a++){
                if(AllZero(m.getRowByIndex(a))){
                    m.removeRowByIndex(a);
                    a--;
                   // System.output.println(m.printMatrix());
                }
            }

            for(int b=0;b<m.columnSize();b++){
                if(AllZero(m.getColumnByIndex(b))){
                    m.removeColumnByIndex(b);
                    b--;
                }
            }
           // System.output.println(m.printMatrix());
            seperatedMatrix.put(l,m);

        }

    }


    public void levelMethod(){

        Iterator it = seperatedMatrix.entrySet().iterator();

        while(it.hasNext()){
            List<Triple<List<Integer>,List<Integer>,Matrix>>levelOfGraph = new ArrayList<>();

            Map.Entry mapElement = (Map.Entry)it.next();
            Matrix m = seperatedMatrix.get(mapElement.getKey());
            List<Integer> firstSet = new ArrayList((List<Integer>)mapElement.getKey());
            List<Integer> current = new ArrayList<>(firstSet);
            List<Integer> originalLabel = new ArrayList<>(m.getColumnLable());

            while (m.getRowSize()>0&&m.getColumnSize()>0) {

               // System.output.println("Size: " + m.getRowSize()+"x"+m.getColumnSize());
                List<Integer> ll = checkRowsOnlyCOnnectTo(m, current);
                Matrix mcopy = m.clone();
                levelOfGraph.add(new Triple<List<Integer>,List<Integer>,Matrix>(current, ll,mcopy));
                for(int i=0;i<ll.size();i++){
                    m.removeRowByIndex(m.getRowLable().indexOf(ll.get(i)));

                }
                for(int i=0;i<current.size();i++){
                    m.removeColumnByIndex(m.getColumnLable().indexOf(current.get(i)));

                }

                List<Integer> a = new ArrayList<>();
                for(int i=0;i<ll.size();i++){
                    if (originalLabel.contains(ll.get(i))){
                        a.add(ll.get(i));
                    }
                }
                current = a;



            }

            levelOfWholeGraph.add(levelOfGraph);

        }
    }


    public void buildNewMatrix(){
        Matrix result = new Matrix(originalSize,originalSize);
        List<Pair> points = new ArrayList<>();


        for(int i=0;i<levelOfWholeGraph.size();i++){
            for(int j=0;j<levelOfWholeGraph.get(i).size();j++){
                points.addAll(replotMatrix(levelOfWholeGraph.get(i).get(j).getFirst(),levelOfWholeGraph.get(i).get(j).getSecond(),levelOfWholeGraph.get(i).get(j).getThird()));
            }

        }
        //System.out.println(points);
        this.points = points;
        for(int i=0;i<points.size();i++){
            result.set(result.getRowLable().indexOf(points.get(i).getFirst()),result.getColumnLable().indexOf(points.get(i).getSecond()),1);
        }
      //  System.out.println(result.printMatrix());
        finalResult = result.clone();
    }

    public String writeToMatlab(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String filename = "Graphic_Output" +dtf.format(now)+".m";

        String matrixInMatlab = finalResult.printInMatlab();

        String connect = "cla\n" +
                "subplot(1,1,1);\n";

        String plot = generatePlot();


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(matrixInMatlab+connect+plot);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;

    }



    //supporting functions==============================================================================================

    public void filterSubsetEachOther(List<List<Integer>> list){
        for(int i=0;i<list.size();i++){
            for(int j=i+1;j<list.size();j++){
                if(Collections.indexOfSubList(list.get(i),list.get(j))!=-1 ||Collections.indexOfSubList(list.get(j),list.get(i))!=-1){
                    if(list.get(i).size()>=list.get(j).size()){
                        list.remove(j);
                        j--;

                    }
                    else {
                        list.remove(i);
                        j = i+1;
                    }
                }
            }
        }
    }




    public void findTopLevel(){
        for(int i=0; i<rowElements.size();i++){
            if(AllZero(matrix.getRowByIndex(i))){
                topLevel.add(i);
            }
        }
    }

    public void findBtmLevel(){
        for(int i=0; i<columnElements.size();i++){
            if(AllZero(matrix.getColumnByIndex(i))){
                btmLevel.add(i);
            }
        }
    }

    public  void findByItSelf(){
        for(int i=0;i< Math.max(btmLevel.size(),topLevel.size());i++){

            if(btmLevel.size()>=topLevel.size()){
                if (topLevel.contains(btmLevel.get(i))){
                int a = btmLevel.get(i);
                byItself.add(a);
                topLevel.remove(new Integer(a));
                btmLevel.remove(new Integer(a));
                i--;
            }
            }
            else {
                if (btmLevel.contains(topLevel.get(i))){
                int a = topLevel.get(i);
                byItself.add(a);
                btmLevel.remove(new Integer(a));
                topLevel.remove(new Integer(a));
                i--;
            }
            }
        }
    }

    public List<Integer> orForListList(List<List<Integer>> list){
        List<Integer> result;
        if(list.size()==1){
            result = list.get(0);
        }
        else {
            result = list.get(0);
            for(int i=1;i<list.size();i++){
                result = mergeWithOr(result,list.get(i));
            }
        }



        return result;
    }


    public void listAndMatrix(List<Integer> list, Matrix matrix){
        for(int i =0;i< matrix.columnSize();i++){
            List<Integer> l = matrix.getColumnByIndex(i);
            List<Integer> l1 = mergeWithAnd(l,list);
            Vector<Integer> v = new Vector<>();
            v.addAll(l1);
            matrix.replaceColumn(i,v);
        }
    }


    public List<Integer> mergeWithOr(List<Integer> l1, List<Integer> l2){
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<l1.size();i++){
            result.add(or(l1.get(i),l2.get(i)));

        }
        return result;
    }

    public List<Integer> mergeWithAnd(List<Integer> l1, List<Integer> l2){
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<l1.size();i++){
            result.add(and(l1.get(i),l2.get(i)));

        }
        return result;
    }



    public String generatePlot() {
        String result ="gplot(A,[";

        int maxHeight=1;
        //int maxWidth=1;
        int graphWidth=1;
        int graphHeight=1;
        List<Triple> plots = new ArrayList<>();

        for(int i=0;i<originalSize;i++){
            if(labelNames.size()>0){
                plots.add(new Triple(labelNames.get(i), -1, -1));
            }
            else {
                plots.add(new Triple(String.valueOf(i), -1, -1));
            }
        }

//        List<Integer> widthTracking = new ArrayList<>();


        for (int i=0;i<levelOfWholeGraph.size();i++){
            if(levelOfWholeGraph.get(i).size()>maxHeight){
                maxHeight = levelOfWholeGraph.get(i).size()+1;
            }
        }



        int widthBase=0;

        Queue<Integer> levelQueue = new LinkedList<>();
        for(int i=0;i<topLevel.size();i++){
            levelQueue.add(topLevel.get(i));
        }


        while(!levelQueue.isEmpty()){
            int baseEle = levelQueue.poll();
            Queue<Integer> current = new LinkedList();
            current.add(baseEle);
            int currHeight=maxHeight;
            int currWidth=widthBase;
            int numOfItemInLevel =1;
            int numOfItemNextLevel =0;
            int maxItemInCurrGraph =1;

            while (!current.isEmpty()){
                int c = current.poll();
                if(numOfItemInLevel<=0){
                    currHeight--;
                    if(numOfItemNextLevel>maxItemInCurrGraph){
                        maxItemInCurrGraph=new Integer(numOfItemNextLevel);
                    }
                    numOfItemInLevel = new Integer(numOfItemNextLevel);
                    numOfItemNextLevel=0;
                    currWidth=widthBase;

                }


                for(int j=0;j<points.size();j++){
                    if((int)points.get(j).getSecond() == c){
                        current.add((int)points.get(j).getFirst());
                        numOfItemNextLevel++;
                    }
                }

                if((int)plots.get(c).getSecond()==-1&&(int)plots.get(c).getThird()==-1) {
                    plots.get(c).setSecond(currWidth);
                    plots.get(c).setThird(currHeight);
                    currWidth++;

                }

            numOfItemInLevel--;

            }


            //System.out.println(widthBase);
            widthBase= widthBase+maxItemInCurrGraph;
            //System.out.println(widthBase);
            graphWidth = widthBase+2;

        }
        for(int i=0;i<plots.size();i++){
            int p=-1;
            if ((int)plots.get(i).getSecond()==-1&&(int)plots.get(i).getSecond()==-1){
                plots.get(i).setThird(p);
                p--;
            }
        }




        for(int i=0;i<plots.size();i++){
            result = result+plots.get(i).getSecond()+" "+plots.get(i).getThird()+";";
        }

        result = result+"],'-o');\n";

        result = result +"text([";
        for(int i=0;i<plots.size();i++){
            result = result+plots.get(i).getSecond()+" ";
        }

        result = result+"],[";
        for(int i=0;i<plots.size();i++){
            result = result+((int)plots.get(i).getThird()-0.25)+" ";
        }
        result = result+"],{";
        for(int i=0;i<plots.size();i++){
            result = result+"'"+plots.get(i).getFirst()+"',";
        }
        result = result+"});\n";


        graphHeight = maxHeight+2;



        result =result+"axis([-1 "+graphWidth+" -1 "+graphHeight+"],'off')";




        //System.out.println("width: "+maxWidth+", height: "+maxHeight);






        return result;
    }


    public List<Integer> checkRowsOnlyCOnnectTo(Matrix m, List<Integer> column){

        //System.output.println("For " +column);

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<m.getRowSize();i++){

            if (ifOnlyConnectedTo(m.getRowByIndex(i),m.getColumnLable(),column)){
                result.add(m.getRowLable().get(i));
               // System.output.println("row "+m.getRowLable().get(i));
            }

        }


        return result;

    }



    public boolean ifOnlyConnectedTo(Vector<Integer> l, List<Integer> label, List<Integer> column){

        if(AllZero(l)){
            return false;
        }
       Vector<Integer> answer= new Vector<>();
        for(int j=0;j<l.size();j++){
            if(column.contains(label.get(j))||l.get(j)==0){
                answer.add(0);
            }
            else {
                answer.add(1);
            }

        }

        return AllZero(answer);
    }


    public List<Pair> replotMatrix(List<Integer> tops, List<Integer> bots, Matrix m){
        List<Pair> result = new ArrayList<>();

        for(int i=0;i<bots.size(); i++){
            Vector<Integer> current = m.getRowByLabel(bots.get(i));
            for(int j=0; j<m.columnSize();j++){
                if(current.get(j)==1&&tops.contains(m.getColumnLable().get(j))){
                    result.add(new Pair(bots.get(i),m.getColumnLable().get(j)));
                }

            }
        }

       // System.output.println(result);
        return result;
    }


    private boolean AllZero(Vector<Integer> v){
        for(int i=0; i<v.size();i++){
            if (v.get(i)!=0){
                return false;
            }
        }
        return true;
    }

    private int and(int a, int b){
        if(a==1&&b==1){
            return 1;
        }
        else return 0;
    }

    private int or(int a, int b){
        if(a==1 || b==1){
            return 1;
        }
        else return 0;
    }


    public List<List<Triple<List<Integer>, List<Integer>, Matrix>>> getLevelOfWholeGraph() {
        return levelOfWholeGraph;
    }

    public List<Integer> getAloneNode() {
        return aloneNode;
    }

    public List<Integer> getRowElements() {
        return rowElements;
    }

    public List<Integer> getColumnElements() {
        return columnElements;
    }

    public List<List<Integer>> getFilterdTopFromRelation() {
        return filterdTopFromRelation;
    }


    public HashMap<Integer, List<Integer>> getTopBtmRelation() {
        return topBtmRelation;
    }

    public HashMap<List<Integer>, Matrix> getSeperatedMatrix() {
        return seperatedMatrix;
    }

    public List<Integer> getTopLevel() {
        return topLevel;
    }

    public List<Integer> getBtmLevel() {
        return btmLevel;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public List<Integer> getByItself() {
        return byItself;
    }
}

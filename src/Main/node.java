package Main;

import java.util.ArrayList;
import java.util.List;

public class node {

    private int name;
    private List<node> parent;
    private List<node> child;


    public node(int name) {
        this.name = name;
        parent = new ArrayList<>();
        child = new ArrayList<>();
    }

    public int getName() {
        return name;
    }

    public List<node> getParent() {
        return parent;
    }

    public List<node> getChild() {
        return child;
    }


    public boolean hasChild(int n){
        if (child.isEmpty()){
            return  false;
        }
        else {
            for (int i = 0; i < child.size(); i++) {
                if(child.get(i).getName() == n){
                    return true;
                }
            }
        }
    return false;
    }

    public boolean hasParent(int n){
        if (parent.isEmpty()){
            return  false;
        }
        else {
            for (int i = 0; i < parent.size(); i++) {
                if(parent.get(i).getName() == n){
                    return true;
                }
            }
        }
        return false;
    }


    public void addChild(node n){
        if (!hasChild(n.name)){
            child.add(n);
            n.addParent(this);

        }

    }

    public void addParent(node n){
        if (!hasParent(n.name)){
            parent.add(n);
            n.addChild(this);
        }

    }


}

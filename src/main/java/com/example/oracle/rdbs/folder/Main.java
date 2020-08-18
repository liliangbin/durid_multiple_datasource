package com.example.oracle.rdbs.folder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<DoneFolder> doneFolder(List<DoneFolder> source,Long id){
        if (source==null) return null;
        List<DoneFolder> temp=new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i).parent==id)
                temp.add(source.get(i));

        }
        source.removeAll(temp);

        System.out.println(source.size());
        for (int i = 0; i < temp.size(); i++) {
            temp.get(i).items=doneFolder(source,temp.get(i).id);
        }
        return temp;
    }


    public static void main(String[] args) {
        List<DoneFolder> dones=new ArrayList<DoneFolder>();
        dones.add(new DoneFolder(1,"一层子节点",0))
                ;
        dones.add(new DoneFolder(2,"一层子节点",0));
        dones.add(new DoneFolder(3,"二层子节点",1));
        dones.add(new DoneFolder(4,"三层子节点",3));

        List<DoneFolder> folders=doneFolder(dones,0l);

        System.out.println(" done ");


    }
}

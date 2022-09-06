package models;

import java.util.ArrayList;

public class CustomList<E>{
	private ArrayList<E> list ;
	
	public CustomList() {
		this.list = new ArrayList<E>(); 
	}
//	public 
	public void add(E e){
        list.add(e); 
        System.out.println("<<<Over here>>>");
        //do other things you want to do when items are added 
    }
    public E remove(E e){
        list.remove(e);
        return e;
        //do other things you want to do when items are removed
    }
    
    public ArrayList<E> getValues(){
    	return list;
    }
    
    public void setValues(ArrayList<E> list) {
    	this.list = list;
    }

}

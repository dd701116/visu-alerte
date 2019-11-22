package reseau;

import java.io.IOException;
import java.util.Vector;

import connector.Ihm;
import connector.Sensor;

public class SlotManager<T> {
	
	private T[] slots;
	public final int SIZE_MAX;
	private int cursor=0;
	
	@SuppressWarnings("unchecked")
	public SlotManager(int nbslot){
		SIZE_MAX = nbslot;
		slots = (T[]) new Object[SIZE_MAX];
	}
	
	public int size(){
		
		int size = 0;
		
		for (int i = 0; i < SIZE_MAX; i++) {
			if(get(i)!=null)
				size++;
		}
		
		return size;
	}
	
	public T get(int index){
		return slots[index];
	}
	
	public int getFirstEmpltySlot(){
		
		for (int i = 0; i < SIZE_MAX; i++) {
			if(get(i)==null)
				return i;
		}
		
		return -1;
		
	}
	
	public int add(T element){
		int emptySlot = getFirstEmpltySlot();
		if (valideIndex(emptySlot)) {
			slots[emptySlot]=element;
		}
		return emptySlot;
	}
	
	public T remove(int index){
		
		if (valideIndex(index)) {
			T element = get(index);
			slots[index] = null;
			return element;
		}
		
		return null;
		
	}
	
	private boolean valideIndex(int index){
		if(index>-1 && index<SIZE_MAX)
			return true;
		return false;
	}
	
	public T getNextElement(){
		
		for (cursor=cursor; cursor < SIZE_MAX; cursor++) {
			if(get(cursor)!=null)
				return get(cursor);
		}		
		resetCursor();
		
		return null;
	}
	
	public void resetCursor(){
		cursor =0;
	}



}

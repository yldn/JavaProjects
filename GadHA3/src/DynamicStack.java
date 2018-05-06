/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
  private DynamicArray dynArr;
  
  /**
   * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
   */
  private int length;
  
  public int getLength() {
    return length;
  }
  
  public DynamicStack (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    length = 0;
  }
  
  /**
   * Diese Methode legt ein Element auf den Stapel.
   * 
   * @param value das Element, das auf den Stapel gelegt werden soll
   */
  public void pushBack (int value) {
    Interval x = null;
    if (length==0){
      x = new EmptyInterval ();
    }
    if(length>0){
      x = new NonEmptyInterval(0,length-1);
    }
    //reportUsage() aufrufen bevor set(); 
    x = dynArr.reportUsage(x, length+1);
    dynArr.set(length, value);

    length++;
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
	  if(length >0){
		  int out = dynArr.get(length-1);		
		  Interval x=null;
		 if(length==1)
			 x = new EmptyInterval ();
		else
			 x = new NonEmptyInterval(0,length-2);
		 x = dynArr.reportUsage(x, 0);
		 length--;
		 return out;
		}
		else throw new RuntimeException();
  }
}

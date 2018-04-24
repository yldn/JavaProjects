
/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär
 * nach Wertebereichen zu suchen.
 */
class BinSea {
  /**
   * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
   * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
   * einem Intervall zu finden. Wird kein passender Index gefunden, wird
   * -1 zurückgegeben.
   * 
   * Beispiel:
   * 
   *               0 <------------------> 5
   * sortedData: [-10, 33, 50, 99, 123, 4242 ]
   * value: 80             ^   ^
   *                       |   |
   *                       |   `- Ergebnis (3) für ersten Index im Intervall, da 99
   *                       |      als erster Wert im Feld größer als 80 ist
   *                       |
   *                       `- Ergebnis (2) für letzten Index im Intervall, da 50
   *                          als letzter Wert kleiner als 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param value der Wert der Intervallbegrenzung, der dem gesucht wird
   * @param lower true für untere Intervallbegrenzung, false für obere Intervallbegrenzung
   * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
   */
  private static int search (int[] sortedData, int value, boolean lower) {

	  if ( sortedData.length == 0){
		   return -1 ;
	  }
	  if(value >sortedData[sortedData.length-1] && !lower ) {
		  return -1;
	  }
	  if(value < sortedData[0] && lower) {
		  return 1;
	  }
	  return binary_rec(sortedData,0,sortedData.length,value,lower);
	  
  }
   //binary search recursive 
  
  	static int binary_rec(int [] Data,int start, int end ,int value,boolean lower) {
  		if(start>end)
  			return start;
  		int mid = start +(end - start)/2;
  		
  		if(Data[mid]>value){
  			
  			return binary_rec(Data,start, mid-(lower? 1:0) , value,lower);
  		}
  			
  		else if (Data[mid]<value)
  			return binary_rec(Data,mid-(lower? 0:1) ,end , value,lower);
  		else 
  			return mid;
  	}
  
  
  //return a index that just right last smaller than the variable value
//   static int binarySearch(int [] Data,int start, int end ,int value){
//	  int mid = 0 ;
//	  while(start < end ){
//		  mid = start +(end - start)/2;
//		  if(Data[mid]< value){
//			  start=mid+1;
//		  }else if(Data[mid]>value){
//			  end= mid ;
//		  }else                                
//			  return -1;
//	  }
//	  return mid;
//  }

  /**
   * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes, deren Werte
   * in einem Wertebereich liegen. Es soll dabei binäre Suche verwendet werden.
   * 
   * Beispiel:
   *                     0 <-----------------------> 5
   * sortedData:         [-10, 33, 50, 99, 123, 4242 ]
   * valueRange: [80;700]              ^   ^
   *                                   |   |
   *                                   |   `- Ergebnis (4) für obere Intervallbegrenzung,
   *                                   |      da 123 als letzter Wert kleiner oder gleich 700 ist
   *                                   |
   *                                   `- Ergebnis (3) für untere Intervallbegrenzung,
   *                                      da 99 als erster Wert größer oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param valueRange der Wertebereich, zu dem Indices gesucht werden
   */
  public static Interval search (int[] sortedData, Interval valueRange) {
	  //x index which smaller than valueRange.from
	  int x = search(sortedData,valueRange.getFrom(),false);
	  //y index which bigger than valueRange.to
	  int y = search(sortedData,valueRange.getTo(),true);
	  
	  return Interval.fromArrayIndices(x,y);
     
  }


	public static void main (String []args){
		int [] Data = new int [] {-10, 33, 50, 99, 123, 4242 };
		
		System.out.println(search(Data,33,true));
		System.out.print(search(Data,Interval.fromArrayIndices(80,90)).toString());
		
		
	}
}
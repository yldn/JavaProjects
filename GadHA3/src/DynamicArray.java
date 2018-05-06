/**
 * Diese Klasse implementiert ein dynamisches Feld.
 */
public class DynamicArray {
  private int[] elements;

  /**
   * Diese Methode gibt die Größe des inneren
   * Feldes zurück.
   * 
   * @return die Größe des inneren Feldes
   */
  public int getInnerLength () {
    return elements.length;
  }

  private final int growthFactor;

  private final int maxOverhead;

  /**
   * Dieser Konstruktor initialisiert ein dynamishes Feld. Es muss dabei gelten,
   * dass
   * 
   * 1. growthFactor >= 1
   * 2. maxOverhead >= 2
   * 3. growthFactor < maxOverhead
   * 
   * @param growthFactor der Wachstumsfaktor; um diesen wird der interne Speicher
   * vergrößtert, wenn nicht mehr genug Platz zur Verfügung steht.
   * @param maxOverhead der maximale Overhead; wird weniger als [maximaler Overhead]-fache
   * des genutzten Speichers benötigt, so wird der interne Speicher verkleinert.
   */
  public DynamicArray (int growthFactor, int maxOverhead) {
    if (growthFactor < 1 || maxOverhead < 2 || maxOverhead <= growthFactor)
      throw new RuntimeException("DynamicArray(): Invalid arguments");
    this.growthFactor = growthFactor;
    this.maxOverhead = maxOverhead;
    elements = new int[0];
  }
  
  /**
   * Diese Methode erlaubt es dem Benutzer, das dynamische Feld über Änderungen
   * im verwendeten Feldbereich zu informieren. Die Methode passt ggf. den internen
   * Speicher an (Reallocation). Elemente dürfen dabei umkopiert und der verwendete Bereich
   * verschoben werden. Die Methode gibt zurück, wo sich die Elemente, die
   * sich vor dem Aufruf in Verwendung befanden, nach dem Aufruf befinden.
   * 
   * Bezüglich der Speicherverwaltung, die z.B. mit Hilfe einer privaten Hilfsfunktion 
   * implementiert werden kann, gelten die folgenden Regeln: 
   * 
   * - Wird aktuell mehr Speicher benötigt, als vorhanden ist,
   * wird die Größe des internen Speichers derart angepasst, dass er anschließend
   * [Speicherforderung]*[Wachstumsfaktor] viele Elemente fassen kann.
   * 
   * - Ist die Speicherforderung zu gering, ist also der bereitgestellte Speicher
   * mehr als um das [Maximaler Overhead]-fache größter als die Speicherforderung,
   * wird die Größe des internen Speichers derart angepasst, dass er anschließend
   * [Speicherforderung]*[Wachstumsfaktor] viele Elemente fassen kann.
   * 
   * @param usage gibt an, welche Elemente des dynamischen Feldes aktuell in Benutzung
   * befindlich sind; es kann ein beliebiger Bereich in Benutzung sein. Insbesondere
   * kann es vorkommen, dass usage.getFrom() größer ist als usage.getTo() - in diesem
   * Fall sind die Elemente [usage.getFrom(); elements.length - 1] und [0; usage.getTo()]
   * in Benutzung.
   * 
   * @param minSize gibt die minimale Größe benötigten Feldbereiches nach dem Aufruf
   * an; der Aufruf löscht niemals Elemente. Es gilt also, dass nach dem Aufruf 
   * max(usage.getSize(), minSize) viel Platz zur Verfügung steht.
   * 
   * @return ein neues Intervall, in dem sich alle Elemente in ungeänderter Reihenfolge
   * befinden, die vor dem Auruf in Verwendung waren
   */
  public Interval reportUsage(Interval usage, int minSize) {
    if(usage==null){
      throw new RuntimeException();
    }
    //when usage.size = element.length
    //elements can not accept any further new numbers
    // element should expand the length of elements.length*growthfactor
    if(elements.length==usage.getSize(elements.length)){
      Interval x = new NonEmptyInterval(0,usage.getSize(elements.length));
      reallocate(usage, (elements.length==0 ?1: elements.length) *growthFactor);
      return x;
    }
    //if usage.size*maxOverhead >=elements.length
    //copy in to a new array which length = usage.size*growthfactor
    if(maxOverhead*usage.getSize(elements.length)<=elements.length&&usage.isEmpty()!=true){
      reallocate(usage,max(minSize,usage.getSize(elements.length)*growthFactor));
    }
    return usage;
  }
  
  //a method that reallocate the Interval if not furfill the condition.
  private void reallocate (Interval usage, int lengthNew) {

    int [] neuarray=new int[lengthNew];

    for(int i=0 ;i<usage.getSize(elements.length);i++){
      neuarray[i]=elements[(i + usage.getFrom())%elements.length];
    }
    elements=neuarray;
  }

  private int max(int x,int y){
    if (x>y)
      return x;
    else
      return y;
  }

  /**
   * Diese Methode holt ein Element aus dem dynamischen Feld.
   * 
   * @param index der Index desjenigen Elementes, das ermittelt
   * werden soll
   * @return das ermittelte Element
   */
  public int get(int index) {
    return elements[index];
  }
  
  /**
   * Diese Methode setzt des Wert des dynamischen Feldes an einem
   * bestimmten Index auf einen Wert.
   * 
   * @param index der Index des zu setzenden Elementes
   * @param value der Wert des zu setzenden Elementes
   */
  public void set(int index, int value) {
    elements[index]=value;
  }
  public String toString () {
	  String out = "[";
	  for(int i = 0 ;i< elements.length;i++) {
		  out+=elements[i];
		  if(i!=elements.length-1) {
			  out+=",";
		  }
	  }
	  out+="]";
	  return out;
  }
  
}

package Assignment2;


public class HashTableQuadraticProbing{
    private int currentSize;
    private static int maxSize;       
    private static Integer[] keys;   
    private static String[] vals;   
   

    public HashTableQuadraticProbing(int capacity){

        currentSize = 0;
        maxSize = capacity;
        keys = new Integer[maxSize];
        vals = new String[maxSize];
    }

    private static int hash(Integer key) 
    {
        return key.hashCode() % maxSize;
    } 
    public int size() 
    {
        return currentSize;
    }
    public boolean isEmpty() 
    {
        return size() == 0;
    }
    public boolean contains(Integer key) 
    {
        return get(key) !=  null;
    }
    public void add(Integer key, String val) 
    {                
        int tmp = hash(key);
        int i = tmp, h = 1;
        do
        {
            if (keys[i] == null)
            {
                keys[i] = key;
                vals[i] = val;
                currentSize++;
                return;
            }
            if (keys[i].equals(key)) 
            { 
                vals[i] = val; 
                return; 
            }            
            i = (i + h * h++) % maxSize;            
        } while (i != tmp);       
    }
    public static String get(Integer key) 
    {
        int i = hash(key), h = 1;
        while (keys[i] != null)
        {
            if (keys[i] == key)
                return vals[i];
            i = (i + h * h++) % maxSize;
   
        }            
        return null;
    }

    public void remove(Integer key) 
    {
        if (!contains(key)) 
            return;
 
      
        int i = hash(key), h = 1;  /// find position key and delete 
        while (!key.equals(keys[i])) 
            i = (i + h * h++) % maxSize;        
        keys[i] = null;
        vals[i] = null;
 
  
        for (i = (i + h * h++) % maxSize; keys[i] != null; i = (i + h * h++) % maxSize)    // rehash all keys   
        {
            Integer tmp1 = keys[i];
            String tmp2 = vals[i];
            keys[i] = null;
            vals[i] = null;
            currentSize--;  
            add(tmp1, tmp2);            
        }
        currentSize--;        
    }
    public static void main(String[] args){

        HashTableQuadraticProbing table = new HashTableQuadraticProbing(10);
    
        System.out.println("This is Quadratic probing Tests");
        System.out.println("'");
        table.add(3,"crab");
        table.add(11,"dolphin");
        table.add(7,"koala");
        table.add(4,"giraf");
        System.out.println(table.contains(7)); // expected true
        System.out.println(table.size());  // expected 4
        System.out.println(get(3));  // expected crab
        System.out.println(get(31));   //expected null
        System.out.println(get(4));    //expected giraf
        table.remove(3);
        System.out.println(table.size()); // expected 3
        table.remove(44);
    
        System.out.println(table.size()); // expected 3 because 44 does not exsit
        table.remove(7);
        System.out.println(table.size()); // expected 2 
        System.out.println(table.isEmpty()); // expected false 

    }
}
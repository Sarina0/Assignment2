package Assignment2;

public class HashTableSeperateChaining{

    private static HashNode[] buckets;
    private static int numOfBuckets;
    private int size;
   
    public HashTableSeperateChaining(){
        this(10); //defualt capacity
    }
    public HashTableSeperateChaining(int capacity){
        HashTableSeperateChaining.numOfBuckets = capacity;
        HashTableSeperateChaining.buckets = new HashNode[numOfBuckets];
        this.size = 0;
    }


    private class HashNode {
        private Integer key;
        private String value;
        private HashNode next;
    
        public HashNode(Integer key, String value){
            this.key = key;
            this.value = value;
        }
}
public int size(){
    return size;
}

public boolean isEmpty(){
    return size == 0;

}

public void add(Integer key, String value){
if(key == null || value == null){
    throw new IllegalArgumentException("Key or value is null");
}
int bucketIndex = getBucketIndex(key);
HashNode head = buckets[bucketIndex];

while(head!= null){
    if(head.key.equals(key)){
        head.value = value;
        return;
    }
    head = head.next;
}
size++;
head = buckets[bucketIndex];
HashNode node = new HashNode(key, value);
node.next = head;
buckets[bucketIndex] = node;
}
private static int getBucketIndex(Integer key){
    return key % numOfBuckets;
}

public static String get(Integer key){
    if(key == null){
        throw new IllegalArgumentException("key is null!");
    }
    int bucketIndex = getBucketIndex(key);
    HashNode head = buckets[bucketIndex];
    while(head!= null){
        if(head.key.equals(key)){
          return head.value;
        }
        head = head.next;
    }
return null;
}

public String remove(Integer key){
    if(key == null){
        throw new IllegalArgumentException("key is null!");
    }

    int bucketIndex = getBucketIndex(key);
    HashNode head = buckets[bucketIndex];
    HashNode previous = null;

    while(head!= null){
        if(head.key.equals(key)){
            break;
        }
        previous = head;
        head = head.next;
    }
    if(head == null){
        return null;
    }
    size--;
    if(previous!= null){
        
    }else{
        buckets[bucketIndex] = head.next;
    }
    return head.value;
}

public static void main(String[] args){

    HashTableSeperateChaining table = new HashTableSeperateChaining(10);

    System.out.println("This is Seperate Chaining Tests");
    System.out.println("'");
    table.add(3,"cat");
    table.add(11,"dogs");
    table.add(7,"koala");
    table.add(4,"ants");
    System.out.println(table.size());  // expected 4
    System.out.println(get(3));  // expected cat
    System.out.println(get(31));   //expected null
    System.out.println(get(4));    //expected ants
    table.remove(3);
    System.out.println(table.size()); // expected 3
    table.remove(44);
    System.out.println(table.size()); // expected 3 because 44 does not exsit
    table.remove(7);
    System.out.println(table.size()); // expected 2 
    System.out.println(table.isEmpty()); // expected false 
    table.remove(11);
    table.remove(4);
    System.out.println(table.size());  // expected 0
    System.out.println(table.isEmpty());   //expected true
}
}
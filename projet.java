module project {
public class Term {
   String searchTerm;
   boolean[] documentPresenceFlags;
   int[] rankingScores;

   public Term() {
       searchTerm = "";
       documentPresenceFlags = new boolean[50];
       rankingScores = new int[50];
       for (int index = 0; index < documentPresenceFlags.length; index++) {
           documentPresenceFlags[index] = false;
           rankingScores[index] = 0;
       }
   }

   public Term(String searchTerm, int[] rankingValues) {
       this.searchTerm = searchTerm;
       documentPresenceFlags = new boolean[50];
       rankingScores = new int[50];
       for (int index = 0; index < rankingValues.length; index++) {
           if (rankingValues[index] != 0) {
               documentPresenceFlags[index] = true;
               rankingScores[index] = rankingValues[index];
           }
       }
   }
   
   public void addDocumentPresence(int documentIndex) {
       documentPresenceFlags[documentIndex] = true;
       rankingScores[documentIndex]++;
   }

   public void setSearchTerm(String searchTerm) {
       this.searchTerm = searchTerm; 
   }
   
   public String getSearchTerm() {
       return searchTerm;
   }
   
   public boolean[] getDocumentPresenceFlags() {
       boolean[] flagsCopy = new boolean[rankingScores.length];
       
       for (int index = 0; index < flagsCopy.length; index++) {
           flagsCopy[index] = documentPresenceFlags[index];
       }
       
       return flagsCopy;
   }

   public int[] getRankingScores() {
       int[] rankingCopy = new int[rankingScores.length];
       for (int index = 0; index < rankingCopy.length; index++) {
           rankingCopy[index] = rankingScores[index];
       }
       return rankingCopy;
   }
   
   @Override
   public String toString() {
       String documentList = "";
       for (int index = 0, count = 0; index < documentPresenceFlags.length; index++) {
           if (documentPresenceFlags[index]) {
               if (count == 0) {
                   documentList += " " + String.valueOf(index);
                   count++;
               } else {
                   documentList += ", " + String.valueOf(index);
                   count++;
               }
           }
       }
       return searchTerm + "[" + documentList + ']';
   }
}
public class TermB {
   String term;

   // AVL Tree to map document numbers with their ranks
   // For each term in the document itself
   BST<Integer, Integer> documentRanks;

   public TermB() {
       term = "";
       documentRanks = new BST<Integer, Integer>();
   }

   public TermB(String term) {
       this.term = term;
       documentRanks = new BST<Integer, Integer>();
   }
   
   public void addDocument(int documentId) {
       if (documentRanks.empty()) {
           documentRanks.insert(documentId, 1);
       } else {
           if (documentRanks.find(documentId)) {
               int rank = documentRanks.retrieve();
               rank++;
               documentRanks.update(rank);
           } else {
               documentRanks.insert(documentId, 1);
           }
       }
   }

   public void setTerm(String term) {
       this.term = term; 
   }
   
   public String getTerm() {
       return term;
   }
   
   public LinkedList<Integer> getDocumentIds() {
       return documentRanks.getKeys();
   }

   public LinkedList<Integer> getDocumentRanks() {
       return this.documentRanks.getData();
   }
   
   @Override
   public String toString() {
       String documentList = "";
       LinkedList<Integer> documentIds = getDocumentIds();
       documentIds.findFirst();
       for (int index = 0; index < documentIds.size(); index++) {
           if (index == 0) {
               documentList += " " + String.valueOf(index);
           } else {
               documentList += ", " + String.valueOf(index);
           }
           documentIds.findNext();
       }
       return term + "[" + documentList + ']';
   }
}
/**
*
*/
public class TermT {
   String termText;
   
   // AVL Tree mapping document numbers to their ranks
   // For each term within the document itself
   AVLTree<Integer, Integer> documentRanks;
   
   public TermT() {
       termText = "";
       documentRanks = new AVLTree<Integer, Integer>();
   }

   public TermT(String termText) {
       this.termText = termText;
       documentRanks = new AVLTree<Integer, Integer>();
   }
   
   public void addDocumentId(int documentId) {
       if (documentRanks.empty()) {
           documentRanks.insert(documentId, 1);
       } else {
           if (documentRanks.find(documentId)) {
               int rank = documentRanks.retrieve();
               rank++;
               documentRanks.update(rank);
           } else {
               documentRanks.insert(documentId, 1);
           }
       }
   }

   public void setTermText(String termText) {
       this.termText = termText; 
   }
   
   public String getTermText() {
       return termText;
   }
   
   public LinkedList<Integer> getDocumentIds() {
       return documentRanks.getKeys();
   }

   public LinkedList<Integer> getDocumentRanks() {
       return this.documentRanks.getData();
   }
   
   @Override
   public String toString() {
       String documentList = "";
       LinkedList<Integer> ids = getDocumentIds();
       ids.findFirst();
       for (int index = 0; index < ids.size(); index++) {
           if (index == 0) {
               documentList += " " + String.valueOf(index);
           } else {
               documentList += ", " + String.valueOf(index);
           }
           ids.findNext();
       }
       return termText + "[" + documentList + ']';
   }
}
/**
*
*/
public class BST<K extends Comparable<K>, T>{

/*==================================================================
   class BSTNode
==================================================================*/
  class BSTNode<K extends Comparable<K>, T> {
       public K key;
       public T value;
       public BSTNode<K, T> leftChild, rightChild;

       /** Creates a new instance of BSTNode */
       public BSTNode(K k, T v) {
               key = k;
               value = v;
               leftChild = rightChild = null;
       }

       public BSTNode(K k, T v, BSTNode<K, T> left, BSTNode<K, T> right) {
               key = k;
               value = v;
               leftChild = left;
               rightChild = right;
       }
   }
   
       BSTNode<K, T> root; 
       BSTNode<K, T> currentNode;
       int elementCount;
               
       public BST()
       {
           root = currentNode = null;
           elementCount = 0;
       }
       
       public int size()
       {
           return elementCount;
       }

       public boolean isEmpty()
       {
           return root == null;
       }

       public void clearTree()
       {
           root = currentNode = null;
           elementCount = 0;
       }

       public T getCurrentValue()
       {
           T value = null;
           if (currentNode != null)
               value = currentNode.value;
           return value;
       }

       public void updateValue(T newValue)
       {
           if (currentNode != null)
               currentNode.value = newValue;
       }

       public boolean search(K searchKey)
       {
           BSTNode<K, T> tempNode = root;

           if (isEmpty())
               return false;

           while (tempNode != null) {
               if (tempNode.key.compareTo(searchKey) == 0) {
                   currentNode = tempNode;
                   return true;
               } else if (searchKey.compareTo(tempNode.key) < 0)
                   tempNode = tempNode.leftChild;
               else
                   tempNode = tempNode.rightChild;
           }
           return false;
       }

       public boolean insertNode(K newKey, T newValue)
       {

           if (isEmpty()) {
               currentNode = root = new BSTNode<K, T>(newKey, newValue);
               elementCount++;
               return true;
           }
           BSTNode<K, T> parentNode = null;
           BSTNode<K, T> childNode = root;
           
           while (childNode != null) {
               if (childNode.key.compareTo(newKey) == 0) {
                   return false;
               } else if (newKey.compareTo(childNode.key) < 0) {
                   parentNode = childNode;
                   childNode = childNode.leftChild;
               } else {
                   parentNode = childNode;
                   childNode = childNode.rightChild;
               }
           }
          
           if (newKey.compareTo(parentNode.key) < 0) {
               parentNode.leftChild = new BSTNode<K, T>(newKey, newValue);
               currentNode = parentNode.leftChild;
           } else {
               parentNode.rightChild = new BSTNode<K, T>(newKey, newValue);
               currentNode = parentNode.rightChild;
           }
           elementCount++;
           return true;
       }

       public boolean removeNode(K targetKey)
       {
           Boolean isRemoved = new Boolean(false);
           BSTNode<K, T> tempNode;
           
           tempNode = removeHelper(targetKey, root, isRemoved);
           root = tempNode;
           
           if (currentNode.key.compareTo(targetKey) == 0)
               currentNode = root;
           if (isRemoved)
               elementCount--;
           
           return isRemoved;
       }
   
       private BSTNode<K, T> removeHelper(K key, BSTNode<K, T> node, boolean flag) 
       {
           BSTNode<K, T> tempNode, replacementNode = null;
           if (node == null)
               return null;
           if (key.compareTo(node.key) < 0)
               node.leftChild = removeHelper(key, node.leftChild, flag); 
           else if (key.compareTo(node.key) > 0)
               node.rightChild = removeHelper(key, node.rightChild, flag); 
           else {
                   
               flag = true;
               if (node.leftChild != null && node.rightChild != null) { 
                   tempNode = findMinimum(node.rightChild);
                   node.key = tempNode.key;
                   node.value = tempNode.value;
                   node.rightChild = removeHelper(tempNode.key, node.rightChild, flag);
               } else {
                   if (node.rightChild == null) 
                       replacementNode = node.leftChild;
                   else if (node.leftChild == null) 
                       replacementNode = node.rightChild;
                   return replacementNode;
               }
           }
           return node;
       }
       private BSTNode<K, T> findMinimum(BSTNode<K, T> node)
       {
           if (node == null)
               return null;

           while (node.leftChild != null) {
               node = node.leftChild;
           }
           return node;
       }
       
       public void traverseTree()
       {
           if (root != null)
               traverseNodes(root);
       }
       
       private void traverseNodes(BSTNode<K, T> node)
       {
           if (node == null)
               return;
           traverseNodes(node.leftChild);
           System.out.println(node.value);
           traverseNodes(node.rightChild);
       }

       public LinkedList<T> getValues()
       {
           LinkedList<T> valuesList = new LinkedList<T>();
           if (root != null)
               collectValues(root, valuesList);
           return valuesList;
       }
       private void collectValues(BSTNode<K, T> node, LinkedList<T> valuesList)
       {
           if (node == null)
               return;
           collectValues(node.leftChild, valuesList);
           valuesList.insert(node.value);
           collectValues(node.rightChild, valuesList);
       }

       public LinkedList<K> getKeys()
       {
           LinkedList<K> keysList = new LinkedList<K>();
           if (root != null)
               collectKeys(root, keysList);
           return keysList;
       }
       private void collectKeys(BSTNode<K, T> node, LinkedList<K> keysList)
       {
           if (node == null)
               return;
           collectKeys(node.leftChild, keysList);
           keysList.insert(node.key);
           collectKeys(node.rightChild, keysList);
       }
}
/**
*
* @param <T>
*/
public class LinkedList<T> {
   
   // Class ListNode
   class ListNode<T> {
       public T value;
       public ListNode<T> nextNode;

       public ListNode() {
           value = null;
           nextNode = null;
       }

       public ListNode(T value) {
           this.value = value;
           nextNode = null;
       }

       // Setters/Getters...
       public T getValue() {
           return value;
       }

       public void setValue(T value) {
           this.value = value;
       }

       public ListNode<T> getNextNode() {
           return nextNode;
       }

       public void setNextNode(ListNode<T> nextNode) {
           this.nextNode = nextNode;
       }
   }

   // LinkedList properties
   private ListNode<T> headNode;
   private ListNode<T> currentNode;
   int listSize;
   
   public LinkedList() {
       headNode = currentNode = null;
       listSize = 0;
   }

   public boolean isEmpty() {
       return headNode == null;
   }

   public int getSize() {
       return listSize;
   }

   public boolean isLast() {
       return currentNode.nextNode == null;
   }

   public boolean isFull() {
       return false; // LinkedList is never full unless memory is exhausted.
   }

   public void moveToFirst() {
       currentNode = headNode;
   }

   public void moveToNext() {
       currentNode = currentNode.nextNode;
   }

   public T getCurrentValue() {
       return currentNode.value;
   }

   public void updateCurrentValue(T newValue) {
       currentNode.value = newValue;
   }

   public void insert(T newValue) {
       ListNode<T> tempNode;
       if (isEmpty()) {
           currentNode = headNode = new ListNode<T>(newValue);
       } else {
           tempNode = currentNode.nextNode;
           currentNode.nextNode = new ListNode<T>(newValue);
           currentNode = currentNode.nextNode;
           currentNode.nextNode = tempNode;
       }
       listSize++;
   }

   public void remove() {
       if (currentNode == headNode) {
           headNode = headNode.nextNode;
       } else {
           ListNode<T> tempNode = headNode;
           while (tempNode.nextNode != currentNode)
               tempNode = tempNode.nextNode;

           tempNode.nextNode = currentNode.nextNode;
       }

       if (currentNode.nextNode == null)
           currentNode = headNode;
       else
           currentNode = currentNode.nextNode;
       listSize--;
   }

   public void printList() {
       if (headNode == null)
           System.out.println("No data in the list");
       else {
           ListNode<T> tempNode = headNode;
           while (tempNode != null) {
               System.out.print(tempNode.value + "  ");
               tempNode = tempNode.nextNode;
           }
       }
       System.out.println("");
   }
}


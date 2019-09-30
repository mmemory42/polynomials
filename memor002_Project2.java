// CSCI 1913 Project 2
// Michael Memory
// Prof. James Moen
// 30 November 2018

class Poly{

   private class Term{                                   // Nested Class - Term (Node for this specific linked list)
      private int coef;
      private int expo;
      private Term next;
      private Term(int base,int exp,Term next){
         this.coef = base;
         this.expo = exp;
         this.next = next;
      }
   }
   
   private Term first;                                   
   private Term last;
   
   public Poly(){                                        // Constructor of Poly
      Term head = new Term(1, Integer.MAX_VALUE, null);
      first = head;
      last = head;
   }
   
   public boolean isZero(){                              // Function to test if the Poly (for this.isZero) is the zero polynomial with no terms 
      return (this.first.next == null);
   }
   
   public Poly minus(){                                  // Function to provide a negative instance of Poly.minus
      Poly result = new Poly();
      Term temp = this.first.next;
      while (temp != null){
         int value = temp.coef;
         value = -(value);
         Term stub = new Term(value, temp.expo, last.next);
         result.last.next = stub;
         result.last = stub;
         temp = temp.next;
      }
      return result;
   }
   
   public Poly plus(Poly that){                          // Function to provide the computational Poly of this and that 
      Poly result = new Poly();
      Term left = this.first.next;
      Term right = that.first.next;
      while (left != null && right != null){
         if (left.expo > right.expo){
            Term temp = new Term(left.coef, left.expo, result.last.next);
            result.last.next = temp;
            result.last = temp;
            left = left.next;
         }
         else if (right.expo > left.expo){
            Term temp2 = new Term(right.coef, right.expo, result.last.next);
            result.last.next = temp2;
            result.last = temp2;
            right = right.next;
         }
         else{
            int sum = left.coef + right.coef;
            if (sum != 0){
               Term stub = new Term(sum, left.expo, result.last.next);
               result.last.next = stub;
               result.last = stub;
               left = left.next;
               right = right.next;
            }
            else{
               left = left.next;
               right = right.next;
            }
         }
      }
      if (left != null){
         result.last.next = left;
      }
      else if(right != null){
         result.last.next = right;
      }
      return result; 
   }
   
   public Poly plus(int coef, int expo){                          // Function to add a single term of coef & expo to Poly
      if (coef == 0 || expo < 0 || expo >= this.last.expo)
         throw new IllegalArgumentException("Invalid Input.");
      else{
         Term temp = new Term(coef, expo, last.next);
         this.last.next = temp;
         this.last = temp;
         return this;
      }
   }
   
   public String toString(){                                      // Poly toString function for println/print
      StringBuilder builder = new StringBuilder();
      Term temp = this.first.next;
      if (temp == null)
         return "0";
      else{
         builder.append(temp.coef);
         builder.append("x");
         builder.append(temp.expo);
         temp = temp.next;
         while(temp != null){
            if(temp.coef > 0)
               builder.append(" + ");
            else
               builder.append(" - ");
            builder.append(Math.abs(temp.coef));
            builder.append("x");
            builder.append(temp.expo);
            temp = temp.next;
         }
         return builder.toString();
      }
   }
   

public static void main(String[] args)
  {
    Poly p = new Poly().plus(3,5).plus(2,4).plus(2,3).plus(-1,2).plus(5,0);
    Poly q = new Poly().plus(7,4).plus(1,2).plus(-4,1).plus(-3,0);
    Poly z = new Poly();

    System.out.println(p);                 // 3x5 + 2x4 + 2x3 - 1x2 + 5x0
    System.out.println(q);                 // 7x4 + 1x2 - 4x1 - 3x0
    System.out.println(z);                 // 0
    System.out.println(p.minus());         // -3x5 - 2x4 - 2x3 + 1x2 + 5x0
    System.out.println(q.minus());         // -7x4 - 1x2 + 4x1 + 3x0 
    System.out.println(z.minus());         // 0

    System.out.println(p.plus(q));         // 3x5 + 9x4 + 2x3 - 4x1 + 2x0
    System.out.println(p.plus(z));         // 3x5 + 2x4 + 2x3 - 1x2 + 5x0
    System.out.println(p.plus(q.minus())); // 3x5 - 5x4 + 2x3 - 2x2 + 4x1 + 8x0
    
    Poly t = new Poly().plus(-3,4).plus(2,3).plus(11,2).plus(140,1).plus(-130,0);
    System.out.println(t);                 // -3x4 + 2x3 + 11x2 + 140x1 - 130x0
    
    System.out.println(t.minus());         // 3x4 - 2x3 - 11x2 - 140x1 + 130x0
    System.out.println(p.plus(t));         // 3x5 - 1x4 + 4x3 + 10x2 + 140x1 - 125x0
    System.out.println(z.plus(p));         // 3x5 + 2x4 + 2x3 - 1x2 + 5x0
  }
}
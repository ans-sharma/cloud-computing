//HOW TO REPLACE THE VALUE OF A CLASS TYPE LIST USING SET FUNCTION..............."NHI SMJ A RHA"


import java.util.ArrayList;
class update{
    int rollno,cl,enroll;
    update(int rollno,int cl, int enroll){
      this.rollno=rollno;
      this.cl=cl;
      this.enroll=enroll;

    }
}
class Test0010{
    public static void main(String[] args) {
        ArrayList<update>list=new ArrayList<update>();
        Value(list,5);
        ReplaceValue(list,5);
        }
        

    
   public static void Value(ArrayList<update>list,int n) {
    for(int i=0;i<5;i++){
        int rollno=i;
        int cl=i+1;
        int enroll=i+2;
        list.add(new update(rollno,cl,enroll));
   }
}
 public static void ReplaceValue(ArrayList<update>list,int n) {
    for(int i=0;i<5;i++){
      list.set(i, new update(11,11,11));//list ki values replace kerne me problem ho rha hai
 }
}
}

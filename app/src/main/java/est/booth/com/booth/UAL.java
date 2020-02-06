package est.booth.com.booth;
import java.util.*;
import java.math.BigInteger;

// TIP: start always with bit of signe.
// Function Added/Edited:
/*
ToBinary(String) 			: convert a Decimal-String to binary
ToDecimal(String)			: convert a Binary-String to decimal
SwitchStatus(String)		: returns 2s complement of the input.
DivisonFormattedOutput(char):format the output of non-signed division into signed-devision results.
divison(String, String)		: is modified to take in charge the Formatted Output.
*/
class UAL {

    public static char L = '0', vp, flag, fless='0';
    public static String M, A, Q;
    public static List<String> listOfA  = new ArrayList<String>();
    public static List<String> listOfQ  = new ArrayList<String>();
    public static List<String> listOfL  = new ArrayList<String>();
    public static List<String> listOfTodo  = new ArrayList<String>();

    // Coversions
    public static String ToBinary(String input){ //Decimal Text input
        int n = Math.abs(Integer.parseInt(input));
        String output = "";
        for(int i = 0; n != 0 ; ++i, n/=2) output = Integer.toString(n%2) + output;
        output = "0" + output;
        char [] A = output.toCharArray();
        if(input.charAt(0) == '-'){
            int i = output.length()-1;
            while(A[i] != '1') i--;
            for(int j = 0; j< i; j++) A[j] = (A[j] == '1' ? '0' : '1' );
        }
        return new String(A);
    }
    public static BigInteger ToDecimal(String A){ //BinaryInput

        char [] V = absv(A).toCharArray();
        BigInteger S = new BigInteger("0");;
        for (int i=0; i<V.length; i++)S = (S.multiply(new BigInteger("2"))).add(new BigInteger((V[i] == '0' ? "0" :"1")));
        return A.charAt(0) == '1' ? S.multiply(new BigInteger("-1")) : S;
    }
    //Logic and, xor, OR
    public static String AND(String A, String B){
        String str = "";
        for (int i = A.length()-1; i>= 0; i--) str += ( A.charAt(i) == B.charAt(i) ? A.charAt(i) : '0');
        return new StringBuilder(str).reverse().toString();
    }
    public static String XOR(String A, String B){
        String str = "";
        for (int i = A.length()-1; i>= 0; i--) str += ( A.charAt(i) != B.charAt(i) ? '1' : '0');
        return new StringBuilder(str).reverse().toString();
    }
    public static String OR(String A, String B){
        String str = "";
        for (int i = A.length()-1; i>= 0; i--) str += ( A.charAt(i) + B.charAt(i) > 96 ? '1' : '0');
        return new StringBuilder(str).reverse().toString();
    }

    //Arithmic ADD, SUB, MUL, DIV
    public static String ADD(String A, String B, int ov){
        String str = "";
        char r = '0';
        for(int i = A.length()-1; i>=0 ; i--){
            if(A.charAt(i) != B.charAt(i)){
                str += ( r == '0' ? '1' : '0');
            }else{
                str += r;
                r = A.charAt(i);
            }
        }
        return new StringBuilder(str=((ov == 1) && (A.charAt(0)+B.charAt(0)==96 ||A.charAt(0)+B.charAt(0)==98))?str+r:str).reverse().toString();
    }
    public static String SUB(String A, String B, int ov){
        String str = "";
        char r = '0';
        for(int i = A.length()-1; i>=0 ; i--){
            if(A.charAt(i) == B.charAt(i)){
                str += r;
            }else{
                str += ( r == '0' ? '1' : '0');
                r = B.charAt(i);
            }
        }
        return new StringBuilder(str +=((ov == 1) && (A.charAt(0) != B.charAt(0)) ? (r == '0' ? '1' : '0' ) : (ov == 1 ? r : ""))).reverse().toString();

    }
    public static String division(String Di, String De){
        //if Div < Dev OR Dev == 0 no extra work, just return
        // NOTE: if the above condition is true, there's NO STEPS Stored.
        BigInteger k = new BigInteger("0");
        BigInteger G = new BigInteger(De);
        if(SUB(absv(Di), absv(De), 0).charAt(0) == '1'){
            listOfA.add(Di); listOfQ.add("0000"); listOfTodo.add("Nothing to do!"); A=Di; Q= "0000"; flag='0'; fless = '1'; return Q;}
        if(k.equals(G)){
            listOfA.add("0000"); listOfQ.add("0000"); listOfTodo.add("X|0 OH REALLY?"); A= "0000"; Q = "0000"; fless = '1'; flag='0'; return Q;}
        vp = '0';
        listOfA.clear();
        listOfQ.clear();
        listOfTodo.clear();
        listOfL.clear();
        M = absv(De); Q = absv(Di);
        int count = 0; int size = M.length();
        A = ""; for (int i = 0; i<size; i++) A += Q.charAt(0);
        if(Di.charAt(0) == '0' && De.charAt(0) == '0') flag = '0';
        if(Di.charAt(0) == '0' && De.charAt(0) == '1') flag = '1';
        if(Di.charAt(0) == '1' && De.charAt(0) == '0') flag = '2';
        if(Di.charAt(0) == '1' && De.charAt(0) == '1') flag = '3';
        while(size-- > 0 ){
            listOfA.add(A);
            listOfQ.add(Q);
            decalageDiv();
            String temp = SUB(A, M, 0);
            if(temp.charAt(0) == '1'){
                char [] Qc = Q.toCharArray();
                Qc[Q.length()-1] = '0';
                Q = new String(Qc);
                listOfTodo.add("Shift Left, then A-M < 0, Q0 = 0");

            }else{
                char [] Qc = Q.toCharArray();
                Qc[Q.length()-1] = '1';
                Q = new String(Qc);
                A = temp;
                listOfTodo.add("Shift Left, then A = A+M > 0, Q0 = 1");
            }
        }
        listOfA.add(A);
        listOfQ.add(Q);
        listOfTodo.add("END + Switch");
        return Q;
    }
    public static String booth(String B, String C){
        vp = '1';
        listOfA.clear();
        listOfQ.clear();
        listOfTodo.clear();
        listOfL.clear();
        M = B; Q = C;
        int count = 0; int size = M.length();
        A = ""; for (int i = 0; i<size; i++) A += '0';
        listOfA.add(A);
        listOfQ.add(Q);
        listOfL.add(Character.toString(L));
        while(count++ < A.length()){
            String op = "" + Q.charAt(size-1) + L;
            if(op.equals("01")) {A = ADD(A, M, 0); listOfTodo.add("Addition + Shift");};
            if(op.equals("10")) {A = SUB(A, M, 0); listOfTodo.add("Soustraction + Shift");};
            if(op.equals("00") || op.equals("11")) listOfTodo.add("Shift Only");

            decalage();
            listOfA.add(A);
            listOfQ.add(Q);
            listOfL.add(Character.toString(L));
        }
        listOfTodo.add("END");
        return A+Q;
    }

    //complemantaire fun
    public static String RSZ(String A, String B){
        int size = A.length();
        int mysize = size > B.length() ? size : B.length();
        if(mysize == size) return A;
        char C  = A.charAt(0);
        for(int i = 0; i < mysize - size; i++) A = Character.toString(C) + A;
        return A;
    }
    public static void decalage(){
        L = Q.charAt(Q.length()-1);
        char[] Qc = Q.toCharArray();
        Qc[Q.length()-1] = '\0';
        for (int i = Qc.length-1; i>0; i--) Qc[i] = Qc[i-1];
        Qc[0] = A.charAt(A.length()-1);
        Q = new String(Qc);

        char[] Ac = A.toCharArray();
        char Af = Ac[0];
        for (int i = Ac.length-1; i>0; i--) Ac[i] = Ac[i-1];
        Ac[0] = Af;
        A = new String(Ac);

    }
    public static String absv(String R){
        int i = R.length()-1;
        char [] nR = R.toCharArray();
        if(nR[0] == '1'){

            while(nR[i] != '1') i--;

            for(int j = 0; j< i; j++) nR[j] = (nR[j] == '1'? '0' : '1' );
        }else{return R;}
        return R = new String(nR);
    }
    public static void Steps(int i){
        System.out.println("COUNT : " + i + "\t|| "+ listOfA.get(i) + " || " + listOfQ.get(i) + " || " + (vp == '1' ? listOfL.get(i) + " || ": "") + listOfTodo.get(i));
    }

    public static void decalageDiv(){
        char[] Ac = A.toCharArray();
        for (int i = 0; i < A.length()-1; i++) Ac[i] = Ac[i+1];
        Ac[A.length()-1] = Q.charAt(0);
        A = new String(Ac);

        char[] Qc = Q.toCharArray();
        for (int i = 0; i< Q.length()-1; i++) Qc[i] = Qc[i+1];
        Qc[Q.length()-1] = '0';
        Q = new String(Qc);


    }

    public static void DivisonFormattedOutput(char C){
        switch(C){
            case '1' : Q = SwitchStatus(Q); break;
            case '2' : Q = SwitchStatus(Q); A = SwitchStatus(A); break;
            case '3' : A = SwitchStatus(A); break;
        }
    }
    public static String SwitchStatus(String R){
        if(ToDecimal(R) == ToDecimal("00")) return new String("0000");
        int uu = R.length()-1;
        char [] nR = R.toCharArray();
        while(nR[uu--] == '0' && uu>=0);
        for(int j = 0; j<=uu; j++) nR[j] = (nR[j] == '1'? '0' : '1' );
        return new String(nR);
    }
    public static void main(String[] args) {
        String F;
        String E;
        Scanner scan = new Scanner(System.in);
        System.out.print("INPUT A:\t"); F = scan.next();
        System.out.print("\nINPUT B:\t"); E = scan.next();
        System.out.println();

        F = RSZ(F, E); // M
        E = RSZ(E, F); // Q
        String Result;
        Result = booth(F, E);
        //Result = division(F, E);

        for (int i = 0; i<listOfA.size(); i++) Steps(i);
        System.out.println("----------------------------");
        System.out.println("\nINPUT A:\t\t" + ToDecimal(F));
        System.out.println("INPUT B:\t\t" +ToDecimal(E));
        System.out.println("OUTPUT BINARY:\t\t" + (vp=='1' ? Result : " R = " + A + " || Q = " + Q));
        System.out.println("OUTPUT DECIMAL:\t\t" + (vp=='1' ? ToDecimal(Result) : " R =\t  " + ToDecimal(A) + " || Q = " + ToDecimal(Q)));

        System.out.println("\n+ Logic:\n");
        System.out.println("and:\t" + AND(F, E));
        System.out.println("xor:\t" + XOR(F, E));
        System.out.println("OR:\t" + OR(F, E));

        System.out.println("\n+ Arithmic:\n");
        System.out.println("ADD:\t" + ADD(F, E, 1));
        System.out.println("SUB:\t" + SUB(F, E, 1));

    }

}
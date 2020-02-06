package est.booth.com.booth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.lang.String;

public class CalculeActivity extends AppCompatActivity {
    TextView tv_A, tv_B, tv_resultat, types_op, tv_resultat_dec;
    String resultat, tv_As, tv_Bs, F , E;
    BigInteger resultat_dec;
    int type_op, i;
    ListView listview;
    Button btn_skip, btn_next;
    final ArrayList<ListItem> lstFinal = new ArrayList<>();
    boolean flagg
            =false, setresultat=false;
    //TableLayout row;
    TextView count, stepM, stpA, stpQ, stpL, stpTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=0;
        Intent intent = getIntent();
        tv_As = intent.getStringExtra("EXTRA_A");
        tv_Bs = intent.getStringExtra("EXTRA_B");
        type_op = intent.getIntExtra("EXTRA_type_op",1);
        if(tv_As.equals(""))tv_As = "00000000";
        if(tv_Bs.equals(""))tv_Bs = "00000000";

        if(type_op == 2 || type_op == 3){
            UAL.fless = '0';
            UAL.listOfTodo.clear();
            UAL.listOfA.clear();
            UAL.listOfL.clear();
            UAL.listOfQ.clear();
            UAL.M = "";
            UAL.A = "";
            UAL.flag = '0';
            UAL.L = '0';
            UAL.Q = "";

            setContentView(R.layout.activity_calcule_complex);

            flagg = true;
            btn_next = findViewById(R.id.btn_next);
            btn_skip = findViewById(R.id.btn_skip);

            //row = findViewById(R.id.rows2);
            count = findViewById(R.id.tv_count2);
            stepM = findViewById(R.id.tv_M2);
            stpA = findViewById(R.id.tv_listOfA2);
            stpQ = findViewById(R.id.tv_listOfQ2);
            stpL = findViewById(R.id.tv_listeOfL2);
            stpTodo = findViewById(R.id.tv_ListOfToDo2);
            listview = findViewById(R.id.list_view_history);
            tv_resultat_dec = findViewById(R.id.tv_resiltat_dec);

            //row.setVisibility(View.VISIBLE);

        }else {
            setContentView(R.layout.activity_calcule_simple);

            tv_resultat = findViewById(R.id.tv_resultat);
            types_op = findViewById(R.id.tv_type_op);
            types_op.setText(Title(type_op));
            setresultat = true;
        }

        tv_A = findViewById(R.id.tv_A);
        tv_B = findViewById(R.id.tv_B);

        F = UAL.RSZ(tv_As,tv_Bs);
        E = UAL.RSZ(tv_Bs,tv_As);

        select_op(type_op, F, E);
        setTitle(Title(type_op));


        //set steps 1 in list view;
        /*StepNow();
        Set_ListView(lstFinal);
        i++;*/

//return the steps in stepA...
        if(setresultat)tv_resultat.setText("= "+resultat);
        /////////////////////////////////////
        tv_A.setText(F);
        tv_B.setText(E);
    }

    void Set_ListView(){

        if (type_op == 3)lstFinal.add(new ListItem(tv_As,"step "+(i+1)+":","A: " + UAL.listOfA.get(i), "Q: " +UAL.listOfQ.get(i),"L: " + UAL.listOfL.get(i),UAL.listOfTodo.get(i)));
        else lstFinal.add(new ListItem(tv_As,"step "+(i+1)+":", "A: "+ UAL.listOfA.get(i), "Q: "+UAL.listOfQ.get(i),UAL.listOfTodo.get(i)));

        listview.setAdapter(new Adapter(lstFinal));
}
    public void clk_skip(View view) {

        while (i <= F.length()) {
            Set_ListView();
            i++;
            if (UAL.fless =='1' )  break;
        }

        btn_next.setEnabled(false);
        btn_skip.setEnabled(false);
        //row.setVisibility(View.INVISIBLE);
    }
    public void clk_next(View view) {
        Set_ListView();
        //if (i < UAL.listOfA.size()-1)StepNow();
        i++;
        if(i > F.length() || UAL.fless =='1'){
            btn_next.setEnabled(false);
            btn_skip.setEnabled(false);
            //row.setVisibility(View.INVISIBLE);

        }
    }
    /*void StepNow(){
        stpL.setText(UAL.listOfQ.get(i+1));
        count.setText("step "+(i+2)+":");
        stepM.setText(UAL.listOfA.get(i+1));
        stpA.setText(tv_As);
        if(type_op == 3)stpQ.setText(UAL.listOfL.get(i+1));
        stpTodo.setText(UAL.listOfTodo.get(i+1));
    }*/

    class Adapter extends BaseAdapter {
        ArrayList<ListItem> listItem = new ArrayList<>();

        Adapter(ArrayList<ListItem> listItems){
            this.listItem = listItems;
        }


        @Override
        public int getCount() {
            return listItem.size();
        }

        @Override
        public Object getItem(int position) {
            return listItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.rows,null);

            TextView stepM = view.findViewById(R.id.tv_M);
            TextView count = view.findViewById(R.id.tv_count);
            TextView stpA = view.findViewById(R.id.tv_listOfA);
            TextView stpL = view.findViewById(R.id.tv_listOfQ);
            TextView stpQ = view.findViewById(R.id.tv_listeOfL);
            TextView stpTodo = view.findViewById(R.id.tv_ListOfToDo);

            stepM.setText(listItem.get(position).stepM);
            count.setText(listItem.get(position).count);
            stpA.setText(listItem.get(position).stepA);
            stpTodo.setText(listItem.get(position).stepTodo);

            if(type_op == 3){
                stpL.setText(listItem.get(position).stepQ);
                stpQ.setText(listItem.get(position).stepL);
            } else {
                stpQ.setText(listItem.get(position).stepQ);
                //stpQ.setText(listItem.get(position).stepQ);
            }

            return view;
        }
    }
    void select_op(int type_op, String tv_As, String tv_Bs){
        switch(type_op){
            case 1:
                resultat = UAL.SUB(tv_As, tv_Bs,1);
                break;
            case 2:
                UAL.division(tv_As, tv_Bs);
                resultat = UAL.listOfQ.get(UAL.listOfQ.size()-1);
                //UAL.Q= UAL.listOfQ.get(UAL.listOfQ.size()-1);
                UAL.Q = resultat;
                UAL.DivisonFormattedOutput(UAL.flag);
                resultat= UAL.Q;
                resultat_dec = UAL.ToDecimal(resultat);
                tv_resultat_dec.setText("Q = " + resultat + " = " + String.valueOf(resultat_dec) +"\n" + "R = "+UAL.A + " = " + UAL.ToDecimal(UAL.A));

                break;
            case 3:

                resultat = UAL.booth(tv_As, tv_Bs);
                resultat_dec = UAL.ToDecimal(resultat);
                tv_resultat_dec.setText("AQ = " + resultat + " = " + String.valueOf(resultat_dec));
                break;
            case 4:
                resultat = UAL.ADD(tv_As, tv_Bs,1);
                break;
            case 5:
                resultat = UAL.AND(tv_As, tv_Bs);
                break;
            case 6:
                resultat = UAL.XOR(tv_As, tv_Bs);
                break;
            case 7:
                resultat = UAL.OR(tv_As, tv_Bs);
                break;
        }
    }
    String Title(int type_op){
    String title = null;
        switch(type_op){
            case 1:
                title =  "SUB";
                break;
            case 2:
                title =  "Division";
                break;
            case 3:
                title = "Booth";
                break;
            case 4:
                title ="ADD";
                break;
            case 5:
                title = "and";
                break;
            case 6:
                title = "xor";
                break;
            case 7:
                title = "OR";
                break;
        }
        return title;
    }

    @Override
    public void onBackPressed() {
        if(type_op == 2 || type_op == 3)listview.setAdapter(null);
        finish();
    }
}
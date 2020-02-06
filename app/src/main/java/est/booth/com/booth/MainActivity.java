package est.booth.com.booth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MainActivity extends AppCompatActivity{
CircleMenu circleMenu;
EditText A_bin, B_bin, A_dec, B_dec;
boolean switchh =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleMenu = findViewById(R.id.circle_menu);
        A_bin = findViewById(R.id.et_bin_A);
        B_bin = findViewById(R.id.et_bin_B);
        A_dec = findViewById(R.id.et_dec_A);
        B_dec = findViewById(R.id.et_dec_B);

        circleMenu.setMainMenu(Color.parseColor("#ec6b56"), R.drawable.add, R.drawable.add)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorLogic)), R.drawable.minus_btn)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorLogic)), R.drawable.percentage_icon)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorLogic)), R.drawable.icon_multpil)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorLogic)), R.drawable.plus_icon)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorArethmitic)), R.drawable.and)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorArethmitic)), R.drawable.xor)
                .addSubMenu(Color.parseColor(getResources().getString(R.string.colorArethmitic)), R.drawable.or);
        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(MainActivity.this, "SUB", Toast.LENGTH_SHORT).show();
                        setText(index+1);

                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "DIV", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Booth", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "ADD ", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "and", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, "xor", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                    case 6:
                        Toast.makeText(MainActivity.this, "OR", Toast.LENGTH_SHORT).show();
                        setText(index+1);
                        break;
                }
            }
        }
        );
        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
                                                     @Override
                                                     public void onMenuOpened() {
                                                         BinToDec();
                                                         //Toast.makeText(MainActivity.this, "Menu Opend", Toast.LENGTH_SHORT).show();
                                                     }

                                                     @Override
                                                     public void onMenuClosed() {
                                                         BinToDec();
                                                         //Toast.makeText(MainActivity.this, "Menu Closed", Toast.LENGTH_SHORT).show();
                                                     }
        }
        );

    }

    void setText(int i){
        final  Intent intent = new Intent(this,CalculeActivity.class);
        String txtA = A_bin.getText().toString();
        String txtB = B_bin.getText().toString();
        int x = i;

        intent.putExtra("EXTRA_A",txtA);
        intent.putExtra("EXTRA_B",txtB);
        intent.putExtra("EXTRA_type_op",x);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
            }
        }, 500);
    }
    public void switch_clk(View view){
        if(switchh){
            A_bin.setVisibility(View.INVISIBLE);
            B_bin.setVisibility(View.INVISIBLE);
            A_dec.setVisibility(View.VISIBLE);
            B_dec.setVisibility(View.VISIBLE);
            switchh = false;
        }else{
            A_bin.setVisibility(View.VISIBLE);
            B_bin.setVisibility(View.VISIBLE);
            A_dec.setVisibility(View.INVISIBLE);
            B_dec.setVisibility(View.INVISIBLE);
            switchh = true;
        }
    }
    void BinToDec(){


        if(!switchh){
            String AA = String.valueOf(A_dec.getText());
            String BB = String.valueOf(B_dec.getText());
            if(AA.equals("") && BB.equals("") ){BB = AA = "0";}
            A_bin.setText(""+ UAL.ToBinary(AA),TextView.BufferType.EDITABLE);
            B_bin.setText(""+ UAL.ToBinary(BB),TextView.BufferType.EDITABLE);

        }
        /*if(AA.equals("") && BB.equals("") && !AD.equals("") && !BD.equals("")){

        }else if(AA.equals("") && !BB.equals("")) {
            AA = String.valueOf(A_dec.getText());
            BB = String.valueOf(B_bin.getText());
            A_bin.setText("" + UAL.ToBinary(AA), TextView.BufferType.EDITABLE);
            B_dec.setText("" + UAL.ToDecimal(BB), TextView.BufferType.EDITABLE);
        }else if(!AA.equals("") && BB.equals("")){
            AA = String.valueOf(A_bin.getText());
            BB = String.valueOf(B_dec.getText());
            A_dec.setText("" + UAL.ToBinary(AA), TextView.BufferType.EDITABLE);
            B_bin.setText("" + UAL.ToDecimal(BB), TextView.BufferType.EDITABLE);
        }else if(!A_bin.equals("") && !B_bin.equals("")){
            A_dec.setText(""+ UAL.ToDecimal(AA),TextView.BufferType.EDITABLE);
            B_dec.setText(""+ UAL.ToDecimal(BB),TextView.BufferType.EDITABLE);
        }*/
    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }

}
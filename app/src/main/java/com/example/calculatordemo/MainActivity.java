package com.example.calculatordemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{


    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_plus;  //加
    Button btn_minus;  //减
    Button btn_multiple; //乘
    Button btn_divide;  //除
    Button btn_equle; //等于
    Button btn_point;  //小数点
    Button btn_clear;  //清除
    Button btn_del;  //删除

    EditText editText; //显示内容

    boolean clear_flag; //清空标识
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化按钮
        btn_0=findViewById(R.id.btn_0);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4=findViewById(R.id.btn_4);
        btn_5=findViewById(R.id.btn_5);
        btn_6=findViewById(R.id.btn_6);
        btn_7=findViewById(R.id.btn_7);
        btn_8=findViewById(R.id.btn_8);
        btn_9=findViewById(R.id.btn_9);
        btn_plus=findViewById(R.id.btn_jia);
        btn_minus=findViewById(R.id.btn_jian);
        btn_multiple=findViewById(R.id.btn_cheng);
        btn_divide=findViewById(R.id.btn_chu);
        btn_equle=findViewById(R.id.btn_dengyu);
        btn_point=findViewById(R.id.btn_dian);
        btn_clear=findViewById(R.id.btn_clear);
        btn_del=findViewById(R.id.btn_del);

        editText=findViewById(R.id.editTest);


        //设置按钮点击方式
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiple.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equle.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        String str=editText.getText().toString();
        switch (view.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dian:
                if (clear_flag){
                    clear_flag=false;
                    str="";
                    editText.setText("");
                }
                editText.setText(str+((Button)view).getText());
                break;
            case R.id.btn_jia:
            case R.id.btn_jian:
            case R.id.btn_cheng:
            case R.id.btn_chu:
                if (clear_flag){
                    clear_flag=false;
                    str="";
                    editText.setText("");
                }
                editText.setText(str+" "+((Button)view).getText()+" ");
                break;

            case R.id.btn_del:
                if (clear_flag){
                    clear_flag=false;
                    str="";
                    editText.setText("");
                } else if(str!=null&&!str.equals("")){
                  editText.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_clear:
                clear_flag=false;
                str="";
                editText.setText("");
                break;
            case R.id.btn_dengyu:
                getResult();
                break;
            default:
                break;
        }
    }
    /***
     * 运算实现
     */
    public void getResult() {
        String exp=editText.getText().toString(); //取内容
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if (clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        double result=0;
        String s1=exp.substring(0,exp.indexOf(" ")); //运算符前面的字符串
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2); //运算符
        String s2=exp.substring(exp.indexOf(" ")+3);    //运算符后面的字符串
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result=d1+d2;
            }else if (op.equals("-")){
                result=d1-d2;
            }else if (op.equals("×")){
                result=d1*d2;
            }else if (op.equals("÷")){
                if(d2==0){
                    result=0;
                }
                else {
                    result=d1/d2;
                }
            }
            if (!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")){
                int r=(int) result;
                editText.setText(r+"");
            }else {
                editText.setText(result+"");
            }
        }else  if(!s1.equals("")&&s2.equals("")){
            editText.setText(exp);
        }else if (s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result=0+d2;
            }else if (op.equals("-")){
                result=0-d2;
            }else if (op.equals("×")){
                result=0;
            }else if (op.equals("÷")){
               result=0;
            }
            if (!s2.contains(".")){
                int r=(int) result;
                editText.setText(r+"");
            }else {
                editText.setText(result+"");
            }
        }
        else {
            editText.setText("");
        }

    }
}

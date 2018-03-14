package sdu.cs58.wisit.animalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    //ประกาศตัวเเปร
    Button btn1,btn2,btn3,btn4 ;
    ImageView questionImageView;
    ImageButton volumnImageButton;
    MediaPlayer mediaPlayer;
    int questionCount = 2;
    ArrayList<Integer> qID = new ArrayList<Integer>();
    String answer;
    int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //ผูก eliment กับตัวเเปรบนจาวา
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        questionImageView = findViewById(R.id.imvQuestion);
        volumnImageButton = findViewById(R.id.imvVolumn);

        //วนจำนวนข้อคำถาม
        for (int i=1 ; i <= questionCount ; i++) {
            qID.add(i);
        }//end for
        Collections.shuffle(qID); //กำหนดให้ random คำถาม
        setQuestion(qID.remove(0));


    }//end on create

    private void setQuestion(int qID) {//กำหนดคำถามเเละเฉลยในเเต่ละข้อ
        if (qID == 1) {
            answer = "นก";
            questionImageView.setImageResource(R.drawable.bird);
            mediaPlayer = MediaPlayer.create(this,R.raw.bird);

            ArrayList<String> choice = new ArrayList<String>();// กำหนการเเรนด้อม ช้อย
            choice.add("นก");
            choice.add("ยุง");
            choice.add("สิงโต");
            choice.add("ช้าง");
            Collections.shuffle(choice);
            btn1.setText(choice.remove(0));
            btn2.setText(choice.remove(0));
            btn3.setText(choice.remove(0));
            btn4.setText(choice.remove(0));

        }

    }//end setquestin mhthod
    public void choiceAns(View view) {
    Button button = (Button) view;
        String buttonString = button.getText().toString();
        if (buttonString.equals(answer)) {//เลือกคำตอบถูก คะเเนน +1
            score++;

        }
        if (qID.isEmpty()) {//ถ้าทำครบทุกข้อ qid จะเป็นค่าว่าง
            dailogboxScore();//เมดตอดเเสดงคะเเนนรวม
        } else {//ถ้ายังทำไม่ครบทุกข้อ
            setQuestion(qID.remove(0));

        }



    }//end medthod choiceAns ตรวจคำตอบว่า user เลือคำตอบถูกมั้ย
    private void dailogboxScore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("สรุปคะเเนน");
        builder.setTitle("ได้คะเเนน " + score+"คะเเนน")
                .setCancelable(false)
                .setPositiveButton("ออกจากเกมส์", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("เล่นอีกครั้ง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    public void playSound(View view) {
        mediaPlayer.start();
    } //end play Sound
}//End class

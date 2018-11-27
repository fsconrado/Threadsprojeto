package com.edu.ifal.threads_projeto;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoIniciar;
    private int numero;
    private Handler handler = new Handler();
    private boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);
    }

    public void iniciarThread(View view) {

       // MyThread thread = new MyThread();
        // thread.start();
        pararExecucao=false;

        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

    }

    public void paraThread(View view){
        pararExecucao=true;

    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {

            for(int i=0; i<= 16; i++){
                if(pararExecucao)return;

                numero = i;
                Log.d("Thread", "iniciarThread: Contator" + i);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(numero<=15){
                            botaoIniciar.setText("Thread: Contator" + numero);
                        } else{
                            botaoIniciar.setText("Iniciar Thread");
                        }

                    }
                });

/*                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("Thread: Contator" + numero);
                    }
                });*/

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    class  MyThread extends Thread {

        @Override
        public void run() {

            for(int i=0; i<= 15; i++){

                Log.d("Thread", "iniciarThread: Contator" + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            super.run();
        }
    }
}

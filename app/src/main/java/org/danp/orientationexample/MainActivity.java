package org.danp.orientationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        int whip=0;
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        SensorManager sensorManager2=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor2=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager2.registerListener(sensorEventListener2,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            for(int i=0; i < event.values.length;i++){
                Log.d("VALOR "+i+":",""+event.values[i]);
                TextView texto=findViewById(R.id.nombre);
                TextView texto2=findViewById(R.id.nombre2);
                TextView texto3=findViewById(R.id.nombre3);
                if(i==0)
                    texto.setText("Valor 1 : " + String.valueOf(event.values[i]));

                if(i==1) {
                    texto2.setText("Valor 2 : " + String.valueOf(event.values[i]));
                }

                if(i==2)
                    texto3.setText("Valor 3 : "+String.valueOf(event.values[i]));
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private SensorEventListener sensorEventListener2=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = 0,y=0,z=0;
            TextView texto=findViewById(R.id.coord2);
            TextView texto2=findViewById(R.id.coord3);
            TextView texto3=findViewById(R.id.coord4);
            for(int i=0; i < event.values.length;i++){
                if(i==0) {
                    texto.setText("Valor 1 : " + String.valueOf(event.values[i]));

                    x=event.values[i];
                }

                if(i==1) {
                    texto2.setText("Valor 2 : " + String.valueOf(event.values[i]));
                    y=event.values[i];
                }
                if(i==2) {
                    texto3.setText("Valor 3 : " + String.valueOf(event.values[i]));
                    z=event.values[i];
                }

            }
            if(y>0&&y-x>5){//Defecto
                Button but= findViewById(R.id.btnSensor);
                but.setBackgroundColor(Color.BLACK);
                but.setText("Vertical 1");
            }
            else if (y<-4&&x<4&&y+x<0){
                Button but= findViewById(R.id.btnSensor);
                but.setBackgroundColor(Color.BLACK);
                but.setText("Vertical 2");
            }
            else if(x>0&&y<4&&(x-y>5)){

                Button but= findViewById(R.id.btnSensor);
                but.setBackgroundColor(Color.BLUE);
                but.setText("Horizontal 2");

            }

            else if(x<-4&&y<4&&y+x<0){
                Button but= findViewById(R.id.btnSensor);
                but.setBackgroundColor(Color.BLUE);
                but.setText("Horizontal 1");
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
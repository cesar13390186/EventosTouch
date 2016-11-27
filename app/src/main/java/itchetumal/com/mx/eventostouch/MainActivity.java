package itchetumal.com.mx.eventostouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    float cirCoordX = 0, cirCoordY = 0, coordInicX = 0, coordInicY=0;

    Path ruta = new Path();

    String mensaje = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutP = (LinearLayout) findViewById(R.id.layoutprincipal);
        Lienzo areaDibujo = new Lienzo(this);
        layoutP.addView(areaDibujo);
    }

    class Lienzo extends View{

        float x1=400, y1=400;

        boolean clickOrigen = false;

        public Lienzo(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas){

            Paint pincel = new Paint();
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            pincel.setColor(Color.CYAN);
            pincel.setStrokeWidth(5);

            //mejorar bordes al circulo
            pincel.setAntiAlias(true);
            canvas.drawCircle(cirCoordX,cirCoordY, 20,pincel);

            pincel.setTextSize(50);
            canvas.drawText("x = " + cirCoordX + " " + "Y = " + cirCoordY,0,this.getMeasuredHeight() - 40, pincel);
            canvas.drawText(mensaje,0,this.getMeasuredHeight(),pincel);

            pincel.setStrokeWidth(5);
            //canvas.drawLine(coordInicX,coordInicY,cirCoordX,cirCoordY,pincel);
            canvas.drawPath(ruta,pincel);

            //Hacer mas gruesa la linea
            pincel.setStrokeWidth(3);

            //Dibujar una linea en el eje x
            pincel.setARGB(255, 122, 34, 110);
            canvas.drawLine(0, alto/4, ancho, alto/4, pincel);

           // float divicion1= double(alto/1.5);

            pincel.setARGB(255, 122, 34, 110);
            //canvas.drawLine(0, , ancho, alto, pincel);

            //Dibujar una linea en el eje y
            pincel.setARGB(255, 122, 34, 110);
            canvas.drawLine(ancho/3, 0, ancho/3, alto, pincel);


            float x1=ancho/2, y1=alto/2;

            pincel.setColor(Color.RED);
            canvas.drawCircle(x1, y1, 40, pincel);
        }

        public boolean onTouchEvent(MotionEvent evento){

            cirCoordX = evento.getX();
            cirCoordY = evento.getY();
            if (evento.getAction()== MotionEvent.ACTION_DOWN){
                //coordInicX = evento.getX();
                //coordInicY = evento.getY();
                ruta.moveTo(cirCoordX,cirCoordY);
                mensaje = "Evento Down";

                if (Math.sqrt(Math.pow(cirCoordX-x1, 2) + Math.pow(cirCoordY-y1,2)) <= 300){
                    mensaje = "Diste click en el círculo rojo";
                    clickOrigen = true;
                }
            }
            if (evento.getAction()== MotionEvent.ACTION_UP){
                mensaje = "Evento Up";
            }
            if (evento.getAction()== MotionEvent.ACTION_MOVE) {
                mensaje = "Evento Move";
                //ruta.lineTo(cirCoordX,cirCoordY);
            }

            this.invalidate();
            return true;
        }
    }




}

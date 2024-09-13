package Alexander.taskmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import Alexander.taskmaster.noseloco.Modelo;

public class Agregartarea extends AppCompatActivity {

    private EditText nombreTarea;
    private EditText descripcionTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregartarea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreTarea = findViewById(R.id.nombretarea);
        descripcionTarea = findViewById(R.id.descripciontarea);
        Button cancelar = findViewById(R.id.cancelar);
        Button retroceder = findViewById(R.id.retroceder);
        Button aceptar = findViewById(R.id.aceptar);

        // Botón para limpiar los campos
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreTarea.setText("");
                descripcionTarea.setText("");
            }
        });

        // Botón para volver atrás
        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Botón para aceptar la nota
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreTarea.getText().toString();
                String descripcion = descripcionTarea.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty()) {
                    // alerta de guardado
                    new AlertDialog.Builder(Agregartarea.this)
                            .setTitle("¿Desea guardar nota?")
                            .setMessage("Estas seguro de guardar la nota?")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int wich) {
                                    // Crear un Intent para regresar los datos
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("nombre", nombre);
                                    resultIntent.putExtra("descripcion", descripcion);
                                    setResult(RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            // cancelar el guardado de la nota
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int wich) {
                                    dialogInterface.dismiss();
                                    nombreTarea.setText("");
                                    descripcionTarea.setText("");
                                }
                            })
                            .show();
                }
            }
        });
    }
}

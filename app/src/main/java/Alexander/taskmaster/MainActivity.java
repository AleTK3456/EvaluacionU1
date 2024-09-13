package Alexander.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Alexander.taskmaster.noseloco.Adapter;
import Alexander.taskmaster.noseloco.Modelo;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private ArrayList<Modelo> listaNotas;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar la lista de notas y el RecyclerView
        listaNotas = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.taskview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(listaNotas);
        recyclerView.setAdapter(adapter);

        // Botón para agregar nueva tarea
        Button agregarTask = findViewById(R.id.nuevatarea);
        agregarTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Agregartarea.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK && data != null) {
            // Recoger los datos enviados desde Agregartarea
            String nombre = data.getStringExtra("nombre");
            String descripcion = data.getStringExtra("descripcion");

            // Crear una nueva nota y agregarla a la lista
            Modelo nuevaNota = new Modelo(nombre, descripcion, R.drawable.taskmaster); // Cambia R.drawable.tu_imagen por la imagen adecuada
            listaNotas.add(nuevaNota);
            adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
        }
    }
}

package com.example.dell.directorioturismo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.dell.directorioturismo.appi.VacunacionAppi;
import com.example.dell.directorioturismo.models.Vacunacion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private boolean cargar;
    private int offset;
    private AdaptadorVacunacion turismo;

    public static final String TAG="Datos abiertos Colombia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        turismo = new AdaptadorVacunacion(this);
        recyclerView.setAdapter(turismo);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (cargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Fin.");

                            cargar = false;
                            offset += 20;
                            obtenerDatos();
                        }
                    }
                }
            }
        });




        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cargar = true;
        offset = 0;

        obtenerDatos();

    }



    private void obtenerDatos() {
        VacunacionAppi service = retrofit.create(VacunacionAppi.class);
        Call<ArrayList<Vacunacion>> reporteRespuestaCall = service.obtenerListaTurismo();

        reporteRespuestaCall.enqueue(new Callback<ArrayList<Vacunacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Vacunacion>> call, Response<ArrayList<Vacunacion>> response) {
                if(response.isSuccessful()){
                    ArrayList lista = response.body();
                    turismo .agregarTurismo(lista);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vacunacion>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }
    public void acercade(View view) {
        Intent i = new Intent(this, AcercaDe.class );
        startActivity(i);
    }

}

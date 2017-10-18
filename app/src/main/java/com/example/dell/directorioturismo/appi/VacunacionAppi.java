package com.example.dell.directorioturismo.appi;

import com.example.dell.directorioturismo.models.Vacunacion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by DELL on 17/10/2017.
 */

public interface VacunacionAppi {

    @GET("tciz-4ayz.json")
    Call<ArrayList<Vacunacion>> obtenerListaTurismo();
}

package mx.nitrogena.dadm.mod5.nasa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.Adapter.NasaApodAdapter;
import mx.nitrogena.dadm.mod5.nasa.data.ApodService;
import mx.nitrogena.dadm.mod5.nasa.data.Data;
import mx.nitrogena.dadm.mod5.nasa.model.APOD;
import mx.nitrogena.dadm.mod5.nasa.model.MarsRoverResponse;
import mx.nitrogena.dadm.mod5.nasa.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 05/08/2016.
 */
public class ListActivity extends AppCompatActivity {

    @BindView(R.id.nasaapodlayout_rv_recyclerv)
    RecyclerView marsRoverListRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasa_apod_layout);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        marsRoverListRecycler.setLayoutManager(linearLayoutManager);


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        //Call<APOD> callApodService = apodService.getTodayApod();
        //SE PUEDE USAR LA FORMA DE ARRIBA O LA DE ABAJO, ES LO MISMO
        Call<MarsRoverResponse> callMarsService = apodService.getTodayMarsRoverResponseWithQuery(1000, "sN0q0EGDwJer2FFc4ns7toCYkthNtvgmtuAjtEIV");
        callMarsService.enqueue(new Callback<MarsRoverResponse>() {
            @Override
            public void onResponse(Call<MarsRoverResponse> call, Response<MarsRoverResponse> response) {
                //ESTAMOS OBTENIENDO LOS VALORES DEL JSON QUE USAMOS

                marsRoverListRecycler.setAdapter(new NasaApodAdapter(response.body().getPhotos()));



            }

            @Override
            public void onFailure(Call<MarsRoverResponse> call, Throwable t) {

            }
        });


    }

}

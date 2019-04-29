package com.example.grow.ui.nutrition

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.grow.generic.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.FileNotFoundException
import java.net.URL

class NutritionViewModel(application: Application) : MyViewModel(application) {

    var food: MutableLiveData<JSONObject> = MutableLiveData()

    fun fetchFood(name: String) = scope.launch(Dispatchers.IO) {
        try {
            food.postValue(JSONObject(URL(
                "https://api.edamam.com/api/food-database/parser?nutrition-type=logging&app_id=8b6ae109&app_key=f6258c502f8a7e1820b3532b5f061ef0&ingr=$name")
                .readText()))
        } catch (e: FileNotFoundException) {
            food.postValue(JSONObject("Not found"))
        }
    }

    init {

    }

}

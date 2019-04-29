package com.example.grow.ui.nutrition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.grow.R
import org.json.JSONException
import org.json.JSONObject


class NutritionFragment : Fragment() {

    private lateinit var viewModel: NutritionViewModel

    lateinit var label: TextView
    lateinit var kcal: TextView
    lateinit var proteins: TextView
    lateinit var fat: TextView
    lateinit var carbs: TextView

    lateinit var searchField: EditText
    lateinit var searchBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.nutrition_fragment, container, false)

        label = view.findViewById(R.id.nutrition_label_text)
        kcal = view.findViewById(R.id.nutrition_kcal_text)
        proteins = view.findViewById(R.id.nutrition_proteins_text)
        fat = view.findViewById(R.id.nutrition_fat_text)
        carbs = view.findViewById(R.id.nutrition_carbs_text)

        searchField = view.findViewById(R.id.nutrition_search_edtTxt)
        searchBtn = view.findViewById(R.id.nutrition_search_btn)

        searchBtn.setOnClickListener {
            searchFood(searchField.text.toString())
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NutritionViewModel::class.java)
        viewModel.food.observe(this, Observer {
            val parsed = it.getJSONArray("parsed")
            try {
                val food = parsed.getJSONObject(0).getJSONObject("food")
                populateView(food)
            } catch (e: JSONException) {
                label.text = "Not found"
                clearNutrients()
            }
        })
    }

    private fun searchFood(food: String) {
        label.text = "Loading..."
        clearNutrients()
        viewModel.fetchFood(food)
    }

    private fun populateView(food: JSONObject) {
        label.text = "${food.getString("label")} per 100 grams"
        val nutrients = food.getJSONObject("nutrients")
        val kcalValue = nutrients.getInt("ENERC_KCAL")
        val proteinValue = nutrients.getInt("PROCNT")
        val fatValue = nutrients.getInt("FAT")
        val carbValue = (kcalValue - (proteinValue * 4) - (fatValue * 9)) / 4
        kcal.text = "$kcalValue kcal"
        proteins.text = "${proteinValue}g proteins"
        fat.text = "${fatValue}g fat"
        carbs.text = "${carbValue}g carbs"
    }

    private fun clearNutrients() {
        kcal.text = ""
        proteins.text = ""
        fat.text = ""
        carbs.text = ""
    }

    companion object {
        fun newInstance() = NutritionFragment()
    }

}

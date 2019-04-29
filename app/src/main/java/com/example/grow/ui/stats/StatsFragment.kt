package com.example.grow.ui.stats

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkPermission
import androidx.lifecycle.Observer
import com.example.grow.MainActivity
import com.example.grow.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.w3c.dom.Text


class StatsFragment : Fragment() {

    companion object {
        fun newInstance() = StatsFragment()
    }

    private lateinit var viewModel: StatsViewModel

    lateinit var height: TextView
    lateinit var weight: TextView
    lateinit var waist: TextView
    lateinit var steps: TextView
    lateinit var distance: TextView

    private var fusedLocationClient: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats_fragment, container, false)

        height = view.findViewById(R.id.stats_height_value)
        weight = view.findViewById(R.id.stats_weight_value)
        waist = view.findViewById(R.id.stats_waist_value)
        steps = view.findViewById(R.id.stats_steps_value)
        // Unable to test since i do not own an android phone
        steps.text = arguments?.getFloat("steps").toString()
        distance = view.findViewById(R.id.stats_distance_value)

        /**
         * PARTIALLY COPIED FROM:
         * https://en.proft.me/2019/01/3/how-get-location-latitude-longitude-android-kotlin/
         */
        if (ContextCompat.checkSelfPermission(this.activity!!, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this.activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient?.lastLocation?.addOnSuccessListener(this.activity!!) { location: Location? ->
                if (location == null) {
                    // TODO, handle it
                } else location.apply {
                    val gym = Location("")
                    // Fresh Fitness @ Ryen, Oslo
                    gym.latitude = 59.8936984
                    gym.longitude = 10.8040349
                    // The gyms location is in Oslo, the default starting position is in California
                    // Ca. 8,3k km
                    distance.text = gym.distanceTo(location).div(1000).toString() + " km"
                }
            }
        } else {
            Log.e("LOG", "permission denied")
        }

            return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(StatsViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        viewModel.user.observe(this, Observer {user ->
            user?.let {
                height.text = activity?.getString(R.string.stats_height_value, it.height)
                weight.text = activity?.getString(R.string.stats_weight_value, it.weight)
                waist.text = activity?.getString(R.string.stats_waist_value, it.waist)
            }
        })
    }

}

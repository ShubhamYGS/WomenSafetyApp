package com.shubham.womensafety

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.shubham.womensafety.FirebaseAuth.LoginViewModel
import com.shubham.womensafety.databinding.FragmentDashBoardBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*

class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var Latitude:String=""
    private var Longitude:String=""

    companion object {
        const val TAG = "DashBoardFragment"
        const val SIGN_IN_RESULT_CODE = 1001
        private const val LOCATION_PERMISSION_REQUEST_CODE = 999

    }

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dash_board,container,false)

        binding.guardianButton.setOnClickListener { view:View->
            view.findNavController().navigate(DashBoardFragmentDirections.actionDashBoardFragmentToGuardianInfo())
        }

        binding.locButton.setOnClickListener { view:View->
            view.findNavController().navigate(R.id.action_dashBoardFragment_to_mapsActivity)
        }

        binding.emerButton.setOnClickListener {
            getLocation()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
    }

    private fun getLocation(){
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if(location!=null){
                lastLocation=location
                Latitude = (location.latitude).toString()
                Longitude = (location.longitude).toString()
                Toast.makeText(activity!!,"${Latitude +"  "+ Longitude}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(activity!!,"Try again: Location not fetched",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //User successfully signed in
            } else {
                // Sign in failed. If response is null, the user canceled the
                // sign-in flow using the back button. Otherwise, check
                // the error code and handle the error.
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun observeAuthenticationState() {
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    binding.textView.text=("Welcome, "+FirebaseAuth.getInstance().currentUser?.displayName)
                }
                else -> {
                    launchSignInFlow()
                }
            }
        })
    }

    private fun launchSignInFlow() {
        // Give users the option to sign in / register with their email
        // If users choose to register with their email,
        // they will need to create a password as well
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
            //
        )

        // Create and launch sign-in intent.
        // We listen to the response of this activity with the
        // SIGN_IN_RESULT_CODE code
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ) .setTheme(R.style.LoginTheme_NoActionBar)
                .setLogo(R.drawable.women)
                .build(), DashBoardFragment.SIGN_IN_RESULT_CODE
        )
    }


}

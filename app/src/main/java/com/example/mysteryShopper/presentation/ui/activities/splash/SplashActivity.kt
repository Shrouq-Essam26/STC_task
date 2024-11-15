package com.example.mysteryShopper.presentation.ui.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mysteryShopper.R
import com.example.mysteryShopper.databinding.ActivitySplashBinding
import com.example.mysteryShopper.core.Status
import com.example.mysteryShopper.presentation.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(titleResId = R.string.app_name)
        viewModel.getConfig()
        viewModel.config.observe(this){

            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        dialog.show()
                    }
                    Status.SUCCESS -> {
                        dialog.dismiss()
                        binding.tvHello.text = it.data?.android_version
                    }
                    Status.ERROR -> {
                        dialog.dismiss()
                    }
                }
            }

        }
    }
}
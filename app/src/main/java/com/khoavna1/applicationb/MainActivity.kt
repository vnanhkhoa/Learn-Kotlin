package com.khoavna1.applicationb

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.khoavna1.applicationb.databinding.ActivityMainBinding
import com.khoavna1.basicandroidday08.utils.PermissionRequired

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val activityResultLauncher by lazy {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            it.values.forEach { value ->
                if (!value) {
                    requestPermission.showAlert()
                    return@registerForActivityResult
                }
            }

            Intent("com.khoavna1.basicandroidday08.ACTION").apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
        }
    }

    private lateinit var requestPermission: PermissionRequired

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestPermission = PermissionRequired(this,activityResultLauncher)

        binding.apply {
            btnStart.setOnClickListener {
                if (requestPermission.isPermission("com.khoavna1.basicandroidday08.Permission")) {
                    requestPermission.requestPermission("com.khoavna1.basicandroidday08.Permission")
                } else {
                    Intent("com.khoavna1.basicandroidday08.ACTION").apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(this)
                    }
                }
            }
        }
    }
}
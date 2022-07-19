package com.khoavna1.basicandroidday08.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PermissionRequired(
    private val context: Context,
    private val activityResultLauncher: ActivityResultLauncher<Array<String>>
) {
    fun requestPermission(vararg permission: String) {
        activityResultLauncher.launch(permission.toList().toTypedArray())
    }

    fun isPermission(permission: String): Boolean =
        ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED

    private fun showSettingSystem() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            context.startActivity(this)
        }
    }

    fun showAlert() {
        MaterialAlertDialogBuilder(context).apply {
            setTitle("Permission Required")
            setMessage("This Permission Is Required For Proper Working Of The App")
            setNegativeButton("No") { d, _ ->
                d.dismiss()
                Toast.makeText(
                    context,
                    "This Permission Is Required For Proper Working Of The App",
                    Toast.LENGTH_SHORT
                ).show()
            }
            setPositiveButton("OK") { d, _ ->
                showSettingSystem()
                d.dismiss()
            }
        }.show()
    }
}


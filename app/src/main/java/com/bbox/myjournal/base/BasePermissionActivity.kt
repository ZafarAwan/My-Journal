package com.bbox.myjournal.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bbox.myjournal.R


abstract class BasePermissionActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "BasePermissionActivity"
    }

    @CallSuper
    open fun onPostCreate() {
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkManifestPermissions()
    }

    private data class ManifestPermission(
        val permission: String,
        val isGranted: Boolean,
        val shouldShowDialog: Boolean
    )

    var askedManifestPermission = false

    private fun checkManifestPermissions() {
        val permissions = arrayListOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        val deniedPermissions = permissions.map {
            ManifestPermission(
                permission = it,
                isGranted = (ActivityCompat.checkSelfPermission(this, it)
                        == PackageManager.PERMISSION_GRANTED),
                shouldShowDialog = shouldShowRequestPermissionRationale(it)
            )
        }.filter { !it.isGranted }

        when {
            deniedPermissions.isEmpty() -> {
                grantedManifestPermissions()
            }
            !askedManifestPermission -> {
                askedManifestPermission = true
                requestManifestPermissions.launch(
                    deniedPermissions
                        .map { it.permission }
                        .toTypedArray())
            }
            deniedPermissions.any { it.shouldShowDialog } -> {
                requestManifestPermissions.launch(
                    deniedPermissions
                        .map { it.permission }
                        .toTypedArray())
            }
            else -> {
                showToast(getString(R.string.msg_grant_permissions))
                openAppDetail()
            }
        }
    }

    private fun openAppDetail() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.parse("package:$packageName")
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }
        requestManualManifestPermissions.launch(intent)
    }

    private val requestManualManifestPermissions =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            checkManifestPermissions()
        }

    private val requestManifestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                grantedManifestPermissions()
            } else {
                checkManifestPermissions()
            }
        }

    private val grantedManifestPermissions = {
        onPostCreate()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
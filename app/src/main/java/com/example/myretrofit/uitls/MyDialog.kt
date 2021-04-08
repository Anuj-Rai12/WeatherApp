package com.example.myretrofit.uitls

import android.app.AlertDialog
import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment


class MyDialog : AppCompatDialogFragment() {
     var myHelperInter: MyHelperInter? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alterdaialog = AlertDialog.Builder(activity).setTitle("AutoStart permission")
            .setMessage("This App need autoStart permission so,Press yes to start.\nIf you have given so \n Please ignore.")
            .setPositiveButton("yes") { dialog, which ->
                myHelperInter?.callStart(mystartauto())
            }
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }
        return alterdaialog.create()
    }

    private fun mystartauto(): Intent? {
        val manufacturer = Build.MANUFACTURER
        try {
            val intent = Intent()
            if ("xiaomi".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity"
                )
            } else if ("oppo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.coloros.safecenter",
                    "com.coloros.safecenter.permission.startup.StartupAppListActivity"
                )
            } else if ("vivo".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.vivo.permissionmanager",
                    "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
                )
            } else if ("Letv".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.letv.android.letvsafe",
                    "com.letv.android.letvsafe.AutobootManageActivity"
                )
            } else if ("Honor".equals(manufacturer, ignoreCase = true)) {
                intent.component = ComponentName(
                    "com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.process.ProtectActivity"
                )
            }
            return intent
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
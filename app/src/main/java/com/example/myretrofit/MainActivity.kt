package com.example.myretrofit

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.myretrofit.bottomsheet.MyBottomSheet
import com.example.myretrofit.databinding.ActivityMainBinding
import com.example.myretrofit.mycontrol.MyViewFactory
import com.example.myretrofit.mycontrol.MyViewModel
import com.example.myretrofit.mywork.DisplayNotification
import com.example.myretrofit.repos.Repository
import com.example.myretrofit.room.RoomData
import com.example.myretrofit.room.RoomDataInstance
import com.example.myretrofit.uitls.MyDialog
import com.example.myretrofit.uitls.MyHelperInter
import com.example.myretrofit.uitls.Myhelperclass
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MyHelperInter {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var myViewFactory: MyViewFactory
    private lateinit var repository: Repository
    private val fadeAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }
    private var notifyicon: String = "01d"
    private var notifydesc: String = "checking for Weather"
    private var notifytemp: String = "Updating.."

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myViewModelFun()
        setmydata()
        mydialogfun()
        myViewModel._snackbar.observe(this, {
            it.getContentIfNotHandled()
                ?.let { op ->
                    if (op == "Data Downloaded Successfully") {
                        binding.clec.visibility = View.VISIBLE
                        binding.yu.visibility = View.VISIBLE
                        binding.secretiv.visibility = View.VISIBLE
                    } else
                        Toast.makeText(this, op, Toast.LENGTH_SHORT).show()
                }
        })
        binding.myserchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    //  myViewModel.inital()
                    setmydata(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding.lifecycleOwner = this
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setDemo(string: String) {
        CoroutineScope(Main).launch {
            if (getBitmap(string) != null)
                binding.mybackground.setImageBitmap(getBitmap(string))
            else
                binding.mybackground.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.clearsky,
                        null
                    )
                )
            binding.clec.visibility = View.VISIBLE
            binding.yu.visibility = View.VISIBLE
            binding.secretiv.visibility = View.VISIBLE
            binding.relevi.startAnimation(fadeAnimation)
            binding.secretiv.startAnimation(fadeAnimation)
        }
    }

    private fun myViewModelFun() {
        val dao = RoomDataInstance.getInstance(applicationContext).daoAccessObj
        repository = Repository(dao)
        myViewFactory = MyViewFactory(repository)
        myViewModel = ViewModelProvider(this, myViewFactory).get(MyViewModel::class.java)
        binding.myvarible = myViewModel
    }

    private fun NotifMyResult() {
        val data = Data.Builder()
            .putString("wedescp", notifydesc)
            .putString("wetemp", notifytemp)
            .putString("weicon", notifyicon)
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val showWorker =
            PeriodicWorkRequest.Builder(DisplayNotification::class.java, 16, TimeUnit.MINUTES)
                .setInputData(data)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(this).enqueue(showWorker)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(showWorker.id).observe(
            this,
            {
                Log.i("MyWORK", it.state.toString())
            })

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setmydata(string: String = "Ballia") {
        myViewModel.getsMyData(string).observe(this, {
            if (it.isSuccessful) {
                var desc = "Not Data Found"
                it.body()?.weather?.iterator()?.forEach { op ->
                    desc = op.description
                    Myhelperclass.myIcons[op.icon]?.let { str -> setDemo(str) }
                    notifyicon = op.icon
                    notifydesc = desc
                }
                myViewModel.setData(it, desc)
                myViewModel.temp.observe(this, { temp ->
                    if (!temp.isNullOrEmpty())
                        notifytemp = "Feel's Like $temp°C"
                })
                NotifMyResult()
            } else {
                Toast.makeText(
                    this,
                    "No Data Found\nOr\nTry to give some Space between your Search Query ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun mydialogfun() {
        val myDialog = MyDialog()
        myDialog.show(supportFragmentManager, "MY dialog")
        myDialog.isCancelable = false
        myDialog.myHelperInter = this
        /*if (MyDialog.myintend!=null)
        {
            val list: List<ResolveInfo> =
                packageManager.queryIntentActivities(MyDialog.myintend!!, PackageManager.MATCH_DEFAULT_ONLY)
            if (list.isNotEmpty()) {
            startActivity(intent)
        }*/
        /*}
        else
            Toast.makeText(this, "it is empty", Toast.LENGTH_SHORT).show()*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.dot_menu, menu)
        val editMnu = menu?.findItem(R.id.edithis)
        val delMnu = menu?.findItem(R.id.deletit)
        editMnu?.setOnMenuItemClickListener {
            myBottomsheet()
            return@setOnMenuItemClickListener true
        }
        delMnu?.setOnMenuItemClickListener {
            Toast.makeText(this, "Ruko Jara Sbar Kro", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu)
    }

    private suspend fun getBitmap(string: String): Bitmap? {
        //binding.myprogress.visibility = View.VISIBLE
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(string)
            .build()
        return try {
            val result = (loading.execute(request) as SuccessResult).drawable
            (result as BitmapDrawable).bitmap
        } catch (e: Exception) {
            //binding.myprogress.visibility = View.GONE
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun myBottomsheet() {
        val myBottomSheet = MyBottomSheet()
        myBottomSheet.myHelperInter=this
        myBottomSheet.show(supportFragmentManager, "My_Bottom_Sheet")
    }

    override fun callStart(intent: Intent?) {
        if (intent != null) {
            val list: List<ResolveInfo> =
                packageManager.queryIntentActivities(
                    intent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )
            if (list.isNotEmpty()) {
                startActivity(intent)
            }
        }
    }

    override fun sendData(string: String) {
        if (string.equals("enter the correct optioN",true))
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Successfully $string", Toast.LENGTH_SHORT).show()

    }
}
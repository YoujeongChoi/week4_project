package com.example.week4_new_project

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import com.example.week4_new_project.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask




class GameActivity : AppCompatActivity() {
    val TAG: String = "로그"

    private lateinit var mBinding: ActivityGameBinding
    private val binding get() = mBinding!!

    private lateinit var mDetector: GestureDetectorCompat


    var gametime = 100
    var gametimec = gametime
    val random = Random()
    var score : Int = 0



    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler(Looper.getMainLooper())
    var runnable : Runnable = Runnable{ }

    val thread = Thread(runnable)

    var total = 0
    var started = false
    var gameStarted = false

    
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "GameActivity - onCreate() called")
        mBinding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root

        super.onCreate(savedInstanceState)
        setContentView(view)

        score = 0



        imageArray = arrayListOf(binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4,
            binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9)




        binding.homeIb.setOnClickListener {
            if(started == false) {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "게임이 중단됩니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.startIb.setOnClickListener {

            Log.d(TAG, "버튼클릭")
            thread.start()
            binding.startIb.visibility =  View.INVISIBLE

            hideImages()


            object : CountDownTimer(30000, 1000) {
                override fun onFinish() {
                    binding.timerText.text = "Time over"
                    handler.removeCallbacks(runnable)
                    for (image in imageArray)
                        image.visibility = View.INVISIBLE
                    binding.startIb.visibility =  View.VISIBLE
                }
                override fun onTick(p0: Long) {
                    binding.timerText.text = "Time: " + p0 / 1000
                }
            }.start()


        }
    }


    fun hideImages() {

        runnable = object : Runnable {
            
            override fun run() {
                Log.d(TAG, "GameActivity - run() called")

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                for (image in imageArray) {
                    image.setOnClickListener {
                        if(image == imageArray[index]) {
                            Log.d(TAG, "맞게클릭 ")
                            image.setImageResource(R.drawable.hit_mole)
                            handler.postDelayed(runnable, 500)
                            score+=5
                            binding.scoreTv.text = "Score: $score"
                        } else {
                            Log.d(TAG, "잘못클릭")
                            handler.postDelayed(runnable, 500)
                        }
                    }
                    imageArray[index].setImageResource(R.drawable.mole)
                }


                handler.postDelayed(runnable, 5000)
            }
        }
        handler.post(runnable)
    }


}


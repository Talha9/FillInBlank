package com.example.fillinblank


import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import com.example.fillinblank.databinding.ActivityMainBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class MainActivity : AppCompatActivity() {
    var strList: ArrayList<DataModel> = ArrayList()
    lateinit var binding: ActivityMainBinding
    var adapter: QuestionMakerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val intent = intent
        strList = intent.getParcelableArrayListExtra("IMAGE_LIST")
            ?: throw IllegalStateException("Songs array list is null")
        /*  findViewById<View>(R.id.myimage1).setOnTouchListener(MyTouchListener())
          findViewById<View>(R.id.myimage2).setOnTouchListener(MyTouchListener())
          findViewById<View>(R.id.myimage3).setOnTouchListener(MyTouchListener())
          findViewById<View>(R.id.myimage4).setOnTouchListener(MyTouchListener())
          findViewById<View>(R.id.topleft).setOnDragListener(MyDragListener(this))
          findViewById<View>(R.id.topright).setOnDragListener(MyDragListener(this))
          findViewById<View>(R.id.bottomleft).setOnDragListener(MyDragListener(this))
          findViewById<View>(R.id.bottomright).setOnDragListener(MyDragListener(this))*/




        binding.itemParentContainer.setOnDragListener(MyDragListenerParent(this))
        binding.recView.setOnDragListener(MyDragListenerRecycler(this))
        val itemClasses: ArrayList<ItemClass> = ArrayList()
        for (i in strList) {
            if (i.txt == "SPACE") {
                itemClasses.add(
                    ItemClass(ItemClass.LayoutTwo, null, i.case)
                )
            } else {
                itemClasses.add(
                    ItemClass(ItemClass.LayoutOne, i.txt, null)
                )
            }
        }


        val layoutManager = FlexboxLayoutManager(applicationContext)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.recView.layoutManager = layoutManager

        adapter = QuestionMakerAdapter(itemClasses, this)
        binding.recView.adapter = adapter





        for (i in 0 until strList.size) {
            if (strList[i].txt == "SPACE") {
                val lLayout = binding.itemParentContainer
                val tv = TextView(this)
                tv.text = "TextView ${strList[i].case}"
                tv.id = i + 1
                tv.setBackgroundColor(Color.GRAY)
                tv.setTextColor(Color.WHITE)
                tv.tag = strList[i].case
                tv.setPadding(10, 5, 10, 5)
                tv.setOnTouchListener(MyTouchListener())
                lLayout.addView(tv)
            }
        }


    }

    class MyTouchListener() : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(
                    view
                )
                view.startDrag(data, shadowBuilder, view, 0)

                /*       view.tag = model.ans*/
                view.visibility = View.INVISIBLE
                true
            } else {
                false
            }
        }
    }

    class MyDragListener(mContext: Context, var itemClass: ItemClass) : View.OnDragListener {
        var enterShapeTrue: Drawable =
            mContext.resources.getDrawable(R.drawable.shape_droptarget_true)
        var enterShapeFalse: Drawable = mContext.resources.getDrawable(R.drawable.shape_droptarget)
        var normalShape: Drawable = mContext.resources.getDrawable(R.drawable.shape)


        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {}
                DragEvent.ACTION_DRAG_ENTERED -> {
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    Log.d("DRAGTAG", "onDrag: " + owner.tag)
                    if (itemClass.ans == view.tag) {
                        v.setBackgroundDrawable(enterShapeTrue)
                    } else {
                        v.setBackgroundDrawable(enterShapeFalse)
                    }

                }

                DragEvent.ACTION_DRAG_EXITED -> v.setBackgroundDrawable(normalShape)
                DragEvent.ACTION_DROP -> {
                    // Dropped, reassign View to ViewGroup
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = v as LinearLayout
                    container.addView(view)
                    view.visibility = View.VISIBLE
                }

                DragEvent.ACTION_DRAG_ENDED -> v.setBackgroundDrawable(normalShape)
                else -> {}
            }
            return true
        }
    }

    class MyDragListenerParent(mContext: Context) : View.OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {}
                DragEvent.ACTION_DRAG_ENTERED -> {}
                DragEvent.ACTION_DRAG_EXITED -> {}
                DragEvent.ACTION_DROP -> {
                    // Dropped, reassign View to ViewGroup
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = v as LinearLayout
                    container.addView(view)
                    view.visibility = View.VISIBLE
                }

                DragEvent.ACTION_DRAG_ENDED -> {}
                else -> {}
            }
            return true
        }
    }

    inner class MyDragListenerRecycler(mContext: Context) : View.OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {}
                DragEvent.ACTION_DRAG_ENTERED -> {}
                DragEvent.ACTION_DRAG_EXITED -> {}
                DragEvent.ACTION_DROP -> {
                    // Dropped, reassign View to ViewGroup
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    binding.itemParentContainer.addView(view)
                    view.visibility = View.VISIBLE
                }

                DragEvent.ACTION_DRAG_ENDED -> {}
                else -> {}
            }
            return true
        }
    }


}
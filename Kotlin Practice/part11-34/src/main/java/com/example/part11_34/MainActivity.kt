package com.example.part11_34

import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_data.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class MainActivity : AppCompatActivity() {

    val today = "2017-07-01"
    val yesterday = "2017-06-30"

    val list:MutableList<ItemVO>

    init {
        list = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = DBHelper(this)
        val db = helper.writableDatabase
        val todayCursor = db.rawQuery("select * from tb_data where date = '2017-07-01'", null)
        val yesterdayCursor = db.rawQuery("select * from tb_data where date = '2017-06-30'", null)
        val beforeCursor = db.rawQuery("select * from tb_data where date != '2017-07-01' and date != '2017-06-30'", null)

        if(todayCursor.count != 0) {
            val item = HeaderItem()
            item.headerTitle = "오늘"

            list.add(item)
            while(todayCursor.moveToNext()) {
                val dataItem = DataItem()
                dataItem.name = todayCursor.getString(1)
                dataItem.date = todayCursor.getString(2)
                list.add(dataItem)
            }
        }

        if(yesterdayCursor.count != 0) {
            val item = HeaderItem()
            item.headerTitle = "어제"
            list.add(item)
            while(yesterdayCursor.moveToNext()) {
                val dataItem = DataItem()
                dataItem.name = yesterdayCursor.getString(1)
                dataItem.date = yesterdayCursor.getString(2)
                list.add(dataItem)
            }
        }

        if(beforeCursor.count != 0) {
            val item = HeaderItem()
            item.headerTitle = "이전"
            list.add(item)
            while(beforeCursor.moveToNext()) {
                val dataItem = DataItem()
                dataItem.name = beforeCursor.getString(1)
                dataItem.date = beforeCursor.getString(2)
                list.add(dataItem)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(list)
        recyclerView.addItemDecoration(MyDecoration())
    }

    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val headerView = view.itemHeaderView
    }

    class DataViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val nameView = view.itemNameView
        val dateView = view.itemDateView
        val personView = view.itemPersonView
    }

    class MyAdapter(val list: MutableList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemViewType(position : Int) :Int {
            return list.get(position).type
        }

        // 새로운 뷰 홀더 생성
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) :RecyclerView.ViewHolder {
            if(viewType == ItemVO.TYPE_HEADER) {
                val layoutInflater = LayoutInflater.from(parent?.context)
                return HeaderViewHolder(layoutInflater.inflate(R.layout.item_header, parent, false))
            }

            else {
                val layoutInflater = LayoutInflater.from(parent?.context)
                return DataViewHolder(layoutInflater.inflate(R.layout.item_data, parent, false))
            }
        }

        //생성된 뷰를 해당 포지션에 데이터로 바꿈.
        override fun onBindViewHolder(holder:RecyclerView.ViewHolder?, position:Int) {
            val itemVO = list.get(position)
            if(itemVO.type == ItemVO.TYPE_HEADER){
                val viewHolder = holder as HeaderViewHolder
                val headerItem = itemVO as HeaderItem

                viewHolder.headerView.setText(headerItem.headerTitle)
            } else {
                val viewHolder = holder as DataViewHolder
                val dataItem = itemVO as DataItem
                viewHolder.nameView.setText(dataItem.name)
                viewHolder.dateView.setText(dataItem.date)

                val count = position % 5

                when (count) {
                    0 -> (viewHolder.personView.background as GradientDrawable).setColor(0xff009688.toInt())
                    1 -> (viewHolder.personView.background as GradientDrawable).setColor(0xff4285f4.toInt())
                    2 -> (viewHolder.personView.background as GradientDrawable).setColor(0xff4285f4.toInt())
                    3 -> (viewHolder.personView.background as GradientDrawable).setColor(0xff039be5.toInt())
                    4 -> (viewHolder.personView.background as GradientDrawable).setColor(0xff0097a7.toInt())
                }
            }
        }
        override fun getItemCount() : Int {
            return list.size
        }
    }

    inner class MyDecoration:RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent!!.getChildAdapterPosition(view)
            val itemVO = list.get(index)
            if(itemVO.type == ItemVO.TYPE_DATA){
                view!!.setBackgroundColor(0xFFFFFFFF.toInt())
                ViewCompat.setElevation(view, 10.0f)
            }

            outRect!!.set(20, 10, 20, 10)
        }
    }
}

// view 에 담길 객체 만들기 (itemVO 를 상속받아 header view 와 data view 선언)
abstract class ItemVO {
    abstract val type : Int
    companion object {
        val TYPE_HEADER = 0
        val TYPE_DATA = 1
    }
}

class HeaderItem : ItemVO() {
    var headerTitle:String? = null

    override val type:Int
        get() = ItemVO.TYPE_HEADER
}

internal class DataItem : ItemVO() {
    var name:String? = null
    var date:String? = null
    override val type : Int
        get() = ItemVO.TYPE_DATA
}

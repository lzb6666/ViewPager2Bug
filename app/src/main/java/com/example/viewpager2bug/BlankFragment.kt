package com.example.viewpager2bug

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2bug.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private lateinit var fragmentBlankBinding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBlankBinding = FragmentBlankBinding.inflate(inflater)
        return fragmentBlankBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentBlankBinding.recyclerView.adapter = RvAdapter()
        fragmentBlankBinding.recyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}

class RvAdapter() : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return 40
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val tv = TextView(parent.context)
        tv.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tv.textSize = 20f
        tv.gravity = Gravity.CENTER
        tv.setPadding(20, 55, 20, 55)
        return ViewHolder(tv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tv.text = position.toString()
            itemView.setOnClickListener {
                recyclerView?.scrollToPosition(position)
                Toast.makeText(recyclerView!!.context, "$position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)
}
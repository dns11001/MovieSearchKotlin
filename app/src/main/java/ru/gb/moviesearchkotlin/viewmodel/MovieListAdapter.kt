package ru.gb.moviesearchkotlin.viewmodel

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.model.MovieDTO


class MovieListAdapter(private val dataSet: ArrayList<MovieDTO>, private val resources: Resources) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onClickListener(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageItem: ImageView



        init {
            textView = view.findViewById(R.id.item_name)
            imageItem = view.findViewById(R.id.item_image)
            textView.setOnClickListener {
                listener.onClickListener(adapterPosition)
            }
        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view, mListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val posterId : String = resources.getStringArray(R.array.posters)[position]

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].name

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
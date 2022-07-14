package ru.gb.moviesearchkotlin.view.searchfragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.databinding.SearchFragmentBinding
import ru.gb.moviesearchkotlin.model.MovieDTO
import ru.gb.moviesearchkotlin.viewmodel.MovieListAdapter
import ru.gb.moviesearchkotlin.viewmodel.MovieFragment
import ru.gb.moviesearchkotlin.viewmodel.SearchViewModel


class SearchFragment : Fragment() {


    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var binding: SearchFragmentBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) activity?.supportFragmentManager?.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: ArrayList<MovieDTO> = initMovieList(this.resources)
        val recyclerViewOne: RecyclerView = binding.root.findViewById(R.id.menu_recycler_view)
        val recyclerViewTwo: RecyclerView = binding.root.findViewById(R.id.menu2_recycler_view)
        initRecycleView(recyclerViewOne, data)
        initRecycleView(recyclerViewTwo, data)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {}
        viewModel.sentRequest()
    }

    private fun initRecycleView(recycleView: RecyclerView, data: ArrayList<MovieDTO>) {
        recycleView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleView.layoutManager = layoutManager

        val adapter = MovieListAdapter(data, resources)
        recycleView.adapter = adapter
        adapter.setOnClickListener(object : MovieListAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                val movie = data[position]

                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.search_fragment, MovieFragment.newInstance(movie))
                    .addToBackStack("")
                    .commit()
            }
        })


    }

    fun View.createAndShow(
        text: String, actionText: String, action: (View)
        -> Unit, length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }


}
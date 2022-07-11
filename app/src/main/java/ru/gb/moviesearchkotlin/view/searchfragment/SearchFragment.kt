package ru.gb.moviesearchkotlin.view.searchfragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.databinding.SearchFragmentBinding
import ru.gb.moviesearchkotlin.model.Movie
import ru.gb.moviesearchkotlin.viewmodel.AppState
import ru.gb.moviesearchkotlin.viewmodel.MovieAdapter
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
        val data : ArrayList<Movie> = initMovieList(this.resources)
        val recyclerViewOne: RecyclerView = binding.root.findViewById(R.id.menu_recycler_view)
        val recyclerViewTwo: RecyclerView = binding.root.findViewById(R.id.menu2_recycler_view)
        initRecycleView(recyclerViewOne, data)
        initRecycleView(recyclerViewTwo,data)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.liveData.observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })
        viewModel.sentRequest()
    }

    private fun initRecycleView(recycleView: RecyclerView, data: ArrayList<Movie>) {
        recycleView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleView.layoutManager = layoutManager

        val adapter = MovieAdapter(data, resources)
        recycleView.adapter = adapter
        adapter.setOnClickListener(object : MovieAdapter.onItemClickListener{
            override fun onClickListener(position: Int) {
                val movie = data[position]

                childFragmentManager.beginTransaction()
                    .add(R.id.search_fragment, MovieFragment.newInstance(movie))
                    .commitNow()
            }
        })


    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Ошибка: $appState", Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(), "Загружается...", Toast.LENGTH_LONG).show()
            }
            is AppState.Success -> {
                Toast.makeText(requireContext(), "Результат: $appState", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initMovieList(res: Resources) : ArrayList<Movie> {
        val arrayMovie: ArrayList<Movie> = arrayListOf<Movie>()
        val fixSize = res.getStringArray(R.array.names).size - 1
        for (item in 0..fixSize)
            arrayMovie.add(Movie(item, res.getStringArray(R.array.names)[item],
                res.getStringArray(R.array.names_original)[item]))

        return arrayMovie
    }

}
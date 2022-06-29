package ru.gb.moviesearchkotlin.view.searchfragment

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
import ru.gb.moviesearchkotlin.viewmodel.AppState
import ru.gb.moviesearchkotlin.viewmodel.SearchViewModel


class SearchFragment : Fragment() {


    companion object {
        fun newInstance() = SearchFragment()
    }

    lateinit var binding: SearchFragmentBinding
    lateinit var viewModel: SearchViewModel
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
        val data : Array<String> = resources.getStringArray(R.array.titles)
        val recyclerViewOne: RecyclerView = binding.root.findViewById(R.id.menu_recycler_view)
        val recyclerViewTwo: RecyclerView = binding.root.findViewById(R.id.menu2_recycler_view)
        initRecycleView(recyclerViewOne, data)
        initRecycleView(recyclerViewTwo,data)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })
        viewModel.sentRequest()
    }

    private fun initRecycleView(recycleView: RecyclerView, data: Array<String>) {
        recycleView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleView.layoutManager = layoutManager

        val adapter = MovieAdapter(data)
        recycleView.adapter = adapter


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

}
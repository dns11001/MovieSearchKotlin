package ru.gb.moviesearchkotlin.view.searchfragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.databinding.SearchFragmentBinding
import ru.gb.moviesearchkotlin.model.movie.MovieDTO
import ru.gb.moviesearchkotlin.model.movie.getLines
import ru.gb.moviesearchkotlin.model.movie.initMovieList
import ru.gb.moviesearchkotlin.servicesbroadcasts.BroadcastService
import ru.gb.moviesearchkotlin.viewmodel.MovieListAdapter
import ru.gb.moviesearchkotlin.viewmodel.SearchViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class SearchFragment : Fragment() {


    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var binding: SearchFragmentBinding
    private lateinit var viewModel: SearchViewModel

    private final val SERVICE = "Start service"

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: ArrayList<MovieDTO> = initMovieList(this.resources)

        val searchEditText: AppCompatEditText = binding.root.findViewById(R.id.edit_text_find)

        val recyclerViewPopular: RecyclerView = binding.root.findViewById(R.id.menu_recycler_view)
        val recyclerViewUpcoming: RecyclerView = binding.root.findViewById(R.id.menu2_recycler_view)
        initRecycleView(recyclerViewPopular, data)
        initRecycleView(recyclerViewUpcoming, data)

        binding.findButton.setOnClickListener {
            val editTextData = searchEditText.text.toString()
            val uri = URL("https://imdb-api.com/en/API/Search/k_4i110l81/$editTextData")
            var internetConnection: HttpsURLConnection? = null

            internetConnection = uri.openConnection() as HttpsURLConnection
            internetConnection.readTimeout = 5000
            Thread {
                val reader = BufferedReader(InputStreamReader(internetConnection.inputStream))
                val result = getLines(reader)

                val movie = Gson().fromJson(result, MovieDTO::class.java)

                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.search_fragment, MovieDetailsFragment.newInstance(movie))
                    .addToBackStack("")
                    .commit() // Выкидывает с SocketTimeoutException: Что сделать, чтобы она не появлялась?

            }.start()
        }

        binding.startService.setOnClickListener {
            context?.let {
                it.startService(Intent(it, BroadcastService::class.java).apply {
                    putExtra(
                        SERVICE,
                        "Start"
                    )
                })
            }
        }


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

        val adapter = MovieListAdapter(data)
        recycleView.adapter = adapter
        adapter.setOnClickListener(object : MovieListAdapter.onItemClickListener {
            override fun onClickListener(position: Int) {
                val movie = data[position]

                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.search_fragment, MovieDetailsFragment.newInstance(movie))
                    .addToBackStack("")
                    .commit()
            }
        })


    }


}
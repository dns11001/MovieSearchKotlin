package ru.gb.moviesearchkotlin.viewmodel

import ru.gb.moviesearchkotlin.databinding.FragmentMovieBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.model.Movie
import java.nio.file.Paths
import kotlin.io.path.Path

private const val POSTER = "poster"
private const val NAME = "name"
private const val NAMEORIGINAL = "name original"

class MovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var poster: Int? = null
    private var name: String? = null
    private var nameOriginal: String? = null
    private lateinit var binding: FragmentMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            nameOriginal = it.getString(NAMEORIGINAL)
            poster = it.getInt(POSTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTextView : TextView = binding.root.findViewById(R.id.fragment_movie_name)
        val nameOriginalTextView : TextView = binding.root.findViewById(R.id.fragment_movie_name_original)
        val posterImg : ImageView = binding.root.findViewById(R.id.fragment_image_movie)
        val posterId : String = resources.getStringArray(R.array.posters)[poster!!]


        nameTextView.text = name
        nameOriginalTextView.text = nameOriginal
        posterImg.setImageResource(resources.getIdentifier(posterId, "drawable",
        requireContext().packageName))


    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, movie.name)
                    putString(NAMEORIGINAL, movie.nameOriginal)
                    putInt(POSTER, movie.idPoster)
                }
            }
    }
}
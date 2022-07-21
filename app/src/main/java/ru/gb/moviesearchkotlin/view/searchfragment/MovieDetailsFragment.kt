package ru.gb.moviesearchkotlin.view.searchfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.databinding.FragmentMovieDetailsBinding
import ru.gb.moviesearchkotlin.model.movie.MovieDTO

private const val POSTER = "poster"
private const val NAME = "name"
private const val NAMEORIGINAL = "name original"
private const val DESCRIPTION = "description"

class MovieDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var poster: Int? = null
    private var name: String? = null
    private var nameOriginal: String? = null
    private var descriptionVar: String? = null
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            nameOriginal = it.getString(NAMEORIGINAL)
            poster = it.getInt(POSTER)
            descriptionVar = it.getString(DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTextView: TextView = binding.root.findViewById(R.id.fragment_movie_name)
        val nameOriginalTextView: TextView =
            binding.root.findViewById(R.id.fragment_movie_name_original)
        val posterImg: ImageView = binding.root.findViewById(R.id.fragment_image_movie)
        val posterId: String = resources.getStringArray(R.array.posters)[poster!!]
        val descriptionView: TextView =
            binding.root.findViewById(R.id.fragment_movie_name_description)


        nameTextView.text = name
        nameOriginalTextView.text = nameOriginal
        descriptionView.text = descriptionVar
        //posterImg.setImageResource(resources.getIdentifier(posterId, "drawable",
        //requireContext().packageName))
        posterImg.background =
            resources.getDrawable(R.drawable.zaglushka) // Я не понимаю, что ты хочешь


    }

    companion object {
        @JvmStatic
        fun newInstance(movie: MovieDTO) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, movie.results[0].title)
                    putString(NAMEORIGINAL, movie.results[0].title)
                    putString(DESCRIPTION, movie.results[0].description)
                }
            }
    }
}
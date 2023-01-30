package com.example.amazonprimeclone.fragments.home.details.movie_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.example.amazonprimeclone.R
import com.example.amazonprimeclone.adapters.Cast.MovieCastAdapter
import com.example.amazonprimeclone.adapters.Trailers.MovieTrailerAdapter
import com.example.amazonprimeclone.adapters.home.MovieAdapter
import com.example.amazonprimeclone.databinding.FragmentMovieDetailBinding
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.MovieResponseResults
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var binding: FragmentMovieDetailBinding? = null
    private val movieDetailViewModel by viewModels<MovieDetailsViewModel>()
    private var castAdapter: MovieCastAdapter? = null
    private var trailerAdapter: MovieTrailerAdapter? = null
    private var recommendAdapter: MovieAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return binding!!.getRoot()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.result.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding!!.favoriteBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_like_red))
            } else {
                binding!!.favoriteBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_like_svgrepo_com))
            }
        })
        movieDetailViewModel.movieDetails.observe(viewLifecycleOwner, Observer { movieDetailsModel: MovieDetailsModel? ->
                if (movieDetailsModel != null) {
                    val genres =
                        movieDetailsModel.genres
                    binding!!.movieDesc.setText(movieDetailsModel.overview)
                    Glide.with(binding!!.moviePoster).load(movieDetailsModel.backdrop_path)
                        .into(binding!!.moviePoster)
                    binding!!.movieTitleTv.setText(movieDetailsModel.title)
                    binding!!.timeTv.setText(movieDetailsModel.runtime.toString())
                    binding!!.ratingTv.setText(movieDetailsModel.vote_average.toString())
                    binding!!.categoryTv.setText(genres[0].name)
                }
            })
        movieDetailViewModel.movieTrailerResponse.observe(viewLifecycleOwner,
            Observer {
                trailerAdapter = MovieTrailerAdapter(context, it)
                binding!!.trailerRv.setAdapter(trailerAdapter)
            })

        movieDetailViewModel.movieCastResponse.observe(viewLifecycleOwner, Observer {
            castAdapter = MovieCastAdapter(context, it)
            binding!!.castRv.setAdapter(castAdapter)
        })
        movieDetailViewModel.recommendationMovieData.observe(viewLifecycleOwner) { movieResponseResults: List<MovieResponseResults?>? ->
            recommendAdapter = MovieAdapter(movieResponseResults) { id: String ->
                val actionNavMovieDetailSelf =
                    MovieDetailFragmentDirections.actionNavMovieDetailSelf(id)
                findNavController(binding!!.getRoot()).navigate((actionNavMovieDetailSelf as NavDirections))
            }
            binding!!.recommendationRv.setAdapter(recommendAdapter)
        }
        binding!!.favoriteBtn.setOnClickListener { v -> movieDetailViewModel.result.observe(
                viewLifecycleOwner, Observer {
                    if (it) {
                        movieDetailViewModel.deleteMovie()
                        binding!!.favoriteBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_like_svgrepo_com))

                    } else {
                        Log.d("response",binding!!.movieTitleTv.text.toString())
                        movieDetailViewModel.insertMovie(binding!!.movieTitleTv.text.toString().trim())
                        binding!!.favoriteBtn.setImageDrawable(resources.getDrawable(R.drawable.ic_like_red))
                    }
                })
        }

        binding!!.backBtn.setOnClickListener { v -> findNavController(v).navigateUp() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
import './App.css'
import {useEffect, useState} from "react";
import {Movie} from "./MovieEntities.ts";
import axios from 'axios';
import MovieGallery from "./components/MovieGallery.tsx";

export default function App() {

  const [movies, setMovies] = useState<Movie[]>([])

  useEffect(
      fetchMovieData, []
  )

  function fetchMovieData() {
    axios.get("/api/movies")
        .then(response => {setMovies(response.data)})
        .catch(reason => {console.log(reason)})
  }


  return (
    <>
      <h1>FrameFiesta</h1>
      <MovieGallery movies={movies}/>
    </>
  )
}


import './App.css'
import "./assets/Main.css"
import {useEffect, useState} from "react";
import {Movie} from "./assets/MovieEntities.ts";
import axios from 'axios';
import MovieGallery from "./pages/MovieGallery/MovieGallery.tsx";
import {Route, Routes} from "react-router-dom";
import MovieDetailPage from "./pages/MovieDetail/MovieDetailPage.tsx";
import Header from "./components/Header/Header.tsx";
import StartPage from "./pages/StartPage/StartPage.tsx";

export default function App() {

    const [movies, setMovies] = useState<Movie[]>([])
    const [favoriteState , setFavoriteState] = useState<Movie>()

    useEffect(
        fetchMovieData, [favoriteState]
    )

    function fetchMovieData() {
        axios.get("/api/movies")
            .then(response => {
                setMovies(response.data)
            })
            .catch(reason => {
                console.log(reason)
            })
    }

    function toggleFavorite(id: string, favoriteStatement: boolean) {
        axios.patch("/api/movies/"+id+"?favoriteStatement="+favoriteStatement)
            .then(response => {
                setFavoriteState(response.data)
            })
            .catch(reason => {
                console.log(reason)
            })
    }


    return (
        <>
            <Header/>
            <Routes>
                <Route path={"/"} element={<StartPage movies={movies}/>}/>
                <Route path={"/movies/:id"} element={<MovieDetailPage favoriteState={favoriteState} toggleFavorite={toggleFavorite}/>}/>
                <Route path={"/movies"} element={<MovieGallery movies={movies}  toggleFavorite={toggleFavorite}/>}/>
            </Routes>
        </>
    )
}


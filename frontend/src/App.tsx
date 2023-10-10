import './App.css'
import "./assets/Main.css"
import {useEffect, useState} from "react";
import {Movie} from "./MovieEntities.ts";
import axios from 'axios';
import MovieGallery from "./components/MovieGallery.tsx";
import {Route, Routes} from "react-router-dom";
import MovieDetailPage from "./pages/MovieDetailPage.tsx";
import Header from "./components/Header.tsx";

export default function App() {

    const [movies, setMovies] = useState<Movie[]>([])

    useEffect(
        fetchMovieData, []
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


    return (
        <>
            <Header/>
            <Routes>
                <Route path={"/movies/:id"} element={<MovieDetailPage/>}/>
                <Route path={"/movies"} element={<MovieGallery movies={movies}/>}/>
            </Routes>
        </>
    )
}


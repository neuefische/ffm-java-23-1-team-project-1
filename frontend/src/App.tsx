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
import FavoriteGallery from "./pages/MovieGallery/FavoriteGallery.tsx";
import {UserProfile} from "./assets/UserProfileEntities.ts";

export default function App() {

    const [movies, setMovies] = useState<Movie[]>([])
    const [favoriteState , setFavoriteState] = useState<Movie>()
    const [userProfile, setUserProfile] = useState<UserProfile>()

    useEffect(
        fetchMovieData, [favoriteState]
    )

    useEffect(
        me, []
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
    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin;
        window.open(host + '/oauth2/authorization/github', '_self');
    }

    function me() {
        axios.get("/api/user/me")
            .then(response => {
                setUserProfile(response.data);
            })
            .catch(error => {
                console.error('Fehler beim Abrufen des Benutzerprofils:', error);
            });
    }


    return (
        <>
            <Header userProfile={userProfile} login={login}/>
            {/*<button onClick={login}>Log in with GitHub</button>
            <p>eingeloggt als {userProfile?.name}</p>*/}
            <Routes>
                <Route path={"/"} element={<StartPage movies={movies}/>}/>
                <Route path={"/movies/:id"} element={<MovieDetailPage favoriteState={favoriteState} toggleFavorite={toggleFavorite}/>}/>
                <Route path={"/movies"} element={<MovieGallery movies={movies}  toggleFavorite={toggleFavorite}/>}/>
                <Route path={"/favorites"} element={<FavoriteGallery movies={movies} toggleFavorite={toggleFavorite}/>}/>
            </Routes>
        </>
    )
}

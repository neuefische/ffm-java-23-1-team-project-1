import './App.css'
import "./assets/Main.css"
import {useEffect, useState} from "react";
import {Movie} from "./assets/MovieEntities.ts";
import axios from 'axios';
import MovieGallery from "./pages/MovieGallery/MovieGallery.tsx";
import {Route, Routes, useNavigate} from "react-router-dom";
import MovieDetailPage from "./pages/MovieDetail/MovieDetailPage.tsx";
import Header from "./components/Header/Header.tsx";
import StartPage from "./pages/StartPage/StartPage.tsx";
import FavoriteGallery from "./pages/MovieGallery/FavoriteGallery.tsx";
import {UserProfile} from "./assets/UserProfileEntities.ts";
import ProtectedRoutes from "./assets/ProtectedRoutes.tsx";

export default function App() {

    const [movies, setMovies] = useState<Movie[]>([])
    const [favoriteState, setFavoriteState] = useState<Movie>()
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
                console.error(reason)
            })
    }

    function toggleFavorite(id: string, favoriteStatement: boolean) {
        axios.patch("/api/movies/" + id + "?favoriteStatement=" + favoriteStatement)
            .then(response => {
                setFavoriteState(response.data)
            })
            .catch(reason => {
                console.error(reason)
            })
    }

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin;
        window.open(host + '/oauth2/authorization/github', '_self');
    }

    function logout() {
        axios.post("/api/logout")
            .then(() => {
                setUserProfile(undefined)
            })
            .catch(reason => {
                console.error(reason)
            })
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

    const navigate = useNavigate()

    function deleteMovieById(id: string) {
        axios.delete(`/api/movies/${id}`)
            .then(() => {
                navigate("/movies")
            })
            .then(() => {
                fetchMovieData()
            })
    }


    return (
        <>
            <Header userProfile={userProfile} login={login} logout={logout}/>
            <Routes>
                <Route path={"/"} element={<StartPage movies={movies} userProfile={userProfile}/>}/>

                <Route path={"/movies/:id"}
                       element={<MovieDetailPage updateFunction={deleteMovieById} onMovieUpdate={fetchMovieData}
                                                 favoriteState={favoriteState} toggleFavorite={toggleFavorite}/>}/>
                <Route path={"/movies"} element={<MovieGallery movies={movies} toggleFavorite={toggleFavorite}/>}/>
                <Route element={<ProtectedRoutes userProfile={userProfile}/>}>
                    <Route path={"/favorites"}
                           element={<FavoriteGallery movies={movies} toggleFavorite={toggleFavorite}/>}/>
                </Route>
            </Routes>
        </>
    )
}

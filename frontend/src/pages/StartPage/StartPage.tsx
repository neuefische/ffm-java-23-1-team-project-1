import {Movie} from "../../assets/MovieEntities.ts";
import FavoriteMoviesComponent from "../../components/FavoriteMovies/FavoriteMoviesComponent.tsx";
import "./StartPage.css"
import RandomMovie from "../../components/RandomMovie/RandomMovie.tsx";
import {UserProfile} from "../../assets/UserProfileEntities.ts";

type StartPageProps = {
    movies: Movie[]
    userProfile: UserProfile | undefined
}
export default function StartPage(props: StartPageProps) {

    return (
        <div className="startPage">
            <section className="randomMovie">
            <RandomMovie movies={props.movies} />
            </section>

            {props.userProfile?.name
                &&
                <section className="favoriteComponent">
                <FavoriteMoviesComponent movies={props.movies}/>
            </section>}
        </div>
    );
}


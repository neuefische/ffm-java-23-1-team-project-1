import {Movie} from "../../MovieEntities.ts";
import FavoriteMoviesComponent from "../../components/FavoriteMovies/FavoriteMoviesComponent.tsx";
import "./StartPage.css"

type StartPageProps = {
    movies: Movie[]
}
export default function StartPage(props: StartPageProps) {
    return (
        <div className="startPage">
            <section>andere liste/Darstellung</section>
            <section className="favoriteComponent">
                <FavoriteMoviesComponent movies={props.movies}/>
            </section>
        </div>
    );
}


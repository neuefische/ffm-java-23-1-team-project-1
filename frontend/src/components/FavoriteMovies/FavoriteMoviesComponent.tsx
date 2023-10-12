import {Movie} from "../../assets/MovieEntities.ts";
import "./FavoriteMoviesComponent.css"
import {Link} from "react-router-dom";

type FavoriteMoviesProps = {
    movies: Movie[]
}
export default function FavoriteMoviesComponent(props: FavoriteMoviesProps) {
    return (
        <article>
            <h3>Favorite Movies</h3>
            <ul>
                {props.movies
                    .filter(movie => movie.isFavorite)
                    .slice(0, 10)
                    .map(movie => (
                        <Link to={`/movies/${movie._id}`} key={movie._id}>
                            <li>

                                <img src={movie.thumbnail} alt={movie.title}/>
                                <p>{movie.title}, <span>{movie.year}</span></p>


                            </li>
                        </Link>
                    ))}
            </ul>

        </article>
    );
}


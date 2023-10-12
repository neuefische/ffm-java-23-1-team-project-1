import {Movie} from "../../assets/MovieEntities.ts";
import "./RandomMovie.css"
import {Link} from "react-router-dom";

type RandomMovieProps = {
    movies: Movie[]
}
export default function RandomMovie(props: RandomMovieProps) {
    const randomIndex = Math.floor(Math.random() * props.movies.length);
    const randomMovie = props.movies[randomIndex];
    return (
        <>
            <h3>Der beste Film für heute</h3>
            {randomMovie &&
                <Link to={"/movies/"+randomMovie._id}>
                <article>
                    <img src={randomMovie.thumbnail} alt={randomMovie.title}/>
                    <h4>{randomMovie.title}</h4>
                    <p>{randomMovie.year}</p>
                    <p>{randomMovie.extract}</p>
                </article>
                </Link>
            }
        </>
    );
}


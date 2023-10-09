import {Movie} from "../MovieEntities.ts";
import {Link} from "react-router-dom";

type MovieProps = {
    movie: Movie
}

export default function MovieCard(props: MovieProps) {
    return (
        <Link to={`/movies/${props.movie._id}`}>
            <div>
                <p>{props.movie.title}</p>
                <p>{props.movie.director}</p>
            </div>
        </Link>
    );
}
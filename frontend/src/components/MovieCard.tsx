import {Movie} from "../MovieEntities.ts";

type MovieProps = {
    movie: Movie
}

export default function MovieCard(props: MovieProps) {
    return (
        <div>
            <p>{props.movie.title}</p>
            <p>{props.movie.director}</p>
        </div>
    );
}
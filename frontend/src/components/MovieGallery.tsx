import {Movie} from "../MovieEntities.ts";
import MovieCard from "./MovieCard.tsx";

type MovieGalleryProps = {
    movies : Movie[]
}

export default function MovieGallery(props: MovieGalleryProps) {
    return (
        <div>
            {
                props.movies.map(
                    (movie) => <MovieCard key={movie._id} movie={movie}/>
                )
            }
        </div>
    );
}
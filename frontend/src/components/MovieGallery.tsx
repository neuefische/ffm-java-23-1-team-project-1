import {Movie} from "../MovieEntities.ts";
import MovieCard from "./MovieCard.tsx";

type MovieGalleryProps = {
    movies: Movie[],

    toggleFavorite: (id: string, favoriteStatement: boolean) => void
}

export default function MovieGallery(props: MovieGalleryProps) {


    return (
        <div className="movieGallery">
            {
                props.movies.slice(0, 20).map(
                    (mov) => <MovieCard key={mov._id} movie={mov}  toggleFavorite={props.toggleFavorite}/>
                )
            }
        </div>
    );
}
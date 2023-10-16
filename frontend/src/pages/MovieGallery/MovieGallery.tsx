import {Movie} from "../../assets/MovieEntities.ts";
import MovieCard from "../../components/MovieCard/MovieCard.tsx";
import "./MovieGallery.css"

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
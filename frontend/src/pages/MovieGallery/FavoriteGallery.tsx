import {Movie} from "../../assets/MovieEntities.ts";
import MovieCard from "../../components/MovieCard/MovieCard.tsx";

type FavoriteGalleryProps = {
    movies: Movie[]
    toggleFavorite: (id: string, favoriteStatement: boolean) => void
}
export default function FavoriteGallery(props: FavoriteGalleryProps) {
    return (
        <div className="movieGallery">
            {
                props.movies.filter(movie => movie.isFavorite).map(
                    (mov) => <MovieCard key={mov._id} movie={mov}  toggleFavorite={props.toggleFavorite}/>
                )
            }
        </div>
    );
}


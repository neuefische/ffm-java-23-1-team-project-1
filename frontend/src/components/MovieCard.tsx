import {Movie} from "../MovieEntities.ts";
/*import {Link} from "react-router-dom";*/
import "./MovieCard.css"

type MovieProps = {
    movie: Movie

    toggleFavorite: (id: string, favoriteStatement: boolean) => void
}

export default function MovieCard(props: MovieProps) {
    let classNameIfIsFavorite = props.movie.isFavorite
        ? "isFavoriteIsTrue"
        : "isFavoriteIsFalse"


    return (<div className="movieCard">
            {/*<Link to={`/movies/${props.movie._id}`}>*/}
            <div className="movieCard-container">
                <div>
                    {
                        props.movie.thumbnail
                            ? <img src={props.movie.thumbnail} alt="N/A"/>
                            : <img
                                src="https://images.unsplash.com/photo-1523207911345-32501502db22?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8ZHVtbXklMjBtb3ZpZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"
                                alt="N/A"/>
                    }

                </div>
                <div>
                    <p>{props.movie.title}</p>
                    <p>{props.movie.year}</p>
                </div>
                <svg onClick={() => props.toggleFavorite(props.movie._id, !props.movie.isFavorite)} width="25"
                     height="22" xmlns="http://www.w3.org/2000/svg">
                    <path className={classNameIfIsFavorite}
                        d="M12.5 21.4073L2.815 11.7233C1.54 10.4482 0.8379 8.755 0.8379 6.95344C0.832804 5.15186 1.43072 3.45868 2.7006 2.1888C3.97565 0.913756 5.66783 0.210707 7.46941 0.210707C9.271 0.210707 10.9632 0.913756 12.2382 2.1888L12.5 2.45056L12.7618 2.1888C14.0368 0.913756 15.729 0.210707 17.5306 0.210707C19.3322 0.210707 21.0244 0.913756 22.2994 2.1888C23.5693 3.45868 24.1672 5.15186 24.1672 6.95344C24.1672 8.755 23.4651 10.4482 22.1901 11.7233L12.5 21.4073Z"
                    />
                </svg>


            </div>
            {/* </Link>*/}
        </div>
    );
}
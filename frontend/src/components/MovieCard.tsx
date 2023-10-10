import {Movie} from "../MovieEntities.ts";
import {Link} from "react-router-dom";
import "./MovieCard.css"

type MovieProps = {
    movie: Movie
}

export default function MovieCard(props: MovieProps) {
    return (<div className="movieCard">
            <Link to={`/movies/${props.movie._id}`}>
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
                    <svg width="25" height="22" viewBox="0 0 25 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M22.383 1.98742C21.1079 0.712373 19.4199 0.0154864 17.6184 0.0154864C15.8168 0.0154864 14.1236 0.717535 12.8486 1.99258L12.1826 2.65849L11.5064 1.98225C10.2314 0.707211 8.53302 0 6.73144 0C4.93502 0 3.24184 0.702048 1.97196 1.97193C0.696915 3.24697 -0.00513386 4.94015 2.82663e-05 6.74173C2.82663e-05 8.54331 0.707239 10.2313 1.98228 11.5064L11.6767 21.2008C11.811 21.335 11.9916 21.4073 12.1672 21.4073C12.3427 21.4073 12.5233 21.3402 12.6576 21.206L22.3727 11.527C23.6477 10.252 24.3498 8.5588 24.3498 6.75722C24.3549 4.95564 23.658 3.26246 22.383 1.98742ZM21.3919 10.5411L12.1672 19.7296L2.96309 10.5256C1.95131 9.51379 1.3938 8.17164 1.3938 6.74173C1.3938 5.31182 1.94615 3.96967 2.95792 2.96306C3.96454 1.95644 5.30669 1.39894 6.73144 1.39894C8.16134 1.39894 9.50866 1.95644 10.5204 2.96822L11.6871 4.13486C11.9607 4.40845 12.3994 4.40845 12.673 4.13486L13.8294 2.97854C14.8411 1.96677 16.1884 1.40926 17.6132 1.40926C19.0379 1.40926 20.3801 1.96677 21.3919 2.97338C22.4036 3.98516 22.956 5.32731 22.956 6.75722C22.9611 8.18713 22.4036 9.52928 21.3919 10.5411Z"
                            fill="black"/>
                    </svg>


                </div>
            </Link>
        </div>
    );
}
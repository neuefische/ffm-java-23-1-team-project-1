import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Movie} from "../MovieEntities.ts";

import "./MovieDetailPage.css"

export default function MovieDetailPage() {
    const [movie, setMovie] = useState<Movie>()

    const {id} = useParams()


    useEffect(() => {
        axios.get(`/api/movies/${id}`)
            .then(response => {
                setMovie(response.data)
            })
            .catch(error => console.log(error))
    }, []);


    return (<>
        {
            // movie &&
            <article className="movieDetailContainer">
                <h2>{movie?.title}</h2>
                <p>{movie?.year}</p>
                <img className="poster" src={movie?.thumbnail} alt={movie?.title}/>
                <p>{movie?.extract}</p>
                <a target={"_blank"} href={`https://www.youtube.com/results?search_query=${movie?.title}+trailer+${movie?.year}`}><img src={"https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/YouTube_full-color_icon_%282017%29.svg/120px-YouTube_full-color_icon_%282017%29.svg.png"} alt={"logo"}/></a>
                <svg width="25" height="22" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M12.5 21.4073L2.815 11.7233C1.54 10.4482 0.8379 8.755 0.8379 6.95344C0.832804 5.15186 1.43072 3.45868 2.7006 2.1888C3.97565 0.913756 5.66783 0.210707 7.46941 0.210707C9.271 0.210707 10.9632 0.913756 12.2382 2.1888L12.5 2.45056L12.7618 2.1888C14.0368 0.913756 15.729 0.210707 17.5306 0.210707C19.3322 0.210707 21.0244 0.913756 22.2994 2.1888C23.5693 3.45868 24.1672 5.15186 24.1672 6.95344C24.1672 8.755 23.4651 10.4482 22.1901 11.7233L12.5 21.4073Z"
                    />
                </svg>
            </article>
        }
    </>)

}


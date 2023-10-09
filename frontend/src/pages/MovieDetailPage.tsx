import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Movie} from "../MovieEntities.ts";

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
            <div>
                <h2>{movie?.title}</h2>
            </div>
        }
    </>)

}


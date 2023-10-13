import {useParams} from "react-router-dom";
import {ChangeEvent, useEffect, useState} from "react";
import axios from "axios";
import {Movie} from "../../assets/MovieEntities.ts";

import "./MovieDetailPage.css"

type MovieDetailProps = {
    toggleFavorite: (id: string, favoriteStatement: boolean) => void
    favoriteState: Movie | undefined
}
export default function MovieDetailPage(props: MovieDetailProps) {

    const [movie, setMovie] = useState<Movie | undefined>(props.favoriteState)
    const [isBeingEdited, setIsBeingEdited] = useState(true)
    const [title, setTitle] = useState<String>(movie?.title as String)
    const [year, setYear] = useState<number>()
    const [extract, setExtract] = useState<string>()
    const {id} = useParams()

    useEffect(() => {
        axios.get(`/api/movies/${id}`)
            .then(response => {
                setMovie(response.data)
            })
            .catch(error => console.log(error))
    }, [props.favoriteState]);

    function getMovies() {
        axios.get(`/api/movies/${id}`)
            .then(response => {
                setMovie(response.data)
            })
            .catch(error => console.log(error))
    }

    function submitEditedMovie(movie : Movie) {
        axios.put("/api/movies/"+id, {
            ...movie,
            title: title,
            year: year,
            extract: extract
        })
            .then((response) => {setMovie(response.data)})
        setIsBeingEdited(false)

    }

    function changeTitle(event: ChangeEvent<HTMLInputElement>) {
        const newTitle : string = event.target.value;
            setTitle(newTitle);
    }
    function changeYear(event: ChangeEvent<HTMLInputElement>) {
        const newYear :number = Number(event.target.value);
        setYear(newYear);
    }

  const changeExtract = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const newExtract : string = event.target.value.toString();
      setExtract(newExtract);
    };

    return (<>
        {movie &&
            <div className="container">
                <article className="movieDetailContainer">
                    <div className="text-container">
                        {(!isBeingEdited)
                            ? <>
                                <h2>{movie.title}</h2>
                                <p>{movie.year}</p>
                                <p>{movie.extract}</p>
                            </>
                            : <form>
                                <label>
                                    <input type="text" value={movie.title} onChange={changeTitle}/>
                                </label>
                                <label>
                                    <input type="number" value={movie.year} onChange={changeYear}/>
                                </label>
                                <label>
                                    <textarea value={movie.extract} onChange={changeExtract}/>
                                </label>
                            </form>
                        }

                        <div className="logo-container">
                            <a target={"_blank"}
                               href={`https://www.youtube.com/results?search_query=${movie.title}+trailer+${movie.year}`}><img
                                src={"https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/YouTube_full-color_icon_%282017%29.svg/120px-YouTube_full-color_icon_%282017%29.svg.png"}
                                alt={"logo"}/></a>
                            <div>
                                {isBeingEdited
                                    ? <p onClick={() => {
                                        submitEditedMovie
                                    }}>Save</p>
                                    : <p onClick={() => {
                                        setIsBeingEdited(true)
                                    }}>Edit</p>}
                                <p>
                                    <svg width="37" height="50" viewBox="0 0 37 50" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path
                                            d="M35.5769 5.58935H1.42313C0.637218 5.58935 0 6.22657 0 7.01248V12.7688C0 13.5549 0.637218 14.192 1.42313 14.192H2.39415L6.34828 48.7236C6.43059 49.4422 7.03878 49.9846 7.76218 49.9846H29.2374C29.9608 49.9846 30.5688 49.442 30.6512 48.7236L34.6055 14.1919H35.5765C36.3624 14.1919 36.9997 13.5548 36.9997 12.7687V7.01248C37 6.22657 36.3628 5.58935 35.5769 5.58935ZM2.84625 8.4356H34.1536V11.3457H33.3362H3.66381H2.84636L2.84625 8.4356ZM27.9681 47.1386H9.03172L5.25907 14.1921H31.7408L27.9681 47.1386ZM37 3.14511C37 3.93101 36.3627 4.56823 35.5769 4.56823C35.1019 4.56823 34.6812 4.33552 34.4227 3.97781H34.3294H33.6659H27.4664H26.9464H2.57745C2.3189 4.33552 1.89822 4.56823 1.42324 4.56823C0.637332 4.56823 0.000113787 3.93101 0.000113787 3.14511V2.55468C0.000113787 1.76877 0.637332 1.13156 1.42324 1.13156H26.0731C26.2077 0.485456 26.7805 0 27.4663 0H33.6658C34.3517 0 34.9245 0.485456 35.059 1.13156H35.5768C36.3627 1.13156 36.9999 1.76877 36.9999 2.55468V3.14511H37Z"
                                            fill="black"/>
                                    </svg>
                                </p>
                            </div>

                        </div>
                    </div>
                    <div className="poster-container">
                        <img className="poster" src={movie.thumbnail} alt={movie.title}/>
                        <svg className={"herzSvg"} onClick={() => props.toggleFavorite(movie?._id, !movie.isFavorite)}
                             width="25"
                             height="35"
                             xmlns="http://www.w3.org/2000/svg">
                            <path className={movie.isFavorite
                                ? "isFavoriteIsTrue"
                                : "isFavoriteIsFalse"}
                                  d="M12.5 21.4073L2.815 11.7233C1.54 10.4482 0.8379 8.755 0.8379 6.95344C0.832804 5.15186 1.43072 3.45868 2.7006 2.1888C3.97565 0.913756 5.66783 0.210707 7.46941 0.210707C9.271 0.210707 10.9632 0.913756 12.2382 2.1888L12.5 2.45056L12.7618 2.1888C14.0368 0.913756 15.729 0.210707 17.5306 0.210707C19.3322 0.210707 21.0244 0.913756 22.2994 2.1888C23.5693 3.45868 24.1672 5.15186 24.1672 6.95344C24.1672 8.755 23.4651 10.4482 22.1901 11.7233L12.5 21.4073Z"
                            />
                        </svg>
                    </div>

                </article>
            </div>
        }
    </>)

}

